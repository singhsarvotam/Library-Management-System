<%@ include file="../common/common.jsp" %>
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

<body bgcolor="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="">
<a name="top"></a> 
<%@ include file="../common/header_common.jsp" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="000000"> 
    <td width="180" align="left" valign="top"> 
	  	  <%@ include file = "../common/navi.jsp" %>
    </td>
        <TD width=1 bgColor=#000000><IMG height=1 alt="" 
      src="../images/common/1ptrans.gif" 
      width=1 border=0></TD>
    <td valign="top" width="100%" bgcolor="#f1f1f1" background = "../images/common/question.jpg">
      <center><br>
        <b><a name="top"></a><font size = "6" color = "ffffff">Frequently Asked Questions (FAQs)</font> </b> </br>
      </center>
      
      </p><table cellspacing=5 cellpadding=5 width=620 border=0>
        <tbody> 
        <tr valign=top> 
          <td width=30><img height=8 alt="" 
      src="../images/common/1ptrans.gif" width=30 border=0></td>
          <td width=590> 
            <ul type=square>
              
              <li><a href="faq_library_open_hours.jsp"><font size = "4" color = "ffffff">Library open hours</font></a> 
               
            </ul>
          </td>
        </tr>
        </tbody>
      </table>
      <br>
    </td>
  </tr>
</table>
<jsp:include page="../common/footer.jsp" flush="true" />
</body>
</html>