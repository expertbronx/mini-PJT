package com.model2.mvc.view.purchase;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class AddPurchaseAction extends  Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
     //  request.getParameter()
		HttpSession session = request.getSession();
		String prodNum = request.getParameter("prodNo");
		
		PurchaseService pservice = new PurchaseServiceImpl();
		ProductService service=new ProductServiceImpl();
		Product vo = service.findProduct(prodNum);
		User uvo =  (User) session.getAttribute("user");
		Purchase pvo = new Purchase();
		
			pvo.setBuyer(uvo);
			pvo.setPurchaseProd(vo);
			pvo.setPaymentOption(request.getParameter("paymentOption"));
			pvo.setReceiverName(request.getParameter("receiverName"));
			pvo.setReceiverPhone(request.getParameter("receiverPhone"));
			pvo.setDivyAddr(request.getParameter("receiverAddr"));
			pvo.setDivyRequest(request.getParameter("receiverRequest"));
			pvo.setDivyDate(request.getParameter("receiverDate"));
			System.out.println("내용점검" + pvo);

		request.setAttribute("prodInfo", vo);
		request.setAttribute("purcInfo", pvo);
		
		pservice.addPurchase(pvo);
				
		return "forward:/purchase/addPurchase.jsp";
	}
}
