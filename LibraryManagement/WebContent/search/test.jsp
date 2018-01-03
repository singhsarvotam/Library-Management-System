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
<body bgcolor ="ffffff" background ="../images/searchit.jpg">
<%Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","s@hibl22"); 
		Statement st= con.createStatement(); 
		String isbn = request.getParameter("isbn");
		String title=request.getParameter("title");
		String author = request.getParameter("author");
		String queryFilter1 = request.getParameter("filterOpt1");
		String queryFilter2 = request.getParameter("filterOpt2");
		
		if(queryFilter1 == "And" && queryFilter2 == "And");
		ResultSet resultset=st.executeQuery("select b.isbn, b.title, b.author_name, lb.branch_id, lb.branch_name, bc.no_of_copy, bc.remaining_copy from book b, library_branch lb, book_copies bc where bc.branch_id = lb.branch_id AND bc.book_id = b.isbn AND b.isbn='" + isbn + "' AND b.title LIKE'" + "%"+title+"%" + "' AND b.author_name LIKE'" + author + "'");
		
		
		
		while(resultset.next()) {
    %>
  
  <table width="100%" border="0.5" cellspacing="1" cellpadding="1" align="center">
          <tr> 
            <th width="10%" bgcolor="#D3DCE3"><b>ISBN</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&<%= queryParameter%>> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&<%= queryParameter%>> 
              
              </a> </th>
              <th width="25%" bgcolor="#D3DCE3"><b>Title</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&<%= queryParameter%>> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&<%= queryParameter%>> 
              
              </a> </th>
              <th width="10%" bgcolor="#D3DCE3"><b>Author</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&<%= queryParameter%>> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&<%= queryParameter%>> 
              
              </a> </th>
              <th width="5%" bgcolor="#D3DCE3"><b>Branch ID</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&<%= queryParameter%>> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&<%= queryParameter%>> 
              
              </a> </th>
              <th width="10%" bgcolor="#D3DCE3"><b>Branch Name</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&<%= queryParameter%>> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&<%= queryParameter%>> 
              
              </a> </th>
              <th width="5%" bgcolor="#D3DCE3"><b>No of copies</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&<%= queryParameter%>> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&<%= queryParameter%>> 
              
              </a> </th>
              <th width="5%" bgcolor="#D3DCE3"><b>remaining copies</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&<%= queryParameter%>> 
              
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&<%= queryParameter%>> 
              
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
           </TR>
       </TABLE>
       <BR>
       <%} %>
</head>

</body>
</html>