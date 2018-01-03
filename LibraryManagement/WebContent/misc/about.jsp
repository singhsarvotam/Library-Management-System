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
        <TD width=1 bgColor=#999999><IMG height=1 alt="" 
      src="../images/common/1ptrans.gif" 
      width=1 border=0></TD>
    <td valign="top" width="100%" bgcolor="#f1f1f1" background="../images/common/about_proj.jpg">
      <center>
        <b><u><font size = "5"><br>About Project</font></u></b> 
      </center>
      <p><br>
      	
        <b>&nbsp;&nbsp;<u> Purpose </u></b><br><br>
        &nbsp;&nbsp;This is a simple desktop application that is made using .jsp <br>&nbsp;&nbsp;This application interfaces
        with a back end SQL database implementing Library Management System.<br>
        &nbsp;&nbsp;Users of the system are specifically assumed to be librarians.  
        <br><br>
      </p>
      <p><b>&nbsp;&nbsp;<u>Functionalities: </u></b> </p>
      <p>
		 &nbsp;&nbsp;<u><i><b>1) Graphical User Interface (GUI) and Overall Design</b></i></u><br>
			&nbsp;&nbsp;All interface with the Library (queries, updates, deletes, etc.) is done from a graphical user interface of our 
			&nbsp;&nbsp;original design. Our GUI application will interface with the Library database via an appropriate <br>
			&nbsp;&nbsp;MySQL connector. <br>
			&nbsp;&nbsp;Initial database creation and population is done using MySQL workbench.<br><br>
			
		 &nbsp;&nbsp;<u><i><b>2) Book Search and Availability</b></i></u><br>
			&nbsp;&nbsp;Using this GUI, a librarian and a user is able to search for a book,given any combination of ISBN, title,<br> 
			&nbsp;&nbsp;and/ or Author(s).<br><br>
			
		 &nbsp;&nbsp;<u><i><b>3) Book Loans</b></i></u><br><br>
			&nbsp;&nbsp;<u><i>Checking Out Books:</i></u><br><font size="2">
			&nbsp;&nbsp;a) Using our GUI, a librarian is able to check out a book, given the combination of BOOK_COPIES(Isbn, branch_id) and <br>
			  &nbsp;&nbsp; BORROWER(Card_no), i.e. create a new tuple in BOOK_LOANS.<br>
			  &nbsp;&nbsp; The date_out should be todays date. The due_date has to be 14 days after the date_out.<br>
			&nbsp;&nbsp;b) Each BORROWER is permitted a maximum of 3 BOOK_LOANS. If a BORROWER already has 3 BOOK_LOANS, then the <br>
			&nbsp;&nbsp;   checkout fails and returns a useful error message.<br>
			&nbsp;&nbsp;c)If the number of BOOK_LOANS for a given book at a branch already equals the No_of_copies, <br>
			  &nbsp;&nbsp;then the checkout fails and returns a useful error message.</font><<br><br>

			&nbsp;&nbsp;<u><i>Checking In Books:</i></u><br><font size="2">
			&nbsp;&nbsp;Using our GUI, a librarian is able to check in a book. He/She is also able to locate BOOK_LOANS tuples by searching on <br>
			&nbsp;&nbsp;any of book_id, Card_no, and/or any part of BORROWER name.<br> 
			  &nbsp;&nbsp;Once located, provides a way of selecting one of potentially multiple results and a menu item to check in. </font><br><br>
			  
		 &nbsp;&nbsp;<u><i><b>4) Borrower Management</b></i></u><br>
			&nbsp;&nbsp;a) Using our GUI, a librarian is able to create new borrowers in the system.<br>
			&nbsp;&nbsp;b) All name, SSN, and address attributes are required to create a new account(i.e. value must be not null).<br>
			&nbsp;&nbsp;c) Borrowers are allowed to possess exactly one library card.<br>
			&nbsp;&nbsp;If a new borrower is attempted with the same SSN, then our system rejects and returns a useful error message.<br><br>
		&nbsp;&nbsp;<u><i><b>5) Fines</b></i></u><br><br>
		
		&nbsp;&nbsp;a) Fine_amt attribute is a dollar amount that should have two decimal places.<br>
		&nbsp;&nbsp;b) Paid attribute is a boolean value (or integer 0/1) that idicates whether a fine has been paid.<br>
		&nbsp;&nbsp;c) Fines are assessed at a rate of $0.25/day (twenty-five cents per day).<br>
		&nbsp;&nbsp;d) There are two scenarios for late books<br>
		&nbsp;&nbsp;&nbsp;1. Late books that have been returned — the fine will be [(the difference in days between the due_date and date_in) * $0.25].<br>
		&nbsp;&nbsp;&nbsp;2. Late book that are still out — the estimated fine will be [(the difference between the due_date and TODAY) * $0.25].<br>
		&nbsp;&nbsp;e) If a row already exists in FINES for a particular late BOOK_LOANS record, then<br>
		&nbsp;&nbsp;&nbsp;- If paid == FALSE, do not create a new row, only update the fine_amt if different than current value.<br>
		&nbsp;&nbsp;&nbsp;- If paid == TRUE, do nothing.<br>
		&nbsp;&nbsp;f) Provide a mechanism for librarians to enter payment of fines (i.e. to update a FINES record where paid == TRUE)<br>
		&nbsp;&nbsp;&nbsp;- Do not allow payment of a fine for books that are not yet returned.<br>
		&nbsp;&nbsp;&nbsp;- Display of Fines should be grouped by card_no. i.e. SUM the fine_amt for each Borrower.<br>
		&nbsp;&nbsp;&nbsp;- Display of Fines should provide a mechanism to filter out previously paid fines (either by default or choice).<br>
    </td>
  </tr>
</table>
<jsp:include page="../common/footer.jsp" flush="true" />
</body>
</html>