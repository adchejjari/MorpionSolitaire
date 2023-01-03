package com.example.morpionsolitaire.views;

import com.example.morpionsolitaire.models.Link;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Point extends Circle {
    public final static int SCALE = 35;
    final static int RADIUS = 8;
    final static int TEXT_SIZE = 9;
    final private int coordinateX;
    final private int coordinateY;
    private static final int OFFSET = 1;
    private Text textValue;
    private int value;

    Link link;

    Point root;

    private boolean choice = false;

    public Point(int x, int y, int value){
        super(( y + OFFSET) * SCALE, ( x + OFFSET) * SCALE, RADIUS); // swap x & y cause the Circle class is based on Cartesian coordinates
        this.coordinateX = x;
        this.coordinateY = y;
        this.value = value;
        this.setVisibility();
    }


    public int getCoordinateX(){
        return this.coordinateX;
    }

    public int getCoordinateY(){
        return this.coordinateY;
    }

    public boolean isVisibile(){
        return this.value > 0;
    }

    public void setVisibility(){
        this.setFill( this.value > 0 ? Color.BLACK : Color.TRANSPARENT);
    }


    public void hide(){
        this.value = 0;
        this.setFill(Color.TRANSPARENT);
        this.textValue.setText("");
    }

    public void onDotClick(int v){
        this.value = v;
        if (!this.isVisibile()){
            this.setVisibility();
            this.setValueText();
        }
    }

    public void show(int v){
        this.value = v;
        this.setValueText();
        this.setFill(Color.BLACK);
    }

    public void toChoose(boolean c){ // add root maybe?
        if (isVisibile()){
            choice = c;
            this.setFill( this.choice ? Color.RED : Color.BLACK);
        }
    }

    public Link getLink(){
        return this.link;
    }

    public Point getRootPoint(){
        return root;
    }

    public boolean isPossibility(){
        return choice;
    }


    private void setValueText(){
        if(this.value > 1){
            this.textValue = new Text(Integer.toString(this.value-1));
            this.textValue.setFont(Font.font(TEXT_SIZE));
            this.textValue.setStyle("-fx-font-weight: bold");
            double W = this.textValue.getBoundsInLocal().getWidth();
            double H = this.textValue.getBoundsInLocal().getHeight();
            this.textValue.relocate((this.getCoordinateY() + 1) * SCALE - W / 2, (this.getCoordinateX() + 1) * SCALE - H / 2);
            this.textValue.setFill(Color.WHITE);
        }
    }

    public Text getTextValue(){
        return this.textValue;
    }
}
