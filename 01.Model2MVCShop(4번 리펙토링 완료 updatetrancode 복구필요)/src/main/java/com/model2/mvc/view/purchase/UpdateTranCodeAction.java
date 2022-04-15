package com.model2.mvc.view.purchase;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction extends Action {
       
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ProductService pservice = new ProductServiceImpl();
		PurchaseService service = new PurchaseServiceImpl();

		System.out.println("¿€µø¡ﬂ1");
		
		// §–§–...
		
		Purchase vo =  service.findTranInfo(
				Integer.parseInt(request.getParameter("prodNo")) );
		Product pvo = pservice.findProduct(request.getParameter("prodNo"));
		vo.setPurchaseProd(pvo);
		System.out.println(request.getParameter("proTranCode"));
		
		service.updateTranCode(vo);
		System.out.println("dddd"+ vo);
		return "forward:/index.jsp";
	}
}
