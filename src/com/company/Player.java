package com.company;

public class Player {

    private String name;

    protected char marker;

    public Player() {
        this.name = "";

    }

    public Player(String name, char marker) {
        this.name = name;
        this.marker = marker;
    }


    public String getName() {
        return this.name;
    }

    public char getMarker() {
        return this.marker;
    }

}
