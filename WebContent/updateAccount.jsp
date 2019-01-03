<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updateDetails.mm">
<table>
	<tr>
		<td><label>Account Number</label></td>
		<td><input type="text" name="accountNumber" value="${requestScope.account.bankAccount.accountNumber}" readonly="readonly"></td>
	</tr>
	<tr>
		<td><label>Update Holder Name</label></td>
		<td><input type="text" name="name" value="${requestScope.account.bankAccount.accountHolderName}"></td>
	</tr>
	<tr>
		<td><label>Balance</label></td>
	<td><input type="number" value="${requestScope.account.bankAccount.accountBalance}" readonly="readonly"></td>
	</tr>
	<tr>
		<td><label>Select salaried</label></td>
		<td><input type="radio" name = "salary" ${requestScope.account.salary==true?"checked":""}>Yes</td>
		<td><input type="radio" name="salary" ${requestScope.account.salary==true?"":"checked"}>No</td>
	</tr>
	<tr>
		<td><input type="reset" value="clear"></td>
		<td><input type="submit" value="submit"></td>
	</tr>
</table>
<jsp:include page="exit.html"></jsp:include>
</form>
</body>
</html>