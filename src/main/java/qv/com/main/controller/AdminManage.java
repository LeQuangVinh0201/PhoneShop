package qv.com.main.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import qv.com.main.entities.Brand;
import qv.com.main.entities.Edition;
import qv.com.main.entities.Revenue;
import qv.com.main.entities.Telephone;
import qv.com.main.entities.User;
import qv.com.main.model.AdminLoginDto;
import qv.com.main.model.ExcelGenerator;
import qv.com.main.service.BrandService;
import qv.com.main.service.RevenueService;
import qv.com.main.service.TelephoneService;
import qv.com.main.service.UserService;


@Controller
@RequestMapping("admin")
public class AdminManage {
	@Autowired
	TelephoneService telephoneService;
	
	@Autowired
	RevenueService revenueService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BrandService brandService;
	
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
			
			//check brand exist
			String name = telephone.getBrand().getBrand();
			Brand brand = brandService.findByBrand(name);
			if(brand != null) {
				telephone.setBrand(brand);
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
			
			//check brand exist
			Brand brand = brandService.findByBrand(telephone.getBrand().getBrand());
			if(brand != null) {
				telephone.setBrand(brand);
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
	
	@GetMapping("/manage/statistic")
	public String getStatistic(ModelMap model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		return "Statistic";
	}
	
	
	@PostMapping("/manage/searchRevenue")
	public String getStatisticTable(ModelMap model,@RequestParam  Date startDate, @RequestParam Date endDate) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String startForm = formatter.format(startDate);
		String endForm = formatter.format(endDate);
		
		model.addAttribute("startDateForm", startForm);
		model.addAttribute("endDateForm", endForm);
		
		List<Revenue> revenueList = revenueService.findByStartEndDate(startDate,endDate);
		
		model.addAttribute("revenueList", revenueList);
		
		return "StatisticList";
	}
	
	
	@PostMapping("/manage/exportExcel")
	public void exportExcel (HttpServletResponse response, ModelMap model,@RequestParam  Date startDate, @RequestParam Date endDate)throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Statistic_QVstore_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<Revenue> revenueList = revenueService.findByStartEndDate(startDate,endDate);
		ExcelGenerator excelGen = new ExcelGenerator(revenueList);
		
		excelGen.generate(response);
	}
	
	@GetMapping("/manage/accounts")
	public String accounts(ModelMap model) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		
		return "AccountList";
	}
	
	@GetMapping("/manage/accounts/edit/{userName}")
	public String editAccounts(ModelMap model, @PathVariable(name="userName") String userName) {
		
		User user = userService.findById(userName).get();
		model.addAttribute("user", user);
		
		return "EditAccount";
	}
	
	@PostMapping("/manage/accounts/edit")
	public String saveEditAccounts(ModelMap model, User user) {
		
		userService.saveNoUpdatePass(user);
		
		return "redirect:/admin/manage/accounts";
	}
	
	@GetMapping("/manage/accounts/delete/{userName}")
	public String deleteAccounts(ModelMap model, @PathVariable(name="userName") String userName) {
		
		userService.deleteById(userName);
		
		return "redirect:/admin/manage/accounts";
	}
	
	@GetMapping("/backhome")
	public String backhome(ModelMap model) {
		
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		return "AdminHomePage";
	}
	
	
}
