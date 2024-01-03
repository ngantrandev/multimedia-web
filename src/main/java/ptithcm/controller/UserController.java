package ptithcm.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
	public String UserLoginPost(ModelMap model, @ModelAttribute("user")Student user,HttpSession session,BindingResult errors) {
		model.addAttribute("action","login");
		try {
			if(user.getPassword().trim().length()!=0 && user.getStudentCode().trim().length()!=0) {
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
				model.addAttribute("message", "Đăng nhập thành công");
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
}
