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
import qv.com.main.service.TelephoneService;

@Controller
@RequestMapping("user")
public class UserSearchPhoneBySeries {
	@Autowired
	TelephoneService telephoneService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/searchBySeries/{series}")
	public String listProducts(Model model, @PathVariable(name="series") Optional<String> series) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		List<Telephone> listProducts = telephoneService.findBySeries(series.get());
        model.addAttribute("telephones", listProducts);
         
        return "ListProductsBySeries";
	}
}
