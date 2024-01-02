package ptithcm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {
	@RequestMapping("login")
	public String UserLogin() {
		
		return "user/login";
	}
}
