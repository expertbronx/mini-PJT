package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseViewAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("으응" + request.getParameter("tranNo"));
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		PurchaseService service = new PurchaseServiceImpl();
		Purchase vo = service.getPurchase(tranNo);

		vo.setReceiverName(request.getParameter("userName"));
		vo.setDivyAddr(request.getParameter("addr"));
		vo.setReceiverPhone(request.getParameter("phone"));
		vo.setTranCode(request.getParameter("tranNo"));
		service.updatePurchase(vo);
		request.setAttribute("pvo", vo);
		System.out.println("마지막 점검" + vo);
		return "forward:/purchase/updatePurchase.jsp";
	}
}
