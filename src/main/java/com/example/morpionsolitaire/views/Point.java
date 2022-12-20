package com.example.morpionsolitaire.views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point extends Circle {
    final static int SCALE = 35;
    final static int RADIUS = 7;
    final private int coordinateX;
    final private int coordinateY;
    private boolean visibility;
    private final int OFFSET = 1;

    public Point(int x, int y, boolean visible){
        super((y+1)*SCALE, (x+1)*SCALE, RADIUS); // swap x & y cause the Circle class is based on Cartesian coordinates
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
