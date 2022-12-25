package com.example.morpionsolitaire.db;

import com.example.morpionsolitaire.models.Score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreDataAccessObject {
    private final String TABLE = "scores";
    protected final String ORDRED_BY = "date";

    private static final String[] COLUMNS = {
            "date",
            "name",
            "score"
    };

    public List<Score> getAll() throws SQLException {
        String query = "SELECT * FROM scores";
        //Statement stmt  = conn.createStatement()
        //ResultSet rs    = stmt.executeQuery(query);
        return new ArrayList<Score>();
    }

    public void update(Score s){
        String query = "UPDATE ...";
    }

    public void insert(Score s){


    }
}
