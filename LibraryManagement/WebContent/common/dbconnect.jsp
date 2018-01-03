<%@ page import="sjservlets.*, java.sql.*" %>

<%	
  	ConnectionPool connectionPool;  
	int vendor = DriverUtilities.MYSQL;
    String driver = DriverUtilities.getDriver(vendor);
    String host = "localhost";
    String dbName = "jsp_library";
    String url = DriverUtilities.makeURL(host, dbName, vendor);
    String username = "root";
    String password = "masteryoda";    
    
	try {
   		connectionPool =
        new ConnectionPool(driver, url, username, password, 10, 50, true);
    } catch(SQLException sqle) {
      System.err.println("Error making pool: " + sqle);
      getServletContext().log("Error making pool: " + sqle);
      connectionPool = null;
    }    

    // connectionPool.closeAllConnections();  

%>