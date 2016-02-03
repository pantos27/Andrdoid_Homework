package com.example.veierovioum.lesson14_painter;

import android.graphics.Canvas;
import android.graphics.Paint;
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

    public Rectangle(float x1, float y1, float x2, float y2,Paint _paint) {
        super(_paint);
        this.rectFloat=new RectF(x1,y1,x2,y2);
        rectFloat.sort();
    }

    public Rectangle(Point point1, Point point2,Paint _paint) {
        super(_paint);
        this.rectFloat=new RectF(point1.getX(),point1.getY(),point2.getX(),point2.getY());
        this.rectFloat.sort();
    }





}
