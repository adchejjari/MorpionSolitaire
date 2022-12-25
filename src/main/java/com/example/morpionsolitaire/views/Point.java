package com.example.morpionsolitaire.views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Point extends Circle {
    final static int SCALE = 35;
    final static int RADIUS = 8;
    final static int TEXT_SIZE = 9;
    final private int coordinateX;
    final private int coordinateY;
    private boolean visibility;
    private final int OFFSET = 1;
    private Text value;

    public Point(int x, int y, boolean visible){
        super((y+1)*SCALE, (x+1)*SCALE, RADIUS); // swap x & y cause the Circle class is based on Cartesian coordinates
        this.coordinateX = (y+1)*SCALE;
        this.coordinateY = (x+1)*SCALE;
        this.visibility = visible;
        this.setVisibility();
        this.value = null;
    }

    public Point(int x, int y, boolean visible, int _value){
        this(x,y,visible);
        this.setValueText(_value);
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

    public Text getValue(){
        return value;
    }

    public void setValue(int v){
        setValueText(v);
    }

    private void setValueText(int v){
        this.value = new Text(Integer.toString(v));
        value.setFont(Font.font(TEXT_SIZE));
        value.setStyle("-fx-font-weight: bold");
        double W = value.getBoundsInLocal().getWidth();
        double H = value.getBoundsInLocal().getHeight();
        value.relocate(this.getCoordinateX() - W / 2, this.getCoordinateY() - H / 2);
        value.setFill(Color.WHITE);
    }
}
