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

public class Grid5T extends Grid{

    public Grid5T() throws IOException {
        this.load();
    }

    public void setCell(int line, int column, int value){
        matrix[line][column].setValue(value);
    }

    public static Cell getCell(int i, int j){
        return matrix[i][j];
    }

    public Cell[][] getMatrix(){
        return matrix;
    }

    public boolean isCellEmpty(int i, int j){
        return matrix[i][j].getValue() == 0;
    }




    public List<Link> horizontalJoin(int line, int column){
        List<Link> possibleLinks = new ArrayList<>();
        int leftPivot = column - 1;
        int rightPivot = column + 1;
        int leftCounter = 0;
        int RightCounter = 0;
        while (leftPivot>=0 && leftCounter < 4){
            if (this.matrix[line][leftPivot].getValue()>0 &&
                    !this.matrix[line][leftPivot].isLinked(LinkType.HORIZONTAL) ||
                    this.matrix[line][leftPivot].isExtremity()){
                System.out.println(this.matrix[line][leftPivot].isExtremity());
                leftCounter++;
                leftPivot--;
            }else{
                break;
            }
        }
        while(rightPivot< WIDTH && RightCounter < 4){
            if (this.matrix[line][rightPivot].getValue()>0 &&
                    !this.matrix[line][rightPivot].isLinked(LinkType.HORIZONTAL) ||
                    this.matrix[line][rightPivot].isExtremity()){
                RightCounter++;
                rightPivot++;
            }else{
                break;
            }
        }
        leftPivot++;
        if (leftCounter==0 && RightCounter==4|| RightCounter==0 && leftCounter == 4){
            Cell[] items = new Cell[5];
            for(int i = leftPivot; i <= leftPivot + 4; i++){
                items[i-leftPivot] = this.matrix[line][i];
            }
            Link l = new Link(this.matrix[line][column], items, LinkType.HORIZONTAL);
            possibleLinks.add(l);
        } else if (leftCounter+RightCounter>=4){
            for(int k = leftPivot; k<leftPivot+leftCounter+RightCounter-3; k++){
                Cell[] items = new Cell[5];
                for(int i = k; i <= k + 4; i++){
                    items[i-k] = this.matrix[line][i];
                }
                possibleLinks.add(new Link(this.matrix[line][column], items, LinkType.HORIZONTAL));
            }
        }
        return possibleLinks;
    }


    public List<Link> canJoinVertically(int line, int column){ // rename to hasScoredVertical
        List<Link> possibleLinks = new ArrayList<>();
        int upPivot = line - 1;
        int downPivot = line + 1;
        int upCounter = 0;
        int downCounter = 0;
        while (upPivot>=0 && upCounter < 4){
            if (this.matrix[upPivot][column].getValue()>0 &&
                    !this.matrix[upPivot][column].isLinked(LinkType.VERTICAL)||
                    this.matrix[upPivot][column].isExtremity()){
                upCounter++;
                upPivot--;
            }else{
                break;
            }
        }
        while(downPivot< WIDTH && downCounter < 4){
            if (this.matrix[downPivot][column].getValue()>0 &&
                    !this.matrix[downPivot][column].isLinked(LinkType.VERTICAL)||
                    this.matrix[downPivot][column].isExtremity()){
                downCounter++;
                downPivot++;
            }else{
                break;
            }
        }
        upPivot++;
        if (upCounter==0 && downCounter==4|| downCounter==0 && upCounter==4){
            Cell[] items = new Cell[5];
            for(int i = upPivot; i <= upPivot + 4; i++){
                items[i-upPivot] = this.matrix[i][column];
            }
            Link l = new Link(this.matrix[line][column], items, LinkType.VERTICAL);
            possibleLinks.add(l);
        } else if (upCounter+downCounter>=4){
            for(int k = upPivot; k<upPivot+upCounter+downCounter-3; k++){
                Cell[] items = new Cell[5];
                for(int i = k; i <= k + 4; i++){
                    items[i-k] = this.matrix[i][column];
                }
                possibleLinks.add(new Link(this.matrix[line][column], items, LinkType.VERTICAL));
            }
        }
        return possibleLinks;
    }



