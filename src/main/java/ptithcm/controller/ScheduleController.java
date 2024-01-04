package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.config.EnvConfig;
import ptithcm.entity.AnPham;
import ptithcm.entity.AnPhamForm;
import ptithcm.entity.Schedule;
import ptithcm.entity.ScheduleDay;

@Controller
@RequestMapping("/schedule/")
public class ScheduleController {
	String mssv = "N20DCPT009";

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	EnvConfig envConfig;

	@Transactional
	@RequestMapping("/show")
	public String showForm(ModelMap model, HttpServletRequest request) {

		int tuan = 1;

		List<ScheduleDay> listTkbTuan = getListTkbTuan(tuan);

		model.addAttribute("tuan", tuan);
		model.addAttribute("listTkbTuan", listTkbTuan);

		return "schedule/schedule_page";
	}

	@RequestMapping(value = "tkb/{tuan}.htm", method = RequestMethod.GET)
	public String showFormCreateProduct(ModelMap model, @ModelAttribute("tuan") int tuan) {

		if (tuan < 1) {
			tuan = 1;
		}

		List<ScheduleDay> listTkbTuan = getListTkbTuan(tuan);

		model.addAttribute("tuan", tuan);
		model.addAttribute("listTkbTuan", listTkbTuan);

		return "schedule/schedule_page";
	}

	private List<ScheduleDay> getListTkbTuan(int tuan) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Schedule WHERE mssv = :mssv";
			Query query = session.createQuery(hql);
			query.setParameter("mssv", mssv);

			List<Schedule> listTkb = query.list();

			if (!listTkb.isEmpty()) {
				List<ScheduleDay> listTkbTuan = new ArrayList<>();

				for (Schedule schedule : listTkb) {

					if (schedule.isHocTuan(tuan)) {
						ScheduleDay scheduleDay = new ScheduleDay(schedule.getMonhoc(), schedule.getThu(),
								schedule.getBuoi());
						listTkbTuan.add(scheduleDay);
					}
				}

				return listTkbTuan;
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

}
