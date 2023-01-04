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
    public void testUndoLink() {
        // Create a cell and a link
        Cell cell1 = new Cell(0, 0, 0);
        Cell cell2 = new Cell(1, 1, 1);
        Link link = new Link(cell1, new Cell[] { cell1, cell2 }, LinkType.HORIZONTAL);
        cell1.setLink(link);

        // Set the main link type for the cells
        LinkType mainLink = LinkType.HORIZONTAL;
        cell1.setMainLink(mainLink);
        cell2.setMainLink(mainLink);

        // Link the cells with the main link type
        cell1.link(mainLink);
        cell2.link(mainLink);

        // Check that the cells are linked with the main link type
        assertTrue(cell1.isLinked(mainLink));
        assertTrue(cell2.isLinked(mainLink));

        // Undo the link
        cell1.undoLink();

        // Check that the cells are no longer linked with the main link type
        assertFalse(cell1.isLinked(mainLink));
        assertFalse(cell2.isLinked(mainLink));
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
    public void testHasMainLink() {
        // Create a cell with a main link
        Cell cell = new Cell(0, 0, 0);
        LinkType mainLink = LinkType.HORIZONTAL;
        cell.setMainLink(mainLink);

        // Check that the cell has a main link
        assertTrue(cell.hasMainLink());

        // Create a cell with no main link
        Cell cell2 = new Cell(1, 1, 1);

        // Check that the cell has no main link
        assertTrue(cell2.hasMainLink());
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