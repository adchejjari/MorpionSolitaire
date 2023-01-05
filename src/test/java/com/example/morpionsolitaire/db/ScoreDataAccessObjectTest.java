package com.example.morpionsolitaire.db;

import com.example.morpionsolitaire.models.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreDataAccessObjectTest {

    ScoreDataAccessObject dao;

    @BeforeEach
    void setUp() {
        dao = new ScoreDataAccessObject();
    }

    @Test
    void testGetAll() throws SQLException {
        List<Score> scores = dao.getAll();
        assertTrue(scores.size() >= 0);
    }

    @Test
    void testInsert() throws SQLException {
        Score s = new Score("Test Player", 100, "2022-01-01");
        int numRows = dao.getAll().size();
        dao.insert(s);
        assertEquals(numRows + 1, dao.getAll().size());
    }
}