<%@ include file="../common/common.jsp" %>
<%  response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cahce");
%>
<html>
<head>
<title>Library Management System : Login</title>
<META NAME="description" CONTENT="">
<META NAME="keywords" CONTENT="">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
   A:link                  { text-decoration:  none; font-weight: bold; color: #660033}
   A:active                { text-decoration:  none; font-weight: bold; color: #660033}
   A:visited               { text-decoration:  none; font-weight: bold; color: #336699}
   A:hover                 { text-decoration:  underline; font-weight: bold; color: #FF9999}
.footerLink {  font-family: Arial, Helvetica, sans-serif; font-size: 9pt; font-weight: bold; color: #000099}
-->
</style>
</head>

<body bgcolor="#000000"  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="">
<a name="top"></a> 
<%@ include file="../common/header_common.jsp" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="white"> 
    <td width="180" align="left" valign="top">
    &nbsp; &nbsp; &nbsp; &nbsp;<a href="../mainmenu/mainmenu.jsp"><font color="black">Home</font></a>
	  <%@ include file = "../common/navi.jsp" %>
    </td>
        <TD width=1 bgColor=#000000><IMG height=1 alt="" 
      src="../images/common/1ptrans.gif" 
      width=1 border=0></TD>
    <td valign="top" width="100%" bgcolor="#f1f1f1" background="../images/common/second2.jpg"><center>
<%
	// Checking either had login or not, if not then display login menu
	if (login.isUserLogin()) { %>
	<% out.println("Hey... U already login as: <b>"); %> <jsp:getProperty name="login" property="userID" />
	<% out.println("</b><br>So... wanna to logout?"); %>
	<form name="frmLogout" action="/JavaLibrary/servlet/sjservlets.LogoutProcess" method="post"">
    	<input type="submit" name="Submit" value="Logout">
    </form>
<%	} else { %>
  <form name=login action="login_valid.jsp" method="post">
    <table width=225 border=0 cellpadding=3>
      <tr>
        <td colspan=2>
          
        </td>
      </tr>
      <tr>
      <br><br><br><br><br><br><br><br><br><br>
        <td><b>Username:<font color= "red">*</font></b></td>
        <td>
          <input type=text name=loginname>
        </td>
      </tr>
      <tr>
        <td><b>Password:<font color= "red">*</font></b></td>
        <td>
          <input type=password name=password>
        </td>
      </tr>
      <tr>
        <td colspan=2 align=center>
          <input type=submit value="Login" name="Submit">
        </td>
      </tr>
    </table>
  </form>
<%	} %>
</center></td>
  </tr>
</table>
<jsp:include page="../common/footer.jsp" flush="true" />
</body>
</html>