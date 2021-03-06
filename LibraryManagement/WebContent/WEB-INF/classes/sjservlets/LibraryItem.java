package sjservlets;

/** A Java Bean to keep Library Item Informatioin.
 *  <P>
 *  &copy; 2002 Song Jing; may be freely used or adapted.
 */
 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class LibraryItem extends ConnectionPoolServlet{
	private int		itemRecNumber=0;
	private String 	itemCallNumber="";
	private String	itemCategory="";
	private String 	itemSubject="";
	private String 	itemMediaType="";
	private String	itemLanguage="";
	private String 	itemTitle="";
	private String 	itemBriefDesc="";
	private String 	itemAuthor="";
	private String 	itemKeyword="";
	private String 	itemPublisherName="";
	private String 	itemISBN="";
	private String	itemLength="";
	private String 	itemYear="";
	private int		itemTotQuantity=0;
	private int 	itemTotReserve=0;
	private int 	itemTotAvailable=0;	
	private int		itemTotBorrow=0;

  	public LibraryItem(){
	}
 	 	
 	public LibraryItem(int itemRecNumbe, String itemCallNumber, String itemCategory, String itemSubject, 
 			String itemMediaType, String itemLanguage, String itemTitle, String itemBriefDesc, String itemAuthor, 
 			String itemKeyword, String itemPublisherName, String itemISBN, String itemLength, String itemYear, 
 			int itemTotQuantity, int itemTotReserve, int itemTotAvailable, int itemTotBorrow){ 		
		setItemRecNumber(itemRecNumber);
		setItemCallNumber(itemCallNumber);
		setItemCategory(itemCategory);
		setItemSubject(itemSubject);
		setItemMediaType(itemMediaType);
		setItemLanguage(itemLanguage);
		setItemTitle(itemTitle);
		setItemBriefDesc(itemBriefDesc);
		setItemAuthor(itemAuthor);
		setItemKeyword(itemKeyword);
		setItemPublisherName(itemPublisherName);
		setItemISBN(itemISBN);
		setItemLength(itemLength);
		setItemYear(itemYear);
		setItemTotQuantity(itemTotQuantity);
		setItemTotReserve(itemTotReserve);
		setItemTotAvailable(itemTotAvailable);	
		setItemTotBorrow(itemTotBorrow);
	}

/*	
	public LibraryItem(int itemRecNumber) {
    	String errorMsg;
    	ResultSet myResultSet = null;
    	try {
      		String query = "SELECT * FROM libraryitem where itemRecNumber = '" +itemRecNumber +"'";      
      		Connection connection = connectionPool.getConnection();
			myResultSet = DatabaseUtilities.getQueryResultSet(connection, query, false);                                            
      		connectionPool.free(connection);
    	} catch(Exception e) {
      		errorMsg = "Error: " + e;    		
    	}                   	
        
        resetVariable();        
                    	
    	if (myResultSet != null){
			try{
				while(myResultSet.next()) {
					itemRecNumber = Integer.parseInt(myResultSet.getString("itemRecNumber"));
					itemTotAvailable = Integer.parseInt(myResultSet.getString("itemTotAvailable"));	
				}
			} catch(SQLException sqle) {
				System.err.println("Error connecting: " +sqle);		
			}
		} 
	}
*/
	
	public void setPropertyFromRequestParameter(HttpServletRequest request){
		//setItemRecNumber(Integer.parseInt(request.getParameter("ItemRecNumber")));
		setItemCallNumber(request.getParameter("itemCallNumber"));
		setItemCategory(request.getParameter("itemCategory"));
		setItemSubject(request.getParameter("itemSubject"));
		setItemMediaType(request.getParameter("itemMediaType"));
		setItemLanguage(request.getParameter("itemLanguage"));
		setItemTitle(request.getParameter("itemTitle"));
		setItemBriefDesc(request.getParameter("itemBriefDesc"));
		setItemAuthor(request.getParameter("itemAuthor"));
		setItemKeyword(request.getParameter("itemKeyword"));
		setItemPublisherName(request.getParameter("itemPublisherName"));
		setItemISBN(request.getParameter("itemISBN"));
		setItemLength(request.getParameter("itemLength"));
		setItemYear(request.getParameter("itemYear"));
		setItemTotQuantity(Integer.parseInt(request.getParameter("itemTotQuantity")));
		setItemTotReserve(Integer.parseInt(request.getParameter("itemTotReserve")));
		setItemTotAvailable(Integer.parseInt(request.getParameter("itemTotAvailable")));
		setItemTotBorrow(Integer.parseInt(request.getParameter("itemTotBorrow")));
 	}
 	
 	public void resetVariable() {
 		//setUserRecNumber(0);
 		setItemRecNumber(0);
		setItemCallNumber("");
		setItemCategory("");
		setItemSubject("");
		setItemMediaType("");
		setItemLanguage("");
		setItemTitle("");
	 	setItemBriefDesc("");
		setItemAuthor("");
		setItemKeyword("");
		setItemLanguage("");
		setItemPublisherName("");
		setItemISBN("");
		setItemLength("");
		setItemYear("");
		setItemTotQuantity(0);
		setItemTotReserve(0);
		setItemTotAvailable(0);
		setItemTotBorrow(0);
	}

 	public void setItemRecNumber(int itemRecNumber) {
 		this.itemRecNumber = itemRecNumber;
 	}
  	public void setItemCallNumber(String itemCallNumber) {
		this.itemCallNumber = itemCallNumber;
	}	
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public void setItemSubject(String itemSubject){
		this.itemSubject = itemSubject;
	}
	public void setItemMediaType(String itemMediaType){
		this.itemMediaType = itemMediaType;
	}
	public void setItemLanguage(String itemLanguage){
		this.itemLanguage = itemLanguage;
	}
	public void setItemTitle(String itemTitle){
		this.itemTitle = itemTitle;
	}
	public void setItemBriefDesc(String itemBriefDesc){
		this.itemBriefDesc = itemBriefDesc;
	}
	public void setItemAuthor(String itemAuthor){
		this.itemAuthor = itemAuthor;
	}
	public void setItemKeyword(String itemKeyword){
		this.itemKeyword = itemKeyword;
	}
	public void setItemPublisherName(String itemPublisherName){
		this.itemPublisherName = itemPublisherName;
	}
	public void setItemISBN(String itemISBN){
		this.itemISBN = itemISBN;
	}
	public void setItemLength(String itemLength){
		this.itemLength = itemLength;
	}
	public void setItemYear(String itemYear){
		this.itemYear = itemYear;
	}
	public void setItemTotQuantity(int itemTotQuantity){
		this.itemTotQuantity = itemTotQuantity;
	}
	public void setItemTotReserve(int itemTotReserve){
		this.itemTotReserve = itemTotReserve;
	}
	public void setItemTotAvailable(int itemTotAvailable){
		this.itemTotAvailable = itemTotAvailable;
	}
	public void setItemTotBorrow(int itemTotBorrow) {
		this.itemTotBorrow = itemTotBorrow;
	}
	
	public int getItemRecNumber(){
		return itemRecNumber;
	}
	public String getItemCallNumber(){
		return itemCallNumber;
	}
	public String getItemCategory(){
		return itemCategory;
	}
	public String getItemSubject(){
		return itemSubject;
	}
	public String getItemMediaType(){
		return itemMediaType;
	}
	public String getItemLanguage(){
		return itemLanguage;
	}
	public String getItemTitle(){
		return itemTitle;
	}
	public String getItemBriefDesc(){
		return itemBriefDesc;
	}
	public String getItemAuthor(){
		return itemAuthor;
	}
	public String getItemKeyword(){
		return itemKeyword;
	}
	public String getItemPublisherName(){
		return itemPublisherName;
	}
	public String getItemISBN(){
		return itemISBN;
	}
	public String getItemLength(){
		return itemLength;
	}
	public String getItemYear(){
		return itemYear;
	}
	public int getItemTotQuantity(){
		return itemTotQuantity;
	}
	public int getItemTotReserve(){
		return itemTotReserve;
	}
	public int getItemTotAvailable(){
		return itemTotAvailable;	
	}
	public int getItemTotBorrow(){
		return itemTotBorrow;
	}

}
  
