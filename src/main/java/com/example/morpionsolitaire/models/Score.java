package com.example.morpionsolitaire.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Score {
    private static String playerName;
    private static String date;
    public static int value;


    public Score(String name, int value, String date){
        this(name, value);
        this.date = date;
    }
    public Score(String name, int value){
        this.playerName = name;
        this.value = value;
    }

    public Score() {
        this.value = 0;
        this.playerName = "Guest";
    }

    public static int getValue(){
        return value;
    }

    public static String getPlayerName(){
        return playerName;
    }

    public static String getDate(){
        return date;
    }

    public void increaseScore(){
        this.value++;
    }

    public void setDate(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = myDateObj.format(myFormatObj);
    }
}
