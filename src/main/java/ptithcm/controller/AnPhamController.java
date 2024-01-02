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
import ptithcm.entity.Order;

@Controller
@RequestMapping("/anpham/")
public class AnPhamController {

	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	@RequestMapping("/show_list")
	public String showForm(ModelMap model, HttpServletRequest request) {
		List<AnPham> listAnPham = sessionFactory.getCurrentSession().createQuery("FROM AnPham").list();
		List<Order> listOrder = sessionFactory.getCurrentSession().createQuery("FROM Order").list();

		model.addAttribute("listAnPham", listAnPham);
		model.addAttribute("listOrder", listOrder);
		request.setAttribute("orderSize", listOrder.size());

//		List<Student> notifications = sessionFactory.getCurrentSession().createQuery("FROM Notification").list();
//		System.out.println(listAnPham);

//		System.out.println(notifications);

		return "anpham/index";
	}

}