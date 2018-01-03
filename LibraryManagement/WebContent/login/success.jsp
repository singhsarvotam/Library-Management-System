<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>
</head>
<body bgcolor="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="">
<a name="top"></a> 
<%@ include file="../common/header_common.jsp" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr bgcolor="#f1f1f1"> 
    <td valign="top" width="100%" bgcolor="white" background = "../images/login/loginvalid.jpg">
      <br><div align="right">
        <a href="login.jsp"><b>LOGOUT</b></a></div>
        <center><br>
      <%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
You are not logged in<br/>
<%} else {
%>
<b><a name="top"></a><font size = "5" color = "black"> Welcome </font></b>&nbsp;<b><font size = "5" color = "ffffff"><%=session.getAttribute("userid")%></font></b>
</center>

<%
    }
%>


<br>
      <table cellspacing=5 cellpadding=5 width=620 border=0 height =500>
        <tbody> 
        <tr valign=top> 
          
          <td width=590> 
          <b><font size = "4" color ="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MENU</font></b>
            <ul type=square>
              <li><a href="../admin/checkin_books.jsp">Check-in Books</a> 
              <li><a href="../admin/checkout_books.jsp">Check-out Books</a> 
              <li><a href="../admin/add_borrower.jsp">Add Borrower Details</a> 
              <li><a href="../admin/calculate_fine.jsp">Calculate Fines</a>
            </ul>
          </td>
        </tr>
        
        </tbody>
      </table>
      <a name=Policy></a><img height=1 alt="" src="../images/misc/line_blu.gif" 
width="100%" border=0><br>
    </td>
  </tr>
</table>
<jsp:include page="../common/footer.jsp" flush="true" />

</body>
</html>