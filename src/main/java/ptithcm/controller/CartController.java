package ptithcm.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;
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
import ptithcm.entity.DonHang;
import ptithcm.entity.Order;
import ptithcm.entity.Student;

@Controller
@RequestMapping("/cart/")
public class CartController {
	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	@RequestMapping(value = "addtocart/{maAnPham}.htm")
	public String addToCart(ModelMap model, @PathVariable("maAnPham") String maAnPham, HttpSession sessionClient) {

		String mssv = "";
		Student studentClient = (Student) sessionClient.getAttribute("student");

		if (studentClient == null) {
			return "redirect:/user/login.htm";
		}

		mssv = studentClient.getStudentCode();

		AnPham anpham = getAnPham(maAnPham);

		if (anpham == null) {
			return "redirect:/anpham/show_list.htm";
		}

		Order order = getOrder(maAnPham, mssv);

		if (order != null) { // da co trong cart

			boolean isSuccess = updateProductCart(order.getId(), true);

			System.out.print("tang so luong " + (isSuccess ? "thanh cong" : "that bai"));

			return "redirect:/anpham/show_list.htm";

		} else { // chua add to cart
			Order newOrder = new Order();

			newOrder.setSoLuong(1);
			newOrder.setAnPham(anpham);
			newOrder.setNguoiDat(studentClient);

			boolean isSuccess = createOrder(newOrder);

			System.out.print("add to cart " + (isSuccess ? "thanh cong" : "that bai"));

		}

		return "redirect:/anpham/show_list.htm";
	}

	@Transactional
	@RequestMapping(value = "remove/{maAnPham}.htm")
	public String removeProductCart(ModelMap model, @PathVariable("maAnPham") String maAnPham, HttpSession sessionClient) {

		 Student studentClient = (Student) sessionClient.getAttribute("student");
		
		AnPham anpham = getAnPham(maAnPham);

		if (studentClient == null) {
			return "redirect:/user/login.htm";
		}
		if (anpham == null) {
			return "redirect:/anpham/show_list.htm";
		}

		Order order = getOrder(maAnPham, studentClient.getStudentCode());

		if (order != null) { // da co trong cart

			if (order.getSoLuong() > 1) { // so luong > 1 thi giam xuong
				boolean isSuccess = updateProductCart(order.getId(), false);

				System.out.print("giam so luong " + (isSuccess ? "thanh cong" : "that bai"));
			} else {
				boolean isSuccess = deleteOrder(order.getId());

				System.out.print("xoa order " + (isSuccess ? "thanh cong" : "that bai"));
			}

			return "redirect:/anpham/show_list.htm";

		}

		return "redirect:/anpham/show_list.htm";
	}

	@Transactional
	@RequestMapping("dat_hang")
	public String onPressThanhToan(ModelMap model, HttpSession sessionClient) {
		
		 Student studentClient = (Student) sessionClient.getAttribute("student");
		 
		 if(studentClient==null) {
			 return "redirect:/user/login.htm";
		 }

		List<Order> listOrder = getListOrder(studentClient.getStudentCode());

		

		if (listOrder != null && listOrder.size() > 0) {
			boolean isSuccess = datHang(listOrder, studentClient.getStudentCode());

			System.out.print("dat hang " + (isSuccess ? "thanh cong" : "that bai"));

			model.addAttribute("message", isSuccess ? "Đặt hàng thành công!" : "Đặt hàng thất bại!");
			return "anpham/result_dathang";
		}

		return "redirect:/anpham/show_list.htm";
	}

	private boolean datHang(List<Order> listOrder, String mssv) {
		System.out.println("Dat hang");
		// TODO Auto-generated method stub
		boolean isCreateSuccess = createListDonHang(listOrder);

		if (isCreateSuccess) {
			boolean isClearSuccess = clearOrder(mssv);

			return isClearSuccess;
		}

		return false;
	}

	// clear list order of user
	private boolean clearOrder(String mssv) {

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		try {
			String hql = "DELETE FROM Order WHERE mssv = :mssvParam";
			Query query = session.createQuery(hql);
			query.setParameter("mssvParam", mssv);

			int rowCount = query.executeUpdate();
			System.out.println("Số lượng bản ghi đã bị xóa: " + rowCount);

			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}

		return false;
	}

	private boolean createListDonHang(List<Order> listOrder) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		String timeDatHang = getCurrTime();

		try {
			for (Order cartItem : listOrder) {
				DonHang donHang = new DonHang();
				donHang.setStudent(cartItem.getNguoiDat());
				donHang.setAnPham(cartItem.getAnPham());
				donHang.setSoLuong(cartItem.getSoLuong());
				donHang.setTime(timeDatHang);
				donHang.setState((byte) 0);

				session.save(donHang);

			}

			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}

		return false;
	}

	private Order getOrder(String maAnPham, String mssv) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Order WHERE maanpham = :maAnPham AND mssv = :mssv";
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

	private List<Order> getListOrder(String mssv) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Order WHERE mssv = :mssv";
			Query query = session.createQuery(hql);
			query.setParameter("mssv", mssv);

			List<Order> orderList = query.list();
			if (!orderList.isEmpty()) {
				return orderList;
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

	private AnPham getAnPham(String maAnPham) {
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
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}

		return false;
	}

	// isIncrease = true => tang soluong, nguoc lai giam
	private boolean updateProductCart(int targetOrderId, boolean isIncrease) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Order updateOrder = (Order) session.get(Order.class, targetOrderId);

			if (isIncrease) {
				updateOrder.setSoLuong(updateOrder.getSoLuong() + 1);
			} else {
				updateOrder.setSoLuong(updateOrder.getSoLuong() - 1);
			}

			session.update(updateOrder);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}

		return false;
	}

	private boolean deleteOrder(int targetOrderId) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(session.load(Order.class, targetOrderId));
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			System.out.print(e.toString());
		} finally {
			session.close();
		}

		return false;
	}

	private String getCurrTime() {
		// Lấy thời gian hiện tại
		LocalDateTime currentDateTime = LocalDateTime.now();

		// format ("dd/MM/yyyy HH:mm:ss")
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		// convert
		return currentDateTime.format(formatter);
	}

}
