package com.example.morpionsolitaire.data_base;
import java.sql.*;

public class BDD {
    public static void main(String[] args){
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			@SuppressWarnings("resource")
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/database", "root", "root");
			
			@SuppressWarnings("resource")
			Statement st = con.createStatement();
			
			@SuppressWarnings("resource")
			ResultSet res = st.executeQuery("select * from score");
			
			//Insertion de donnée 
			// String query = "INSERT INTO score ('nom','score','date','heure') values ('willy','111','18/07/1999','18:45')";
			//st.executeUpdate(query);
			while(res.next()) {
				System.out.println("Nom : " + res.getString(2) + " score : " + res.getString(3) + " date : " + res.getString(4) + " heure : " + res.getString(5));
				System.out.println("------------------------------------------------");
			}
			
    	} catch (Exception e) {
			throw new IllegalStateException(e);
		}
    }
}
