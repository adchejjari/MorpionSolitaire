package com.example.morpionsolitaire.db;

import java.sql.*;

public class DBCONNECTOR {
    // JDBC driver name and database URL
    private String DB_URL = "jdbc:sqlite:data/ms.db";
    public Connection connection = null;

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

    public Statement createStatement(String sqlStatement) throws SQLException {
        return this.connection.createStatement();
    }

    public PreparedStatement prepareStatement(String sqlStatement) throws SQLException{
        return this.connection.prepareStatement(sqlStatement);
    }

    public void executeStatement(String sqlStatement) throws SQLException {
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sqlStatement);
        }
    }

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
