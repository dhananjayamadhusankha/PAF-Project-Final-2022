<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery.min.js"></script> 
<script src="Components/payment.js"></script> 
<title>Payment Management</title>
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>PAYMENT DETAILS</h1>
				<form id="formPayment" name="formPayment" method="post" action="Payment.jsp">  
					Customer ID:  
 	 				<input id="cusId" name="cusId" type="text"  class="form-control form-control-sm">
					<br> Telephone No:   
  					<input id="telNo" name="telNo" type="text" class="form-control form-control-sm">   
  					<br> Date:   
  					<input id="date" name="date" type="text"  class="form-control form-control-sm">
					<br>  
					<br> Amount:   
  					<input id="amount" name="amount" type="text"  class="form-control form-control-sm">
					<br>
					<br> Card No:   
  					<input id="cardNo" name="cardNo" type="text"  class="form-control form-control-sm">
					<br>  
					<br> Postal No:   
  					<input id="postalNo" name="postalNo" type="text"  class="form-control form-control-sm">
					<br>
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divPaymentGrid">
					<%
						Payment payObj = new Payment();
						out.print(payObj.readPayment());
					%>
				</div>
			</div>
		</div>
</div>

</body>
</html>