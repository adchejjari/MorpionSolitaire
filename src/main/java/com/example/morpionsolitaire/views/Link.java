package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Link extends Line {
    final int STROKE_WIDTH = 4;

    public Link(Position a, Position b){

        super((5+1)*Point.SCALE, (3+1)*Point.SCALE, (9+1)*Point.SCALE, (3+1)*Point.SCALE);
        System.out.println(a.getColumn());
        System.out.println(a.getLine());
        this.setStroke(Color.RED);
        this.setStrokeWidth(STROKE_WIDTH);
    }
}
