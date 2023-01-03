package com.example.morpionsolitaire.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void getLinkedNodes() {
        assertEquals(null,Cell.getLinkedNodes());
    }

    @Test
    void hasMainLink() {
        assertEquals(false,Cell.hasMainLink());
    }

}