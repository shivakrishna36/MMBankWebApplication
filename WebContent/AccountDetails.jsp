<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="details" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>Account Number</th>
		<th><a href="sortByName.mm">Holder Name</a></th>		
		<th><a href="sortByBalance.mm">Balance</a></th>
		<th><a href="sortBySalaried.mm">Salaried</a></th>
		<th>Over Draft Limit</th>
		<th>Type Of Account</th>
	</tr>
	<details:if test="${requestScope.account!=null}">
	<tr>
		<td>${requestScope.account.bankAccount.accountNumber}</td>
		<td>${requestScope.account.bankAccount.accountHolderName}</td>
		<td>${requestScope.account.bankAccount.accountBalance}</td>
		<td>${requestScope.account.salary==true?"YES":"NO"}
		<td>${"N/A"}</td>
		<td>${"Savings"}</td>
	</tr>
	</details:if>
	<details:if test="${requestScope.accounts!=null}">
	<details:forEach var="account" items="${requestScope.accounts}">
	<tr>
		<td>${account.bankAccount.accountNumber}</td>
		<td>${account.bankAccount.accountHolderName}</td>
		<td>${account.bankAccount.accountBalance}</td>
		<td>${account.salary==true?"YES":"NO"}
		<td>${"N/A"}</td>
		<td>${"Savings"}</td>
	</tr>
	</details:forEach>
	</details:if>
</table>
<jsp:include page="exit.html"></jsp:include>
</body>
</html>