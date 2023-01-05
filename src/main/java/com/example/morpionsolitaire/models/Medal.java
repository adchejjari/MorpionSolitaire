package com.example.morpionsolitaire.models;

public enum Medal {
    GOLD_MEDAL(0),
    SILVER_MEDAL(1),

    BRONZE_MEDAL(2);

    public int getValue(){
        return value;
    }

    private int value;
    Medal(int i){
        value = i;
    }

}
