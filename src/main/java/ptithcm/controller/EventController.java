package ptithcm.controller;

import java.io.File;
import java.net.http.HttpHeaders;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.config.EnvConfig;
import ptithcm.entity.Student;
import ptithcm.entity.event.Event;
import ptithcm.entity.event.EventForm;
import ptithcm.entity.notification.Notification;
import ptithcm.entity.notification.NotificationForm;
import ptithcm.entity.notification.NotificationFormUpdate;

@Controller
public class EventController {
	@Autowired
	public SessionFactory sessionFactory;
		
	@Autowired
	EnvConfig envConfig;
	
	@Transactional
	@RequestMapping ("/event/show")
	public String showPage(ModelMap model) {
		List<Event> events = sessionFactory.getCurrentSession().createQuery("FROM Event").list();
		Collections.reverse(events);
		model.addAttribute("events",events);
		model.addAttribute("isBch",true);
		return "event/event_page";
	}
	
	@Transactional
	@RequestMapping ("/event/create")
	public void createEvent(ModelMap model,
			HttpServletRequest request
			,@ModelAttribute("event") EventForm event,
			HttpServletResponse response
		) {
		try {
			Date date = new Date();
			long miliLong = Instant.now().toEpochMilli();
		    String eventCode = String.valueOf(miliLong);
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    String time = sdf.format(new Date(miliLong));
		    String[] arrEl = event.getFile().getOriginalFilename().split("\\.");
			String ext = arrEl[arrEl.length-1];
			String fileName = eventCode + "." + ext;
			event.getFile().transferTo(new File(
					envConfig.getPathUploadFile()+"/"+fileName));
			Session session = sessionFactory.openSession();
		    Transaction transaction = session.beginTransaction();
		    Student poster = (Student) session.get(Student.class, "N20DCPT009");
		    session.save(new Event(eventCode,event.getName(),event.getContent(),time,fileName,poster));
		    transaction.commit();
		    session.close();
		    response.sendRedirect(request.getContextPath() + "/event/show.htm");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Transactional
	@RequestMapping ("/event/update")
	public void updateEvent(ModelMap model,
			HttpServletRequest request
			,@ModelAttribute("notification") NotificationFormUpdate notification,
			HttpServletResponse response
		) {
		try {
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
		    Student poster = (Student) session.get(Student.class, "N20DCPT009");
		    List<Student> studentsSent = session.createQuery("FROM Student WHERE malop = :classCode").setParameter("classCode", poster.getClassCode()).list();
		    session.update(new Notification(notification.getNotifiCode(),notification.getContent(),notification.getTitle(),poster,notification.getTime(),"docs",fileName,studentsSent));
		    transaction.commit();
		    session.close();
		    response.sendRedirect(request.getContextPath() + "/event/show.htm");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Transactional
	@RequestMapping ("/event/delete")
	public void deleteEvent(ModelMap model,
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
		    response.sendRedirect(request.getContextPath() + "/event/show.htm");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@RequestMapping ("/event/show_form_update")
	public String updateEvent(HttpServletRequest request,ModelMap model) {
		String code = request.getParameter("notificationCode");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String time = request.getParameter("time");
		NotificationFormUpdate noti = new NotificationFormUpdate(code,title,content,time);
		model.addAttribute("notification",noti);
		return "event/show_form_update";
	}
	
	@RequestMapping ("/event/show_form_create")
	public String showFormCreatenEvent(ModelMap model) {
		EventForm event = new EventForm();
		model.addAttribute("event",event);
		return "event/show_form_create";
	}
}
