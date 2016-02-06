package com.example.veierovioum.lesson14_painter;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Veierovioum on 06/02/2016.
 */
public class Triangle extends Shape {

    public Triangle(Point start, Point end, Paint paint) {
        super(start, end, paint);
    }

    @Override
    void draw(Canvas canvas) {
        float dist=startPoint.distance(endPoint);

        canvas.drawLine(startPoint.getX()+dist/2,startPoint.getY(),startPoint.getX()-dist/2,startPoint.getY(),paint);
        canvas.drawLine(endPoint.getX(),endPoint.getY(),startPoint.getX()+dist/2,startPoint.getY(),paint);
        canvas.drawLine(endPoint.getX(),endPoint.getY(),startPoint.getX()-dist/2,startPoint.getY(),paint);

    }
}
