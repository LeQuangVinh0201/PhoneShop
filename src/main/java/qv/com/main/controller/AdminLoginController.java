package qv.com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import qv.com.main.entities.Edition;
import qv.com.main.entities.User;
import qv.com.main.model.AdminLoginDto;
import qv.com.main.service.EditionService;
import qv.com.main.service.RevenueService;
import qv.com.main.service.UserService;

@Controller
public class AdminLoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	RevenueService revenueService;
	
	@Autowired
	EditionService editionService;
	
	@GetMapping("alogin") 
	public String login(ModelMap model) {
		model.addAttribute("account", new AdminLoginDto());
		return "LoginPage";
	}
	
	@GetMapping("logout") 
	public String logout(ModelMap model) {
		model.remove("username");
		model.clear();
		
		session.removeAttribute("username");
		
		return "redirect:/";
	}
	
	@PostMapping("alogin")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") AdminLoginDto dto , BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("LoginPage", model);
		}
		User user = userService.login(dto.getUserName(), dto.getPassword());
		
		if(user == null) {
			model.addAttribute("message","Invalid username or password");
			return new ModelAndView("LoginPage", model);
		}
		
		session.setAttribute("username", user.getUserName());
		
		//get orders of user
		if(user.getProductcart() != null) {
			int orderNumber = user.getProductcart().getOrders().size();
			session.setAttribute("orderNumber", String.valueOf(orderNumber));
		}else {
			session.setAttribute("orderNumber", "0");
		}

		
		Object ruri = session.getAttribute("redirect-uri");
		
		//authorized only admin can access some pages
		if(ruri != null &&  "admin".equalsIgnoreCase(user.getRole())) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		
		//admin login success
		if("admin".equalsIgnoreCase(user.getRole())) {
			return new ModelAndView("forward:/adminloginSuccess", model);
		}
		
		//user login success
		List<Integer> editionIdList = revenueService.findEditionBestSale();
		List<Edition> editionList = editionService.findAllById(editionIdList);
		model.addAttribute("editionList", editionList);
		
		return new ModelAndView("forward:/loginSuccess", model);
	}
	
	@GetMapping("button") 
	public String login1(ModelMap model) {
		model.addAttribute("account", new AdminLoginDto());
		return "LoginPage";
	}
	
	@GetMapping("AdminHomePage") 
	public String login2(ModelMap model) {
		model.addAttribute("account", new AdminLoginDto());
		return "AdminHomePage";
	}
}
