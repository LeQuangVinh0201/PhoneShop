package qv.com.main.controller;


import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import qv.com.main.entities.User;
import qv.com.main.model.UserDto;
import qv.com.main.service.UserService;

@Controller
@RequestMapping("user")
public class AccountController {
	@Autowired
	UserService userService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("user", new UserDto());
		return "";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") UserDto dto, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("HomePage");
		}
		Optional<User> userExisted = userService.findById(dto.getUserName());
		
		if(userExisted.isPresent()) {
			model.addAttribute("account", userExisted); 
			model.addAttribute("errorMessage", "This username has already existed!");
			
			return new ModelAndView("RegisterPage", model);
		}
		
		User user = new  User();
		BeanUtils.copyProperties(dto, user);
		user.setRole("User");
		userService.save(user);
		
//		model.addAttribute("message", "User is saved");
		
		return new ModelAndView("HomePage", model);
	}
}
