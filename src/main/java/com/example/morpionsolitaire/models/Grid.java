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


    public Link canJoinVertically(int line, int column){ // rename to hasScoredVertical
        int count = 0;
        int firstCellIndex = 0;
        this.matrix[line][column].setValue(1);
        for (int i = 0; i<HEIGHT; i++){
            if(this.matrix[i][column].getValue()>0 && !this.matrix[i][column].isLinked(LinkType.VERTICAL)){
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
            this.matrix[line][column].setMainLink(LinkType.VERTICAL);
            Cell[] items = new Cell[5];
            for(int i = firstCellIndex; i <= firstCellIndex + 4; i++){
                this.matrix[i][column].link(LinkType.VERTICAL);
                items[i-firstCellIndex] = this.matrix[i][column];
            }
            Link l = new Link(this.matrix[line][column], items, LinkType.VERTICAL);
            this.matrix[line][column].setLink(l);
            return l;
        }
        this.matrix[line][column].setValue(0);
        return null;
    }

    public Link canJoinHorizontally(int line, int column){
        int count = 0;
        int firstCellIndex = 0;
        this.matrix[line][column].setValue(1);
        for(int i=0; i<WIDTH; i++){
            System.out.print(this.matrix[line][i].getValue());
            if(this.matrix[line][i].getValue()>0 && !this.matrix[line][i].isLinked(LinkType.HORIZONTAL)){
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
            this.matrix[line][column].setMainLink(LinkType.HORIZONTAL);
            Cell[] items = new Cell[5];
            for(int i = firstCellIndex; i <= firstCellIndex + 4; i++){
                this.matrix[line][i].link(LinkType.HORIZONTAL);

                items[i-firstCellIndex] = this.matrix[line][i];
            }
            Link l = new Link(this.matrix[line][column],items, LinkType.HORIZONTAL);
            this.matrix[line][column].setLink(l);
            return l;
        }
        this.matrix[line][column].setValue(0);
        return null;
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
    public Link canJoinSecondDiagonal(int line, int column){
        this.matrix[line][column].setValue(1);
        int count = 0;
        int firstCellLine = 0;
        int firstCellColumn = 0;
        Position initialPosition = this.getFirstDiagonalInitPosition(line,column);
        int tempLineIndex = initialPosition.getLine();
        int tempColumnIndex = initialPosition.getColumn();
        while(tempColumnIndex<WIDTH && tempLineIndex>0){
            if(this.matrix[tempLineIndex][tempColumnIndex].getValue()>0 &&
                    !this.matrix[tempLineIndex][tempColumnIndex].isLinked(LinkType.SECOND_DIAGONAL)){
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
            this.matrix[line][column].setMainLink(LinkType.SECOND_DIAGONAL);
            Cell[] items = new Cell[5];
            int j = firstCellColumn;
            for(int i = firstCellLine; i >= firstCellLine - 4; i--){
                this.matrix[i][j].link(LinkType.SECOND_DIAGONAL);
                items[j-firstCellColumn] = this.matrix[i][j];
                j++;
            }
            Link l = new Link(this.matrix[line][column], items, LinkType.SECOND_DIAGONAL);
            this.matrix[line][column].setLink(l);
            return l;
        }
        this.matrix[line][column].setValue(0);
        return null;
    }
    public Link canJoinFirstDiagonal(int line, int column){
        this.matrix[line][column].setValue(1);
        int count = 0;
        int firstCellLine = 0;
        int firstCellColumn = 0;
        Position initialPosition = this.getSecondDiagonalInitPosition(line,column);
        int tempLineIndex = initialPosition.getLine();
        int tempColumnIndex = initialPosition.getColumn();
        while(tempColumnIndex<WIDTH && tempLineIndex<HEIGHT){
            if(this.matrix[tempLineIndex][tempColumnIndex].getValue()>0 &&
                !this.matrix[tempLineIndex][tempColumnIndex].isLinked(LinkType.FIRST_DIAGONAL)){
                if(count == 0){
                    firstCellLine = tempLineIndex;
                    firstCellColumn = tempColumnIndex;
                }
                count++;
                if (count == 5){
                    System.out.println("dirst diagonal");
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
            this.matrix[line][column].setMainLink(LinkType.FIRST_DIAGONAL);
            Cell[] items = new Cell[5];
            int j = firstCellColumn;
            for(int i = firstCellLine; i <= firstCellLine + 4; i++){
                this.matrix[i][j].link(LinkType.FIRST_DIAGONAL);
                items[i-firstCellLine] = this.matrix[i][j];
                System.out.println(this.matrix[i][j].getI() + " " + this.matrix[i][j].getJ());
                j++;
            }
            Link l = new Link(this.matrix[line][column], items, LinkType.FIRST_DIAGONAL);
            this.matrix[line][column].setLink(l);
            return l;
        }
        this.matrix[line][column].setValue(0);
        return null;
    }

    public Link canLink(int line, int column){
        Link firstDiagonalMove = this.canJoinFirstDiagonal(line,column);
        if(firstDiagonalMove!=null){
            return firstDiagonalMove;
        }
        Link secondDiagonal = this.canJoinSecondDiagonal(line, column);
        if(secondDiagonal!=null){
            return secondDiagonal;
        }
        Link horizontal = this.canJoinHorizontally(line,column);
        if(horizontal!=null){
            return horizontal;
        }
        Link vertical = this.canJoinVertically(line, column);
        if(vertical!=null){
            return vertical;
        }
        return null;
    }

    public void resetCell(int i, int j){
        LinkType mainType = this.matrix[i][j].getMainLink();
        Cell[] link = this.matrix[i][j].getLinkedNodes().getNodes();
        for(Cell c : link){
            this.matrix[c.getI()][c.getJ()].unlink(mainType);
        }
        this.matrix[i][j].setMainLink(LinkType.NONE);
        this.matrix[i][j].unlink(mainType);
        this.matrix[i][j].setValue(0);
    }
}
