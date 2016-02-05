package com.example.veierovioum.lesson14_painter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by Veierovioum on 02/02/2016.
 */
public class Rectangle extends Shape{
    RectF rectFloat;

    @Override
    void draw(Canvas canvas) {
        canvas.drawRect(this.rectFloat,this.paint);
    }


    public Rectangle(Point point1, Point point2,Paint _paint) {
        super(point1,point2,_paint);
        this.rectFloat=new RectF(point1.getX(),point1.getY(),point2.getX(),point2.getY());
        this.rectFloat.sort();
    }





}
