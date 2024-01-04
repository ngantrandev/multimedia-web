package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.config.EnvConfig;
import ptithcm.entity.Schedule;
import ptithcm.entity.ScheduleDay;
import ptithcm.entity.Student;

@Controller
@RequestMapping("/home/")
public class ScheduleController {

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	EnvConfig envConfig;

	@Transactional
	@RequestMapping("/index")
	public String showForm(ModelMap model, HttpServletRequest request, HttpSession sessionClient) {

		int tuan = 1;

		String mssv = "";
		Student studentClient = (Student) sessionClient.getAttribute("student");
		
		List<ScheduleDay> listTkbTuan = null;

		if (studentClient == null) {
			listTkbTuan = new ArrayList<ScheduleDay>();
		}
		else {
			mssv = studentClient.getStudentCode();

			listTkbTuan = getListTkbTuan(tuan, mssv);
		}

		model.addAttribute("tuan", tuan);
		model.addAttribute("listTkbTuan", listTkbTuan);

		return "schedule/schedule_page";
	}

	@RequestMapping(value = "tkb/{tuan}.htm", method = RequestMethod.GET)
	public String showFormTkbTuan(ModelMap model, @ModelAttribute("tuan") int tuan, HttpSession sessionClient) {

		if (tuan < 1) {
			tuan = 1;
		}
		
		String mssv = "";
		Student studentClient = (Student) sessionClient.getAttribute("student");
		
		List<ScheduleDay> listTkbTuan = null;

		if (studentClient == null) {
			listTkbTuan = new ArrayList<ScheduleDay>();
		}
		else {
			mssv = studentClient.getStudentCode();

			listTkbTuan = getListTkbTuan(tuan, mssv);
		}

		model.addAttribute("tuan", tuan);
		model.addAttribute("listTkbTuan", listTkbTuan);

		return "schedule/schedule_page";
	}

	private List<ScheduleDay> getListTkbTuan(int tuan, String mssv) {


		Session session = sessionFactory.openSession();
		List<ScheduleDay> listTkbTuan = new ArrayList<>();
		try {
			String hql = "FROM Schedule WHERE mssv = :mssv";
			Query query = session.createQuery(hql);
			query.setParameter("mssv", mssv);

			List<Schedule> listTkb = query.list();

			if (!listTkb.isEmpty()) {
				

				for (Schedule schedule : listTkb) {

					if (schedule.isHocTuan(tuan)) {
						ScheduleDay scheduleDay = new ScheduleDay(schedule.getMonhoc(), schedule.getThu(),
								schedule.getBuoi());
						listTkbTuan.add(scheduleDay);
					}
				}
}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listTkbTuan;

	}

}
