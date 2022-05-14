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

<!--  link font-->
<head>
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>

<!--add the font on body  -->
<style>
body {
  font-family: "Roboto", sans-serif;
}
</style>

<title>Payment Management</title>
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col"> 
			<h1 class="font-weight-bold">PAYMENT DETAILS</h1>
				<form class="row g-3" id="formPayment" name="formPayment" method="post" action="Payment.jsp">  
					<div class="col-md-6">
						<label class="form-label">Customer ID:</label>  
	 	 				<input id="cusId" name="cusId" type="text"  class="form-control form-control-sm" placeholder="Enter Customer ID" required>
					</div>
					
					<div class="col-md-6">
						<label class="form-label">Telephone No:</label>
						<input id="telNo" name="telNo" type="tel" class="form-control form-control-sm" placeholder="Enter Telephone No" required>
					</div>    
  					
  					<div class="col-md-6">
	  					<label class="form-label">Date:</label>
	  					<input id="date" name="date" type="text" class="form-control form-control-sm" placeholder="Enter Date" required>
  					</div>
					 
					<div class="col-md-6">
						<label class="form-label">Amount:</label>
					  	<input id="amount" name="amount" type="text" class="form-control form-control-sm" placeholder="Enter Amount" required>
					</div>    
					
					<div class="col-md-6">
						<label class="form-label">Card No:</label>
						<input id="cardNo" name="cardNo" type="text" class="form-control form-control-sm" placeholder="Enter Card No" required>
					</div>   
  					
					<div class="col-md-6">
						<label class="form-label">Postal No:</label>
						<input id="postalNo" name="postalNo" type="text" class="form-control form-control-sm" placeholder="Enter Postal Code" required>
					</div>
					
  					<div class="col-12">
	  					<input id="btnSave" name="btnSave" type="button" value="Submit Payment" class="btn btn-primary" required>  
						<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
  					</div>
					 
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