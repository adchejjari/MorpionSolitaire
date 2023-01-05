/**
 * Represents a point on the game grid in the game of Morpion Solitaire.
 *
 * <p>The point has a position on the grid represented by its x and y coordinates,
 * and a value that determines whether it is visible or hidden. The point can also
 * be in a "chosen" state, which indicates that it is a possibility for the next move.
 *
 * <p>The point also has a link to another point on the grid, representing the line
 * that connects the two points.
 *
 * @author Adnan Mathuschan
 * @version 1.0
 * @since 2023-01-05
 */

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


    /**
     * Constructs a new point with the given coordinates and value.
     *
     * @param x       the x coordinate of the point
     * @param y       the y coordinate of the point
     * @param value   the value of the point (0 if hidden, greater than 0 if visible)
     */
    public Point(int x, int y, int value){
        super(( y + OFFSET) * SCALE, ( x + OFFSET) * SCALE, RADIUS); // swap x & y cause the Circle class is based on Cartesian coordinates
        this.coordinateX = x;
        this.coordinateY = y;
        this.value = value;
        this.setVisibility();
    }

    /**
     * Returns the x coordinate of the point.
     *
     * @return the x coordinate of the point
     */
    public int getCoordinateX(){
        return this.coordinateX;
    }

    /**
     * Returns the y coordinate of the point.
     *
     * @return the y coordinate of the point
     */
    public int getCoordinateY(){
        return this.coordinateY;
    }

    /**
     * Returns whether the point is visible.
     *
     * @return true if the point is visible, false if it is hidden
     */
    public boolean isVisibile(){
        return this.value > 0;
    }

    /**
     * Sets the visibility of the point based on its value.
     * A point is visible if its value is greater than 0, and hidden otherwise.
     */
    public void setVisibility(){
        this.setFill( this.value > 0 ? Color.BLACK : Color.TRANSPARENT);
    }

    /**
     * Hides the point by setting its value to 0 and making it invisible.
     */
    public void hide(){
        this.value = 0;
        this.setFill(Color.TRANSPARENT);
        this.textValue.setText("");
    }

    /**
     * Changes the value and appearance of the {@code Point} object when it is clicked.
     * @param v The new value to be set for the {@code Point}.
     */
    public void onDotClick(int v){
        this.value = v;
        if (!this.isVisibile()){
            this.setVisibility();
            this.setValueText();
        }
    }

    /**
     * Shows the point with the given value, and sets the value text.
     *
     * @param v   the value to show on the point
     */
    public void show(int v){
        this.value = v;
        this.setValueText();
        this.setFill(Color.BLACK);
    }

    /**
     * Sets the "chosen" state of the point.
     * If the point is chosen, it is highlighted in red.
     *
     * @param c   true if the point is to be chosen, false if it is to be un-chosen
     */
    public void toChoose(boolean c){ // add root maybe?
        if (isVisibile()){
            choice = c;
            this.setFill( this.choice ? Color.RED : Color.BLACK);
        }
    }

    /**
     * Returns the link between this point and another point.
     *
     * @return the link between this point and another point
     */
    public Link getLink(){
        return this.link;
    }

    /**
     * Returns the root point of this point's link.
     *
     * @return the root point of this point's link
     */
    public Point getRootPoint(){
        return root;
    }

    /**
     * Returns whether the point is in the "chosen" state.
     *
     * @return true if the point is in the "chosen" state, false otherwise
     */
    public boolean isPossibility(){
        return choice;
    }

    /**
     * Sets the value text of the point based on its value.
     */
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

    /**
     * Returns the value text of the point.
     *
     * @return the value text of the point
     */
    public Text getTextValue(){
        return this.textValue;
    }
}
