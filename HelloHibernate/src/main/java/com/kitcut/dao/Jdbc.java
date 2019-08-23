package com.kitcut.dao;

import java.sql.*;
import java.util.Date;

public class Jdbc {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "123456";

    public static void execute() {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Employee";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    public static void executeBatch() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create statement object
            stmt = conn.createStatement();

// Set auto-commit to false
            conn.setAutoCommit(false);

// Create SQL statement
            int size = 100000;
//            System.out.println(new Date());
//            for(int i = 0; i< size; i++){
//                String SQL1 = "INSERT INTO Employee (first_name, last_name) " +
//                        "VALUES('Zia', 'Ali')";
//
//                stmt.execute(SQL1);
//            }
            System.out.println(new Date());

            for(int i = 0; i< size; i++){
                String SQL1 = "INSERT INTO Employee (first_name, last_name) " +
                        "VALUES('Zia', 'Ali')";

                stmt.addBatch(SQL1);
            }
            stmt.executeBatch();
            System.out.println(new Date());

// Add above SQL statement in the batch.

// Create an int[] to hold returned values
            int[] count = stmt.executeBatch();

//Explicitly commit statements to apply changes
            conn.commit();
            //STEP 6: Clean-up environment
            stmt.close();
            conn.close();
        } catch (
                SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (
                Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
