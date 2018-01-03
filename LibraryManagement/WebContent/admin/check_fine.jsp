<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<body bgcolor ="ffffff" background ="../images/book.jpg">
<%Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraray_management","root","shyambaba@1986"); 
		Statement st= con.createStatement();
		String card = request.getParameter("card_no");
		String query = "SELECT DISTINCT bl.loan_id, br.card_no, br.fname, br.lname, br.email, br.phone, bl.branch_id, DATEDIFF(bl.date_in, bl.due_date) as Diff FROM borrowers br, book_loans bl WHERE bl.card_no= br.card_no AND bl.card_no='" + card + "'";
		ResultSet resultset=st.executeQuery(query);
	while(resultset.next()) {
    %>
  <table width="100%" border="0.5" cellspacing="1" cellpadding="1" align="center">
          <tr> 
            <th width="5%" bgcolor="#D3DCE3"><b>Loan ID</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&> 
              
              </a> </th>
              <th width="5%" bgcolor="#D3DCE3"><b>Card No</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&> 
              
              </a> </th>
              <th width="15%" bgcolor="#D3DCE3"><b>First Name</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&> 
              
              </a> </th>
              <th width="15%" bgcolor="#D3DCE3"><b>Last Name</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&> 
              
              </a> </th>
              <th width="25%" bgcolor="#D3DCE3"><b>Email</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&> 
              
              </a> </th>
              <th width="15%" bgcolor="#D3DCE3"><b>Phone</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&> 
              
              </a> </th>
              <th width="5%" bgcolor="#D3DCE3"><b>Branch ID</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&> 
              
              </a> </th>
              <th width="5%" bgcolor="#D3DCE3"><b>Diff</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&> 
              
              </a> </th>
           </TR>
           <TR>
               <TD> <b><%= resultset.getString(1) %> </b></TD>
           	   <TD> <b><%= resultset.getString(2) %> </b></TD>
           	   <TD> <b><%= resultset.getString(3) %> </b></TD>
           	   <TD> <b><%= resultset.getString(4) %> </b></TD>
           	   <TD> <b><%= resultset.getString(5) %> </b></TD>
           	   <TD> <b><%= resultset.getString(6) %> </b></TD>
           	   <TD> <b><%= resultset.getString(7) %> </b></TD>
           	   <TD> <b><%= resultset.getString(8) %> </b></TD>
           	   
           </TR>
       </TABLE>
       <BR>
       <%} %>
   </head>

</body>
</html>