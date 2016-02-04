package com.example.veierovioum.lesson14_painter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class PainterActivity extends AppCompatActivity {
    MyPainter myPainter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myPainter= (MyPainter) findViewById(R.id.myPainter);
        myPainter.selectedShape= MyPainter.Shapes.Rectangle;
        myPainter.selectedColor=SelectedColor.GREEN;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabClrear);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 04/02/2016 undo clear
                myPainter.clearAllShapes();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fabUndo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPainter.removeLastShape();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ArrayList<SelectedColor> colors=new ArrayList<>();

        for (SelectedColor color : SelectedColor.values())
        {
            colors.add(color);
        }

//        MyPainter.Shapes.values()
//
//        Spinner spinner= (Spinner) findViewById(R.id.spinnerShape);
//        ArrayAdapter<Shape> adapter=new ArrayAdapter<Shape>(this,android.R.layout.simple_expandable_list_item_1,colors);


    }

}
