package com.example.veierovioum.lesson14_painter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PainterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = MyPainter.TAG;
    MyPainter myPainter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabClrear);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<Shape> clearedShapes=myPainter.clearAllShapes();
                Snackbar.make(view, "Shapes cleared", Snackbar.LENGTH_LONG)
                        .setAction("Undo Clear", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myPainter.restoreShapes(clearedShapes);
                            }
                        }).show();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fabUndo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove last added shape and temporarily save it
                final Shape removedShape=myPainter.removeLastShape();
                Snackbar.make(view, "Last shaped removed", Snackbar.LENGTH_LONG)
                        .setAction("Restore Shape", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (removedShape != null) {
                                    myPainter.addShape(removedShape);
                                }
                            }
                        }).show();
            }
        });



        Spinner shapesSpinner= (Spinner) findViewById(R.id.spinnerShape);
        ArrayAdapter<MyPainter.Shapes> shapesArrayAdapterdapter=new ArrayAdapter<MyPainter.Shapes>(this,android.R.layout.simple_expandable_list_item_1,MyPainter.Shapes.values());
        shapesSpinner.setAdapter(shapesArrayAdapterdapter);
        shapesSpinner.setOnItemSelectedListener(this);


        Spinner colorsSpinner= (Spinner) findViewById(R.id.spinnerColor);
        ArrayAdapter<SelectedColor> colorsArrayAdapterdapter=new ArrayAdapter<SelectedColor>(this,android.R.layout.simple_expandable_list_item_1,SelectedColor.values());
        colorsSpinner.setAdapter(colorsArrayAdapterdapter);
        colorsSpinner.setOnItemSelectedListener(this);

        myPainter= (MyPainter) findViewById(R.id.myPainter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId()==R.id.spinnerColor)
        {
            SelectedColor color= (SelectedColor) parent.getItemAtPosition(position);
            //sets painter color
            myPainter.setSelectedColor(color);
            //changes text color
            if (view != null) {
                ((TextView)view).setTextColor(color.getValue());
            }
        }

        if (parent.getId()==R.id.spinnerShape){
            MyPainter.Shapes shape= (MyPainter.Shapes) parent.getItemAtPosition(position);

            myPainter.setSelectedShape(shape);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "onNothingSelected: ");
    }
}
