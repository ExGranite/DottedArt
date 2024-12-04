package da.jade.project;

import java.sql.*;

public class CreateTable {
   public static void createAll(Connection c) {
	   Statement stmt = null;
	   String sql = null;
	   try {
//	   stmt = c.createStatement();
//       sql = "CREATE TABLE PAYMENT " +
//                      "(PAYMENT_ID INT PRIMARY KEY     NOT NULL," +
//                      " USER_ID           INT, " + 
//                      " CARD_TYPE            CHAR(50), " + 
//                      " CARD_NUMBER        CHAR(50), " + 
//                      " CARD_MONTH         CHAR(50), " + 
//                      " CARD_YEAR        CHAR(50), " + 
//                      " ADDRESS        CHAR(50))"; 
//       stmt.executeUpdate(sql);
//       stmt.close();
       
//       stmt = c.createStatement();
//       sql = "CREATE TABLE ACCOUNT " +
//                      "(USER_ID INT PRIMARY KEY     NOT NULL," +
//                      " USER_TYPE           CHAR(50), " + 
//                      " FNAME            CHAR(50), " + 
//                      " LNAME        CHAR(50), " + 
//                      " EMAIL         CHAR(50), " + 
//                      " PASSWORD        CHAR(50))"; 
//       stmt.executeUpdate(sql);
//       stmt.close();
       
//       stmt = c.createStatement();
//       sql = "CREATE TABLE ARTWORK " +
//                      "(ART_ID INT PRIMARY KEY     NOT NULL," +
//                      " NAME           CHAR(50), " + 
//                      " DESCRIPTION            CHAR(50), " + 
//                      " GENRE        CHAR(50), " + 
//                      " TAGS         CHAR(50), " + 
//                      " STATUS         CHAR(50), " + 
//                      " ARTIST_ID         INT, " + 
//                      " OWNER_ID         INT, " + 
//                      " PRICE        CHAR(50))"; 
//       stmt.executeUpdate(sql);
//       stmt.close();
       
//       stmt = c.createStatement();
//       sql = "CREATE TABLE RECOMMENDATION " +
//                      "(RECOMMENDATION_ID INT PRIMARY KEY     NOT NULL," +
//                      " USER_ID           INT, " + 
//                      " TAGS        CHAR(50))"; 
//       stmt.executeUpdate(sql);
//       stmt.close();

//       stmt = c.createStatement();
//       sql = "CREATE TABLE CATEGORY " +
//                      "(CATEGORY_ID INT PRIMARY KEY     NOT NULL," +
//                      " ART_ID           INT, " + 
//                      " TAGS        CHAR(50))"; 
//       stmt.executeUpdate(sql);
//       stmt.close();
	   } catch ( Exception e ) {
	    	  System.out.println(e.getMessage());
	   }
   }
   
   public static ResultSet getData(Connection c, String st) {
	   ResultSet rs = null;
	   try {
		   rs = c.createStatement().executeQuery(st);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return rs;
   }
   
   public static void editData(Connection c, String st) {
	   try {
		   c.createStatement().executeUpdate(st);
//		   c.commit();
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
   }
   
   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      
      String URL = "jdbc:sqlite:dottedart.db";
//      private static final 
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection(URL);
         System.out.println("Opened database successfully");

//         createAll(c);
//         String t1 = "DELETE FROM ARTWORK WHERE art_id = 7;";
//         String t2 = "INSERT INTO CATEGORY (category_id, art_id, tags) VALUES (2, 1, 'Wet');";
//         String t3 = "UPDATE CATEGORY set ART_ID = 1 where TAGS = 'Wet';";
//         editData(c, t1);
//         editData(c, t1);
//         ResultSet rs = getData(c, "SELECT * FROM ACCOUNT WHERE USER_ID = '1'");
//         while (rs.next()) {
//        	 int user_id = rs.getInt("user_id");
////        	 int art_id = rs.getInt("art_id");
////        	 String tags = rs.getString("tags");
//        	 System.out.printf("ID: %d.%n", user_id);
//         }
//         System.out.println("Data is " + rs);
         
         
         c.close();
      } catch ( Exception e ) {
    	  System.out.println(e.getMessage());
      }
      System.out.println("Done");
   }
}