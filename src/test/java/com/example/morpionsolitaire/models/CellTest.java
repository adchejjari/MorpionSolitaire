package com.example.morpionsolitaire.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {


    @Test
    public void testSetLink() {
        // Create a cell and a link
        Cell cell = new Cell(0, 0, 0);
        Link link = new Link(cell, new Cell[] { cell }, LinkType.HORIZONTAL);

        // Set the link for the cell
        cell.setLink(link);

        // Check that the cell's linked nodes are correct
        assertEquals(link.getNodes(), cell.getLinkedNodes().getNodes());

        // Check that the link's cells are correct
        assertTrue(Arrays.asList(link.getNodes()).contains(cell));
    }

    @Test
    public void testLink() {
        // Create a cell
        Cell cell = new Cell(0, 0, 0);

        // Link the cell with a horizontal link
        LinkType linkType = LinkType.HORIZONTAL;
        cell.link(linkType);

        // Check that the cell is linked with a horizontal link
        assertTrue(cell.isLinked(linkType));
    }

    @Test
    public void testSetValue() {
        // Create a cell
        Cell cell = new Cell(0, 0, 0);

        // Set the value of the cell
        int value = 1;
        cell.setValue(value);

        // Check that the cell's value is correct
        assertEquals(value, cell.getValue());
    }

    @Test
    public void testIsLinked() {
        // Create a cell
        Cell cell = new Cell(0, 0, 0);

        // Link the cell with a horizontal link
        LinkType linkType = LinkType.HORIZONTAL;
        cell.link(linkType);

        // Check that the cell is linked with a horizontal link
        assertTrue(cell.isLinked(linkType));

        // Check that the cell is not linked with a vertical link
        assertFalse(cell.isLinked(LinkType.VERTICAL));
    }

    @Test
    public void testSetMainLink() {
        // Create a cell
        Cell cell = new Cell(0, 0, 0);

        // Set the main link type for the cell
        LinkType mainLink = LinkType.HORIZONTAL;
        cell.setMainLink(mainLink);

        // Check that the main link type for the cell is correct
        assertEquals(mainLink, cell.getMainLink());
    }



    @Test
    public void testUnlink() {
        // Create a cell
        Cell cell = new Cell(0, 0, 0);

        // Link the cell with a horizontal link
        LinkType linkType = LinkType.HORIZONTAL;
        cell.link(linkType);

        // Check that the cell is linked with a horizontal link
        assertTrue(cell.isLinked(linkType));

        // Unlink the cell
        cell.unlink(linkType);

        // Check that the cell is not linked with a horizontal link
        assertFalse(cell.isLinked(linkType));
    }

}