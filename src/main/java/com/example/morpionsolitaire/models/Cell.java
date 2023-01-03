package com.example.morpionsolitaire.models;

import java.util.*;

public class Cell {

    private int value;
    final private int i;
    final private int j;
    private Set<LinkType> links;
    private static LinkType mainLink = LinkType.NONE;
    private static Link linkedNodes;

    public Cell(int _i, int _j, int _v){
        this.i = _i;
        this.j = _j;
        this.value = _v;
        this.links = new HashSet<LinkType>();
    }

    public void setLink(Link lnk){
        linkedNodes = lnk;
    }

    public static Link getLinkedNodes(){
        return linkedNodes;
    }

    public void undoLink(){
        for (Cell c: linkedNodes.getNodes()){
            c.unlink(mainLink);
        }
    }

    public void link(LinkType lt){
        this.links.add(lt);
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

    
    public boolean isLinked(LinkType lnk){
        for(LinkType l : this.links){
            if (l == lnk){
                return true;
            }
        }
        return false;
    }

    public void setMainLink(LinkType l){
        this.mainLink = l;
    }

    public LinkType getMainLink(){
        return this.mainLink;
    }

    public static boolean hasMainLink(){
        return mainLink != LinkType.NONE;
    }

    public void unlink(LinkType t){

        for (LinkType l : this.links){
            System.out.print(l);
            System.out.println("  ");
            if (t == l){
                links.remove(t);
                break;
            }
        }
    }
}

