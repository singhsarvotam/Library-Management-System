<% 
//	String currentURI=(String) session.getAttribute("currentURI");
//	if(currentURI==null) {
		String currentURI = request.getRequestURI();
		session.setAttribute("currentURI", currentURI);
//	}
%>
<SCRIPT LANGUAGE="JAVASCRIPT">
<!--  hide	

function customize(form) {    
	var address = document.helpForm.url.value;   
	var op_tool  = (document.helpForm.tool.value== "true")  ? 1 : 0;   
	var op_loc_box  = (document.helpForm.loc_box.value == "true")  ? 1 : 0; 
	var op_dir  = (document.helpForm.dir.value == "true")  ? 1 : 0;    
	var op_stat  = (document.helpForm.stat.value == "true")  ? 1 : 0;    
	var op_menu  = (document.helpForm.menu.value == "true")  ? 1 : 0;    
	var op_scroll  = (document.helpForm.scroll.value == "true")  ? 1 : 0;  
	var op_resize  = (document.helpForm.resize.value == "true")  ? 1 : 0;    

	var op_wid  = document.helpForm.wid.value;   
	var op_heigh = document.helpForm.heigh.value;                 

	var option = "toolbar=" + op_tool + ",location=" + op_loc_box + ",directories=" 
		+ op_dir + ",status=" + op_stat + ",menubar=" + op_menu + ",scrollbars="  
		+ op_scroll + ",resizable="  + op_resize + ",width=" + op_wid + ",height=" 
		+ op_heigh;

	var new_win = window.open(address, "NewWindow", option );

}



function clear(form){ 

	document.form1.wid.value=""; 

	document.form1.heigh.value="";

}

// done hiding -->

</SCRIPT>

<table class=flyoutMenu 
      style="BORDER-TOP-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
      cellspacing=0 cellpadding=2 width=180 border=0 background = "../images/common/navi.jpg">
  <tbody> 
  <tr> 
    <td> 
      <table cellspacing=0 cellpadding=0 width=200 border=0 >
        <tbody> <br><br>
        <tr> 
          <td class=flyoutHeading>&nbsp; &nbsp;<a href="../login/login.jsp"><font color="black">Librarian Login</font></a></td>
        </tr>
        <tr> 
          <td class=flyoutHeading>&nbsp; &nbsp;<a href="../misc/about.jsp"><font color="black">About&nbsp;Project</font></a></td>
        </tr>
        <tr> 
          <td class=flyoutHeading>&nbsp; &nbsp;<a href="../misc/faq.jsp"><font color="black">FAQ</font></a></td>
        </tr>
        </tbody> 
      </table>
      
      <table cellspacing=0 cellpadding=0 width=176 border=0>
        <tbody> 
        <tr> 
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        &nbsp;&nbsp;&nbsp;<button type="button" name="back" onclick="history.back()">Return</button>
      	<br><br><br>
          <td class=flyoutHeading>&nbsp; <a href="../about/aboutme.jsp"><font color="black">About&nbsp;Me</font></a></td>
          <td class=flyoutHeading>&nbsp; &nbsp;&nbsp;<a href="../about/contactme.jsp"><font color="black">Contact&nbsp;Me</font></a></td>
        </tr>
        </tbody></table>
    </td>
  </tr>
  </tbody> 
</table>

