package com.qa.cgi.testscripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class ExampleDDTthruDatabase {
	
	@Test
	public void DDT() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded"); 
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedetails", "root" ,"Jc_23484");
		//java.sql package
		
		Statement createStatement = con.createStatement();
		
		ResultSet executeQuery = createStatement.executeQuery("select * from employeedetails");
	
		while(executeQuery.next()) {
			String Id = executeQuery.getString("id");
			
			System.out.println(Id);
		}
	}
	

}
