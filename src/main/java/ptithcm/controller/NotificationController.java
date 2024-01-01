package ptithcm.controller;

import java.io.File;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.config.EnvConfig;
import ptithcm.entity.Notification;
import ptithcm.entity.Student;

@Controller
public class NotificationController {
	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	ServletContext context;
	
//	@Autowired
//	EnvConfig envConfig;
	
	
	@Transactional
	@RequestMapping ("/notification/show")
	public String showPage(ModelMap model) {
		Student student = (Student) sessionFactory.getCurrentSession().createQuery("FROM Student WHERE mssv = :mssv").setParameter("mssv", "N20DCPT009").uniqueResult();
		ArrayList<Notification> notifications = new ArrayList<>(student.getNotifications());
		model.addAttribute("notifications",notifications);
		return "notification/notification_page";
	}
	
//	@RequestMapping ("/notification/create")
//	public String createnNotification(ModelMap model,@RequestParam("img") MultipartFile file) {
//		try {
//			file.transferTo(new File(
//							envConfig.getPathUploadFile()+"/"+file.getOriginalFilename()));
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return "student/form";
//	}
	
	@RequestMapping ("/notification/download_file/${fileName}")
	public File downloadFile(HttpServletRequest request,@PathVariable("fileName") String name) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(name).getFile());
		return file;

	}
}
