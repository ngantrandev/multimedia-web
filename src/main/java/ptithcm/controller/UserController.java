package ptithcm.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.Student;

@Controller
@Transactional
@RequestMapping("/user/")
public class UserController {
	public String salt  = "SALT_PW_MULTI_WEB_SHA256";
	public String sha256(String original) throws NoSuchAlgorithmException {
		original = original+salt;
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(original.getBytes());
	    byte[] digest = md.digest();
	    return new String(DatatypeConverter.printHexBinary(digest));
	}
	@Autowired
	public SessionFactory sessionFactory;
	@RequestMapping(value ="login",method = RequestMethod.GET)
	public String UserLoginGet(ModelMap model,@ModelAttribute("user")Student user,HttpSession session) {
		model.addAttribute("action","login");
		user = new Student();
		return "user/login";
	}
	@RequestMapping(value ="login",method = RequestMethod.POST)
	public String UserLoginPost(ModelMap model, @ModelAttribute("user") Student user,HttpSession session,BindingResult errors) {
		model.addAttribute("action","login");
		try {
			if(user.getPassword().trim().length()!=0 && user.getStudentCode().toUpperCase().trim().length()!=0) {
				Query query = sessionFactory.getCurrentSession().createQuery("FROM Student WHERE mssv = :mssv AND password = :password");
				query.setParameter("mssv", user.getStudentCode());
				String pass = sha256(user.getPassword());
				query.setParameter("password",pass);
				Student student = (Student) query.uniqueResult();
				if(student.getStudentCode()!=null) {
					session.setAttribute("student", student);
					model.addAttribute("message","Đăng nhập thành công");
				}
			}
			if(user.getStudentCode().trim().length()<1) {
				errors.rejectValue("studentCode", "user", "Vui Lòng nhập mã sinh viên");
			}
			if(user.getPassword().trim().length()<1) {
				errors.rejectValue("password", "user", "Vui Lòng nhập mật khẩu");
			}
			if (errors.hasErrors()) {
				model.addAttribute("message","Đăng nhập thất bại");
			} 
			else {
				user = new Student();
				model.addAttribute("user",user);
				model.addAttribute("message", "Đăng nhập thành công");
				return "home/index";
			}
				
		}catch(Exception ex) {	
			model.addAttribute("message","Đăng nhập thất bại");
		}
		return "user/login";
	}
	
	@RequestMapping(value="register",method= RequestMethod.GET)
	public String UserRegisterGet(ModelMap model,@ModelAttribute("user")Student user) {
		user = new Student();
		model.addAttribute("action","register");
		return "user/login";
	}
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String UserRegisterPost(ModelMap model,@ModelAttribute("user")Student user,BindingResult errors) {
		model.addAttribute("action","register");
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if(user.getFullName().trim().length()<1) {
				errors.rejectValue("fullName","user","vui lòng nhập họ và tên sinh viên");
			}
			if(user.getStudentCode().trim().length()<1) {
				errors.rejectValue("studentCode","user","vui lòng nhập mã số sinh viên ");
			}
			System.out.println(user.getGender()+" gioi tinh");
			if(user.getGender()!=0 && user.getGender()!=1) {
				errors.rejectValue("gender","user","vui lòng chọn giới tính ");
			}
			if(user.getPassword().trim().length()<1) {
				errors.rejectValue("password","user","vui lòng nhập mật khẩu ");
			}
			if(user.getBirthday().trim().length()<1) {
				errors.rejectValue("birthday","user","vui lòng nhập ngày tháng năm sinh");
			}
			else if(user.getBirthday().trim().length()>1) {
			SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthday());  
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
			String strDate = dateFormat.format(date1); 
			user.setBirthday(strDate);
			System.out.println(user.getBirthday()+" ngay sinh");
			}
			if(user.getClassCode().trim().length()<1) {
				errors.rejectValue("classCode","user","vui lòng nhập lớp");
			}
			if(user.getPhone().trim().length()>=10&&user.getPhone().trim().length()<=11) {
				int phone=0;
				try {
				phone=Integer.parseInt(user.getPhone().trim());
				}catch(Exception ex) {}
				if(phone<1) {
					errors.rejectValue("phone","user","vui lòng nhập số điện thoại ");
				}
			}else {
				errors.rejectValue("phone","user","vui lòng nhập số điện thoại ");
			}
			Query query = sessionFactory.getCurrentSession().createQuery("FROM Student WHERE mssv = :mssv or sdt = :phone");
			query.setParameter("mssv", user.getStudentCode());
			query.setParameter("phone",user.getPhone());
			List<Student> students= query.list();
			if(students.size()>0) {
				if(students.get(0).getStudentCode().equals(user.getStudentCode())) {
					errors.rejectValue("studentCode","user","mã số sinh viên đã tồn tại");
				}
				if(students.get(0).getPhone().equals(user.getPhone())) {
					errors.rejectValue("phone","user","số điện thoại này đã được sử dụng");
				}
			}
			if(!errors.hasErrors()) {
				user.setPassword(sha256(user.getPassword()));
				session.save(user);
				t.commit();
				model.addAttribute("message","Thêm mới thành công");
				user = new Student();
				model.addAttribute("user",user);
				model.addAttribute("action","login");
				return "user/login";
			}

		}catch(Exception ex){
			System.out.println(ex);
			t.rollback();
			model.addAttribute("message","Thêm mới thất bại");
		}finally {
			session.close();
		}
		return "user/login";
	}
	
}
