/**
 * This class represents a link between cells in a game of Morpion Solitaire.
 * A link consists of a root cell and a set of nodes, which are connected by the link.
 * The link has a type, which can be either horizontal, vertical, or diagonal.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */

package com.example.morpionsolitaire.models;

public class Link{
    private Cell root;
    private Cell[] nodes;
    private LinkType type;

    /**
     * Constructs a new link with the given root cell, set of nodes, and type.
     * @param r The root cell of the link.
     * @param items The nodes of the link.
     * @param l The type of the link.
     */
    public Link(Cell r, Cell[] items, LinkType l){
        root = r;
        nodes = items;
        type = l;
    }

    /**
     * Returns the first node of the link.
     * @return The first node of the link.
     */
    public Cell getFirstNode(){
        return nodes[0];
    }

    /**
     * Returns the last node of the link.
     * @return The last node of the link.
     */
    public Cell getLastNode(){
        return nodes[4];
    }

    /**
     * Returns the root cell of the link.
     * @return The root cell of the link.
     */
    public Cell getRoot(){
        return root;
    }

    /**
     * Returns the nodes of the link.
     * @return The nodes of the link.
     */
    public Cell[] getNodes(){
        return nodes;
    }

    /**
     * Returns the type of the link.
     * @return The type of the link.
     */
    public LinkType getLinkType(){
        return type;
    }

    /**
     * Returns whether the type of the link is the same as the given type.
     * @param l The type to compare to the type of the link.
     * @return {@code true} if the type of the link is the same as the given type, {@code false} otherwise.
     */
    public boolean isLinkType(LinkType l){
        return type == l;
    }
}
