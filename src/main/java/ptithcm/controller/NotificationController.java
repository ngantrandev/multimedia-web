package ptithcm.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.config.EnvConfig;
import ptithcm.entity.Student;
import ptithcm.entity.notification.Notification;
import ptithcm.entity.notification.NotificationForm;
import ptithcm.entity.notification.NotificationFormUpdate;

@Controller
public class NotificationController {
	@Autowired
	public SessionFactory sessionFactory;
		
	@Autowired
	EnvConfig envConfig;
	
	@Transactional
	@RequestMapping ("/notification/show")
	public String showPage(ModelMap model,HttpSession session) {
		Student studentSession = (Student) session.getAttribute("student");
		Student student = (Student) sessionFactory.getCurrentSession().createQuery("FROM Student WHERE mssv = :mssv").setParameter("mssv", studentSession.getStudentCode()).uniqueResult();
		ArrayList<Notification> notifications = new ArrayList<>(student.getNotifications());
		Collections.reverse(notifications);
		for(int i=0;i<notifications.size();++i) {
			notifications.get(i).setFileArr(new ArrayList<String>());
			if(notifications.get(i).getFiles() != null) {
				if(notifications.get(i).getFiles().trim().equals("")) continue;
				String[] arr = notifications.get(i).getFiles().split(" ");
				for(int j=0;j<arr.length;++j) {
					notifications.get(i).getFileArr().add(arr[j]);
				}
				
			}
		}
		model.addAttribute("notifications",notifications);
		model.addAttribute("isBcs",(studentSession.getBcs()==1));
		return "notification/notification_page";
	}
	
	@Transactional
	@RequestMapping ("/notification/create")
	public String createNotification(ModelMap model,
			HttpServletRequest request
			,@ModelAttribute("notification") NotificationForm notification,
			HttpServletResponse response,
			HttpSession sessionClient,
			BindingResult errors
		) {
		try {
			if(notification.getTitle() == null || notification.getTitle().length()==0) {
				errors.rejectValue("title", "notification","Vui lòng nhập tiêu đề");
				return "notification/show_form_create";
			}
			Date date = new Date();
			long miliLong = Instant.now().toEpochMilli();
		    String notiCode = String.valueOf(miliLong);
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    String time = sdf.format(new Date(miliLong));
			String fileName = "";
			MultipartFile[] files = notification.getFiles();
			for(int i=0;i<files.length;++i) {
				System.out.println("size files: "+files[i].getSize());
				if(files[i].getSize()==0) {
					continue;
				}
				String[] arrEl = files[i].getOriginalFilename().split("\\.");
				String ext = arrEl[arrEl.length-1];
				String normalizedName = notiCode+"-"+i+"."+ext;
				files[i].transferTo(new File(
						envConfig.getPathUploadFile()+"/"+normalizedName));
				fileName += normalizedName + " ";
			}
			fileName = fileName.trim();
			Session session = sessionFactory.openSession();
		    Transaction transaction = session.beginTransaction();
		    Student studentSession = (Student) sessionClient.getAttribute("student");
		    Student poster = (Student) session.get(Student.class, studentSession.getStudentCode());
		    List<Student> studentsSent = session.createQuery("FROM Student WHERE malop = :classCode").setParameter("classCode", poster.getClassCode()).list();
		    session.save(new Notification(notiCode,notification.getContent(),notification.getTitle(),poster,time,"docs",fileName,studentsSent));
		    transaction.commit();
		    session.close();
		    return "notification/show";
		} catch (Exception e) {
			System.out.println(e);
			return "notification/show_form_create";
		}
	}
	
	@Transactional
	@RequestMapping ("/notification/update")
	public String updateNotify(ModelMap model,
			HttpServletRequest request
			,@ModelAttribute("notification") NotificationFormUpdate notification,
			HttpServletResponse response,
			BindingResult errors
		) {
		try {
			if(notification.getTitle() ==null || notification.getTitle().length()==0) {
				errors.rejectValue("title", "notification","Vui lòng nhập tiêu đề");
				return "notification/show_form_create";
			}
			String fileName = "";
			MultipartFile[] files = notification.getFiles();
			for(int i=0;i<files.length;++i) {
				System.out.println("size files: "+files[i].getSize());
				if(files[i].getSize()==0) {
					continue;
				}
				String[] arrEl = files[i].getOriginalFilename().split("\\.");
				String ext = arrEl[arrEl.length-1];
				String normalizedName = notification.getNotifiCode()+"-"+i+"."+ext;
				files[i].transferTo(new File(
						envConfig.getPathUploadFile()+"/"+normalizedName));
				fileName += normalizedName + " ";
			}
			fileName = fileName.trim();
			Session session = sessionFactory.openSession();
		    Transaction transaction = session.beginTransaction();
		    Notification notificationInDb = (Notification) session.get(Notification.class,notification.getNotifiCode());
		    notificationInDb.setTitle(notification.getTitle());
		    notificationInDb.setContent(notification.getContent());
		    notificationInDb.setFiles(fileName);
		    session.update(notificationInDb);
		    transaction.commit();
		    session.close();
		    return "notification/show";
		} catch (Exception e) {
			System.out.println(e);
			return "notification/show_form_create";
		}
	}
	
	@Transactional
	@RequestMapping ("/notification/delete")
	public void deleteNotify(ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response
		) {
		try {
			String code = request.getParameter("notificationCode");
			Session session = sessionFactory.openSession();
		    Transaction transaction = session.beginTransaction();
		    Query query = session.createQuery("DELETE Notification WHERE matb = :matb").setParameter("matb", code);
		    query.executeUpdate();
		    transaction.commit();
		    session.close();
		    response.sendRedirect(request.getContextPath() + "/notification/show.htm");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@RequestMapping ("/notification/show_form_update")
	public String updateNotification(HttpServletRequest request,ModelMap model) {
		String code = request.getParameter("notificationCode");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String time = request.getParameter("time");
		NotificationFormUpdate noti = new NotificationFormUpdate(code,title,content,time);
		model.addAttribute("notification",noti);
		return "notification/show_form_update";
	}
	
	@RequestMapping ("/notification/show_form_create")
	public String showFormCreatenNotification(ModelMap model) {
		NotificationForm noti = new NotificationForm();
		model.addAttribute("notification",noti);
		return "notification/show_form_create";
	}
}
