package com.model2.mvc.service.product.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;



/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchsaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testInsertPurchase() throws Exception {
		User uvo = new User();
		Product pvo = new Product();
		Purchase vo = new Purchase();
		//TranNo = Sequence
		vo.setBuyer(uvo);
		vo.setPurchaseProd(pvo);
		
		vo.getPurchaseProd().setProdNo(10002);
		vo.getBuyer().setUserId("user02");
		vo.setPaymentOption("001");
		vo.setReceiverName("������");
		vo.setReceiverPhone("010-4722-3802");
		vo.setDivyAddr("��õ-����-û��");
		vo.setDivyRequest("�������ּ���");
		vo.setTranCode("002");
		//RegDate = SYSDATE
		vo.setDivyDate("22/05/10");
		
		System.out.println("purchase : " +  vo);
		
		purchaseService.addPurchase(vo);
		
		vo = purchaseService.getPurchase(10006);

		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
		Assert.assertEquals("user02", vo.getBuyer().getUserId());	//BuyerId
    	Assert.assertEquals("001", vo.getPaymentOption());
		Assert.assertEquals("������", vo.getReceiverName());
		Assert.assertEquals("010-4722-3802", vo.getReceiverPhone());
		Assert.assertEquals("��õ-����-û��", vo.getDivyAddr());
		Assert.assertEquals("�������ּ���", vo.getDivyRequest());
		Assert.assertEquals("22/05/10", vo.getDivyDate());
	}
		@Test
		public void testfindPurchase() throws Exception {

			Purchase vo = new Purchase();
			
			vo = purchaseService.getPurchase(10006);
			
			Assert.assertEquals("������", vo.getReceiverName());
			Assert.assertEquals("010-4722-3802", vo.getReceiverPhone());

	}
		
		//@Test
		public void testfindTranInfo() throws Exception {

			Purchase vo = new Purchase();
			
			vo = purchaseService.findTranInfo(10002);
			
			Assert.assertEquals("������", vo.getReceiverName());
			Assert.assertEquals("010-4722-3802", vo.getReceiverPhone());

	}

		 //@Test
		public void testupdatePurchase() throws Exception {
		
			Purchase vo = new Purchase();
			Assert.assertNotNull(vo);
				
			vo.setReceiverName("changeee");
			vo.setDivyAddr("changeee");
			vo.setReceiverPhone("010-0000-1111");
			vo.setTranNo(10022);
			
			vo = purchaseService.updatePurchase(vo);
			
			vo = purchaseService.getPurchase(10022);
			
			Assert.assertEquals("changeee", vo.getReceiverName());
			Assert.assertEquals("changeee", vo.getDivyAddr());
			Assert.assertEquals("010-0000-1111", vo.getReceiverPhone());
			
		}
		//@Test
		public void testupdateTrancode() throws Exception {
				
				Purchase vo = new Purchase();
				Assert.assertNotNull(vo);
					
				vo.setTranCode("003");
				vo.setTranNo(10022);
				
				purchaseService.updateTranCode(vo);	
			}
		//@Test
		 public void testGetPurchaseList() throws Exception{
			 
			 	Search search = new Search();
			 	Purchase vo = new Purchase();
			 	User uvo = new User();
			 	
			 	uvo.setUserId("user02");
			 	vo.setBuyer(uvo);
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	Map<String,Object> map = purchaseService.getPurchaseList(search, vo.getBuyer().getUserId());
			 	
			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 	
			 	System.out.println("=======================================");
			 	
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
//			 	search.setSearchCondition("0");
//			 	search.setSearchKeyword("");
			 	map = purchaseService.getPurchaseList(search, uvo.getUserId());
			 	

			 	
			 	totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 }
		
 }	 