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

<body bgcolor="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="" >
<a name="top"></a> 
<%@ include file="../common/header_common.jsp" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr bgcolor="000000"> 
    <td width="180" align="left" valign="top" > 
	  	  <%@ include file = "../common/navi.jsp" %>
    </td>
        <TD width=1 bgColor=#999999><IMG height=1 alt="" 
      src="../images/common/1ptrans.gif" 
      width=1 border=0></TD>
    <td valign="top" width="100%" bgcolor="#f1f1f1" background="../images/common/timing.jpg">
      
<br>
      <table cellspacing=0 cellpadding=0 width=620 border=0>
        <tbody> 
        <tr valign=top> 
          <td align=right>&nbsp; </td>
        </tr>
        </tbody>
      </table>
      <table cellspacing=0 cellpadding=0 width=620 border=0>
        <tbody> 
        <tr valign=top> 
          <td width=30><img  
      src="../images/common/1ptrans.gif" width=30 border=0><br>
          </td>
          <td valign=top width=590><b class=subHdrBlu><u><font color="white">Library Opening Hours</font></u></b> 
          </td>
        </tr>
        
        </tbody>
      </table>
      <table cellspacing=5 cellpadding=5 width=620 border=0>
        <tbody> 
        <tr valign=top> 
          <td width=30><img height=8 alt="" 
      src="../images/common/1ptrans.gif" width=30 border=0></td>
          
        </tr>
        </tbody>
      </table>
	
<p> 
      <table cellspacing=0 cellpadding=0 width=620 border=0>
        <tbody> 
        <tr valign=top> 
          <td width=30><img  
      src="../images/common/1ptrans.gif" width=30 border=0><br>
          </td>
          <td valign=top width=590><b class=subHdrBlu><u><font color="white">Timings:</font></u> 
            </b></td>
        </tr>
        </tbody>
      </table>
      <table cellspacing=5 cellpadding=5 width=620 border=0>
        <tbody> 
        <tr valign=top> 
          <td width=30><img height=8 alt="" 
      src="../images/common/1ptrans.gif" width=30 border=0></td>
          <td width=590><b>
            <p> 
            <table cellspacing=0 cellpadding=0 border=0>
              <tbody> 
              <tr> 
                <td><font color="white">Mon - Thu</font></td>
                <td>&nbsp; : &nbsp;&nbsp;&nbsp;</td>
                <td><font color="white">Open 24 hrs</font></td>
              </tr>
              <tr> 
                <td><font color="white">Fri</font></td>
                <td>&nbsp; : &nbsp;&nbsp;&nbsp;</td>
                <td><font color="white">Open 24 hrs - Close at Midnight</font></td>
              </tr>
              <tr> 
                <td><font color="white">Sat</font></td>
                <td>&nbsp; : &nbsp;&nbsp;&nbsp;</td>
                <td><font color="white">8 am - Midnight</font></td>
              </tr>
              <tr> 
                <td><font color="white">Sun</font></td>
                <td>&nbsp; : &nbsp;&nbsp;&nbsp;</td>
                <td><font color="white">8 am - Remain Open</font></td>
              </tr>
              <tr> 
                <td colspan=3><b><br><br><font color="white">Closed on public holidays</font></b></td>
              </tr>
              </tbody>
            </table>
      
          </td>
        </tr>
        </tbody>
      </table>
      
    </td>
  </tr>
</table>
<jsp:include page="../common/footer.jsp" flush="true" />
</body>
</html>