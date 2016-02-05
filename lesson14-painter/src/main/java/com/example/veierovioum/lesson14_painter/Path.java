package com.example.veierovioum.lesson14_painter;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Veierovioum on 05/02/2016.
 */
public class Path extends Shape{

    private android.graphics.Path innerPath;

    public Path(Point start, Point end, Paint _paint) {
        super(start,end,_paint);
        innerPath=new android.graphics.Path();
        innerPath.moveTo(start.getX(),start.getY());
        innerPath.lineTo(end.getX(),end.getY());
    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawPath(innerPath,paint);
    }

    void addToPath(Point newPoint){
        this.innerPath.lineTo(newPoint.getX(),newPoint.getY());
    }
}
