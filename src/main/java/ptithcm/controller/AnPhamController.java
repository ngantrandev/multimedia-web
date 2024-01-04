package ptithcm.controller;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.config.EnvConfig;
import ptithcm.entity.AnPham;
import ptithcm.entity.AnPhamForm;
import ptithcm.entity.Order;
import ptithcm.entity.Student;

@Controller
@RequestMapping("/anpham/")
public class AnPhamController {

//	String mssv = "N20DCPT009";

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	EnvConfig envConfig;

	@Transactional
	@RequestMapping("/show_list")
	public String showForm(ModelMap model, HttpServletRequest request, HttpSession sessionClient) {

		List<AnPham> listAnPham = sessionFactory.getCurrentSession().createQuery("FROM AnPham").list();

		Student studentClient = (Student) sessionClient.getAttribute("student");

		String mssv = "";// khai bao mssv

		// check neu co thong tin
		if (studentClient != null) {
			mssv = studentClient.getStudentCode();
		}

		System.out.println("mssv: " + mssv);

		List<Order> listOrder = getListOrder(mssv);

		model.addAttribute("listAnPham", listAnPham);
		model.addAttribute("listOrder", listOrder);
		if (listOrder != null)
			request.setAttribute("orderSize", listOrder.size());
		else
			request.setAttribute("orderSize", 0);

//		List<Student> notifications = sessionFactory.getCurrentSession().createQuery("FROM Notification").list();
//		System.out.println(listAnPham);

//		System.out.println(notifications);

		return "anpham/index";
	}

	@Transactional
	@RequestMapping("/my_product")
	// get my list product
	public String showMyProduct(ModelMap model, HttpServletRequest request, HttpSession sessionClient) {

		Student studentClient = (Student) sessionClient.getAttribute("student");
		String mssv = "";

		if (studentClient != null) {
			mssv = studentClient.getStudentCode();
		}

		System.out.println("mssv: " + mssv);

		List<AnPham> listAnPham = getListAnPham(mssv);

		if (listAnPham == null) {
			listAnPham = new ArrayList<>();
		}

		model.addAttribute("listAnPham", listAnPham);

		return "anpham/my_product_page";
	}

	@Transactional
	@RequestMapping(value = "/my_product/delete/{maAnPham}.htm")
	public String removeAnPham(ModelMap model, @PathVariable("maAnPham") String maAnPham) {

		AnPham anpham = getAnPham(maAnPham);

		if (anpham == null) {
			model.addAttribute("message", "Ấn phẩm không tồn tại!");

			return "anpham/result_page";
		}

		else { // co trong database

			boolean isSuccess = deleteAnPham(anpham.getMaAnPham());

			System.out.println("xoa an pham " + (isSuccess ? "thanh cong" : "that bai"));

			model.addAttribute("message", isSuccess ? "Xóa ấn phẩm thành công!" : "Xóa ấn phẩm thất bại");

			return "anpham/result_page";

		}

	}

	@RequestMapping(value = "/my_product/create", method = RequestMethod.GET)
	public String showFormCreateProduct(ModelMap model) {
		AnPhamForm anPhamForm = new AnPhamForm();
		model.addAttribute("anPhamForm", anPhamForm);
		return "anpham/form_create";
	}

