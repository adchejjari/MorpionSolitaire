package com.example.morpionsolitaire.models;

public class Position {
    private int line;
    private int column;

    public Position(int i, int j){
        this.line = i;
        this.column = j;
    }

    public int getLine(){
        return line;
    }

    public int getColumn(){
        return column;
    }
}
