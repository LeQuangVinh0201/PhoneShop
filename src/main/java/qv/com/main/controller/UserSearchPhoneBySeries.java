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
import qv.com.main.entities.Telephone;
import qv.com.main.entities.User;
import qv.com.main.service.TelephoneService;
import qv.com.main.service.UserService;

@Controller
@RequestMapping("user")
public class UserSearchPhoneBySeries {
	@Autowired
	TelephoneService telephoneService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/searchBySeries/{series}")
	public String listProducts(Model model, @PathVariable(name="series") Optional<String> series) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		List<Telephone> listProducts = telephoneService.findBySeries(series.get());
        model.addAttribute("telephones", listProducts);
        
        if(username != null) {
        	User userNew = userService.findById(username).get();
        	
    		if(userNew.getProductcart() != null) {
        		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
            }
        }
        
        return "ListProductsBySeries";
	}
	
	@GetMapping("/findByName/{name}")
	public String listProducts(Model model, @PathVariable(name="name") String name) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		List<Telephone> listProducts = telephoneService.findByNameLike(name);
        model.addAttribute("telephones", listProducts);
        
        if(username != null) {
        	User userNew = userService.findById(username).get();
        	
    		if(userNew.getProductcart() != null) {
        		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
            }
        }else {
        	model.addAttribute("message", "You need login first!");
        	return "forward:/";
        }

        return "ListProductsBySeries";
	}
}
