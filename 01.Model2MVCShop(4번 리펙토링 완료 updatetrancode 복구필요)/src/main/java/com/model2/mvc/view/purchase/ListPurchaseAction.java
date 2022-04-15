package com.model2.mvc.view.purchase;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class ListPurchaseAction extends Action {
    
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Search search=new Search();
		int currentPage=1;

		if( request.getParameter("currentPage")  != null ){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println("체에ㅔ에에에에에엥크" + currentPage);
		search.setCurrentPage(currentPage);
		
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);

		PurchaseService purchaseService=new PurchaseServiceImpl();
		System.out.println(search);
		System.out.println(user.getUserId());
		Map<String , Object> map = purchaseService.getPurchaseList(search, user.getUserId());
		System.out.println("체크" + map);
		Page resultPage	= 
		new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListPuchaseAction ::"+resultPage);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		System.out.println("끼모링" + request.getAttribute("list"));
		
		return  "forward:/purchase/listPurchase.jsp";
	}
}