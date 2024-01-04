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
import javax.servlet.http.HttpSession;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.config.EnvConfig;
import ptithcm.entity.Student;
import ptithcm.entity.event.Event;
import ptithcm.entity.event.EventForm;
import ptithcm.entity.event.EventFormUpdate;
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
	public String createEvent(ModelMap model,
			HttpServletRequest request
			,@ModelAttribute("event") EventForm event,
			HttpServletResponse response,
			HttpSession sessionClient,
			BindingResult errors
		) {
		try {
			if(event.getName() == null || event.getName().length()==0) {
				errors.rejectValue("name", "event","Vui lòng nhập tên");
				return "event/show_create_form";
			}
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
		    Student studentClient = (Student) sessionClient.getAttribute("student");
		    Student poster = (Student) session.get(Student.class, studentClient.getStudentCode());
		    session.save(new Event(eventCode,event.getName(),event.getContent(),time,fileName,poster));
		    transaction.commit();
		    session.close();
		    return "event/show";
		} catch (Exception e) {
			System.out.println(e);
			return "event/show_create_form";
		}
	}
	
	@Transactional
	@RequestMapping ("/event/update")
	public String updateEvent(ModelMap model,
			HttpServletRequest request
			,@ModelAttribute("event") EventFormUpdate event,
			HttpServletResponse response,
			BindingResult errors
		) {
		try {
			if(event.getName()==null || event.getName().length() == 0) {
				errors.rejectValue("title", "event","Vui lòng nhập tên");
				return "event/show_form_update";
			}
			String fileName = "";
			String[] arrEl = event.getFile().getOriginalFilename().split("\\.");
			if(arrEl.length>1) {
				String ext = arrEl[arrEl.length-1];
				fileName = event.getEventCode() + "." + ext;
				event.getFile().transferTo(new File(
						envConfig.getPathUploadFile()+"/"+fileName));
			}
			Session session = sessionFactory.openSession();
		    Transaction transaction = session.beginTransaction();
		    Event eventInDb = (Event) session.get(Event.class, event.getEventCode());
		    eventInDb.setName(event.getName());
		    eventInDb.setContent(event.getContent());
		    eventInDb.setNameThumbImg(fileName);
		    session.update(eventInDb);
		    transaction.commit();
		    session.close();
		    response.sendRedirect(request.getContextPath() + "/event/show.htm");
		    return "event/show";
		} catch (Exception e) {
			System.out.println(e);
			return "event/show_form_update";
		}
	}
	
	@Transactional
	@RequestMapping ("/event/delete")
	public void deleteEvent(ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response
		) {
		try {
			String code = request.getParameter("eventCode");
			Session session = sessionFactory.openSession();
		    Transaction transaction = session.beginTransaction();
		    Query query = session.createQuery("DELETE Event WHERE masukien = :mask").setParameter("mask", code);
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
		String code = request.getParameter("eventCode");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String time = request.getParameter("time");
		EventFormUpdate event = new EventFormUpdate(code,name,content,time);
		model.addAttribute("event",event);
		return "event/show_form_update";
	}
	
	@RequestMapping ("/event/show_form_create")
	public String showFormCreatenEvent(ModelMap model) {
		EventForm event = new EventForm();
		model.addAttribute("event",event);
		return "event/show_form_create";
	}
}
