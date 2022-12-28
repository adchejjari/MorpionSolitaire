package com.example.morpionsolitaire.models;

public enum LinkType {

    NONE(0),
    HORIZONTAL(1),
    VERTICAL(2),
    FIRST_DIAGONAL(3),
    SECOND_DIAGONAL(4);
    private int type;
    LinkType(int t){ this.type = t;}

}
