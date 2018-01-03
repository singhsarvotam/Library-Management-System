<%@ include file="../search/search_libraryitem.jsp" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name='userList' action='/JavaLibrary/servlet/sjservlets.FormProcess'>

        <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
          <tr> 
            <th width="35%" bgcolor="#D3DCE3"><b>Title*</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=ASC&<%= queryParameter%>> 
              <% if(queryOrderField.equals("itemTitle") && queryOrderType.equals("ASC")) 
					out.println("<img src='../images/common/icon_sort_up_on.gif' width='20' height='7' border='0'>");
					else
					out.println("<img src='../images/common/icon_sort_up_off.gif' width='20' height='7' border='0'>");
				%>
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemTitle&queryOrderType=DESC&<%= queryParameter%>> 
              <% if(queryOrderField.equals("itemTitle") && queryOrderType.equals("DESC")) 
					out.println("<img src='../images/common/icon_sort_down_on.gif' width='20' height='7' border='0'>");
					else
					out.println("<img src='../images/common/icon_sort_down_off.gif' width='20' height='7' border='0'>");
				%>
              </a> </th>
            <th width="25%" bgcolor="#D3DCE3"><b>Author(s)</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemSubject&queryOrderType=ASC&<%= queryParameter%>> 
              <% if(queryOrderField.equals("itemSubject") && queryOrderType.equals("ASC")) 
					out.println("<img src='../images/common/icon_sort_up_on.gif' width='20' height='7' border='0'>");
					else
					out.println("<img src='../images/common/icon_sort_up_off.gif' width='20' height='7' border='0'>");
				%>
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemSubject&queryOrderType=DESC&<%= queryParameter%>> 
              <% if(queryOrderField.equals("itemSubject") && queryOrderType.equals("DESC")) 
					out.println("<img src='../images/common/icon_sort_down_on.gif' width='20' height='7' border='0'>");
					else
					out.println("<img src='../images/common/icon_sort_down_off.gif' width='20' height='7' border='0'>");
				%>
              </a> </th>
            <th width="15%" bgcolor="#D3DCE3"><b>ISBN</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemMediaType&queryOrderType=ASC&<%= queryParameter%>> 
              <% if(queryOrderField.equals("itemMediaType") && queryOrderType.equals("ASC")) 
					out.println("<img src='../images/common/icon_sort_up_on.gif' width='20' height='7' border='0'>");
					else
					out.println("<img src='../images/common/icon_sort_up_off.gif' width='20' height='7' border='0'>");
				%>
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemMediaType&queryOrderType=DESC&<%= queryParameter%>> 
              <% if(queryOrderField.equals("itemMediaType") && queryOrderType.equals("DESC")) 
					out.println("<img src='../images/common/icon_sort_down_on.gif' width='20' height='7' border='0'>");
					else
					out.println("<img src='../images/common/icon_sort_down_off.gif' width='20' height='7' border='0'>");
				%>
              </a> </th>
            <th width="20%" bgcolor="#D3DCE3"><b>Lang</b> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemLanguage&queryOrderType=ASC&<%= queryParameter%>> 
              <% if(queryOrderField.equals("itemLanguage") && queryOrderType.equals("ASC")) 
					out.println("<img src='../images/common/icon_sort_up_on.gif' width='20' height='7' border='0'>");
					else
					out.println("<img src='../images/common/icon_sort_up_off.gif' width='20' height='7' border='0'>");
				%>
              </a> <a href=../search/search_libraryitem.jsp?&queryOrderField=itemLanguage&queryOrderType=DESC&<%= queryParameter%>> 
              <% if(queryOrderField.equals("itemLanguage") && queryOrderType.equals("DESC")) 
					out.println("<img src='../images/common/icon_sort_down_on.gif' width='20' height='7' border='0'>");
					else
					out.println("<img src='../images/common/icon_sort_down_off.gif' width='20' height='7' border='0'>");
				%>
              </a> </th>
            
          </tr>
</body>
</html>