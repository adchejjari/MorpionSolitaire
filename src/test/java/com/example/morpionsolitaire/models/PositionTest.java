package com.example.morpionsolitaire.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getLine() {
        assertEquals(0,Position.getLine());
    }

    @Test
    void getColumn() {
        assertEquals(0,Position.getColumn());
    }
}