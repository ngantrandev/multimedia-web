package ptithcm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ptithcm.entity.Student;

@Controller
public class ForgetPasswordController {
	@Autowired
	public SessionFactory sessionFactory;
		
	@Autowired
	JavaMailSender mailer;
	
	private String generateRandomOtp() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
	}
	
	
	@Transactional
	@RequestMapping ("/password/authOtp")
	public void authOtp(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession sessionClient) {
		try {
			String otpGot = request.getParameter("otp");
			Session session = sessionFactory.openSession();
		    Transaction transaction = session.beginTransaction();
		    Student studentClient = (Student) sessionClient.getAttribute("student");
		    Student studentDb = (Student) session.get(Student.class,studentClient.getStudentCode());
		    if(otpGot.equals(studentDb.getOtp())) {
		    	studentDb.setOtp("");
		    	session.update(studentDb);
			    transaction.commit();
			    session.close();
			    response.sendRedirect(request.getContextPath() + "/password/show_form_reset_pass.htm");
		    } else {
		    	model.addAttribute("message","Sai otp");
		    	response.sendRedirect(request.getContextPath() + "/password/show_form_otp.htm");
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping ("/password/show_form_reset_pass")
	public String showFormResetPass(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession sessionClient) {
		return "password/show_form_reset_pass";
	}
	public String salt  = "SALT_PW_MULTI_WEB_SHA256";
	public String sha256(String original) throws NoSuchAlgorithmException {
		original = original+salt;
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(original.getBytes());
	    byte[] digest = md.digest();
	    return new String(DatatypeConverter.printHexBinary(digest));
	}
	@RequestMapping ("/password/resetPass")
	public String resetPass(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession sessionClient) {
		try {
			String newPass = request.getParameter("newPw");
			String confirmNewPass = request.getParameter("confirmNewPw");
			if(newPass.equals(confirmNewPass)) {
				Session session = sessionFactory.openSession();
			    Transaction transaction = session.beginTransaction();
			    Student studentClient = (Student) sessionClient.getAttribute("student");
			    Student studentDb = (Student) session.get(Student.class,studentClient.getStudentCode());
			    studentDb.setPassword(sha256(newPass));
			    session.update(studentDb);
			    transaction.commit();
			    session.close();
			    System.out.println("Passed");
			    response.sendRedirect(request.getContextPath() + "/user/login.htm");
			    return "user/login";
			} else {
				model.addAttribute("message","Xác nhận mật khẩu mới không chính xác");
				System.out.println("Not Passed");
				return "password/show_form_reset_pass";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Not Passed Catched!!");
			model.addAttribute("message","Có lỗi xảy ra");
			System.out.println("Not Passed");
			return "password/show_form_reset_pass";
		}
	}
	
	@Transactional
	@RequestMapping ("/password/sendOtp")
	public void sendOtpPass(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession sessionClient) {
		String mssv = request.getParameter("mssv").toLowerCase();
		String email = mssv + "@student.ptithcm.edu.vn";
		String otp = generateRandomOtp();
		Session session = sessionFactory.openSession();
	    Transaction transaction = session.beginTransaction();
	    Student student = (Student) session.get(Student.class, "N20DCPT009");
	    sessionClient.setAttribute("student", student);
	    student.setOtp(otp);
	    session.update(student);
	    transaction.commit();
	    session.close();
	    MimeMessage mail = mailer.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mail);
	    try {
			helper.setFrom("n20dcpt009@student.ptithcm.edu.vn","Hệ thống Multimedia Web");
			helper.setTo(email);
			helper.setReplyTo("n20dcpt009@student.ptithcm.edu.vn","Reply");
			helper.setSubject("Quên mật khẩu web multimedia");
			helper.setText("OTP của bạn là: "+otp,true);
			mailer.send(mail);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	    try {
			response.sendRedirect(request.getContextPath() + "/password/show_form_otp.htm");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping ("/password/show_form_otp")
	public String showFormOTP(ModelMap model) {
		return "password/show_form_otp";
	}
	
	@RequestMapping ("/password/show_form_forgot")
	public String showFormForgotPass(ModelMap model) {
		return "password/show_form_forgot";
	}
}