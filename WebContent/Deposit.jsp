<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Deposit.mm">
<table>
	<tr>
		<td><label>Enter the account number :</label></td>
		<td><input type="number" name="accountNumber"></td>
	</tr>
	<tr>
		<td><label>Enter the amount:</label></td>
		<td><input type="number" name="amount"/></td>
	</tr>
	<tr><td><input type="submit" value="OK"/></td></tr>
</table>
</form>
<jsp:include page="exit.html"></jsp:include>
</body>
</html>