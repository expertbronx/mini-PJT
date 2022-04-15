package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Purchase;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}
	
	
	public void insertPurchase(Purchase vo) throws Exception{
		sqlSession.insert("PurchaseMapper.addPurchase", vo);
		
}
	
	public Purchase findPurchase(int i) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.findPurchase", i);
	}
	
public Purchase findTranInfo(int i) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.findTranInfo", i);
		}

	public Purchase updatePurchase(Purchase vo) throws Exception{	
		return sqlSession.selectOne("PurchaseMapper.updatePurchase", vo);
	}
	
	public void updateTranCode(Purchase vo) throws Exception{
		sqlSession.selectOne("PurchaseMapper.updateTrancode", vo);
//		System.out.println(vo);
//		System.out.println("업뎃 메서드 종료");
//		Connection con = DBUtil.getConnection();
//
//		String sql = "UPDATE transaction "
//		+ "SET tran_status_code = ? "
//		+ "WHERE prod_no= ? ";
//		System.out.println("최종점검" + vo);
//		PreparedStatement stmt = con.prepareStatement(sql);
//		if( vo.getTranCode().equals("002" ))  {
//		stmt.setString(1, "003");
//		stmt.setInt(2, vo.getPurchaseProd().getProdNo());
//		}else if(  vo.getTranCode().equals("003" )) {
//		stmt.setString(1, "004");
//		stmt.setInt(2, vo.getPurchaseProd().getProdNo());
//		}
//		
//		ResultSet rs = stmt.executeQuery();
//		vo.getPurchaseProd().setProTranCode("tran_status_code");
//		con.close();
		
	}
	

	
	public Map<String, Object> getPurchaseList(Search search, String i) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search",search);
		map.put("userId",i);
				
		map.put("list", sqlSession.selectList("PurchaseMapper.getPurchaseList",map));		
		map.put("totalCount",sqlSession.selectOne("PurchaseMapper.getTotalCount", i));
				
		return map;
	}

	public int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	public String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("UserDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
	
}
	
