/**
 * The LinkWidget class represents a visual representation of a link between two cells in the game of Morpion Solitaire.
 *
 * @author Adnan Mathuschan
 * @version 1.0
 * @since 2023-01-05
 */

package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Cell;
import com.example.morpionsolitaire.models.LinkType;
import com.example.morpionsolitaire.models.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LinkWidget extends Line {
    final int STROKE_WIDTH = 5;

    /**
     * Constructs a new LinkWidget object with the specified starting and ending cells.
     *
     * @param a The starting cell of the link.
     * @param b The ending cell of the link.
     */
    public LinkWidget(Cell a, Cell b){

        super((a.getJ()+1)*Point.SCALE, (a.getI()+1)*Point.SCALE, (b.getJ()+1)*Point.SCALE, (b.getI()+1)*Point.SCALE);
        this.setStroke(Color.DARKBLUE);
        this.setStrokeWidth(STROKE_WIDTH);
    }
}
