package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;

public class Dot{

    final static int SCALE = 200;
    final static int RADIUS = 7;
    final private int coordinateX;
    final private int coordinateY;
    private boolean visibility = false;

    public Dot(int x, int y){
        this.coordinateX = x;
        this.coordinateY = y;
    }

    public int getCoordinateX(){
        return this.coordinateX;
    }

    public int getCoordinateY(){
        return this.coordinateY;
    }

    public boolean isVisibile(){
        return visibility;
    }

    public void setVisibility(boolean v) {
        this.visibility = v;
    }

    public void draw(){
        Circle circle = new Circle(this.coordinateX * SCALE, this.coordinateY*SCALE, RADIUS);
    }
}
