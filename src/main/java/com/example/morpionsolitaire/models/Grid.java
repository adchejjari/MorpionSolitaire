package com.example.morpionsolitaire.models;

import com.example.morpionsolitaire.views.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

    final static int WIDTH = 16;
    final static int HEIGHT = 16;

    final private String defaultGrid = "/src/main/java/com/example/morpionsolitaire/grids/default.grid";

    private Cell[][] matrix = new Cell[WIDTH][WIDTH];

    public Grid() throws IOException {
        this.load();
    }

    public void load() throws IOException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString() + defaultGrid;
        for (int i = 0 ; i< HEIGHT; i++) {
            String read = Files.readAllLines(Paths.get(s)).get(i);
            String[] line = read.split("");
            for (int j = 0; j < WIDTH; j++){
                matrix[i][j] = new Cell(i, j, Integer.parseInt(line[j]));
            }
        }
    }

    public void setCell(int line, int column, int value){
        matrix[line][column].setValue(value);
    }

    public Cell getCell(int i, int j){
        return matrix[i][j];
    }

    public Cell[][] getMatrix(){
        return matrix;
    }

    public boolean isCellEmpty(int i, int j){
        return matrix[i][j].getValue() == 0;
    }


    public Move canMoveVertically(int line, int column){ // rename to hasScoredVertical
        int count = 0;
        int firstCellIndex = 0;
        this.matrix[line][column].setValue(1);
        for (int i = 0; i<HEIGHT; i++){
            if(this.matrix[i][column].getValue()>0 && this.matrix[i][column].getLinkType() != LinkType.VERTICAL){
                if(count==0){
                    firstCellIndex = i; // save first cell to link
                }
                count++;
                if (count == 5){
                    break;
                }
            }
            else{
                count = 0;
            }
        }
        if(count==5){
            this.matrix[line][column].setLinkType(LinkType.VERTICAL);
            for(int i = firstCellIndex; i <= firstCellIndex + 4; i++){
                this.matrix[i][column].setLinkType(LinkType.VERTICAL);
            }
            return new Move(true, new Position(firstCellIndex, column), new Position(firstCellIndex+4, column));
        }
        this.matrix[line][column].setValue(0);
        return new Move(false, null, null);
    }

    public Move canMoveHorizontally(int line, int column){
        int count = 0;
        int firstCellIndex = 0;
        this.matrix[line][column].setValue(1);
        for(int i=0; i<WIDTH; i++){
            System.out.print(this.matrix[line][i].getValue());
            if(this.matrix[line][i].getValue()>0 && this.matrix[line][i].getLinkType() != LinkType.HORIZONTAL){
                System.out.println("count : "+count);
                if(count==0){
                    firstCellIndex = i; // save first cell to link
                }
                count++;
                if (count == 5){
                    break;
                }
            }
            else{
                count = 0;
            }
        }

        if(count==5){
            this.matrix[line][column].setLinkType(LinkType.HORIZONTAL);
            for(int i = firstCellIndex; i <= firstCellIndex + 4; i++){
                this.matrix[line][i].setLinkType(LinkType.HORIZONTAL);
            }
            return new Move(true, new Position(line, firstCellIndex), new Position(line, firstCellIndex+4));
        }
        this.matrix[line][column].setValue(0);
        return new Move(false, null, null);
    }

    public Position getSecondDiagonalInitPosition(int i, int j){
        while(i>0 && j>0){
            i--;
            j--;
        }
        return new Position(i,j);
    }

    public Position getFirstDiagonalInitPosition(int i, int j){
        while(i < HEIGHT-1 && j > 0){
            i++;
            j--;
        }
        return new Position(i,j);
    }
    public Move hasScoredSecondDiagonal(int line, int column){
        this.matrix[line][column].setValue(1);
        int count = 0;
        int firstCellLine = 0;
        int firstCellColumn = 0;
        Position initialPosition = this.getFirstDiagonalInitPosition(line,column);
        int tempLineIndex = initialPosition.getLine();
        int tempColumnIndex = initialPosition.getColumn();
        while(tempColumnIndex<WIDTH && tempLineIndex>0){
            if(this.matrix[tempLineIndex][tempColumnIndex].getValue()>0 && this.matrix[tempLineIndex][tempColumnIndex].getLinkType() != LinkType.FIRST_DIAGONAL){
                if(count == 0){
                    firstCellLine = tempLineIndex;
                    firstCellColumn = tempColumnIndex;
                }
                count++;
                if (count == 5){
                    break;
                }
            }
            else{
                count = 0;
            }
            tempColumnIndex++;
            tempLineIndex--;
        }
        if(count==5){
            this.matrix[line][column].setLinkType(LinkType.FIRST_DIAGONAL);
            int j = firstCellColumn;
            for(int i = firstCellLine; i > firstCellLine - 4; i--){
                this.matrix[i][j].setLinkType(LinkType.FIRST_DIAGONAL);
                j++;
            }
            return new Move(true, new Position(firstCellLine, firstCellColumn), new Position(firstCellLine-4, firstCellColumn+4));
        }
        this.matrix[line][column].setValue(0);
        return new Move(false, null, null);
    }
    public Move hasScoredFirstDiagonal(int line, int column){
        this.matrix[line][column].setValue(1);
        int count = 0;
        int firstCellLine = 0;
        int firstCellColumn = 0;
        Position initialPosition = this.getSecondDiagonalInitPosition(line,column);
        int tempLineIndex = initialPosition.getLine();
        int tempColumnIndex = initialPosition.getColumn();
        while(tempColumnIndex<WIDTH && tempLineIndex<HEIGHT){
            if(this.matrix[tempLineIndex][tempColumnIndex].getValue()>0 && this.matrix[tempLineIndex][tempColumnIndex].getLinkType() != LinkType.SECOND_DIAGONAL){
                if(count == 0){
                    firstCellLine = tempLineIndex;
                    firstCellColumn = tempColumnIndex;
                }
                count++;
                if (count == 5){
                    break;
                }
            }
            else{
                count = 0;
            }
            tempColumnIndex++;
            tempLineIndex++;
        }

        if(count==5){
            this.matrix[line][column].setLinkType(LinkType.SECOND_DIAGONAL);
            int j = firstCellColumn;
            for(int i = firstCellLine; i <= firstCellLine + 4; i++){
                this.matrix[i][j].setLinkType(LinkType.SECOND_DIAGONAL);
                j++;
            }
            return new Move(true, new Position(firstCellLine, firstCellColumn), new Position(firstCellLine+4, firstCellColumn+4));
        }
        this.matrix[line][column].setValue(0);
        return new Move(false, null, null);
    }

    public Move canLink(int line, int column){
        Move firstDiagonalMove = this.hasScoredFirstDiagonal(line,column);
        if (firstDiagonalMove.canMakeMove()){
            return firstDiagonalMove;
        }
        Move verticalMove = this.canMoveVertically(line, column);
        if (verticalMove.canMakeMove()){
            return verticalMove;
        }
        Move horizontalMove = this.canMoveHorizontally(line, column);
        if (horizontalMove.canMakeMove()) {
            return horizontalMove;
        }
        Move secondDiagonal = this.hasScoredSecondDiagonal(line, column);
        if (secondDiagonal.canMakeMove()) {
            return secondDiagonal;
        }
        return new Move(false, null, null);
    }

    public void resetCell(int i, int j){
        this.matrix[i][j] = new Cell(i,j,0);
        System.out.println("position : " + i + " " + j);
        for (int k = 0; k< 16; k++){
            for (int m=0 ; m<16 ; m++ ){
                System.out.print(this.matrix[k][m].getValue());
            }
            System.out.println(" p");
        }

        for (int k = 0; k< 16; k++){
            for (int m=0 ; m<16 ; m++ ){
                System.out.print(this.matrix[k][m].getLinkType());
            }
            System.out.println(" p");
        }
    }
}
