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
<body bgcolor ="ffffff" background ="../images/bookone.png">
<%Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","s@hibl22"); 
		Statement st= con.createStatement(); 
		Statement st1= con.createStatement();
		Statement st2= con.createStatement();
		Statement st3= con.createStatement();
		String card = request.getParameter("card_no");
		String book_id = request.getParameter("isbn");
		String branch_id = request.getParameter("branch_id");
		String date_out = request.getParameter("date_out");
		
		int count =0;
		int remain_copy=0;
		
		ResultSet resultset=st.executeQuery("SELECT COUNT(loan_id) from book_loan WHERE card_no='" + card + "'");
		if(resultset.next())
		{	
			count = Integer.parseInt(resultset.getString(1));
		}
		
		ResultSet resultset1 = st.executeQuery("SELECT remaining_copy from book_copies where book_id='" + book_id + "'");
		if(resultset1.next())
		{	
			remain_copy = Integer.parseInt(resultset1.getString(1));
		}
		
		if(count == 3)  
	    {
	        out.println("You already have 3 book loans");
	    }	
		else if(remain_copy == 0)
		{
			out.println("Not enough copies to check out");
		}
		else
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			String date = df.format(new Date());
			String query2 = "INSERT into book_loans(isbn,branch_id,card_no, date_out, due_date) VALUES ('" + book_id + "','" + branch_id + "','" + card + "','" + date_out + "','" + date_out + "')";
			st2.executeUpdate(query2);
			//out.println("Kaam ho gya");
			remain_copy = remain_copy-1;
			String query3 = "UPDATE branch_copy SET remaining_copy ='" + remain_copy + "'WHERE book_id ='" + book_id + "'";
			st3.executeUpdate(query3);
		}
		%>
		  
		 <BR>

</head>
</body>
</html>