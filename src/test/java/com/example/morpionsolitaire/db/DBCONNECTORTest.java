package com.example.morpionsolitaire.db;

import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DBCONNECTORTest {

    @Test
    public void testOpen() {
        DBCONNECTOR db = new DBCONNECTOR();
        db.open();
        assertNotNull(db.connection);
    }

    @Test
    public void testCreateStatement() throws SQLException {
        DBCONNECTOR db = new DBCONNECTOR();
        db.open();

        Statement stmt = db.createStatement("SELECT 1");  // <-- Correction de l'erreur
        assertNotNull(stmt);

        db.close();
    }

    @Test
    public void testPrepareStatement() throws SQLException {
        DBCONNECTOR db = new DBCONNECTOR();
        db.open();

        PreparedStatement stmt = db.prepareStatement("SELECT 1 WHERE 1 = ?");  // <-- Correction de l'erreur
        assertNotNull(stmt);

        db.close();
    }

    @Test
    public void testExecuteStatement() throws SQLException {
        DBCONNECTOR db = new DBCONNECTOR();
        db.open();

        db.executeStatement("CREATE TABLE test (id INTEGER PRIMARY KEY)");  // <-- Requête à exécuter

        db.close();
    }

    @Test
    public void testClose() throws SQLException {
        DBCONNECTOR db = new DBCONNECTOR();
        db.open();
        db.close();  // <-- Appel de la méthode à tester

        // Vérification que la connexion est bien fermée
        assertTrue(db.connection.isClosed());
    }
}