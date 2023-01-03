package com.example.morpionsolitaire.models;

import javafx.geometry.Pos;

public class Move {
    private boolean canMakeMove;
    private static Position positionA;
    private static Position positionB;

    public Move(boolean _canMove, Position a, Position b){
        canMakeMove = _canMove;
        positionA = a;
        positionB = b;
    }

    public boolean canMakeMove(){
        return canMakeMove;
    }

    public static Position getPositionA(){
        return positionA;
    }

    public  Position getPositionB(){
        return positionB;
    }
}
