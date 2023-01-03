package com.example.morpionsolitaire.models;

public class Position {
    private static int line;
    private static int column;

    public Position(int i, int j){
        this.line = i;
        this.column = j;
    }

    public static int getLine(){
        return line;
    }

    public static int getColumn(){
        return column;
    }
}
