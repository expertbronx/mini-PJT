package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String prodNo = request.getParameter("prodNo");
		System.out.println("가져오는 상품번호" + prodNo);
		ProductService service=new ProductServiceImpl();
		Product vo = new Product();
		vo.setProdName(request.getParameter("prodName"));
		vo.setProdDetail(request.getParameter("prodDetail"));
		vo.setManuDate(request.getParameter("manuDate"));
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		vo.setFileName(request.getParameter("fileName").replace("../../images/", ""));
		vo.setProdNo(Integer.parseInt(prodNo));
		request.setAttribute("vo", vo);
		System.out.println("가져오는 오브젝트" + vo);

		service.updateProduct(vo);
		
		return "forward:/getProduct.do?prodNo="+prodNo;
	}
}