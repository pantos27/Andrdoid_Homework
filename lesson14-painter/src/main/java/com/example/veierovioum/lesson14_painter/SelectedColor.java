package com.example.veierovioum.lesson14_painter;

import android.graphics.Color;

/**
 * paint color options
 */
public enum SelectedColor {
    BLUE ("Blue", Color.BLUE),
    BLACK ("Black",Color.BLACK),
    RED ("Red",Color.RED),
    GREEN ("Green",Color.GREEN),
    MAGENTA ("Green",Color.MAGENTA),
    YELLOW ("Yellow",Color.YELLOW),
    CYAN ("Cyan",Color.CYAN);

    private final int value;
    private final String name;
    SelectedColor(String _name,int color) {
        this.name=_name;
        this.value=color;
    }

    public int getValue(){return value;}
    public String getName(){return name;}

}
