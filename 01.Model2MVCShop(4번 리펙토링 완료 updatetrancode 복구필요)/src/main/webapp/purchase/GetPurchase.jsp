<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<!-- ROLE == ADMIN -->
<c:if test = "${param.menu eq 'manage'}">
<c:if test = "${empty map.list[param.count].proTranCode}">
판매중
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 002}">
구매완료 <a href="/updateTranCode.do?proTranCode=${map.list[param.count].proTranCode}&prodNo=${map.list[param.count].prodNo}">배송하기</a>
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 003}">
배송중
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 004}">
판매완료
</c:if>
</c:if>
<!-- ROLE == USER && MENU == SEARCH -->
<c:if test = "${param.menu eq 'search'}">
<c:if test = "${empty map.list[param.count].proTranCode}">
판매중
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 002}">
재고없음
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 003}">
재고없음
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 004}">
재고없음
</c:if>
</c:if>
<!-- ROLE == USER && MENU == NULL -->
<c:if test = "${empty param.menu}">
<c:if test = "${list[param.count].tranCode eq 002}">
배송 대기
</c:if>
<c:if test = "${list[param.count].tranCode eq 003}">
배송중 <a href="/updateTranCode.do?proTranCode=${list[param.count].tranCode}&prodNo=${list[param.count].purchaseProd.prodNo}">수취확인</a>
</c:if>
<c:if test = "${list[param.count].tranCode eq 004}">
배송완료
</c:if>
</c:if>
</body>
</html>