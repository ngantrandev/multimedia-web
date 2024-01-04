package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
//		Student user = new Student();
//		user.setStudentCode("N20DCPT021");
		System.out.println(user.getStudentCode());
		List<Point> listpoint = sessionFactory.getCurrentSession().createQuery("FROM Point p WHERE p.studentCode=:mssv ORDER BY p.nh DESC ").setParameter("mssv", user.getStudentCode()).list();
		if(listpoint.size()>0)
		{
			for(int i=0;i<listpoint.size();i++) {
			System.out.println(listpoint.get(i).toString());
			}
		}
		model.addAttribute("listpoint",listpoint);
		return "point/index";
	}

}
