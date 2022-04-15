package com.model2.mvc.service.product.test;

import java.util.List;
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
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testInsertProduct() throws Exception {

		Product vo = new Product();
		vo.setProdName("testUserName");
		vo.setProdDetail("testUserName");
		vo.setManuDate("22-04-07");
		vo.setPrice(30000);
		vo.setFileName("dddddd");
		
		productService.insertProduct(vo);
		
		vo = productService.findProduct("10018");

		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
		Assert.assertEquals("testUserName", vo.getProdName());
		Assert.assertEquals("testUserName", vo.getProdDetail());
		Assert.assertEquals("22-04-07", vo.getManuDate());
		Assert.assertEquals(30000, vo.getPrice());
		Assert.assertEquals("dddddd", vo.getFileName());
	}
	@Test
	public void testfindProduct() throws Exception {

			Product productVO = new Product();
			
			productVO = productService.findProduct("10003");
			
			Assert.assertEquals("testUserName", productVO.getProdName());
			Assert.assertEquals("testUserName", productVO.getProdDetail());
			Assert.assertEquals("22-04-07", productVO.getManuDate());
			Assert.assertEquals(30000, productVO.getPrice());
			Assert.assertEquals("dddddd", productVO.getFileName());

	}

		//@Test
		public void testupdateProduct( ) throws Exception {
		
			Product vo = productService.findProduct("testUserName");
			Assert.assertNotNull(vo);
			
			Assert.assertEquals("testUserName", vo.getProdName());
			Assert.assertEquals("testUserName", vo.getProdDetail());
			Assert.assertEquals("22-04-07", vo.getManuDate());
			Assert.assertEquals(30000, vo.getPrice());
			Assert.assertEquals("dddddd", vo.getFileName());
			
			vo.setProdName("change");
			vo.setProdDetail("change");
			vo.setManuDate("77-77-77");
			vo.setPrice(7777);
			vo.setFileName("change");
			
			productService.updateProduct(vo);
			
			vo = productService.findProduct("10018");
			
			Assert.assertEquals("change", vo.getProdName());
			Assert.assertEquals("change", vo.getProdDetail());
			Assert.assertEquals("77-77-77", vo.getManuDate());
			Assert.assertEquals(7777, vo.getPrice());
			Assert.assertEquals("change", vo.getFileName());
			
		}
		//@Test
		 public void testGetProductListAll() throws Exception{
			 
			 	Search search = new Search();
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	Map<String,Object> map = productService.getProductList(search);
			 	
			 	List<Object> list = (List<Object>)map.get("list");
			 	Assert.assertEquals(3, list.size());
			 	
				//==> console 확인
			 	//System.out.println(list);
			 	
			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 	
			 	System.out.println("=======================================");
			 	
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	search.setSearchCondition("0");
			 	search.setSearchKeyword("");
			 	map = productService.getProductList(search);
			 	
			 	list = (List<Object>)map.get("list");
			 	Assert.assertEquals(3, list.size());
			 	
			 	//==> console 확인
			 	System.out.println(list);
			 	
			 	totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 }
		
		 //@Test
		 public void testGetProductListByProdNo() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("10018");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 
		 //@Test
		 public void testGetUserListByProdName() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword("change");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }	 
		 //@Test
		 public void testGetUserListByPrice() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("7777");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }	 
		
 }	 