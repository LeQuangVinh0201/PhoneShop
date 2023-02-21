package qv.com.main.controller;

import java.util.ArrayList;
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
import qv.com.main.entities.Orders;
import qv.com.main.entities.ProductCart;
import qv.com.main.entities.Telephone;
import qv.com.main.entities.User;
import qv.com.main.service.EditionService;
import qv.com.main.service.TelephoneService;
import qv.com.main.service.UserService;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	TelephoneService telephoneService;
	
	@Autowired
	EditionService editionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/orders/{editionId}")
	public String listProducts(Model model, @PathVariable(name="editionId") Optional<Integer> editionId) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		Edition edi = editionService.findById(editionId.get()).get();
		
		User user = userService.findById(username).get();
		
		if(user.getProductcart() == null) {
			Orders order = new Orders();
			order.setQuantity(1);
			order.setEdition(edi);
			
			List<Orders> orderlist = new ArrayList<Orders>();
			orderlist.add(order);
			
			ProductCart cart = new ProductCart();
			cart.setStatus("pending");
			cart.setOrders(orderlist);
			order.setProductcart(cart);
			user.setProductcart(cart);
		}else {
			user.getProductcart().getOrders().get(0).setQuantity(1);
			user.getProductcart().getOrders().get(0).setEdition(edi);
			user.getProductcart().setStatus("pending");
		}
		
		
		userService.saveNoUpdatePass(user);
         
		model.addAttribute("productcarts", user.getProductcart());
		
        return "CartsDetail";
	}

}
