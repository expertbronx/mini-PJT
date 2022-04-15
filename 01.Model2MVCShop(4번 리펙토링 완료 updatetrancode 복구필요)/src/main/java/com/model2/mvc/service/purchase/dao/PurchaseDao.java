package com.model2.mvc.service.purchase.dao;


import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	
	public Purchase findPurchase(int i) throws Exception;
	
	public Purchase findTranInfo(int i) throws Exception;

	public void insertPurchase(Purchase vo) throws Exception;

	public Map<String, Object> getPurchaseList(Search search, String i) throws Exception;

	public Purchase updatePurchase(Purchase vo) throws Exception;
	
	public void updateTranCode(Purchase vo) throws Exception;		

	public int getTotalCount(String sql) throws Exception;

	public String makeCurrentPageSql(String sql , Search search) throws Exception;

	
}	
