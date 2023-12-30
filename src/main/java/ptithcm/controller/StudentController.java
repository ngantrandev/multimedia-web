package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.Student;


@Controller
public class StudentController {

	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	@RequestMapping ("/student/show_form")
	public String showForm(HttpServletRequest request) {
		List<Student> students = sessionFactory.getCurrentSession().createQuery("FROM Student").list();

//		List<Student> notifications = sessionFactory.getCurrentSession().createQuery("FROM Notification").list();
		System.out.println(students);
//		System.out.println(notifications);

		return "student/form";
	}
//	
//	@RequestMapping("/student/save_data")
//	public String saveForm(HttpServletRequest request) {
//		String name = request.getParameter("name");
//		String mark = request.getParameter("mark");
//		String major = request.getParameter("major");
//		
//		request.setAttribute("name", name);
//		request.setAttribute("mark", mark);
//		request.setAttribute("major", major);
//		
//		return "student/success";
//	}

}
