package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Link extends Line {
    final int STROKE_WIDTH = 5;

    public Link(Position a, Position b){

        super((a.getColumn()+1)*Point.SCALE, (a.getLine()+1)*Point.SCALE, (b.getColumn()+1)*Point.SCALE, (b.getLine()+1)*Point.SCALE);
        this.setStroke(Color.DARKBLUE);
        this.setStrokeWidth(STROKE_WIDTH);
    }
}
