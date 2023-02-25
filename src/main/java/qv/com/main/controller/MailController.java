package qv.com.main.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import qv.com.main.entities.Orders;
import qv.com.main.entities.Revenue;
import qv.com.main.entities.Telephone;
import qv.com.main.entities.User;
import qv.com.main.service.CartService;
import qv.com.main.service.EditionService;
import qv.com.main.service.OrdersService;
import qv.com.main.service.RevenueService;
import qv.com.main.service.TelephoneService;
import qv.com.main.service.UserService;


@Controller
@RequestMapping("cart")
public class MailController {
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RevenueService revenueService;
	
	@Autowired
	TelephoneService telephoneService;
	
	@Autowired
	EditionService editionService;
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	HttpSession session;
	
	@PostMapping("/mail/complete")
	public String complete(@RequestParam String phoneNumber,@RequestParam String fullName
			,@RequestParam String email, @RequestParam String phoneNameList , @RequestParam String oriPriceForm
			,@RequestParam String saleForm, @RequestParam String finalPriceForm,Model model) {
		
		// Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        String contentText = "Xin chào Anh/Chị "  + fullName +"," + "\n" + "\n";
        contentText += "Số điện thoại đặt hàng : " + phoneNumber + "\n";
        contentText += " Chi tiết đặt hàng như sau: " + "\n";
        contentText += phoneNameList + "\n";
        contentText += oriPriceForm + "\n" +  saleForm + "\n" + finalPriceForm + "\n" + "\n";
        contentText += "Cảm ơn quý khách đã mua hàng tại QV Store!";
        
        
        message.setTo(email);
        message.setSubject("Xác nhận đặt Hàng thành công tại QV Store");
        message.setText(contentText);
        
        String username = (String) session.getAttribute("username");
		User user = userService.findById(username).get();
		
		//send data to revenue table
		List<Orders> orderList = user.getProductcart().getOrders();
		
		List<Revenue> revenueList = new ArrayList<Revenue>();
		
		for (int i = 0; i < orderList.size(); i++) {
			Telephone tele = telephoneService.findById(orderList.get(i).getEdition().getTelephone().getMasp()).get();
			Revenue rev = new Revenue();
			rev.setUsername(username);
			rev.setPhonename(tele.getName());
			rev.setCapacity(orderList.get(i).getEdition().getCapacity());
			rev.setQuantity(orderList.get(i).getQuantity());
			rev.setSellprice(orderList.get(i).getEdition().getPrice() - orderList.get(i).getEdition().getDiscount());
			rev.setTotal((orderList.get(i).getEdition().getPrice() - orderList.get(i).getEdition().getDiscount())* orderList.get(i).getQuantity());
			rev.setSelldate(new Date());
			revenueList.add(rev);
		}
		
		revenueService.saveAll(revenueList);
		
		
		//remove cart after finish
		user.setProductcart(null);
		userService.saveNoUpdatePass(user);

        // Send Message!
        emailSender.send(message);
		
        return "redirect:/loginSuccess";
	}
}
