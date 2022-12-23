package com.example.morpionsolitaire.models;

public class Cell {

    private int value;
    final private int i;
    final private int j;
    private LinkType linkType;
    public Cell(int _i, int _j, int _v){
        this.i = _i;
        this.j = _j;
        this.value = _v;
        this.linkType = LinkType.NONE;
    }

    public void setLinkType(LinkType lt){
        this.linkType = lt;
    }

    public void setValue(int v){
        this.value = v;
    }

    public int getI(){
        return this.i;
    }

    public int getJ(){
        return this.j;
    }

    public int getValue(){
        return this.value;
    }

    public LinkType getLinkType(){
        return this.linkType;
    }
}

