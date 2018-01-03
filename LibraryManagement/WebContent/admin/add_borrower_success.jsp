<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%String queryOrderField= "";
String queryOrderType= "";
String queryParameter= "";

String querySearch= "";
String queryCondition= "";
String queryOrder= ""; %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<body bgcolor ="ffffff" background ="../images/adds.jpg">
<%Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","s@hibl22"); 
		Statement st= con.createStatement(); 
		Statement st1= con.createStatement();
		Statement st2  = con.createStatement();
		String ssn = request.getParameter("ssn");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		ResultSet rs2 = st.executeQuery("SELECT ssn from borrower WHERE ssn ='" + ssn + "'");
		if(rs2.next())
		{
			out.println("You are already registered");
		}
		else
		{
		String card = null;
		ResultSet resultset=st.executeQuery("SELECT card_no from borrower ORDER by card_no DESC LIMIT 1;");
		while(resultset.next())
		{
		 card = resultset.getString(1);
		}
		String newCard = null;
		newCard = card.substring(2);
		int id=0;
		id = Integer.parseInt(newCard)+1;
		newCard = String.format("%06d", id);
		st1.executeUpdate("INSERT into borrower VALUES (\"ID" +newCard + "\",\"" + ssn + "\",\"" + fname + "\",\"" + lname + "\",\"" + address + "\",\"" + phone + "\",\"" + email + "\");");
		out.println("Thanks for Registering..!! Your card number is: " + "ID" + newCard);
		}
		%>
		  
		       <BR>
		</head>
</body>
</html>