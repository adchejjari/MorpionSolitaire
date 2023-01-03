package com.example.morpionsolitaire.models;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    @Test
    void getValue() {
        assertEquals(0,Score.getValue());
    }

    @Test
    void getPlayerName() {
        assertEquals("Guest",Score.getPlayerName());
    }

    @Test
    void getDate() {
        assertEquals(format.format(date),Score.getDate());
    }

}