package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao  purchaseDao;
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addPurchase(Purchase purchaseVO) throws Exception {
			purchaseDao.insertPurchase(purchaseVO);
	}

	@Override
	public Purchase getPurchase(int i) throws Exception {
		return purchaseDao.findPurchase(i);
	}
	@Override
	public Purchase findTranInfo(int i) throws Exception{
		return purchaseDao.findTranInfo(i);
	}
	@Override
	public Map<String, Object> getPurchaseList(Search search, String i) throws Exception{	
		return purchaseDao.getPurchaseList(search, i);
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		return null;
	}

	@Override
	public Purchase updatePurchase(Purchase purchaseVO) throws Exception {
		return purchaseDao.updatePurchase(purchaseVO);
	}

	@Override
	public void updateTranCode(Purchase purchaseVO) throws Exception {
			purchaseDao.updateTranCode(purchaseVO);
	}

}
