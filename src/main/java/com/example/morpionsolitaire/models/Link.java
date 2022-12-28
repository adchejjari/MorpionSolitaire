package com.example.morpionsolitaire.models;

public class Link{
    private Cell root;
    private Cell[] nodes;
    private LinkType type;

    public Link(Cell r, Cell[] items, LinkType l){
        root = r;
        nodes = items;
        type = l;
    }

    public Cell getFirstNode(){
        return nodes[0];
    }

    public Cell getLastNode(){
        return nodes[4];
    }

    public Cell getRoot(){
        return root;
    }

    public Cell[] getNodes(){
        return nodes;
    }

    public LinkType getLinkType(){
        return type;
    }

    public boolean isLinkType(LinkType l){
        return type == l;
    }
}
