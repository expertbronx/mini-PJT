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
	
	@RequestMapping("/getPurchase.do")
	public String addPurchase(	 @RequestParam("prodNo") String prodNo,
															@RequestParam("tranNo") int tranNo,
															Model model ) throws Exception {
		
		model.addAttribute("vo", productService.findProduct(prodNo));
		model.addAttribute("pvo", purchaseService.getPurchase(tranNo));
		
	return "forward:/purchase/getPurchaseView.jsp";
	
	}
	
	@RequestMapping("/listPurchase.do")
	public String listProduct( @ModelAttribute("search") Search search,
													HttpSession session,
													HttpServletRequest request,
													Model model)  throws Exception {
	
	session = request.getSession();
	User user = (User)session.getAttribute("user");
	
	if(search.getCurrentPage() ==0 ){
		search.setCurrentPage(1);
	}
	search.setPageSize(pageSize);

	// Business logic 수행
	Map<String , Object> map=purchaseService.getPurchaseList(search, user.getUserId());
	Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	System.out.println(resultPage);
	
	model.addAttribute("list", map.get("list"));
	model.addAttribute("resultPage", resultPage);
	model.addAttribute("search", search);
	
	return "forward:/purchase/listPurchase.jsp";
}
	
	@RequestMapping("/updatePurchase.do")
	public String updatePurchase(	@ModelAttribute("vo") Purchase vo,
															@RequestParam("tranNo") int tranNo,
															Model model ) throws Exception {
		
		vo = purchaseService.getPurchase(tranNo);
		model.addAttribute("vo", vo);
		
	return "forward:/purchase/updatePurchaseView.jsp";
	
	}
	
	@RequestMapping("/updatePurchaseView.do")
	public String updatePurchaseView(	@ModelAttribute("vo") Purchase vo,
																		Model model ) throws Exception {
		
		purchaseService.updatePurchase(vo);
		vo = purchaseService.getPurchase(vo.getTranNo());
		model.addAttribute("vo", vo);
		
	return "forward:/purchase/updatePurchase.jsp";
	
	}
	
	@RequestMapping("/updateTranCode.do")
	public String updateTranCode(	@ModelAttribute("vo") Purchase vo,
																@RequestParam("prodNo") int prodNo,
																@RequestParam("tranCode") String tranCode,
																		Model model ) throws Exception {
			
		vo = purchaseService.findTranInfo(prodNo);
		if ( vo.getTranCode().equals("002")) {
		vo.setTranCode("003");
		}else if( vo.getTranCode().equals("003")) {
			vo.setTranCode("004");
		}
		purchaseService.updateTranCode(vo);
		
	return "redirect:/index.jsp";
	
	}
	
	
}













