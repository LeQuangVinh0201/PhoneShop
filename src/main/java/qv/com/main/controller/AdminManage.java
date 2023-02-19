package qv.com.main.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import qv.com.main.entities.Edition;
import qv.com.main.entities.Telephone;
import qv.com.main.model.AdminLoginDto;
import qv.com.main.service.TelephoneService;


@Controller
@RequestMapping("admin")
public class AdminManage {
	@Autowired
	TelephoneService telephoneService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/manage/products")
	public String listProducts(Model model) {
		List<Telephone> listProducts = telephoneService.findAll();
        model.addAttribute("telephones", listProducts);
         
        return "AdminListProducts";
	}
	
	@GetMapping(value= {"/manage/products/newOredit", "/manage/products/newOredit/{masp}"})
	public String addNewOrEdit(Model model, @PathVariable(name="masp", required = false) Optional<String> masp) {
		Telephone telephone;
		
		if(masp.isPresent()) {
			Optional<Telephone> existedPhone = telephoneService.findById(masp.get());
			
			if(existedPhone.isPresent()) {
				telephone = existedPhone.get();
				
//				for (int i = 0; i < telephone.getEditions().size(); i++) {
//					Edition edi = telephone.getEditions().get(i);
//					BigDecimal number = new BigDecimal(edi.getPrice());
//					double myDouble = number.doubleValue();
//					edi.setPrice(myDouble);
//					
//			    }
			}else {
				telephone = new Telephone();
			}
			
		}else {//create new
			telephone = new Telephone();
			
			List<Edition> newList2 = new ArrayList<Edition>(3);
			
			for (int i = 0; i < 3; i++) {
				if(i == 0) {
					newList2.add(new Edition());
					newList2.get(0).setCapacity(128);
				}else if( i == 1) {
					newList2.add(new Edition());
					newList2.get(1).setCapacity(256);
				}else {
					newList2.add(new Edition());
					newList2.get(2).setCapacity(512);
				}
		    }
			telephone.setEditions(newList2);
		}

		model.addAttribute("telephone", telephone);
        return "NewOrEdit";
	}
	
	@PostMapping("/manage/products/newOrEdit")
	public String saveOrUpdate(ModelMap model,@Valid Telephone telephone,BindingResult result, HttpServletRequest request,
			@RequestParam(name="pictureUrl", required = false) MultipartFile photo ) {
//		if(result.hasErrors()) {
//			model.addAttribute("account", new AdminLoginDto());
//			return "LoginPage";
//		}
		String username = (String) session.getAttribute("username");
		Optional<Telephone> existOrNot = telephoneService.findById(telephone.getMasp());
		
		if( existOrNot.isPresent()) { //update
			if(telephone.getPictureUrl() == null) {
				Path path = Paths.get("uploads/");
				
				try {
					InputStream inputStream = photo.getInputStream();
					Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					telephone.setPictureUrl(photo.getOriginalFilename().toLowerCase());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
			for (int i = 0; i < telephone.getEditions().size(); i++) {
				telephone.getEditions().get(i).setTelephone(telephone);
		    }
			
			Date nowDate = new Date();
			telephone.setUpdatedAt(nowDate.toString());
			telephone.setUpdatedBy(username);
		}else { //create new
			Path path = Paths.get("uploads/");
			
			try {
				InputStream inputStream = photo.getInputStream();
				Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				telephone.setPictureUrl(photo.getOriginalFilename().toLowerCase());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			List<Edition> newList = telephone.getEditions();
			
			for (int i = 0; i <= 2; i++) {
				newList.get(i).setTelephone(telephone);
		    }
			Date nowDate2 = new Date();
			telephone.setEditions(newList);
			telephone.setCreatedBy(username);
			telephone.setCreatedAt(nowDate2.toString());
		}
		
		
		telephoneService.save(telephone);
		
		return "redirect:/admin/manage/products";
	}
	
	@GetMapping("/manage/products/delete/{masp}")
	public String delete( @PathVariable("masp") String masp) {
		telephoneService.deleteById(masp);
		
		return "redirect:/admin/manage/products";
	}
	
}
