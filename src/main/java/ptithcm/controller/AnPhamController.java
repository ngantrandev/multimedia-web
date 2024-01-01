package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.AnPham;

@Controller
@RequestMapping("/anpham/")
public class AnPhamController {
	
	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	@RequestMapping ("/show_list")
	public String showForm(ModelMap model) {
		List<AnPham> listAnPham = sessionFactory.getCurrentSession().createQuery("FROM AnPham").list();
		
		model.addAttribute("listAnPham", listAnPham);

//		List<Student> notifications = sessionFactory.getCurrentSession().createQuery("FROM Notification").list();
		System.out.println(listAnPham);
		
		
//		System.out.println(notifications);

		return "anpham/index";
	}

}