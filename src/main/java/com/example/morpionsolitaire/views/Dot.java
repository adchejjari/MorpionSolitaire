package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class Dot extends Circle {

    final static int SCALE = 35;
    final static int RADIUS = 7;
    final private int coordinateX;
    final private int coordinateY;
    private boolean visibility;

    public Dot(int x, int y, boolean visible){
        super( x * SCALE, y * SCALE, RADIUS);
        this.coordinateX = x;
        this.coordinateY = y;
        this.visibility = visible;
        this.setVisibility();
    }

    public double getCoordinateX(){
        return this.coordinateX;
    }

    public double getCoordinateY(){
        return this.coordinateY;
    }

    public boolean isVisibile(){
        return visibility;
    }


    private void setVisibility(){
        this.setFill(this.isVisibile() ? Color.BLACK : Color.TRANSPARENT);
    }

    public void onDotClick(){
        if (!this.isVisibile()){
            this.visibility = true;
            this.setVisibility();
        }
    }

}
