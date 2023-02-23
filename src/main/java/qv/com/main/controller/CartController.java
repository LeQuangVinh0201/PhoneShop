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
import qv.com.main.repo.CartRepository;
import qv.com.main.service.CartService;
import qv.com.main.service.EditionService;
import qv.com.main.service.OrdersService;
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
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/orders/{editionId}")
	public String listProducts(Model model, @PathVariable(name="editionId") Optional<Integer> editionId) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
        User user = userService.findById(username).get();
		
		Edition edi = editionService.findById(editionId.get()).get();
		
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
			List<Orders> orderList = user.getProductcart().getOrders();
			boolean newOrder = true;
			for (int i = 0; i < orderList.size(); i++) {
				if(editionId.get() == orderList.get(i).getEdition().getId()) {
					newOrder = false;
					break;
				}
			}
			
			if(newOrder) {
				Orders order = new Orders();
				order.setQuantity(1);
				order.setEdition(edi);
				order.setProductcart(user.getProductcart());
				user.getProductcart().getOrders().add(order);
			}
			
		}
		
		userService.saveNoUpdatePass(user);
		
		int oriPrice = 0;
		int sale = 0;
		int finalPrice = 0;
		List<Orders> orderList = user.getProductcart().getOrders();
		for (int i = 0; i < orderList.size(); i++) {
			oriPrice += orderList.get(i).getEdition().getPrice() * orderList.get(i).getQuantity();
			sale += orderList.get(i).getEdition().getDiscount() * orderList.get(i).getQuantity();
		}
		
		finalPrice = oriPrice - sale;
		model.addAttribute("oriPrice", oriPrice);
		model.addAttribute("sale", sale);
		model.addAttribute("finalPrice", finalPrice);
         
		model.addAttribute("productcarts", user.getProductcart());
		
		User userNew = userService.findById(username).get();
		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
		
        return "CartsDetail";
	}
	
	@GetMapping("/orders")
	public String listProducts(Model model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		User user = userService.findById(username).get();
		
		int oriPrice = 0;
		int sale = 0;
		int finalPrice = 0;
		List<Orders> orderList = user.getProductcart().getOrders();
		for (int i = 0; i < orderList.size(); i++) {
			oriPrice += orderList.get(i).getEdition().getPrice() * orderList.get(i).getQuantity();
			sale += orderList.get(i).getEdition().getDiscount() * orderList.get(i).getQuantity();
		}
		
		finalPrice = oriPrice - sale;
		model.addAttribute("oriPrice", oriPrice);
		model.addAttribute("sale", sale);
		model.addAttribute("finalPrice", finalPrice);
         
		model.addAttribute("productcarts", user.getProductcart());
		
		User userNew = userService.findById(username).get();
		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
		
        return "CartsDetail";
	}
	
	@GetMapping("/orders/plusAction/{orderId}")
	public String plusAction(Model model, @PathVariable(name="orderId") Optional<Integer> orderId) {
		Orders order = ordersService.findById(orderId.get());
		order.setQuantity(order.getQuantity() + 1);
		ordersService.save(order);
		
        return "redirect:/cart/orders";
	}
	
	@GetMapping("/orders/subAction/{orderId}")
	public String subAction(Model model, @PathVariable(name="orderId") Optional<Integer> orderId) {
		Orders order = ordersService.findById(orderId.get());
		if(order.getQuantity() > 1) {
			order.setQuantity(order.getQuantity() - 1);
			ordersService.save(order);
		}
		
        return "redirect:/cart/orders";
	}
	
	@GetMapping("/orders/deleteAction/{orderId}")
	public String deleteAction(Model model, @PathVariable(name="orderId") Optional<Integer> orderId) {
		String username = (String) session.getAttribute("username");
		User user = userService.findById(username).get();
		Orders order = ordersService.findById(orderId.get());
		
//	ordersService.delete(order);
		
		ProductCart cart = cartService.findById(order.getProductcart().getId()).get();
		cart.removeOrder(order);
		cartService.save(cart);
		

		
        return "redirect:/cart/orders";
	}

}
