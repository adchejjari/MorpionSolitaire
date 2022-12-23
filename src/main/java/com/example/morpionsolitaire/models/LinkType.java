package com.example.morpionsolitaire.models;

public enum LinkType {

    NONE(0),
    HORIZONTAL(1),
    VERTICAL(2),
    DIAGONAL(3);
    private int type;
    LinkType(int t){ this.type = t;}
}
