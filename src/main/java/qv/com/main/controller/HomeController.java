package qv.com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import qv.com.main.entities.User;
import qv.com.main.model.AdminLoginDto;
import qv.com.main.service.UserService;


@Controller
public class HomeController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String index1() {
		return "HomePage";
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess(ModelMap model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		User userNew = userService.findById(username).get();
		if(userNew.getProductcart() != null) {
    		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
        }
		
		return "HomePageLogined";
	}
	
	@RequestMapping("/adminloginSuccess")
	public String adminloginSuccess(ModelMap model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		return "AdminHomePage";
	}
	
	
//	@RequestMapping("/Login")
//	public String Login() {
//		return "LoginPage";
//	}
	
	@RequestMapping("/Register")
	public String Register(Model model) {
		model.addAttribute("account", new User()); 
		return "RegisterPage";
	}
	
	
}
