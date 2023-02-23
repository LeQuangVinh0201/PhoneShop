package qv.com.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import qv.com.main.entities.Edition;
import qv.com.main.entities.Telephone;
import qv.com.main.entities.User;
import qv.com.main.service.EditionService;
import qv.com.main.service.TelephoneService;
import qv.com.main.service.UserService;

@Controller
@RequestMapping("user")
public class PhoneDetailController {
	@Autowired
	TelephoneService telephoneService;
	
	@Autowired
	EditionService editionService;

	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/phoneDetail/{id}")
	public String listProducts(Model model, @PathVariable(name="id") Optional<String> id) {
		Optional<Edition> edi = editionService.findById(Integer.parseInt(id.get()));
		Edition edition = edi.get();
		Telephone tele  = edi.get().getTelephone();
		
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
        model.addAttribute("telephone", tele);
        model.addAttribute("edition", edition);
        
        User userNew = userService.findById(username).get();
		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
        
        return "PhoneDetail";
	}
}
