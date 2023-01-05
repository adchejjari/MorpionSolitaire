/**
 * Represents a cell in the Morpion Solitaire game.
 *
 * Each cell has a value, a row index `i`, a column index `j`, and a set of links to
 * other cells. The links are represented by the `LinkType` enum, which can be one of:
 * NONE, UP, DOWN, LEFT, RIGHT.
 *
 * The `Cell` class also has a `parent` field, which refers to the cell's parent in the
 * game tree. The `mainLink` field is the link type used to reach this cell from its
 * parent.
 *
 * The `linkedNodes` field represents a linked list of cells that are linked to this cell.
 * If the `linkedNodes` field is not null, it means that this cell is part of a chain of
 * linked cells.
 *
 * The `canBeSelected` field is a flag that indicates whether or not the cell can be
 * selected by the player.
 *
 * The `extremity` field is a flag that indicates whether or not the cell is an extremity
 * of a chain of linked cells.
 *
 *  @author Adnan Mathuschan
 *  @version 1.0
 *  @since 2023-01-05
 */

package com.example.morpionsolitaire.models;

import java.util.*;

public class Cell {
    private int value;
    final private int i;
    final private int j;
    private Set<LinkType> links;
    Cell parent;
    private LinkType mainLink = LinkType.NONE;
    private Link linkedNodes;
    private boolean canBeSelected = false;

    private boolean extremity = false;

    /**
     * Constructs a new `Cell` with the given row index `_i`, column index `_j`, and value `_v`.
     * @param _i the row index of the cell
     * @param _j the column index of the cell
     * @param _v the value of the cell
     */
    public Cell(int _i, int _j, int _v){
        this.i = _i;
        this.j = _j;
        this.value = _v;
        this.links = new HashSet<LinkType>();

    }

    /**
     * Returns true if this cell is an extremity of a chain of linked cells, false otherwise.
     * @return true if this cell is an extremity of a chain of linked cells, false otherwise
     */
    public boolean isExtremity(){
        return extremity;
    }

    /**
     * Sets the `extremity` field of this cell to the given boolean value `b`.
     * @param b the value to set the `extremity` field to
     */
    public void setExtremity(boolean b){
        extremity = b;
    }

    /**
     * Returns true if this cell is part of a chain of linked cells, false otherwise.
     * @return true if this cell is part of a chain of linked cells, false otherwise
     */
    public boolean isLinking(){
        return linkedNodes != null;
    }

    /**
     * Sets the `canBeSelected` field of this cell to the given boolean value `b`.
     * @param b the value to set the `canBeSelected` field to
     */
    public void setCanBeSelected(boolean b){
        canBeSelected = b;
    }

    public boolean canBeSelected(){
        return canBeSelected;
    }


    public void setLink(Link lnk){
        linkedNodes = lnk;
    }

    /**
     * Returns the `linkedNodes` field of this cell.
     * @return the `linkedNodes` field of this cell
     */
    public Link getLinkedNodes(){
        return linkedNodes;
    }

    public Cell getParent(){
        return this.parent;
    }


    public void link(LinkType lt){
        this.links.add(lt);
    }

    /**
     * Sets the value of this cell to the given integer `v`.
     * @param v the value to set this cell to
     */
    public void setValue(int v){
        this.value = v;
    }

    public int getI(){
        return this.i;
    }

    public int getJ(){
        return this.j;
    }

    /**
     * Returns the value of this cell.
     * @return the value of this
     */
    public int getValue(){
        return this.value;
    }

    /**
     * Returns true if this cell is linked to another cell in the given `LinkType` direction,
     * false otherwise.
     * @param lnk the `LinkType` direction to check for a link
     * @return true if this cell is linked to another cell in the given `LinkType` direction,
     * false otherwise
     */
    public boolean isLinked(LinkType lnk){
        for(LinkType l : this.links){
            if (l == lnk){
                return true;
            }
        }
        return false;
    }

    public void setMainLink(LinkType l){
        this.mainLink = l;
    }

    public LinkType getMainLink(){
        return this.mainLink;
    }

    /**
     * Removes the given `LinkType` object from the set of links of this cell.
     * @param t the `LinkType` object to remove from the set of links of this cell
     */
    public void unlink(LinkType t){
        for (LinkType l : this.links){
            if (t == l){
                links.remove(t);
                break;
            }
        }
    }
}

