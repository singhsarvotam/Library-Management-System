<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  <tr bgcolor="white"> 
    <td width="180" align="left" valign="top"> 
    &nbsp; &nbsp; &nbsp; &nbsp;<a href="../mainmenu/mainmenu.jsp"><font color="black">Home</font></a>
	  	  <%@ include file = "../common/navi.jsp" %>
    </td>
        <TD width=1 bgColor=#999999><IMG height=1 alt="" 
      src="../images/common/1ptrans.gif" 
      width=1 border=0></TD>
    <td valign="top" width="100%" bgcolor="#f1f1f1" background=../images/common/about.jpg> 
      <div align="center"> <br>
        <p><b><font size ="4"><u>About Me:</u></font></b></p>
      </div>
      <ul>
        <li><b><span 
            class=clsCatHeader>Name:&nbsp;</span></b><nobr></nobr> Sarvotam Pal Singh </li>
        <li><b><span 
            class=clsCatHeader>Sex:&nbsp;</span></b><nobr></nobr> Male</li>
        <li><b><span 
            class=clsCatHeader>DOB:&nbsp;</span></b><nobr></nobr> August 15, 1993 
        </li>
        <li><b><span 
            class=clsCatHeader>Education Level:&nbsp;</span></b> 
          <ul>
            <li>Graduation: <a href="http://www.utdallas.edu/" target="_blank">UTD</a> 
              (USA) - MS in Computer Science (expected graduate date: May 2017)</li>
            <li>Undergraduation:  <a href="http://www.ptu.ac.in/" target="_blank">Punjab Technical University</a> 
              (India) - B.Tech in Computer Science (2011-2015)</li>
          </ul>
        </li>
        <li><b><span 
            class=clsCatHeader>Programming Language:&nbsp;</span></b> J2EE (JSP, 
          Servlet, Bean, C, C++, JAVA.</li>
          <li><b><span 
            class=clsCatHeader>OS:&nbsp;</span></b> Ubuntu, Windows XP, Windows7, Windows10, MacOS.</li>
            <li><b><span 
            class=clsCatHeader>Databases:&nbsp;</span></b> MySQL, Microsoft Access</li>
          <li><b><span 
            class=clsCatHeader>Other Tools: &nbsp;</span></b><nobr></nobr> Wireshark Packet Analyzer, PuTTY, MySQL Workbench, Rhapsody</li>
        <li><b><span 
            class=clsCatHeader>Hobbies and Interests:&nbsp;</span></b><nobr></nobr> Software 
          Programming, Dancing, Anchoring, Movies, Music, Sports. </li>
      </ul>
    </td>
  </tr>
</table>
<jsp:include page="../common/footer.jsp" flush="true" />
</body>
</html>