package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrodb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//read
	public String readPayment()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr>"
					+ "<th>Customer ID</th>"
					+ "<th>TEL No</th><th>date</th>"
					+ "<th>Amount</th>"
					+ "<th>Card No</th>"
					+ "<th>Postal No</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";
	 
			String query = "select * from payment"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String payId = Integer.toString(rs.getInt("payId")); 
				String cusId = rs.getString("cusId");
				String telNo = rs.getString("telNo");
				String date = rs.getString("date");
				String amount = rs.getString("amount");
				String cardNo = rs.getString("cardNo");
				String postalNo = rs.getString("postalNo");

				// Add into the HTML table 
				output += "<tr><td><input id='hidPaymentIDUpdate' "
						+ "name='hidPaymentIDUpdate' "
						+ "type='hidden' value='" + payId + "'>" 
						+ cusId + "</td>"; 
				output += "<td>" + telNo + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + postalNo + "</td>";

				// buttons     
//				output += "<td><input name='btnUpdate' type='button'"
//						+ "value='Update' class='btnUpdate btn btn-secondary'></td>"
//						+ "<td><form method='post' action='Payment.jsp'>"
//						+ "<input name='btnRemove' type='submit'"
//						+ "value='Remove' class='btnRemove btn btn-danger'>"
//						+ "<input name='hidPaymentIDDelete' type='hidden'"
//						+ "value='" + payId + "'>" + "</form></td></tr>"; 
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-payid='" + payId + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-payid='" + payId + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Payment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//Add details about the payment
	public String insertPayment(String cusId, String telNo, String date, String amount, String cardNo, String postalNo)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for inserting.";
			} 
	 
			// create a prepared statement 
			String query = " insert into payment (payId , cusId , telNo , date , amount, cardNo, postalNo)"+ " values (?, ?, ?, ?, ?, ?, ?)"; 
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, cusId);
			 preparedStmt.setString(3, telNo);
			 preparedStmt.setString(4, date);
			 preparedStmt.setString(5, amount);
			 preparedStmt.setString(6, cardNo);
			 preparedStmt.setString(7, postalNo);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newPayment = readPayment(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updatePayment(String payId, String cusId, String telNo, String date, String amount, String cardNo, String postalNo)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for updating.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE payment SET cusId=?,telNo=?,date=?,amount=?,cardNo=?,postalNo=? WHERE payId=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, cusId);
			preparedStmt.setString(2, telNo);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, amount);
			preparedStmt.setString(5, cardNo);
			preparedStmt.setString(6, postalNo);
			preparedStmt.setInt(7, Integer.parseInt(payId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPayment = readPayment();    
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Payment.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deletePayment(String payId)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "delete from payment where payId=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(payId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPayment = readPayment();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
