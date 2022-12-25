package com.example.morpionsolitaire.db;

import com.example.morpionsolitaire.models.Score;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreDataAccessObject {
    private final String TABLE = "scores";
    protected final String ORDRED_BY = "date";

    DBCONNECTOR db = new DBCONNECTOR();

    private static final String[] COLUMNS = {
            "date",
            "name",
            "score"
    };

    public List<Score> getAll() throws SQLException {
        List<Score> scores = new ArrayList<>();
        db.open();
        String query = "SELECT * FROM score";
        Statement stmt  = db.createStatement(query);
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Score s = new Score(rs.getString("name"),rs.getInt("score"), rs.getString("date"));
            scores.add(s);
        }
        db.close();
        return scores;
    }

    public void insert(Score s) throws SQLException {
        db.open();
        String query = "INSERT INTO score(name,score, date) VALUES(?,?,?);";
        PreparedStatement pstmt = db.prepareStatement(query);
        pstmt.setString(1, s.getPlayerName());
        pstmt.setInt(2, s.getValue());
        pstmt.setString(3, s.getDate());
        pstmt.executeUpdate();
        db.close();
    }
}
