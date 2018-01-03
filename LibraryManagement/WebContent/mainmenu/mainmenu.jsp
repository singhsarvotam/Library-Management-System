<%@ include file="../common/common.jsp" %>
<%@ include file="../common/dbconnect.jsp" %>
<html>
<head>
<title>Library Management System</title>
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

<body bgcolor="#ffffff" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="">
<a name="top"></a> 
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr > 
   <td width="10%" align="left" valign="top" bgcolor="white" >
    <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="../search/search_libraryitem.jsp"><font color="black">Search</font></a>
    <img src ="../images/common/Search.gif"></img>
    <br>
 <%@ include file = "../common/navi.jsp" %>
    </td>
    <td width="75%" align="left" valign="top" bgcolor="#000000" background = "../images/mainmenu/download (1).jpg" width=80% height =100%></img></td>
   </tr>
</table>
<table>
<tr><jsp:include page="../common/footer.jsp" flush="true" /></tr>
</table>
</body>
</html>