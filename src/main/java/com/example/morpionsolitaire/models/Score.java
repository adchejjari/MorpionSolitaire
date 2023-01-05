package com.example.morpionsolitaire.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Score {
    private String playerName;
    private String date;
    private int value;


    public Score(String name, int value, String date){
        this(name, value);
        this.date = date;
    }
    public Score(String name, int value){
        this.playerName = name;
        this.value = value;
        this.setDate();
        if(Objects.equals(name, "") || name==null){
            this.playerName = "Guest";
        }
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

    public String getDate(){
        return date;
    }

    public void setDate(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = myDateObj.format(myFormatObj);
    }
}
