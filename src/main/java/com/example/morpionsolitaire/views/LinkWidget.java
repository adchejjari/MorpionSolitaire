package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Cell;
import com.example.morpionsolitaire.models.LinkType;
import com.example.morpionsolitaire.models.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LinkWidget extends Line {
    final int STROKE_WIDTH = 5;

    public LinkWidget(Cell a, Cell b){

        super((a.getJ()+1)*Point.SCALE, (a.getI()+1)*Point.SCALE, (b.getJ()+1)*Point.SCALE, (b.getI()+1)*Point.SCALE);
        this.setStroke(Color.DARKBLUE);
        this.setStrokeWidth(STROKE_WIDTH);
    }
}
