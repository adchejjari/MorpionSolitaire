/**

 * This is an enumeration that represents the type of link that can be made between two dots in the Morpion Solitaire game.
 * The link can be either horizontal, vertical, or diagonal.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */


package com.example.morpionsolitaire.models;

public enum LinkType {

    NONE(0),
    HORIZONTAL(1),
    VERTICAL(2),
    FIRST_DIAGONAL(3),
    SECOND_DIAGONAL(4);
    private int type;

    /**
     * Constructs a new LinkType with the given integer value.
     * @param t The integer value of the link type.
    */
    LinkType(int t){ this.type = t;}

}
