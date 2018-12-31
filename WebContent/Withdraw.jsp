<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Withdraw.mm">
<table>
	<tr>
		<td><label>Enter account number:</label></td>
		<td><input type="number" name="accnumber"/></td>
	</tr>
	<tr>
		<td><label>Enter Amount:</label></td>
		<td><input type="number" name="amount"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="submit"/></td>
		<td><input type="reset" value="clear"/></td>
	</tr>
</table>
 </form>
 <jsp:include page="exit.html"></jsp:include>
</body>
</html>