package com.example.morpionsolitaire.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Grid {

    final static int WIDTH = 16;
    final static int HEIGHT = 16;

    final private String defaultGrid = "/src/main/java/com/example/morpionsolitaire/grids/default.grid";

    private int[][] matrix = new int[WIDTH][WIDTH];

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
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
    }

    public void setCell(int line, int column, int value){
        matrix[line][column] = value;
    }

    public int getCellValue(int i, int j){
        return matrix[i][j];
    }

    public int[][] getMatrix(){
        return matrix;
    }

    public boolean isCellEmpty(int i, int j){
        return matrix[i][j] == 0;
    }
}
