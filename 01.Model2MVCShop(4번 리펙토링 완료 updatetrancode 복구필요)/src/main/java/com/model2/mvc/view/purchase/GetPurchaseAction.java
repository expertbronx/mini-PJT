package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class GetPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNum = Integer.parseInt(request.getParameter("prodNo"));
		PurchaseService service = new PurchaseServiceImpl();
		ProductService pservice = new ProductServiceImpl();
		request.setAttribute("vo", pservice.findProduct(Integer.toString(prodNum)));
		request.setAttribute("pvo", service.getPurchase(Integer.parseInt(request.getParameter("tranNo"))));
		System.out.println("vo 내용" + request.getAttribute("vo"));
		System.out.println("pvo 내용" + request.getAttribute("pvo"));
		return "forward:/purchase/getPurchaseView.jsp?";
	}
}
