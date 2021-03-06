package sjservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/** A main and most important servlet that use to 
 *  process all the HTML form.
 *  <P>
 *  &copy; 2002 Song Jing; may be freely used or adapted.
 */

public class FormProcess extends ConnectionPoolServlet {
	
	private boolean debug = false;
	private boolean debug_user_delete = false;
	private boolean printParameter = false; 
	private boolean printParameter2 = false;

  	public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
  			throws ServletException, IOException {
  				
		HttpSession session = request.getSession(true);		
		PrintWriter out = response.getWriter();
		
		Form form = (Form)session.getAttribute("form");
		Login login=(Login)session.getAttribute("login");
		User user = (User)session.getAttribute("user");
		User staff = (User)session.getAttribute("staff");
		LibraryItem libraryitem = (LibraryItem)session.getAttribute("libraryitem");
		DBProcess dbprocess = (DBProcess)session.getAttribute("dbprocess");
		BorrowRec borrowrec = (BorrowRec)session.getAttribute("borrowrec");
		ReserveRec reserverec = (ReserveRec)session.getAttribute("reserverec");
		dbprocess.resetVariable();
		session.setAttribute("dbprocess", dbprocess); 
		String formName = form.getFormName();
		String formType = form.getFormType();
		String query="";
		ResultSet myResultSet=null;

    	if(debug){
			response.setContentType("text/html");
    		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
    		String title = "Connection Pool Test";
    		out.println(ServletUtilities.headWithTitle(title) +
        	 	"<BODY BGCOLOR=\"#FDF5E6\">\n" +
               	"<CENTER>\n"); 
 		}
 		
 		if (printParameter2) {
 			gotoPage("/servlet/sjservlets.ShowParameters", request, response);
 		}
 	
 		if( (debug) || (printParameter) ){ 
 			out.println(		              	
        		"<TABLE BORDER=1 ALIGN=CENTER>\n" +
            	"<TR BGCOLOR=\"#FFAD00\">\n" +
            	"<TH>Parameter Name<TH>Parameter Value(s)");
            Enumeration paramNames = request.getParameterNames();
    		while(paramNames.hasMoreElements()) {
      			String paramName = (String)paramNames.nextElement();
      			out.print("<TR><TD>" + paramName + "\n<TD>");
      			String[] paramValues = request.getParameterValues(paramName);
      			if (paramValues.length == 1) {
        			String paramValue = paramValues[0];
        			if (paramValue.length() == 0)
          				out.println("<I>No Value</I>");
        			else
          				out.println(paramValue);
      			} else {
        			out.println("<UL>");
        			for(int i=0; i<paramValues.length; i++) {
          				out.println("<LI>" + paramValues[i]);
        			}
        			out.println("</UL>");
      			}
    		}
    		out.println("</TABLE><BR>");
    	}   	
        
        if(debug){      	
        	out.println("Form Session Variables<br>");
        	out.println("formName: " + formName +"<br>");
        	out.println("formType: " + formType +"<br>");
			out.println("<HR>");
			out.println("Now Having <b>" +formType +"</b> Operatioin for <b>" +formName +"</b><BR><BR>");	
		}
		
		char formOperationCode;
		if (formType.equals("add")) {
			formOperationCode = 'a';
		} else if (formType.equals("edit")){
			formOperationCode = 'e';
		} else if (formType.equals("delete")){
			// Do something for delete existing user
			formOperationCode = 'd';
		}else if (formType.equals("borrow")) {
			formOperationCode = 'b';
		}else if (formType.equals("return")) {
			formOperationCode = 'r';
		}else{
			return;
		}			

		if( formName.equals("user")){		
			String orgUserID = user.getUserID();
			String orgUserPassword = user.getUserPassword();		
			switch(formOperationCode) {
      		case 'a':	      			
				user.setPropertyFromRequestParameter(request);
      			if(debug) out.println("user+a");
      			if(checkDuplicateUserID(user.getUserID())){
      				dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("user");
				    dbprocess.setSQLQuery(query);
      				dbprocess.setProcessResult("<font color='RED'><B>Record ADD Error</B></font>");
      				dbprocess.setProcessMsg("<font color='RED'>Duplicate User ID: " +user.getUserID() +"</font>");
					dbprocess.setProcessAction("<a href='" +form.getFormURL() +"'>" +
      						"<img src='/JavaLibrary/images/common/back_arrow.gif' width='38' height='21' border='0'>BACK</a>");
					dbprocess.setProcessComplete(false);
					//user.setUserID(orgUserID);
				}else{
      				query = "INSERT INTO user (" +
      					"userID, userPassword, userType, userFirstName, userLastName, userGender, userEmail, " +
      					"userICNumber, userAddress1, userAddress2, userCity, userState, userPostCode, userCountry, " +
      					"userOccupation, userContactNumber, userTotQuota, userQuotaAvailable, userTotReservation, userReserveAvailable) VALUES (" +
      					"'" +user.getUserID() +"', PASSWORD('" +user.getUserPassword() +"'), '" +user.getUserType() +
      					"', '" +user.getUserFirstName() +"', '" +user.getUserLastName() +"', '" +user.getUserGender() +
      					"', '" +user.getUserEmail() +"', '" +user.getUserICNumber() +"', '" +user.getUserAddress1() +
      					"', '" +user.getUserAddress2() +"', '" +user.getUserCity() +"', '" +user.getUserState() +
      					"', '" +user.getUserPostCode() +"', '" +user.getUserCountry() +"', '" +user.getUserOccupation() +
      					"', '" +user.getUserContactNumber() +"', " +user.getUserTotQuota() +", " +user.getUserQuotaAvailable() +
      					", " +user.getUserTotReservation() +", " +user.getUserReserveAvailable() +")";
      				myResultSet = formDatabaseOperation(query);	 
					
					user.resetVariable();
					session.setAttribute("user", user);				
					dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("user");
      				dbprocess.setSQLQuery(query);
      				dbprocess.setProcessResult("<B>User Record success added</B>");
      				dbprocess.setProcessMsg("");
					dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> " +
      							"<a href='/JavaLibrary/user/user_add.jsp'>[Add another user record]</a>");    	
      				dbprocess.setProcessComplete(true);
      			}	      			
      			break;
      		case 'e': 
				user.setPropertyFromRequestParameter(request);
      			if(debug) out.println("user+e");         			
      			if( (!(orgUserID.equals(user.getUserID()))) && (checkDuplicateUserID(user.getUserID()))){
      				dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("user");
      				dbprocess.setSQLQuery(query);
      				dbprocess.setProcessResult("<font color='RED'><B>Record EDIT Error</B></font>");
      				dbprocess.setProcessMsg("<font color='RED'>Duplicate User ID: " +user.getUserID() +"</font>");
					dbprocess.setProcessAction("<a href='" +form.getFormURL() +"'>" +
      						"<img src='/JavaLibrary/images/common/back_arrow.gif' width='38' height='21' border='0'>BACK</a>");
					dbprocess.setProcessComplete(false);
					user.setUserID(orgUserID);
      			}else{ 
      				String setPasswordCondition;
      				if (orgUserPassword.equals(user.getUserPassword()))
      					setPasswordCondition = "', userPassword='" +user.getUserPassword() +"'";
      				else
      					setPasswordCondition = "', userPassword=PASSWORD('" +user.getUserPassword() +"')";
       				
       				query = "UPDATE user SET userID='" +user.getUserID() +setPasswordCondition +
      					", userType='" +user.getUserType() +"', userFirstName='" +user.getUserFirstName() +
      					"', userLastName='" +user.getUserLastName() +"', userGender='" +user.getUserGender() +
      					"', userEmail='" +user.getUserEmail() +"', userICNumber='" +user.getUserICNumber() +
      					"', userAddress1='" +user.getUserAddress1() +"', userAddress2='" +user.getUserAddress2() +
      					"', userCity='" +user.getUserCity() +"', userState='" +user.getUserState() +
      					"', userPostCode='" +user.getUserPostCode() +"', userCountry='" +user.getUserCountry() +
      					"', userOccupation='" +user.getUserOccupation() + "', userContactNumber='" +user.getUserContactNumber() +
      					"', userTotQuota=" +user.getUserTotQuota() +", userQuotaAvailable=" +user.getUserQuotaAvailable() +
      					",  userTotReservation=" +user.getUserTotReservation() +", userReserveAvailable=" +user.getUserReserveAvailable() +
      					" WHERE  userRecNumber ='" +user.getUserRecNumber() +"'";
      				myResultSet = formDatabaseOperation(query);	       		
      				
      				// Get back the encrypted user password by perform another query
      				query = "SELECT * from user where userID='" +user.getUserID() +"'";
      				myResultSet = formDatabaseOperation(query);	
      				String newEncyptUserPassword=null;
    				if (myResultSet != null){
						try{
							while(myResultSet.next()) {
								newEncyptUserPassword = myResultSet.getString("userPassword");
							}
						} catch(SQLException sqle) {
							System.err.println("Error connecting: " +sqle);		
						}
					} 
					user.setUserPassword(newEncyptUserPassword);	

					// Display the procesing messager
      				dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("user");
      				dbprocess.setSQLQuery(query);
      				dbprocess.setProcessResult("<B>Record Update Success</B>");
      				dbprocess.setProcessMsg("");
      				dbprocess.setProcessComplete(true);
     				
      				// update the staff bean if staff edit their own record from my account
      				if( (login.getUserType().equals("admin")) || (login.getUserType().equals("librarian"))){
      					if (staff.getUserRecNumber() == (user.getUserRecNumber())){
      						//if staff update their own record
      						staff.setPropertyFromOtherUserBean(user);
      						session.setAttribute("staff", staff);
      						dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a>");
      					}else{
						// if staff update other people record
   							dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> " +
      							"<a href='/JavaLibrary/admin/user_list.jsp'>[Back to User List]</a>");
      					}
					}else{ 			
						// if user update their own record
      					dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a>");
      				}      				
      				session.setAttribute("user", user);	      			 		
      			}     			
      			break;
      		case 'd':
				int itemReserve = 0; 
				int userTotReservation = 0;
				int userReserveAvailable = 0;      		
      			int totalRecDel = 0;
      			int totalRecDelErr=0;
      			int staffRecNumber=staff.getUserRecNumber();
      			int delRecNumber;
      			String userType = login.getUserType();
      			StringBuffer RecDelErrReason= new StringBuffer("");
      			if(debug) out.println("user+d"); 
				if(request.getParameterValues("del_rec") != null){
      				String[] paramValues = request.getParameterValues("del_rec");
      				if(debug_user_delete){
      					if (paramValues.length == 1) {
        					String paramValue = paramValues[0];
        					if (paramValue.length() == 0)
          						out.println("<I>No Value</I>");
        					else
          						out.println(paramValue);
      					} else {
        					out.println("<UL>");
        					for(int i=0; i<paramValues.length; i++) {
          						out.println("<LI>" + paramValues[i]);
         					}
        					out.println("</UL>");
      					}
      				}else{  
      					// Only admin right can perform deleteion
      					if (userType.equals("admin")) {
      						for (int i=0; i<paramValues.length; i++){ 	
      							delRecNumber = Integer.parseInt(paramValues[i]);					 		
      							if (staffRecNumber==delRecNumber) {
      								totalRecDelErr=totalRecDelErr+1;
      								RecDelErrReason.append("<li><font color='RED'>U are not allow delete yourself. </font></li>");
      							// check either the user that delete either still got any item not yet return or not
      							}else {
      								// check either user still have item(s) not yet return
      								int itemBorrow = 0;
      								int userTotQuota =0;
      								int userQuotaAvailable=0;
      								String userID="";
      								query = "SELECT * FROM user WHERE userRecNumber =" +delRecNumber;
      								myResultSet = formDatabaseOperation(query);      	
      								if (myResultSet != null){
										try{	
											while(myResultSet.next()) {						
												userID = myResultSet.getString("userID");
      											userTotQuota = Integer.parseInt(myResultSet.getString("userTotQuota"));
												userQuotaAvailable = Integer.parseInt(myResultSet.getString("userQuotaAvailable"));	
												userTotReservation = Integer.parseInt(myResultSet.getString("userTotReservation"));
												userReserveAvailable= Integer.parseInt(myResultSet.getString("userReserveAvailable"));
											}
      									} catch(SQLException sqle) {
											System.err.println("Error connecting: " +sqle);		
										}
									}
									itemBorrow = userTotQuota - userQuotaAvailable;
									itemReserve = userTotReservation - userReserveAvailable;
									
							
      								if (itemBorrow>0) {
      									totalRecDelErr = totalRecDelErr + 1;
      									RecDelErrReason.append("<li><font color='RED'>User ID: <i>" +userID +
      										"</i> still have <i>" +itemBorrow +"</i> item(s) no yet return</font></li>");
									} else if (itemReserve > 0) {									
      									totalRecDelErr = totalRecDelErr + 1;
      									RecDelErrReason.append("<li><font color='RED'>User ID: <i>" +userID +
      										"</i> still have <i>" +itemReserve +"</i> item(s) in reserve list</font></li>");										
      								}else{
      								totalRecDel = totalRecDel + 1;  	
      								query = "DELETE FROM user WHERE userRecNumber=" +delRecNumber;		
      								myResultSet = formDatabaseOperation(query);
      								}
      							}
      						}
      						dbprocess.setDBName("jsp_library");
      						dbprocess.setTblName("user");
      						dbprocess.setSQLQuery(query);
      						dbprocess.setProcessResult("<B>Delete Record(s)</B>");
      						// If delete operation having error, then display the error messager
      						if(totalRecDelErr>0){
      							dbprocess.setProcessMsg(totalRecDel +" record(s) success delete" +
      								"<br><br><font color='RED'>" +totalRecDelErr + "</font> record(s) having problem" +
      								"<ul>" +RecDelErrReason +"</ul>");
      						}else{
      							dbprocess.setProcessMsg(totalRecDel +" record(s) success delete");      				
      						}
      						dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> " +
      							"<a href='/JavaLibrary/admin/user_list.jsp'>[Back to User List]</a>");
							dbprocess.setProcessComplete(true); 
      					}else{
      					    dbprocess.setDBName("jsp_library");
      						dbprocess.setTblName("user");
      						dbprocess.setSQLQuery(query);
      						dbprocess.setProcessResult("<font color='RED'><B>Record Delete Error</B></font>");
      						dbprocess.setProcessMsg("<font color='RED'>Insufficiency Security Level</font>");
							dbprocess.setProcessAction("<a href='" +form.getFormURL() +"'>" +
      							"<img src='/JavaLibrary/images/common/back_arrow.gif' width='38' height='21' border='0'>BACK</a>");
							dbprocess.setProcessComplete(true);
      					}
      				}            				     				
				}else{
					dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("user");
      				dbprocess.setSQLQuery(query);
      				dbprocess.setProcessResult("<font color='RED'><B>Record Delete Error</B></font>");
      				dbprocess.setProcessMsg("<font color='RED'>No record(s) select</font>");
					dbprocess.setProcessAction("<a href='" +form.getFormURL() +"'>" +
      						"<img src='/JavaLibrary/images/common/back_arrow.gif' width='38' height='21' border='0'>BACK</a>");
					dbprocess.setProcessComplete(true);
				}		
                break;
      		default:
                break;
      		}     					
		}else if( formName.equals("libraryitem")){
			// Do something for library item form	
			switch(formOperationCode) {
      		case 'a':
     			if(debug) out.println("item+a");
     			libraryitem.setPropertyFromRequestParameter(request);
      			query = "INSERT INTO libraryitem (" +
      				"itemCallNumber, itemCategory, itemSubject, itemMediaType, itemLanguage, itemTitle, itemBriefDesc, " + 
      				"itemAuthor, itemKeyword, itemPublisherName, itemISBN, itemLength, itemYear, " +
      				"itemTotQuantity, itemTotReserve, itemTotAvailable, itemTotBorrow) VALUES (" +
      				"'" +libraryitem.getItemCallNumber() +"', '" +libraryitem.getItemCategory() +
      				"', '" +libraryitem.getItemSubject() +"', '" +libraryitem.getItemMediaType() +
      				"', '" +libraryitem.getItemLanguage() +"', '" +libraryitem.getItemTitle() +
      				"', '" +libraryitem.getItemBriefDesc() +"', '" +libraryitem.getItemAuthor() +
      				"', '" +libraryitem.getItemKeyword() +"', '" +libraryitem.getItemPublisherName() +
      				"', '" +libraryitem.getItemISBN() +"', '" +libraryitem.getItemLength() +
      				"', '" +libraryitem.getItemYear() +"', " +libraryitem.getItemTotQuantity() +
      				", " +libraryitem.getItemTotReserve() +", " +libraryitem.getItemTotAvailable() +
      				", " +libraryitem.getItemTotBorrow() +")";
	   			myResultSet = formDatabaseOperation(query);	 
				libraryitem.resetVariable();
				session.setAttribute("libraryitem", libraryitem);				
				dbprocess.setDBName("jsp_library");
      			dbprocess.setTblName("libraryitem");
      			dbprocess.setSQLQuery(query);
      			dbprocess.setProcessResult("<B>Item Record success added</B>");
      			dbprocess.setProcessMsg("");
				dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> " +
      						"<a href='/JavaLibrary/libraryitem/libraryitem_add.jsp'>[Add another item record]</a>");    	
      			dbprocess.setProcessComplete(true);
      			break;
      		case 'e':
      			if(debug) out.println("item+e");   
      			libraryitem.setPropertyFromRequestParameter(request);      	
      			query = "UPDATE libraryitem SET itemCategory='" +libraryitem.getItemCategory() +
      				"', itemSubject='" +libraryitem.getItemSubject() + 
      				"', itemMediaType='" +libraryitem.getItemMediaType() +"', itemLanguage='" +libraryitem.getItemLanguage() +
      				"', itemTitle='" +libraryitem.getItemTitle() +"', itemBriefDesc='" +libraryitem.getItemBriefDesc() +
      				"', itemAuthor='" +libraryitem.getItemAuthor() +"', itemKeyword='" +libraryitem.getItemKeyword() +
      				"', itemPublisherName='" +libraryitem.getItemPublisherName() +"', itemISBN='" +libraryitem.getItemISBN() +
      				"', itemLength='" +libraryitem.getItemLength() +"', itemYear='" +libraryitem.getItemYear() +
      				"', itemTotQuantity=" +libraryitem.getItemTotQuantity() +", itemTotReserve=" +libraryitem.getItemTotReserve() +
      				", itemTotAvailable=" +libraryitem.getItemTotAvailable() +", itemTotBorrow=" +libraryitem.getItemTotBorrow() +  				
      				" WHERE itemRecNumber = '" +libraryitem.getItemRecNumber() +"'";
      				myResultSet = formDatabaseOperation(query);	
      				
					// Display the procesing messager
      				dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("libraryitem");
      				dbprocess.setSQLQuery(query);
      				dbprocess.setProcessResult("<B>Record Update Success</B>");
      				dbprocess.setProcessMsg("");
   					dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> " +
      					"<a href='/JavaLibrary/admin/libraryitem_list.jsp'>[Back to Item List]</a>");
					dbprocess.setProcessComplete(true); 						
      				//session.setAttribute("libraryitem", libraryitem);	  		
      			break;
      		case 'd':
      			if(debug) out.println("item+d");
      			int totalRecDel = 0;
      			int totalRecDelErr=0;
      			int delRecNumber;
      			String itemCallNumber = "";
      			String userType = login.getUserType();
      			StringBuffer RecDelErrReason= new StringBuffer("");
				int itemTotQuantity = 0;
				int itemTotAvailable = 0; 
				int itemTotBorrow = 0; 
				int itemTotReserve = 0;
				if(request.getParameterValues("del_rec") != null){
      				String[] paramValues = request.getParameterValues("del_rec");
   					// Only admin right can perform deleteion
   					if (userType.equals("admin")) {
      					for (int i=0; i<paramValues.length; i++){ 	
      						delRecNumber = Integer.parseInt(paramValues[i]);      											 		
      						query = "SELECT * FROM libraryitem WHERE itemRecNumber =" +delRecNumber;
      						myResultSet = formDatabaseOperation(query);      	
      						if (myResultSet != null){
								try{	
									while(myResultSet.next()) {	
										itemCallNumber = myResultSet.getString("itemCallNumber");
										itemTotQuantity = Integer.parseInt(myResultSet.getString("itemTotQuantity"));
										itemTotReserve = Integer.parseInt(myResultSet.getString("itemTotReserve"));
										itemTotAvailable = Integer.parseInt(myResultSet.getString("itemTotAvailable"));
										itemTotBorrow = Integer.parseInt(myResultSet.getString("itemTotBorrow"));
									}
      							} catch(SQLException sqle) {
									System.err.println("Error connecting: " +sqle);		
								}
							}
      							
      						if (itemTotBorrow > 0) {
      							totalRecDelErr=totalRecDelErr+1;
   								RecDelErrReason.append("<li><font color='RED'>Item Call Number: " +itemCallNumber +
      								" still have " +itemTotBorrow +" in quantity not yet return</font></li>");
      						} else if (itemTotReserve > 0) {
      							totalRecDelErr = totalRecDelErr + 1;
      							RecDelErrReason.append("<li><font color='RED'>Item Call Number: " +itemCallNumber +
      								" still have " +itemTotReserve +" in quantity under reserve list</font></li>");
      						}else {
      							totalRecDel = totalRecDel + 1;  	
      							query = "DELETE FROM libraryitem WHERE itemRecNumber=" +delRecNumber;		
      							myResultSet = formDatabaseOperation(query);
      						}
      					}      				
      					dbprocess.setDBName("jsp_library");
      					dbprocess.setTblName("libraryitem");
      					dbprocess.setSQLQuery(query);
      					dbprocess.setProcessResult("<B>Delete Record(s)</B>");
      					// If delete operation having error, then display the error messager
      					if(totalRecDelErr>0){
      						dbprocess.setProcessMsg(totalRecDel +" record(s) success delete" +
      							"<br><br><font color='RED'>" +totalRecDelErr + "</font> record(s) having problem" +
      							"<ul>" +RecDelErrReason +"</ul>");
      					}else{
      						dbprocess.setProcessMsg(totalRecDel +" record(s) success delete");      	
      					}			
      					dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> " +
      						"<a href='/JavaLibrary/admin/libraryitem_list.jsp'>[Back to Item List]</a>");
						dbprocess.setProcessComplete(true); 						
      				}else{
      				    dbprocess.setDBName("jsp_library");
      					dbprocess.setTblName("libraryitem");
      					dbprocess.setSQLQuery(query);
      					dbprocess.setProcessResult("<font color='RED'><B>Record Delete Error</B></font>");
      					dbprocess.setProcessMsg("<font color='RED'>Insufficiency Security Level</font>");
						dbprocess.setProcessAction("<a href='" +form.getFormURL() +"'>" +
      						"<img src='/JavaLibrary/images/common/back_arrow.gif' width='38' height='21' border='0'>BACK</a>");
						dbprocess.setProcessComplete(true);
      				}       				     				
				}else{
					dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("libraryitem");
      				dbprocess.setSQLQuery(query);
      				dbprocess.setProcessResult("<font color='RED'><B>Record Delete Error</B></font>");
      				dbprocess.setProcessMsg("<font color='RED'>No record(s) select</font>");
					dbprocess.setProcessAction("<a href='" +form.getFormURL() +"'>" +
      						"<img src='/JavaLibrary/images/common/back_arrow.gif' width='38' height='21' border='0'>BACK</a>");
					dbprocess.setProcessComplete(true);
				}	
                break;
      		default:
                break;
      		} 
		}else if( formName.equals("borrowrec")){
			// Do something for borrow record form
			switch(formOperationCode) {
      		case 'b':
      		    StringBuffer recErrReason= new StringBuffer("");
      			int totalRecErr=0;
      			int numSameRecInBorrowRec = 0;
      			int numSameRecInReserveRec = 0;
      			int totalBorrow = 0;
      			String itemCallNumber="";
				// Get how many items borrow from parameter pass by http request
      			String[] paramValues = request.getParameterValues("itemCallNumber");
      			String borrowID = request.getParameter("borrowerID");
      			
      			if (paramValues.length > 0 ) {
      				for (int i=0; i<paramValues.length; i++) {
      					itemCallNumber = paramValues[i];  
      					
      					//Check eitehr user had make the reservation for the same item or not
      					query = "SELECT count(*) FROM reserverec WHERE reserverID = '" +borrowID +
      						"' AND itemCallNumber = '" +itemCallNumber +"'";
      					myResultSet = formDatabaseOperation(query);
				    	if (myResultSet != null){
							try{
								while(myResultSet.next()) {
			 						numSameRecInReserveRec = Integer.parseInt(myResultSet.getString("count(*)"));
								}
							} catch(SQLException sqle) {
								System.err.println("Error connecting: " +sqle);		
							}
						}      							
      					
      					//Check either borrower already borrow out the same item in pervious or not and not yet return
      					query = "SELECT count(*) FROM borrowrec WHERE borrowerID = '" +borrowID +
      						"' AND itemCallNumber = '" +itemCallNumber +"' AND borrowStatus != 'return'";
      					myResultSet = formDatabaseOperation(query);
				    	if (myResultSet != null){
							try{
								while(myResultSet.next()) {
			 						numSameRecInBorrowRec = Integer.parseInt(myResultSet.getString("count(*)"));
								}
							} catch(SQLException sqle) {
								System.err.println("Error connecting: " +sqle);		
							}
						}
						// If that are same record exist in previous reserve list
						if (numSameRecInReserveRec >= 1 ){  
							totalRecErr = totalRecErr + 1;
							recErrReason.append("<li><font color='RED'>Item Call Number: " +itemCallNumber +
      							" already exist in user reserve list</font></li>");
      					// If that are same record exist in user existing borrow list
						} else if(numSameRecInBorrowRec >= 1) {
							totalRecErr = totalRecErr + 1;
							recErrReason.append("<li><font color='RED'>Item Call Number: " +itemCallNumber +
      							" already exist in user borrow record</font></li>");					
						} else {   					
     						// Insert into the borrowrec table
      						borrowrec.setPropertyFromRequestParameter(request, itemCallNumber);
      						query = "INSERT INTO borrowrec (" +
      							"itemCallNumber, staffIDBorrow, staffIDReturn, borrowerID, borrowerEmail, " +
      							" borrowDate, dueDate, returnDate, borrowStatus) VALUES (" +
      							"'" +borrowrec.getItemCallNumber() +"', '" +borrowrec.getStaffIDBorrow() +
      							"', '" +borrowrec.getStaffIDReturn() +"', '" +borrowrec.getBorrowerID() +
      							"', '" +borrowrec.getBorrowerEmail() +"', '" +borrowrec.getBorrowDate() +
      							"', '" +borrowrec.getDueDate() +"', '" +borrowrec.getReturnDate() +
      							"', '" +borrowrec.getBorrowStatus() +"' )";
      						if (debug) out.println("<br>Query for borrowrec: " +query);
      						myResultSet = formDatabaseOperation(query);
      						borrowrec.resetVariable();
      						session.setAttribute("borrowrec", borrowrec); 
      					        					
      						totalBorrow = totalBorrow + 1;         					
      					
      						//Update the libraryitem database
      						query = "UPDATE libraryitem SET itemTotAvailable = itemTotAvailable - 1" +
      							" , itemTotBorrow = itemTotBorrow + 1 " +
      							" WHERE itemCallNumber ='" +itemCallNumber +"'";
      						myResultSet = formDatabaseOperation(query);
      						if (debug) out.println("<br>Query for libraryitem: " +query);
						}
					}			
					// Update the user database
					if (totalBorrow > 0){
       					query = "UPDATE user SET userQuotaAvailable = userQuotaAvailable - " +totalBorrow +
      						" WHERE  userID ='" +borrowID +"'";
      					myResultSet = formDatabaseOperation(query);	       		
       					if (debug) out.println("<br>Query for user database: " +query);   
					}
      			}
					
				dbprocess.setDBName("jsp_library");
      			dbprocess.setTblName("libraryitem");
      			dbprocess.setSQLQuery("Query: " +query);
      			dbprocess.setProcessResult("<B>Borrow Record success added</B>");
      			// If delete operation having error, then display the error messager
      			if(totalRecErr>0){
      			dbprocess.setProcessMsg(totalBorrow +" item(s) success added" +
      				"<br><br><font color='RED'>" +totalRecErr + "</font> record(s) having problem" +
      				"<ul>" +recErrReason +"</ul>");
      			}else{
      				dbprocess.setProcessMsg(totalBorrow +" item(s) success added");      	
      			}
				dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> " +
      				"<a href='/JavaLibrary/operation/borrow.jsp'>[Add another borrow record]</a>");    	
      			dbprocess.setProcessComplete(true);  
				 				
      			break;
      		case 'r':
      			if(debug) out.println("return");						
      			if(request.getParameterValues("borrowRecNumber") != null){      			
      				String errorMsg;
      				int borrowRecNumber=0;
      				String borrowerID = ""; String reserverID = ""; String borrowStatus = "";
					itemCallNumber = "";
      				String dueDate = "";
      				Date todayDate = new Date();
					String todayDateStr = ServletUtilities.dateToString(todayDate);
					Date reserveCancelDateAfterInform = ServletUtilities.diffrenceDay(todayDate, 7);
					String reserveCancelDateAfterInformStr = ServletUtilities.dateToString(reserveCancelDateAfterInform);
					int overdueDay = 0;
					float penaltyAmount = 0;
					float totalPenaltyAmount = 0;
      				totalRecErr=0;					
					recErrReason= new StringBuffer("");
      				StringBuffer recUpdateInfo = new StringBuffer("");
      				StringBuffer penaltyInfo = new StringBuffer("");
					int totalReserveUpdate=0;
					int totalReturnNumber=0;
      				paramValues = request.getParameterValues("borrowRecNumber");
					if (debug) out.println("<BR>borrowerID: " +borrowerID);
       				if (paramValues.length > 0 ) {
      					for (int i=0; i<paramValues.length; i++) {						
      						// Get the itemCallNumber and calculate the total due day and penalty amount 
      						borrowRecNumber = Integer.parseInt(paramValues[i]);      					
      						if (debug) out.println("<br><br>borrowRecNumber: " +borrowRecNumber);    
      						query = "SELECT *, (TO_DAYS(NOW())-TO_DAYS(dueDate)) as overdueDay " +
      								" FROM borrowrec where borrowRecNumber = " +borrowRecNumber;  
      						if(debug) out.println("<br>Query(Select): " +query);  					
    						try {
      							Connection connection = connectionPool.getConnection();
								myResultSet = DatabaseUtilities.getQueryResultSet(connection, query, false);                                            
      							connectionPool.free(connection);
				    		} catch(Exception e) {
      							errorMsg = "Error: " + e;    		
    						}    
    					
    						overdueDay=0;
							penaltyAmount=0; 
    						if (myResultSet != null){
								try{
									while(myResultSet.next()) {
										borrowerID = myResultSet.getString("borrowerID");
										itemCallNumber = myResultSet.getString("itemCallNumber");
										dueDate = myResultSet.getString("dueDate");
										overdueDay = Integer.parseInt(myResultSet.getString("overdueDay"));
									}
								} catch(SQLException sqle) {
									System.err.println("Error connecting: " +sqle);		
								}
							}	
							if(debug) out.println("<br>itemCallNumber: " +itemCallNumber);	
							
							// Update the penaltyrec
							if(overdueDay > 0) {
								if(debug) out.println("<br>Update the penaltyrec");
								penaltyAmount = (float) (overdueDay * 1.5);
								query = "INSERT INTO penaltyrec (borrowRecNumber, borrowerID, overdueDay, penaltyAmount)" +
									" VALUES (" +borrowRecNumber +", '" +borrowerID +"', " +overdueDay +", " +penaltyAmount +")";
								myResultSet = formDatabaseOperation(query);
								totalPenaltyAmount = totalPenaltyAmount +penaltyAmount;			
								penaltyInfo.append("<li>Item Call Number: " +itemCallNumber +
      								" had overdue for " +overdueDay +" days and panelty is: $" +penaltyAmount +"</li>"); 
								if(debug) out.println("<br>Query(overdueDay): " +query);	
							}
							
							// Update the borrowrec record
							if(debug) out.println("<br>Update the borrow record");
							query = "UPDATE borrowrec SET returnDate ='" +todayDateStr +
								"', StaffIDReturn ='" +login.getUserID() +"', borrowStatus = 'return' " +
								" WHERE borrowRecNumber =" +borrowRecNumber;
							if(debug) out.println("<br>Query(borrowrec): " +query);
							myResultSet = formDatabaseOperation(query);						
						
							// Update the libraryitem record
							if(debug) out.println("<br>Update the libraryitem record");
							query = "UPDATE libraryitem SET itemTotAvailable = itemTotAvailable  + 1" +
								", itemTotBorrow = itemTotBorrow  -1" +
								" WHERE itemCallNumber = '" +itemCallNumber +"'";
							if(debug) out.println("<br>Query(libraryitem): " +query);
							myResultSet = formDatabaseOperation(query);
							
							// Update the user record
							if(debug) out.println("<br>Update the user record");
							query = "UPDATE user SET userQuotaAvailable   = userQuotaAvailable   +1" +
								" WHERE userID ='" +borrowerID +"'";
							if(debug) out.println("<br>Query(user): " +query);
							myResultSet = formDatabaseOperation(query);							

							// Update the user bean if the reserver ID for the record that cancel same as login user. 
							if (reserverID.equals(login.getUserID())) {								
								user.setUserQuotaAvailable(user.getUserQuotaAvailable()+1);
								session.setAttribute("user", user);
								// if staff update their own record then set the staff bean
								if( (login.getUserType().equals("admin")) || (login.getUserType().equals("librarian"))){
      								staff.setPropertyFromOtherUserBean(user);
      								session.setAttribute("staff", staff);
      							}
      						}					
						
							// Update the reserve record
							if(debug) out.println("<br>Update the reserve record");
							query = "SELECT * FROM reserverec WHERE itemCallNumber ='" +itemCallNumber +"'" +
									" AND reserveStatus != 'available' ORDER BY reserveCancelDate ASC limit 1";
							if(debug) out.println("<br>Query(reserverec - select): " +query);
							try {  
      							Connection connection = connectionPool.getConnection();
								myResultSet = DatabaseUtilities.getQueryResultSet(connection, query, false);                                            
      							connectionPool.free(connection);
				    		} catch(Exception e) {
      							errorMsg = "Error: " + e;    		
    						}    
    						
    						reserverID = "";
    						if (myResultSet != null){
								try{
									while(myResultSet.next()) {
										reserverID = myResultSet.getString("reserverID");
									}
								} catch(SQLException sqle) {
									System.err.println("Error connecting: " +sqle);		
								}
							}	
								
							// Update the status to available and the cancel date to one week after inform
							if(!reserverID.equals("")){
								query = "UPDATE reserveRec SET reserveStatus = 'available'" +
									", reserveInformDate = '" +todayDateStr +"'" +
									", reserveCancelDate = '" +reserveCancelDateAfterInformStr +"'" +
									" WHERE reserverID = '" +reserverID +"'";
								if(debug) out.println("<br>Query(reserverec - update): " +query);
								myResultSet = formDatabaseOperation(query);	
								recUpdateInfo.append("<li>Item Call Number: " +itemCallNumber +
      								" had update as available in reserve queue for reserver ID: " +reserverID +"</li>");							
								totalReserveUpdate = totalReserveUpdate +1;
							}
							totalReturnNumber = totalReturnNumber + 1;												
    					}
    				}   			
					dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("borrowrec");
      				dbprocess.setSQLQuery("Query: " +query);
      				dbprocess.setProcessResult("<B>Return Record success added</B>");
      				// If delete return having update the reserver record and/or penalty invlove, display the messager
     				
					String ReserveUpdateMsg = "";
					String PenaltyMsg = "";
					
      				if(totalReserveUpdate>0)
      					ReserveUpdateMsg = "<br><br>" + totalReserveUpdate + " record(s) in reserve queue had update" +
      						"<ul>" +recUpdateInfo +"</ul>";
      				if(totalPenaltyAmount>0)
      					PenaltyMsg = "<br><br>Total Penalty: $" +totalPenaltyAmount +" for the item that had overdue" +
      						"<ul>" +penaltyInfo + "</ul>";
      				
      				dbprocess.setProcessMsg(totalReturnNumber +" Return records success added" +
      					ReserveUpdateMsg + PenaltyMsg);

					dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> ");   	
      				dbprocess.setProcessComplete(true);        			      			

				}else{
					dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("borrowrec");
      				dbprocess.setSQLQuery("");
      				dbprocess.setProcessResult("<font color='RED'><B>Record Delete Error</B></font>");
      				dbprocess.setProcessMsg("<font color='RED'>No record(s) select</font>");
					dbprocess.setProcessAction("<a href='" +form.getFormURL() +"'>" +
      					"<img src='/JavaLibrary/images/common/back_arrow.gif' width='38' height='21' border='0'>BACK</a>");
					dbprocess.setProcessComplete(true);
				}    
      			break;
      		default:
                break;
      		} 
      	}else if( formName.equals("reserve")){
			// Do something for reserve record form
			switch(formOperationCode) {
      		case 'a':
      			if(debug) out.println("reserve+a");      	
      			StringBuffer recErrReason= new StringBuffer("");
      			int totalRecErr=0;
      			int numSameRecInReserveRec = 0;
      			int numSameRecInBorrowRec = 0;
      			int totalReserve = 0;
      			String itemCallNumber="";
				// Get how many items borrow from parameter pass by http request
      			String[] paramValues = request.getParameterValues("itemCallNumber");
      			String reserverID = request.getParameter("reserverID");
 
       			if (paramValues.length > 0 ) {
      				for (int i=0; i<paramValues.length; i++) {
      					itemCallNumber = paramValues[i];
      					
      					//Check either reserver had make the same item in current reservation or not
      					query = "SELECT count(*) FROM reserverec WHERE reserverID = '" +reserverID +
      						"' AND itemCallNumber = '" +itemCallNumber +"'";
      					myResultSet = formDatabaseOperation(query);
				    	if (myResultSet != null){
							try{
								while(myResultSet.next()) {
			 						numSameRecInReserveRec = Integer.parseInt(myResultSet.getString("count(*)"));
								}
							} catch(SQLException sqle) {
								System.err.println("Error connecting: " +sqle);		
							}
						}
						
						//Check either reserver had same item in current borrow record or not
						query = "SELECT count(*) FROM borrowrec WHERE borrowerID = '" +reserverID +
							"' AND itemCallNumber = '" +itemCallNumber +"' AND borrowStatus != 'return'";
      					myResultSet = formDatabaseOperation(query);
				    	if (myResultSet != null){
							try{
								while(myResultSet.next()) {
			 						numSameRecInBorrowRec = Integer.parseInt(myResultSet.getString("count(*)"));
								}
							} catch(SQLException sqle) {
								System.err.println("Error connecting: " +sqle);		
							}
						}						
						
						// If that are same record exist in previous reserve list
						if (numSameRecInReserveRec >= 1 ){  
							totalRecErr = totalRecErr + 1;
							recErrReason.append("<li><font color='RED'>Item Call Number: " +itemCallNumber +
      							" already exist in user reserve list</font></li>");
      					// If that are same record exist in user existing borrow list
						} else if(numSameRecInBorrowRec >= 1) {
							totalRecErr = totalRecErr + 1;
							recErrReason.append("<li><font color='RED'>Item Call Number: " +itemCallNumber +
      							" already exist in user borrow record</font></li>");					
						} else {    					
     						// Insert into the reserverec table
      						reserverec.setPropertyFromRequestParameter(request, itemCallNumber);
      						query = "INSERT INTO reserverec (" +
      							"itemCallNumber, reserverID, reserverEmail, reserveDate, reserveCancelDate" +
      							", reserveInformDate, reserveStatus) VALUES (" +
      							"'" +reserverec.getItemCallNumber() +"', '" +reserverec.getReserverID() +
      							"', '" +reserverec.getReserverEmail() +"', '" +reserverec.getReserveDate() +
      							"', '" +reserverec.getReserveCancelDate() +"', '" +reserverec.getReserveInformDate() +
      							"', '" +reserverec.getReserveStatus() +"') ";
      						if (debug) out.println("<br>Query for reserverec: " +query);

      						myResultSet = formDatabaseOperation(query);
      						reserverec.resetVariable();
      						session.setAttribute("reserverec", reserverec); 
      					        					
      						totalReserve = totalReserve + 1;         					
      					
      						//Update the libraryitem database
      						query = "UPDATE libraryitem SET itemTotReserve = itemTotReserve + 1" +
      							" WHERE itemCallNumber ='" +itemCallNumber +"'";
      						myResultSet = formDatabaseOperation(query);
      						if (debug) out.println("<br>Query for libraryitem: " +query);
						}
					}			
					// Update the user database
					if (totalReserve > 0){
       					query = "UPDATE user SET userReserveAvailable = userReserveAvailable - " +totalReserve +
      						" WHERE  userID ='" +reserverID +"'";
      					myResultSet = formDatabaseOperation(query);	       		
       					if (debug) out.println("<br>Query for user database: " +query);   
       				}
       			
       				// Update the user bean
					user.setUserReserveAvailable(user.getUserReserveAvailable()-totalReserve);
					session.setAttribute("user", user);
					// if staff update their own record then set the staff bean
					if( (login.getUserType().equals("admin")) || (login.getUserType().equals("librarian"))){
      						staff.setPropertyFromOtherUserBean(user);
      						session.setAttribute("staff", staff);
      				}		
      			}
				
				dbprocess.setDBName("jsp_library");
      			dbprocess.setTblName("reserverec");
      			dbprocess.setSQLQuery("Query: " +query);
      			dbprocess.setProcessResult("<B>Reserve Record success added</B>");
      			// If delete operation having error, then display the error messager
      			if(totalRecErr>0){
      			dbprocess.setProcessMsg(totalReserve +" item(s) success added" +
      				"<br><br><font color='RED'>" +totalRecErr + "</font> record(s) having problem" +
      				"<ul>" +recErrReason +"</ul>");
      			}else{
      				dbprocess.setProcessMsg(totalReserve +" item(s) success added");      	
      			}
				dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> ");   	
      			dbprocess.setProcessComplete(true);   
      			break;
      		case 'd':
      			if(debug) out.println("reserve+d");
				if(request.getParameterValues("reserveRecNumber") != null){      			
      				String errorMsg;
      				int reserveRecNumber=0;
      				reserverID = "";
					itemCallNumber = "";
					recErrReason= new StringBuffer("");
      				totalRecErr=0;
      				int totalReserveCancel = 0;
      				int totalReserveUpdate = 0;
      				StringBuffer recUpdateInfo = new StringBuffer("");
      				paramValues = request.getParameterValues("reserveRecNumber");
      				String reserveStatus = "";
      				Date todayDate = new Date();
					String todayDateStr = ServletUtilities.dateToString(todayDate);
					Date reserveCancelDateAfterInform = ServletUtilities.diffrenceDay(todayDate, 7);
					String reserveCancelDateAfterInformStr = ServletUtilities.dateToString(reserveCancelDateAfterInform);
       				if (paramValues.length > 0 ) {
      					for (int i=0; i<paramValues.length; i++) {
      						// Get the borrowerID and itemCallNumber from the parameter
      						reserveRecNumber = Integer.parseInt(paramValues[i]);      					
      						if (debug) out.println("<br><br>reserveRecNumber: " +reserveRecNumber);      					
    						try {
      							query = "SELECT * FROM reserverec where reserveRecNumber = " +reserveRecNumber;      
      							Connection connection = connectionPool.getConnection();
								myResultSet = DatabaseUtilities.getQueryResultSet(connection, query, false);                                            
      							connectionPool.free(connection);
				    		} catch(Exception e) {
      							errorMsg = "Error: " + e;    		
    						}    
    					
    						if (myResultSet != null){
								try{
									while(myResultSet.next()) {
										reserverID = myResultSet.getString("reserverID");
										itemCallNumber = myResultSet.getString("itemCallNumber");
										reserveStatus = myResultSet.getString("reserveStatus");
									}
								} catch(SQLException sqle) {
									System.err.println("Error connecting: " +sqle);		
								}
							}	
							
							if (debug) out.println("<BR>reserverID: " +reserverID);
							if (debug) out.println("<br>itemCallNumber: " +itemCallNumber);
							if (debug) out.println("<br>reserveStatus: " +reserveStatus);										
							
							// Update the libraryitem record
							if(debug) out.println("<br>Update the libraryitem record");
							query = "UPDATE libraryitem SET itemTotReserve= itemTotReserve - 1" +
								" WHERE itemCallNumber = '" +itemCallNumber +"'";
							if(debug) out.println("<br>Query(libraryitem): " +query);
							myResultSet = formDatabaseOperation(query);
							
							// Update the user record
							if(debug) out.println("<br>Update the user record");
							query = "UPDATE user SET userReserveAvailable = userReserveAvailable +1" +
								" WHERE userID ='" +reserverID +"'";
							if(debug) out.println("<br>Query(user): " +query);
							myResultSet = formDatabaseOperation(query);							

							// Update the user bean if the reserver ID for the record that cancel same as login user.
							if (reserverID.equals(login.getUserID())) {								
								user.setUserReserveAvailable(user.getUserReserveAvailable()+1);
								session.setAttribute("user", user);
								// if staff update their own record then set the staff bean
								if( (login.getUserType().equals("admin")) || (login.getUserType().equals("librarian"))){
      								staff.setPropertyFromOtherUserBean(user);
      								session.setAttribute("staff", staff);
      							}
      						}	
      													
							// Update the reserve record
							if(debug) out.println("<br>Update the reserve record");
							query = "DELETE FROM reserverec WHERE reserveRecNumber=" +reserveRecNumber;	
							if(debug) out.println("<br>Query(reserverec): " +query);	
							myResultSet = formDatabaseOperation(query);
							reserverID = "";
							
							// If the reservestatus is available then transfer the available to next first person in queue
							if(reserveStatus.equals("available")) {
								// Select the first person in next queue for the same item
								query = "SELECT * FROM reserverec WHERE itemCallNumber ='" +itemCallNumber +"'" +
									" AND reserveStatus != 'available' " +
									" ORDER BY reserveCancelDate ASC limit 1";
								if(debug) out.println("<br>Query(reserverec - select): " +query);
								try {  
      								Connection connection = connectionPool.getConnection();
									myResultSet = DatabaseUtilities.getQueryResultSet(connection, query, false);                                            
      								connectionPool.free(connection);
				    			} catch(Exception e) {
      								errorMsg = "Error: " + e;    		
    							}    
    					
    							if (myResultSet != null){
									try{
										while(myResultSet.next()) {
											reserverID = myResultSet.getString("reserverID");
										}
									} catch(SQLException sqle) {
										System.err.println("Error connecting: " +sqle);		
									}
								}	
								
								// Update the status to available and the cancel date to one week after inform
								if(!reserverID.equals("")){
									query = "UPDATE reserveRec SET reserveStatus = 'available'" +
										", reserveInformDate = '" +todayDateStr +"'" +
										", reserveCancelDate = '" +reserveCancelDateAfterInformStr +"'" +
										" WHERE reserverID = '" +reserverID +"'";
									if(debug) out.println("<br>Query(reserverec - update): " +query);
									myResultSet = formDatabaseOperation(query);	
									recUpdateInfo.append("<li>Availability of Item Call Number: " +itemCallNumber +
      									" had update in queue to reserver ID: " +reserverID +"</li>");							
									totalReserveUpdate = totalReserveUpdate +1;
								}
							}
							totalReserveCancel = totalReserveCancel +1;							
    					}
    				}   			
					dbprocess.setDBName("jsp_library");
      				dbprocess.setTblName("reserverec");
      				dbprocess.setSQLQuery("Query: " +query);
      				dbprocess.setProcessResult("<B>Reserve Record success cancel</B>");
      				// If cancel reservation have involve next queue, then display the queue update messager
      				if(totalReserveUpdate>0){
      				dbprocess.setProcessMsg(totalReserveCancel +" reserve records success cancel" +
      					"<br><br>" +totalReserveUpdate + " record(s) in queue had update" +
      					"<ul>" +recUpdateInfo +"</ul>");
      				}else{
      					dbprocess.setProcessMsg(totalReserveCancel +" reserve records success cancel");      	
      				}
					dbprocess.setProcessAction("<a href='/JavaLibrary/mainmenu/mainmenu.jsp'>[Main Menu]</a> ");   	
      				dbprocess.setProcessComplete(true);        			      			

				}else{
				dbprocess.setDBName("jsp_library");
      			dbprocess.setTblName("lreserverec");
      			dbprocess.setSQLQuery("");
      			dbprocess.setProcessResult("<font color='RED'><B>Reservation cancel Error</B></font>");
      			dbprocess.setProcessMsg("<font color='RED'>No record(s) select</font>");
				dbprocess.setProcessAction("<a href='" +form.getFormURL() +"'>" +
      					"<img src='/JavaLibrary/images/common/back_arrow.gif' width='38' height='21' border='0'>BACK</a>");
				dbprocess.setProcessComplete(true);
			}     		      			
                break;
      		default:
                break;
      		} 	
		}else {
			// ??  Should be some form that I miss.. hahaha.
			// Should report it...  :)
		}
		
		if(debug){
			out.println("<BR>Query: <b>" +query +"</b>");
	        out.println("</CENTER>\n</BODY></HTML>");
  		}
  		else{
      		session.setAttribute("dbprocess", dbprocess); 
      		gotoPage("/common/dbprocess_result.jsp", request, response);  
  		}  			 
  	}  	

	private ResultSet formDatabaseOperation(String query){  	
  	   	String errorMsg;
  	   	ResultSet myResultSet=null;
    	try {
      		Connection connection = connectionPool.getConnection();
			myResultSet = DatabaseUtilities.getQueryResultSet(connection, query, false);                                            
      		connectionPool.free(connection);
    	} catch(Exception e) {
      		errorMsg = "Error: " + e; 
      		connectionPool = null;   		
    	}    	   	
    	return(myResultSet); 
  	}
  	
  	private boolean checkDuplicateUserID(String userID){
  		int totUserRec = 0; 
  	   	String errorMsg;
  	   	ResultSet myResultSet=null;
    	try {
    		String query = "SELECT count(*) totUserRec FROM user WHERE userID='" +userID +"'"; 
      		Connection connection = connectionPool.getConnection();
			myResultSet = DatabaseUtilities.getQueryResultSet(connection, query, false);                                            
      		connectionPool.free(connection);
    	} catch(Exception e) {
      		errorMsg = "Error: " + e;    	
      		connectionPool = null;	
    	}
    	                          	
    	if (myResultSet != null){
			try{
				while(myResultSet.next()) {
					totUserRec = Integer.parseInt(myResultSet.getString("totUserRec"));						
				}
			} catch(SQLException sqle) {
				System.err.println("Error connecting: " +sqle);		
			}
		} 
    		
    	if (totUserRec > 0){
    		return(true);
    	}else{
    		return(false);
    	}  	
    }
}