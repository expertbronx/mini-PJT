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
�Ǹ���
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 002}">
���ſϷ� <a href="/updateTranCode.do?proTranCode=${map.list[param.count].proTranCode}&prodNo=${map.list[param.count].prodNo}">����ϱ�</a>
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 003}">
�����
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 004}">
�ǸſϷ�
</c:if>
</c:if>
<!-- ROLE == USER && MENU == SEARCH -->
<c:if test = "${param.menu eq 'search'}">
<c:if test = "${empty map.list[param.count].proTranCode}">
�Ǹ���
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 002}">
������
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 003}">
������
</c:if>
<c:if test = "${map.list[param.count].proTranCode eq 004}">
������
</c:if>
</c:if>
<!-- ROLE == USER && MENU == NULL -->
<c:if test = "${empty param.menu}">
<c:if test = "${list[param.count].tranCode eq 002}">
��� ���
</c:if>
<c:if test = "${list[param.count].tranCode eq 003}">
����� <a href="/updateTranCode.do?proTranCode=${list[param.count].tranCode}&prodNo=${list[param.count].purchaseProd.prodNo}">����Ȯ��</a>
</c:if>
<c:if test = "${list[param.count].tranCode eq 004}">
��ۿϷ�
</c:if>
</c:if>
</body>
</html>