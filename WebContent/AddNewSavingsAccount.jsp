<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="skyblue">
<form action="AddNewSavingsAccount.mm">
<table>
	<tr>
		<td><label>Enter Name</label></td>
		<td><input type="text" name="Holdername"/></td>
	</tr>
	<tr>
		<td><label>Enter Initial Balance</label></td>
		<td><input type="number" name="accountBalance"/></td>
	</tr>
	<tr>
		<td><label>Select salaried</label></td>
		<td><input type="radio" name = "salary" value="Yes"/>Yes
		<input type="radio" name="salary" value="No"/>No</td>
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