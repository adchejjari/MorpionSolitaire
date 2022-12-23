package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Link extends Line {
    final int STROKE_WIDTH = 4;

    public Link(Position a, Position b){

        super((a.getColumn()+1)*Point.SCALE, (a.getLine()+1)*Point.SCALE, (b.getColumn()+1)*Point.SCALE, (b.getLine()+1)*Point.SCALE);
        //System.out.println(a.getColumn() + " " + a.getLine());
        //System.out.println(b.getColumn() + " " + b.getLine());
        this.setStroke(Color.RED);
        this.setStrokeWidth(STROKE_WIDTH);
    }
}
