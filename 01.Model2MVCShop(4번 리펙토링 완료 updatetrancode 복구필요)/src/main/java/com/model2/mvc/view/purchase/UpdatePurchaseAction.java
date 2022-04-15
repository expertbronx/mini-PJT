package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int tranNum = Integer.parseInt(request.getParameter("tranNo"));
		PurchaseService service = new PurchaseServiceImpl();
		Purchase vo = service.getPurchase(tranNum);
		request.setAttribute("vo", vo);
		request.setAttribute("prodNo", request.getParameter("prodNo"));
		return "forward:/purchase/updatePurchaseView.jsp";
	}
}
