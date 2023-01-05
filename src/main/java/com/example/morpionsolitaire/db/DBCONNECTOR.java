/**

 DBCONNECTOR is a class that provides methods for connecting to and interacting with an SQLite database.
 The database is specified in the DB_URL field and is opened upon calling the open() method.
 The initScheme() method can be used to initialize the schema for the database if it does not already exist.
 The createStatement(), prepare Statement(), and execute Statement() methods can be used to send SQL statements to the database.
 The close() method can be used to close the connection to the database.
 */

package com.example.morpionsolitaire.db;

import java.sql.*;

public class DBCONNECTOR {
    // JDBC driver name and database URL
    private String DB_URL = "jdbc:sqlite:data/ms.db";
    public Connection connection = null;
    /**

     Opens a connection to the database specified in the DB_URL field.
     */
    public void open() {
        try {
            this.connection = DriverManager.getConnection(DB_URL);
            if (this.connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                this.initScheme();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**

     Initializes the scheme for the database if it does not already exist.
     */
    public void initScheme(){
        String query = "CREATE TABLE IF NOT EXISTS score (\n" +
                       "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                       "name VARCHAR(255) NOT NULL,\n" +
                       "date VARCHAR(255) NOT NULL,\n" +
                       "score INTEGER DEFAULT 0 NOT NULL\n" +
                       ");";
        try {
            this.connection = DriverManager.getConnection(DB_URL);
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**

     Creates a Statement object for sending SQL statements to the database.
     @param sqlStatement the SQL statement to be sent to the database
     @return a Statement object that can be used to execute the given SQL statement
     @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public Statement createStatement(String sqlStatement) throws SQLException {
        return this.connection.createStatement();
    }

    /**

     Creates a PreparedStatement object for sending parameterized SQL statements to the database.
     @param sql Statement the SQL statement to be sent to the database
     @return a PreparedStatement object that can be used to execute the given SQL statement
     @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public PreparedStatement prepareStatement(String sqlStatement) throws SQLException{
        return this.connection.prepareStatement(sqlStatement);
    }

    /**

     Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement or an SQL statement that returns nothing, such as an SQL DDL statement.
     @param sqlStatement the SQL statement to be sent to the database
     @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public void executeStatement(String sqlStatement) throws SQLException {
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sqlStatement);
        }
    }

    /**

     Closes the connection to the database.
     */
    public void close(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
