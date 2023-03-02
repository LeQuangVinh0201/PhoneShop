package qv.com.main.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import qv.com.main.service.BrandService;
import qv.com.main.service.TelephoneService;
import qv.com.main.service.UserService;

@Controller
@RequestMapping("user")
public class UserSearchPhoneByBrand {
	@Autowired
	TelephoneService telephoneService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/searchByBrand/{brand}")
	public String listProducts(Model model, @PathVariable(name="brand") Optional<String> brand) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		List<Telephone> listProducts = telephoneService.findByBrand(brandService.findByBrand(brand.get()));
        model.addAttribute("telephones", listProducts);
        model.addAttribute("brandItem", brand.get());
        
        if(username != null) {
        	User userNew = userService.findById(username).get();
        	
    		if(userNew.getProductcart() != null) {
        		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
            }
        }
        
        return "ListProductsByBrand";
	}
	
	@GetMapping("/searchByBrand/desc/{brand}")
	public String listProductsDesc(Model model, @PathVariable(name="brand") Optional<String> brand) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
        model.addAttribute("brandItem", brand.get());
		
		List<Telephone> listProducts = telephoneService.findByBrand(brandService.findByBrand(brand.get()));
		List<Edition> ediList = new ArrayList<>();
		
		for (int i = 0; i < listProducts.size(); i++) {
			ediList.addAll(listProducts.get(i).getEditions());
		}
		
        for (int i = 0 ; i < ediList.size() - 1; i++) {
            for (int j = i + 1; j < ediList.size(); j++) {
                if ((ediList.get(i).getPrice() - ediList.get(i).getDiscount()) < (ediList.get(j).getPrice() - ediList.get(j).getDiscount())) {
                    Collections.swap(ediList, i, j);
                }
            }
        }
        
        model.addAttribute("editionList", ediList);

        if(username != null) {
        	User userNew = userService.findById(username).get();
        	
    		if(userNew.getProductcart() != null) {
        		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
            }
        }
        
        return "ListProductsByBrandSort";
	}
	
	@GetMapping("/searchByBrand/asc/{brand}")
	public String listProductsAsc(Model model, @PathVariable(name="brand") Optional<String> brand) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
        model.addAttribute("brandItem", brand.get());
		
		List<Telephone> listProducts = telephoneService.findByBrand(brandService.findByBrand(brand.get()));
		List<Edition> ediList = new ArrayList<>();
		
		for (int i = 0; i < listProducts.size(); i++) {
			ediList.addAll(listProducts.get(i).getEditions());
		}
		
        for (int i = 0 ; i < ediList.size() - 1; i++) {
            for (int j = i + 1; j < ediList.size(); j++) {
                if ((ediList.get(i).getPrice() - ediList.get(i).getDiscount()) > (ediList.get(j).getPrice() - ediList.get(j).getDiscount())) {
                    Collections.swap(ediList, j, i);
                }
            }
        }
        
        model.addAttribute("editionList", ediList);

        if(username != null) {
        	User userNew = userService.findById(username).get();
        	
    		if(userNew.getProductcart() != null) {
        		model.addAttribute("orderNumber", userNew.getProductcart().getOrders().size());
            }
        }
        
        return "ListProductsByBrandSort";
	}

}