	@Transactional
	@RequestMapping(value = "/my_product/create", method = RequestMethod.POST)
	public String createNewProduct(ModelMap model, HttpServletRequest request,
			@ModelAttribute("anPhamForm") AnPhamForm anPhamForm, HttpServletResponse response, BindingResult errors,
			HttpSession sessionClient) {

		if (anPhamForm.getTen().length() == 0) {
			errors.rejectValue("ten", "anPhamForm", "Vui lòng nhập tên ấn phẩm!");
		}
		if (anPhamForm.getGia().length() == 0) {
			errors.rejectValue("gia", "anPhamForm", "Vui lòng nhập giá ấn phẩm!");
		} else {
			try {
				int giatien = Integer.parseInt(anPhamForm.getGia());
				if (giatien <= 1000 || giatien >= 1000000) {
					errors.rejectValue("gia", "anPhamForm", "Giá phải nằm trong khoảng 1000 - 1.000.000 vnđ!");
				}
			} catch (Exception e) {

			}
		}

		if (anPhamForm.getSoLuongTon().length() == 0) {
			errors.rejectValue("soLuongTon", "anPhamForm", "Vui lòng nhập số lượng tồn!");
		} else {
			try {
				int soLuongTon = Integer.parseInt(anPhamForm.getSoLuongTon());
				if (soLuongTon < 0) {
					errors.rejectValue("soLuongTon", "anPhamForm", "Số lượng tồn không được nhỏ hơn 0!");
				}
			} catch (Exception e) {

			}
		}

		if (anPhamForm.getMoTa().length() == 0) {
			errors.rejectValue("moTa", "anPhamForm", "Vui lòng nhập mô tả!");
		}

		if (anPhamForm.getFile() == null || anPhamForm.getFile().getSize() == 0
				|| anPhamForm.getFile().getName().length() == 0) {
			errors.rejectValue("file", "anPhamForm", "Vui lòng chọn ảnh!");
		} else if (anPhamForm.getFile().getContentType().equals("image/jpeg") == false) {
			errors.rejectValue("file", "anPhamForm", "Vui lòng chỉ chọn file .png hoặc .jpeg!");
		}

		if (errors.hasErrors()) {
			return "anpham/form_create";
		}

		try { //
			String fileName = "";
			String fileUri = "";
			boolean isSuccess = false;
			MultipartFile file = anPhamForm.getFile();
			long miliLong = Instant.now().toEpochMilli();
			System.out.println("size files: " + file.getSize());
			if (file != null & file.getSize() > 0) {
				String arrEl = file.getOriginalFilename();
				String normalizedName = String.valueOf(miliLong) + "_" + arrEl;

				fileUri = envConfig.getPathUploadFile() + "/" + normalizedName;
				file.transferTo(new File(fileUri));
				fileName = normalizedName;
				fileName = fileName.trim();

				String mssv = "";
				Student studentClient = (Student) sessionClient.getAttribute("student");
				if (studentClient != null) {
					mssv = studentClient.getStudentCode();

					AnPham newAnPham = new AnPham(); //
					newAnPham.setMssv(mssv); //
					newAnPham.setTenAnPham(anPhamForm.getTen());
					newAnPham.setGia(anPhamForm.getGia());
					newAnPham.setSoLuongTon(Integer.parseInt(anPhamForm.getSoLuongTon()));
					newAnPham.setMoTa(anPhamForm.getMoTa());
					newAnPham.setImgUrl("uploaded_file/" + normalizedName);
					isSuccess = createAnPham(newAnPham);
				}

			}
			model.addAttribute("message", isSuccess ? "Đăng ấn phẩm thành công!" : "Đăng ấn phẩm thất bại");
			return "anpham/result_page";
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/anpham/my_product/create.htm";
	}

	@RequestMapping(value = "/my_product/edit/{maAnPham}.htm", method = RequestMethod.GET)
	public String showFormEditProduct(ModelMap model, @PathVariable("maAnPham") String maAnPham) {

		AnPham currAnPham = getAnPham(maAnPham);

		if (currAnPham == null) {
			model.addAttribute("message", "Ấn phẩm không tồn tại!");
			return "anpham/result_page";
		}

		AnPhamForm anPhamForm = new AnPhamForm(currAnPham.getTenAnPham(), currAnPham.getGia(),
				String.valueOf(currAnPham.getSoLuongTon()), currAnPham.getMoTa(), null);

		model.addAttribute("anPhamForm", anPhamForm);
		model.addAttribute("oldImage", currAnPham.getImgUrl());
		model.addAttribute("currAnPham", currAnPham);
		return "anpham/form_update";
	}

	@Transactional
	@RequestMapping(value = "/my_product/edit/{maAnPham}.htm", method = RequestMethod.POST)
	public String editProduct(ModelMap model, HttpServletRequest request,
			@ModelAttribute("anPhamForm") AnPhamForm anPhamForm, @PathVariable("maAnPham") String maAnPham,
			HttpServletResponse response, BindingResult errors, HttpSession sessionClient) {

		anPhamForm.setGia(anPhamForm.getGia().replaceAll("[^0-9]", ""));
		anPhamForm.setSoLuongTon(anPhamForm.getSoLuongTon().replaceAll("[^0-9]", ""));

		if (anPhamForm.getTen().length() == 0) {
			errors.rejectValue("ten", "anpham", "Vui lòng nhập tên ấn phẩm!");
		}
		if (anPhamForm.getGia().length() == 0) {
			errors.rejectValue("gia", "anpham", "Vui lòng nhập giá ấn phẩm!");
		} else {

			try {

				int giatien = Integer.parseInt(anPhamForm.getGia());
				if (giatien <= 1000 || giatien >= 1000000) {
					errors.rejectValue("gia", "anpham", "Giá phải nằm trong khoảng 1000 - 1.000.000 vnđ!");
				}
			} catch (Exception e) {

			}
		}

		if (anPhamForm.getSoLuongTon().length() == 0) {
			errors.rejectValue("soLuongTon", "anpham", "Vui lòng nhập số lượng tồn!");
		} else {
			try {
				int soLuongTon = Integer.parseInt(anPhamForm.getSoLuongTon());
				if (soLuongTon < 0) {
					errors.rejectValue("soLuongTon", "anPhamForm", "Số lượng tồn không được nhỏ hơn 0!");
				}
			} catch (Exception e) {

			}
		}

		if (anPhamForm.getMoTa().length() == 0) {
			errors.rejectValue("moTa", "anpham", "Vui lòng nhập mô tả!");
		}

		if (anPhamForm.getFile().getSize() > 0 && anPhamForm.getFile().getContentType().equals("image/jpeg") == false) {
			errors.rejectValue("file", "anpham", "Vui lòng chỉ chọn file .png hoặc .jpeg!");
		}

		if (errors.hasErrors()) {
			AnPham currAnPham = getAnPham(maAnPham);
			model.addAttribute("currAnPham", currAnPham);
			return "anpham/form_update";
		}

		AnPham currAnPham = getAnPham(maAnPham);

		String mssv = "";
		Student studentClient = (Student) sessionClient.getAttribute("student");
		System.out.println("mssv: " + mssv);
		if (studentClient != null) {
			mssv = studentClient.getStudentCode();
		}

		AnPham newAnPham = new AnPham(); //
		newAnPham.setMaAnPham(Integer.parseInt(maAnPham));
		newAnPham.setMssv(mssv); //
		newAnPham.setTenAnPham(anPhamForm.getTen());
		newAnPham.setGia(anPhamForm.getGia());
		newAnPham.setSoLuongTon(Integer.parseInt(anPhamForm.getSoLuongTon()));
		newAnPham.setMoTa(anPhamForm.getMoTa());

		boolean isSuccess = false;
		MultipartFile file = anPhamForm.getFile();

		if (anPhamForm.getFile().getSize() == 0) { // khong co file
			System.out.println(anPhamForm.getFile().getSize());
			System.out.println(currAnPham.getImgUrl());
			newAnPham.setImgUrl(currAnPham.getImgUrl());
		} else {
			try { //
				String fileName = "";
				String fileUri = "";

				long miliLong = Instant.now().toEpochMilli();
				System.out.println("size files: " + file.getSize());
				if (file != null & file.getSize() > 0) {
					String arrEl = file.getOriginalFilename();
					String normalizedName = String.valueOf(miliLong) + "_" + arrEl;

					fileUri = envConfig.getPathUploadFile() + "/" + normalizedName;
					file.transferTo(new File(fileUri));
					fileName = normalizedName;
					fileName = fileName.trim();

					newAnPham.setImgUrl("uploaded_file/" + normalizedName);

				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		isSuccess = updateAnPham(newAnPham);
		model.addAttribute("message", isSuccess ? "Cập nhật ấn phẩm thành công!" : "Cập nhật ấn phẩm thất bại");
		return "anpham/result_page";

	}

	private List<AnPham> getListAnPham(String mssv) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM AnPham WHERE mssv = :mssv";
			Query query = session.createQuery(hql);
			query.setParameter("mssv", mssv);

			List<AnPham> listAnPham = query.list();
			if (!listAnPham.isEmpty()) {
				return listAnPham;
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

	private boolean updateAnPham(AnPham anpham) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			AnPham updateAnPham = (AnPham) session.get(AnPham.class, anpham.getMaAnPham());

			System.out.println(updateAnPham);

			updateAnPham.setTenAnPham(anpham.getTenAnPham());
			updateAnPham.setGia(anpham.getGia());
			updateAnPham.setSoLuongTon(anpham.getSoLuongTon());
			updateAnPham.setMoTa(anpham.getMoTa());
			updateAnPham.setImgUrl(anpham.getImgUrl());

			session.update(updateAnPham);
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

	private boolean deleteAnPham(int targetAnPhamId) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(session.load(AnPham.class, targetAnPhamId));
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

	private boolean createAnPham(AnPham anpham) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(anpham);
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

}