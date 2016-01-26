package com.example.aattar.lesson12_exe3;

import java.io.Serializable;

/**
 * Created by aattar on 26/01/2016.
 */
public class Colour implements Serializable {
    int colour;
    String name;

    public Colour(int colour, String name) {
        this.colour = colour;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getColour() {
        return colour;
    }
}