    public List<Link> canJoinSecondDiagonal(int line, int column){
        List<Link> possibleLinks = new ArrayList<>();
        int downColumnPivot = column + 1;
        int downLinePivot = line + 1;
        int downCounter = 0;

        while(downLinePivot<HEIGHT && downColumnPivot<WIDTH && downCounter<4){
            if (this.matrix[downLinePivot][downColumnPivot].getValue()>0 &&
                    !this.matrix[downLinePivot][downColumnPivot].isLinked(LinkType.SECOND_DIAGONAL)||
                    this.matrix[downLinePivot][downColumnPivot].isExtremity()){
                downLinePivot++;
                downColumnPivot++;
                downCounter++;
            }
            else{
                break;
            }

        }

        int upLinePivot = line - 1;
        int upColumnPivot = column - 1;
        int upCounter = 0;
        while(upColumnPivot>0 && upLinePivot>0 && upCounter<4){
            if (this.matrix[upLinePivot][upColumnPivot].getValue()>0 &&
                    !this.matrix[upLinePivot][upColumnPivot].isLinked(LinkType.SECOND_DIAGONAL)||
                    this.matrix[upLinePivot][upColumnPivot].isExtremity()){
                upLinePivot--;
                upColumnPivot--;
                upCounter++;
            }
            else{
                break;
            }

        }
        upLinePivot++;
        upColumnPivot++;
        if (upCounter==0 && downCounter==4|| downCounter==0 && upCounter==4){
            Cell[] items = new Cell[5];
            int j = upColumnPivot;
            for(int i = upLinePivot; i <= upLinePivot + 4; i++){
                items[i-upLinePivot] = this.matrix[i][j];
                j++;
            }
            possibleLinks.add(new Link(this.matrix[line][column], items, LinkType.SECOND_DIAGONAL));

        }else if (upCounter+downCounter>=4) {
            int j = upColumnPivot;
            for (int k = upLinePivot; k < upLinePivot + upCounter + downCounter - 3; k++) {
                Cell[] items = new Cell[5];
                int p = j;
                for (int i = k; i <= k + 4; i++) {
                    items[i - k] = this.matrix[i][p];
                    p++;
                }
                j++;
                possibleLinks.add(new Link(this.matrix[line][column], items, LinkType.SECOND_DIAGONAL));
            }
        }
        return possibleLinks;
    }

    public List<Link> canJoinFirstDiagonal(int line, int column){
        List<Link> possibleLinks = new ArrayList<>();
        int downColumnPivot = column - 1;
        int downLinePivot = line + 1;
        int downCounter = 0;

        while(downLinePivot<HEIGHT && downColumnPivot>0 && downCounter<4){
            if (this.matrix[downLinePivot][downColumnPivot].getValue()>0 &&
                    !this.matrix[downLinePivot][downColumnPivot].isLinked(LinkType.FIRST_DIAGONAL)||
                    this.matrix[downLinePivot][downColumnPivot].isExtremity()){
                downLinePivot++;
                downColumnPivot--;
                downCounter++;
            }
            else{
                break;
            }
        }
        int upLinePivot = line - 1;
        int upColumnPivot = column + 1;
        int upCounter = 0;
        while(upColumnPivot<WIDTH && upLinePivot>0 && upCounter<4){
            if (this.matrix[upLinePivot][upColumnPivot].getValue()>0 &&
                    !this.matrix[upLinePivot][upColumnPivot].isLinked(LinkType.FIRST_DIAGONAL)||
                    this.matrix[upLinePivot][upColumnPivot].isExtremity()){
                upLinePivot--;
                upColumnPivot++;
                upCounter++;
            }
            else{
                break;
            }
        }
        upLinePivot++;
        upColumnPivot--;
        if (upCounter==0 && downCounter==4 || downCounter==0 && upCounter==4){
            Cell[] items = new Cell[5];
            int j = upColumnPivot;
            for(int i = upLinePivot; i <= upLinePivot - 4; i++){
                items[i-upLinePivot] = this.matrix[i][j];
                j--;
            }
            possibleLinks.add(new Link(this.matrix[line][column], items, LinkType.FIRST_DIAGONAL));
        }else if (upCounter+downCounter>=4) {
            int j = upColumnPivot;
            for (int k = upLinePivot; k < upLinePivot + upCounter + downCounter - 3; k++) {
                Cell[] items = new Cell[5];
                int p = j;
                for (int i = k; i <= k + 4; i++) {
                    items[i - k] = this.matrix[i][p];
                    p--;
                }
                j--;
                possibleLinks.add(new Link(this.matrix[line][column], items, LinkType.FIRST_DIAGONAL));
            }
        }
        return possibleLinks;
    }

