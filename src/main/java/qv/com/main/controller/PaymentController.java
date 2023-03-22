package qv.com.main.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import qv.com.main.PaypalPaymentIntent;
import qv.com.main.PaypalPaymentMethod;
import qv.com.main.entities.Edition;
import qv.com.main.entities.Orders;
import qv.com.main.entities.Revenue;
import qv.com.main.entities.Telephone;
import qv.com.main.entities.User;
import qv.com.main.service.CartService;
import qv.com.main.service.EditionService;
import qv.com.main.service.OrdersService;
import qv.com.main.service.PaypalService;
import qv.com.main.service.RevenueService;
import qv.com.main.service.TelephoneService;
import qv.com.main.service.UserService;
import qv.com.main.model.Utils;
@Controller
public class PaymentController {
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;
	
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
	

	@PostMapping("/pay")
	public String pay(HttpServletRequest request,@RequestParam("price") double price,@RequestParam String phoneNumber,@RequestParam String fullName
			,@RequestParam String email, @RequestParam String phoneNameList , @RequestParam String oriPriceForm
			,@RequestParam String saleForm, @RequestParam String finalPriceForm, @RequestParam String address,Model model ){
		
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(
					price,
					"USD",
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"payment description",
					cancelUrl,
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					//send email confirmation
					// Create a Simple MailMessage.
			        SimpleMailMessage message = new SimpleMailMessage();
			        String contentText = "Xin chào Anh/Chị "  + fullName +"," + "\n" + "\n";
			        contentText += "Số điện thoại đặt hàng : " + phoneNumber + "\n";
			        contentText += " Chi tiết đặt hàng như sau: " + "\n";
			        contentText += phoneNameList + "\n";
			        contentText += oriPriceForm + "\n" +  saleForm + "\n" + finalPriceForm + "\n" + "\n";
			        contentText += "Địa chỉ giao hàng : " + address + "\n" ;
			        contentText += "Hình thức thanh toán : " + "Đã thanh toán qua paypal" + "\n" + "\n";
			        contentText += "Cảm ơn quý khách đã mua hàng tại QV Store!";
			        
			        
			        message.setTo(email);
			        message.setSubject("Xác nhận đặt Hàng thành công tại QV Store");
			        message.setText(contentText);
			        
			        session.setAttribute("messagePaypal", message);
			        
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
						rev.setEditionId(orderList.get(i).getEdition().getId());
						rev.setSellprice(orderList.get(i).getEdition().getPrice() - orderList.get(i).getEdition().getDiscount());
						rev.setTotal((orderList.get(i).getEdition().getPrice() - orderList.get(i).getEdition().getDiscount())* orderList.get(i).getQuantity());
						rev.setSelldate(new Date());
						revenueList.add(rev);
						
						//update storage of Edition
//						Edition edi = editionService.findById(orderList.get(i).getEdition().getId()).get();
//						edi.setStorage( edi.getStorage() - orderList.get(i).getQuantity());
//						editionService.save(edi);
					}
					
					session.setAttribute("orderList", orderList);
					
					session.setAttribute("revenueList", revenueList);
					
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
	
	@GetMapping(URL_PAYPAL_CANCEL)
	public ModelAndView cancelPay(ModelMap model){
		model.addAttribute("statusSuccess", "Đặt hàng thất bại!");
		return new ModelAndView("forward:/loginSuccess", model);
	}
	
	@GetMapping(URL_PAYPAL_SUCCESS)
	public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,ModelMap model){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				String username = (String) session.getAttribute("username");
				User user = userService.findById(username).get();
				
				SimpleMailMessage message = (SimpleMailMessage) session.getAttribute("messagePaypal");
				List<Orders> orderList  = (List<Orders>) session.getAttribute("orderList");
				List<Revenue> revenueList = (List<Revenue>) session.getAttribute("revenueList");
				
				for (int i = 0; i < orderList.size(); i++) {
					//update storage of Edition
					Edition edi = editionService.findById(orderList.get(i).getEdition().getId()).get();
					edi.setStorage( edi.getStorage() - orderList.get(i).getQuantity());
					editionService.save(edi);
				}
				
				revenueService.saveAll(revenueList);
				
				
				//remove cart after finish
				user.setProductcart(null);
				userService.saveNoUpdatePass(user);

		        // Send Message!
		        emailSender.send(message);
		        
		        session.removeAttribute("messagePaypal");
		        session.removeAttribute("orderList");
		        session.removeAttribute("revenueList");
				
				model.addAttribute("statusSuccess", "Quý Khách đã hoàn tất đơn hàng, vui lòng kiểm tra email xác nhận!");
				return new ModelAndView("forward:/loginSuccess", model);
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return new ModelAndView("forward:/", model);
	}
	
}
