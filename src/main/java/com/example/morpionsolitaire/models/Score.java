package com.example.morpionsolitaire.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {
    private String playerName;
    private SimpleDateFormat date;
    private int value;

    public Score(String name, int value){
        this.playerName = name;
        this.value = value;
    }

    public Score() {
        this.value = 0;
        this.playerName = "Guest";
    }

    public int getValue(){
        return value;
    }

    public String getPlayerName(){
        return playerName;
    }

    public SimpleDateFormat getDate(){
        return date;
    }

    public void increaseScore(){
        this.value++;
    }

    public void setDate(SimpleDateFormat d){
        this.date = d;
    }


}