    public List<Link> canLink(int line, int column){
        List<Link> links = new ArrayList<>();

        links.addAll(this.canJoinVertically(line, column));

        links.addAll(this.horizontalJoin(line, column));

        links.addAll(this.canJoinSecondDiagonal(line, column));

        links.addAll(this.canJoinFirstDiagonal(line, column));

        return links;
    }


    public void debug(){
        for(int i = 0; i < HEIGHT; i++){
            for (int j = 0; j<WIDTH;j++){
                System.out.print(matrix[i][j].isLinked(LinkType.HORIZONTAL)+ "    ");
            }
            System.out.println("  ");
        }
        System.out.println("------------------------------------------------------------");
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
        //debug();
    }

    public void playMove(int i, int j) {
        List<Link> possiblities = canLink(i,j);
        if (possiblities.size() == 1 && this.matrix[i][j].getValue()==0){
            Link possibleLink = possiblities.get(0);
            setSingleLink(possibleLink);
        }
        else if(possiblities.size()>1){
            this.possibleMoves = possiblities;
            setSelectionCells();
        }
        if (this.matrix[i][j].canBeSelected()) {
            Link moveToPlay = getLinkFromSelectedcell(this.matrix[i][j]);
            removeSelection();
            setSingleLink(moveToPlay);
            this.possibleMoves = null;
        }
    }

    public void removeSelection(){
        for(int i = 0; i < HEIGHT; i++){
            for (int j = 0; j<WIDTH;j++){
                this.matrix[i][j].setCanBeSelected(false);
            }
        }
    }

    public Link getLinkFromSelectedcell(Cell c){
        for(Link link: this.possibleMoves){
            if (c==link.getFirstNode() || c==link.getLastNode()){
                return link;
            }
        }
        return null;

    }

    public void setSelectionCells(){
        for(Link move: possibleMoves){
            if(move.getFirstNode() == move.getRoot()){
                move.getLastNode().setCanBeSelected(true);
            }else{
                move.getFirstNode().setCanBeSelected(true);
            }
        }
    }

    public void setSingleLink(Link l){
        int i = l.getRoot().getI();
        int j = l.getRoot().getJ();
        for (Cell c: l.getNodes()){
            c.link(l.getLinkType());
        }
        this.matrix[i][j].setLink(l);
        this.matrix[i][j].setValue(1);
        this.movesHistory.add(l);
        setExtremities(l);
    }

    public void setExtremities(Link l){
        Cell firstExtremity = l.getFirstNode();
        Cell lastExtremity = l.getLastNode();
        this.matrix[firstExtremity.getI()][firstExtremity.getJ()].setExtremity(true);
        this.matrix[lastExtremity.getI()][lastExtremity.getJ()].setExtremity(true);
    }

    public void undoLastMove(){
        int index = this.movesHistory.size() - 1;
        if (index >= 0) {
            Link linkToRemove = this.movesHistory.get(index);
            for (Cell c : linkToRemove.getNodes()) {
                c.unlink(linkToRemove.getLinkType());
            }
            Cell rootCell = linkToRemove.getRoot();
            rootCell.setMainLink(LinkType.NONE);
            this.matrix[rootCell.getI()][rootCell.getJ()].setValue(0);
            rootCell.setLink(null);
            movesHistory.remove(index);
        }
    }


    /*public List<Link> getMovesHistory(){
        return movesHistory;
    }*/
}



