package ptithcm.controller.notification;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.Notification;
import ptithcm.entity.Student;

@Controller
public class NotificationController {
	@Autowired
	public SessionFactory sessionFactory;
	
	@Transactional
	@RequestMapping ("/notification/show")
	public String showForm(ModelMap model) {
		Student student = (Student) sessionFactory.getCurrentSession().createQuery("FROM Student WHERE mssv = :mssv").setParameter("mssv", "N20DCPT009").uniqueResult();
		ArrayList<Notification> notifications = new ArrayList<>(student.getNotifications());
		model.addAttribute("notifications",notifications);
		return "notification/notification_page";
	}
}
