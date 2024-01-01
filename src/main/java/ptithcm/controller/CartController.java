package ptithcm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.AnPham;
import ptithcm.entity.Order;
import ptithcm.entity.Student;

@Controller
@RequestMapping("/cart/")
public class CartController {
	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	@RequestMapping(value = "cart/add/{maAnPham}.htm")
	public String addToCart(ModelMap model, @PathVariable("maAnPham") String maAnPham) {

		String mssv = "N20DCPT044";

		Order order = getOrder(maAnPham, mssv);

		if (order != null) {
			model.addAttribute("order", order);
			return "record/update";
		} else {

			Order newOrder = new Order();
			newOrder.setSoLuong(1);

		}

		return "record/index";
	}

	private Order getOrder(String maAnPham, String mssv) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Order WHERE id = :maAnPham AND mssv = :mssv";
			Query query = session.createQuery(hql);
			query.setParameter("maAnPham", maAnPham);
			query.setParameter("mssv", mssv);

			List<Order> orderList = query.list();
			if (!orderList.isEmpty()) {
				return orderList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	private Student getStudent(String mssv) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Student WHERE mssv = :mssv";
			Query query = session.createQuery(hql);
			query.setParameter("mssv", mssv);

			List<Student> studentList = query.list();
			if (!studentList.isEmpty()) {
				return studentList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	private AnPham getOrder(String maAnPham) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM AnPham WHERE maanpham = :maAnPham";
			Query query = session.createQuery(hql);
			query.setParameter("maAnPham", maAnPham);

			List<AnPham> listAnPham = query.list();
			if (!listAnPham.isEmpty()) {
				return listAnPham.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	private boolean createOrder(Order order) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(order);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}

		return false;
	}

}
