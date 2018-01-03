<%@ include file="/common/common.jsp" %>

<%@ include file="/common/dbconnect.jsp" %>


<jsp:setProperty name="form" property="formName" value="libraryitem" />
<jsp:setProperty name="form" property="formType" value="delete" />
<jsp:setProperty name="form" property="formURL" value="/JavaLibrary/search/search_libraryitem.jsp" />
<%  response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cahce");
// 	URLcurrent = "/JavaLibrary/search/search_libraryitem.jsp";
//	URLcurrent=response.encodeURL(URLcurrent);	
%>
<html>
<head>
<script src="js/submit.js"></script>
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

<%
	// Initialize the variables
	String queryOrderField= "";
	String queryOrderType= "";
	String queryParameter= "";

	String queryFilter= "";
	String querySearch= "";
	String queryCondition= "";
	String queryOrder= "";
	String query= "";

	String itemSubject = "";
	String filterOpt1 = "";
	String itemMediaType = "";
	String filterOpt2 = "";
	String itemLanguage = "";
	String itemTitle = "";
	String searchOpt1 = "";
	String itemCallNumber = "";
	String searchOpt2 = "";
	String itemKeyword = "";
	String searchOpt3 = "";
	String itemISBN = "";

	int recDisplayNum= 0;
	String recDisplayNumStr= "";

	ResultSet myResultSet=null;
	ResultSet rsCount=null;

	int pageCurrent=0;
	int pageOffSet=0;
    String errorMsg= "";

	
%>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="">
<a name="top"></a> 
<%@ include file="../common/header_common.jsp" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr><td width="40%" align="left" valign="top" bgcolor="white"><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="../mainmenu/mainmenu.jsp"><font color ="black">Home</font></a>
    <br>
    <%@ include file = "../common/navi.jsp" %>
    </td>
    <td width="90%" align="right" valign="top" bgcolor="#ffffff" background = "../images/searchit.jpg"><br>
      <center>
        <font size = "6"><b>Search Items<br>
        </b></font>
      </center>
<%
	queryParameter="&queryOrderField=" +queryOrderField +
		"&queryOrderType=" +queryOrderType +"&recDisplayNum=" +recDisplayNum;
%>
<br><br><br><br>
<form name="form1" method="post" action="../search/test.jsp">
          <center>ISBN:<input type = "text" name="isbn"></input></center>
              <br><center><select name=filterOpt1>
                  <option value="and" selected>And</option>
                  <option value="or">Or</option>
                </select></center><br>
         
          <center>Book Title:<input type = "text" name="title"></input></center>
     
              <br><center><select name=filterOpt2>
                  <option value="and" selected>And</option>
                  <option value="or">Or</option>
                </select></center><br>
                
          <center>Book Author:<input type = "text" name="author"></input></center>
              <br>
              
 <div align="center"> 
                  <input type="submit" name="Search" value="Search" onclick="submitForm">
                </div></form>
                <br><br>
      
                
</td>
</tr>

</table>
<table>
<tr><jsp:include page="../common/footer.jsp" flush="true" /></tr>
</table>
</body>
</html>