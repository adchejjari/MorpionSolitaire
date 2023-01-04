package com.example.morpionsolitaire.models;

import javafx.beans.value.ObservableBooleanValue;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {


    @Test
    public void testSetCell() throws IOException {
        // Create a new Grid object
        Grid grid = new Grid();

        // Set the value of a cell in the grid
        grid.setCell(0, 0, 1);

        // Verify that the value of the cell was set correctly
        assertEquals(1, grid.getCell(0, 0).getValue());
    }

    @Test
    public void testGetCell() throws IOException {
        // Create a new Grid object
        Grid grid = new Grid();

        // Set the value of the Cell at position (0, 0) to 1 using the setCell method
        grid.setCell(0, 0, 1);

        // Get the Cell object at position (0, 0) using the getCell method
        Cell cell = grid.getCell(0, 0);

        // Check if the value of the Cell object is 1
        assertEquals(1, cell.getValue());
    }

    @Test
    public void testIsCellEmpty() throws IOException {
        // Create a new Grid object
        Grid grid = new Grid();

        // Set the value of the Cell at position (0, 0) to 0 using the setCell method
        grid.setCell(0, 0, 0);

        // Check if the isCellEmpty method returns true for the Cell at position (0, 0)
        assertTrue(grid.isCellEmpty(0, 0));

        // Set the value of the Cell at position (0, 0) to 1 using the setCell method
        grid.setCell(0, 0, 1);

        // Check if the isCellEmpty method returns false for the Cell at position (0, 0)
        assertFalse(grid.isCellEmpty(0, 0));
    }

}