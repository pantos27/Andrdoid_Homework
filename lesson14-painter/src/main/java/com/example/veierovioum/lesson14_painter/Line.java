package com.example.veierovioum.lesson14_painter;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Veierovioum on 05/02/2016.
 */
public class Line extends Shape {
    public Line(Point start, Point end, Paint paint) {
        super(start, end, paint);
    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawLine(startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getY(),paint);
    }
}
