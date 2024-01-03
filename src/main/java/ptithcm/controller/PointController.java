package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ptithcm.entity.Point;
import ptithcm.entity.Student;

@Controller
@RequestMapping("/xemdiem")
@Transactional
public class PointController {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@RequestMapping("")
	public String showpoint(ModelMap model,HttpServletRequest request) {
		HttpSession se = request.getSession();
		Student user = (Student) se.getAttribute("student");
		System.out.println(user.getStudentCode());
		List<Point> listpoint = sessionFactory.getCurrentSession().createQuery("FROM Point Where mssv=:mssv").setParameter("mssv",user.getStudentCode()).list();
		if(listpoint.size()>0)
		{
			System.out.println(listpoint.get(0).toString());
		}
		
		return "point/index";
	}
}
