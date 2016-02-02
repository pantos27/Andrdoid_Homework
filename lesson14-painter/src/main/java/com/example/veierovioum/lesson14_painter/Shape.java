package com.example.veierovioum.lesson14_painter;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Veierovioum on 02/02/2016.
 */
public abstract class Shape {
    Paint paint;
    void draw(Canvas canvas, Point start,Point end){}
    Point startPoint;
    Point endPoint;

}
