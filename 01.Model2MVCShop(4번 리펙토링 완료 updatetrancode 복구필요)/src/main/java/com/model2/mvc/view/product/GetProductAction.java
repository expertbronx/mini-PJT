package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String prodNo=request.getParameter("prodNo");
		
		ProductService service=new ProductServiceImpl();
		Product vo=service.findProduct(prodNo);
		request.setAttribute("vo", vo);
		String keep = "/";
		keep += request.getParameter("prodNo");

		Cookie[] cookies = request.getCookies();
		// 쿠키에 값이 있다면
		for(int i = 0; i<cookies.length; i++){
			String name = cookies[i].getName(); // 쿠키 이름
			String value = cookies[i].getValue(); // 쿠키 값 
		if(name.equals("history")){
			Cookie cookie = new Cookie("history", value + keep);
			response.addCookie(cookie);
		}else{
			Cookie cookie = new Cookie("history", request.getParameter("prodNo"));
			response.addCookie(cookie);
		}

	}
		
		request.setAttribute("vo", vo);

		return "forward:/product/getProduct.jsp";
	}
}