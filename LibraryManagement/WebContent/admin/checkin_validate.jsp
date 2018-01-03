<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*, java.text.SimpleDateFormat, java.util.Date"%>
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
<body bgcolor ="ffffff" background ="../images/booktwo.png">
<%Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","s@hibl22"); 
		Statement st= con.createStatement(); 
		Statement st1= con.createStatement();
		Statement st2= con.createStatement();
		Statement st3= con.createStatement();
		String card = request.getParameter("card_no");
		String book_id = request.getParameter("isbn");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String date_in = request.getParameter("date_in");
		
		int remain_copy=0;
		ResultSet resultset=st.executeQuery("SELECT loan_id from book_loan WHERE card_no='" + card + "'AND isbn = '" + book_id + "'");
		if(resultset.next())
		{	
			String loan_id  = resultset.getString(1);
			String query1 = "UPDATE book_loan SET date_in ='" + date_in + "'WHERE book_id ='" + book_id + "' AND loan_id = '" + loan_id + "'";
			remain_copy = remain_copy+1;
			String query2 = "UPDATE book_copies SET remaining_copy ='" + remain_copy + "'WHERE book_id ='" + book_id + "'";
			st2.executeUpdate(query2);
			out.println("Thanyou, Your book has been checked in.");
		}
		%>
		  
		 <BR>

</head>
</body>
</html>