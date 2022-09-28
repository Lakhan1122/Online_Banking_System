package com.banking;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	
	 static Connection con; // Global Connection Object
	    public static Connection getConnection()
	    {
	        try {
	            
	            
	            String mysqlJDBCDriver
	                = "com.mysql.cj.jdbc.Driver"; //jdbc driver
	            String url
	                = "jdbc:mysql://localhost:3306/bank"; //mysql url
	            String user = "root";        //mysql username
	            String pass = "1122";  //mysql passcode
	             Class.forName(mysqlJDBCDriver);
	            con = DriverManager.getConnection(url, user,
	                                              pass);
	        }
	        catch (Exception e) {
	            System.out.println("Connection Failed!");
	        }
	 
	        return con;
	    }

}
