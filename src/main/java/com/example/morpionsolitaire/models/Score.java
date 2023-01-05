/**
 * A class representing a score in a game.
 *
 * @author  Adnan Mathuschan
 * @version 1.0
 * @since   2023/01/05
 */

package com.example.morpionsolitaire.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Score {
    private String playerName;
    private String date;
    private int value;

    /**
     * Constructs a new Score object with the given player name, value, and date.
     *
     * @param name the name of the player who achieved the score
     * @param value the value of the score
     * @param date the date when the score was achieved
     */
    public Score(String name, int value, String date){
        this(name, value);
        this.date = date;
    }

    /**
     * Constructs a new Score object with the given player name and value.
     * The date will be set to the current date.
     *
     * @param name the name of the player who achieved the score
     * @param value the value of the score
     */
    public Score(String name, int value){
        this.playerName = name;
        this.value = value;
    }

    /**
     * Constructs a new Score object with default values.
     * The player name will be set to "Guest" and the value will be set to 0.
     * The date will be set to the current date.
     */
    public Score() {
        this.value = 0;
        this.playerName = "Guest";
    }

    /**
     * Returns the value of the score.
     *
     * @return the value of the score
     */
    public int getValue(){
        return value;
    }

    /**
     * Returns the name of the player who achieved the score.
     *
     * @return the name of the player who achieved the score
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Returns the date when the score was achieved.
     *
     * @return the date when the score was achieved
     */
    public String getDate(){
        return date;
    }

    /**
     * Increases the value of the score by 1.
     */
    public void increaseScore(){
        this.value++;
    }

    /**
     * Sets the date of the score to the current date.
     */
    public void setDate(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = myDateObj.format(myFormatObj);
    }
}
