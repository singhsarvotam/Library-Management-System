<%@ include file="../common/common.jsp" %>
<%  response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cahce");
%>
<html>
<head>
<title>Calculate Fines</title>
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
  <tr bgcolor="000000"> 
    <td width="180" align="left" valign="top">
    &nbsp; &nbsp; &nbsp; &nbsp;<a href="../mainmenu/mainmenu.jsp"><font color="ffffff">Home</font></a>
	  <%@ include file = "../common/navi.jsp" %>
    </td>
        <TD width=1 bgColor=#000000><IMG height=1 alt="" 
      src="../images/common/1ptrans.gif" 
      width=1 border=0></TD>
    <td valign="top" width="100%" bgcolor="#f1f1f1" background="../images/login/calculatefine.jpg"><center>

  <form name=fines action="check_fine.jsp" method="post">
    <table width=225 border=0 cellpadding=3>
      <tr>
        <td colspan=2>
          
        </td>
      </tr>
      <tr>
      <br><br><br><br>
        <td><b>Card Number:<font color= "red">*</font></b></td>
        <td>
          <input type=text name="card_no">
        </td>
      </tr>
      <tr></tr>
      <tr>
        <td colspan=2 align="center">
          <br><input type=submit value="Check Fine" name="fine">
        </td>
        
      </tr>
</table>
  </form>

</center></td>
  </tr>
</table>
<jsp:include page="../common/footer.jsp" flush="true" />
</body>
</html>