package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;



@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("productServiceImpl")	
	private ProductService productService;
	//setter Method 구현 않음
		
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView( @RequestParam("prodNo") String prodNo, 
																Model model ) throws Exception {
		
	model.addAttribute("prodInfo", productService.findProduct(prodNo));
	
	return "forward:/purchase/addPurchaseView.jsp";
}
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase( @ModelAttribute("pvo") Purchase purchase,
														 @RequestParam("prodNo") String prodNo,
														 HttpSession session,
														 HttpServletRequest request,
														 Model model ) throws Exception {
	
		session = request.getSession();
		purchase.setBuyer((User)session.getAttribute("user"));
		purchase.setPurchaseProd(productService.findProduct(prodNo));
		
		purchaseService.addPurchase(purchase);
		
	return "forward:/purchase/addPurchase.jsp";
}
	
}













