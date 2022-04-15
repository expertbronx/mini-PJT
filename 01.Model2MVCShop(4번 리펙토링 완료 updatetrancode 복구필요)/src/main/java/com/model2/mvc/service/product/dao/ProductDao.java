package com.model2.mvc.service.product.dao;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {

	public void insertProduct(Product productVO) throws Exception ;

	public Product findProduct(String prodNo) throws Exception ;

	public List<Product> getProductList(Search search) throws Exception ;

	public void updateProduct(Product productVO) throws Exception ;

	public int getTotalCount(Search search) throws Exception ;

}
