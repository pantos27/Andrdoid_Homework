package com.example.veierovioum.lesson14_painter;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Veierovioum on 05/02/2016.
 */
public class Circle extends Shape {
    public Circle(Point start, Point end,Paint _paint) {
        super(start,end,_paint);
    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawCircle(startPoint.getX(),startPoint.getY(),startPoint.distance(endPoint),paint);
    }
}
