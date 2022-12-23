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

    public Move canLink(int line, int column){
        int count = 0;
        int firstCellIndex = 0;
        this.matrix[line][column].setValue(1);
        for(int i=0; i<WIDTH-1; i++){
            if(this.matrix[line][i].getValue()>0 && this.matrix[line][i].getLinkType() != LinkType.HORIZONTAL){
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
        return new Move(false, null, null);
    }
}
