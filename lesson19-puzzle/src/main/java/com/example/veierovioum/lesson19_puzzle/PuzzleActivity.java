package com.example.veierovioum.lesson19_puzzle;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PuzzleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private static final String TAG = "PuzzleLog";
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    ArrayList<Integer> idArray,correctArray;
    GridView gView;
    ImageAdapter adapter;
    private int item1;
    private boolean firstMove=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_puzzle);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.reshuffle_button).setOnTouchListener(mDelayHideTouchListener);

        gView=(GridView) findViewById(R.id.gridView);

        idArray=new ArrayList<>();
        fillArray(idArray);
        Collections.shuffle(idArray);

        Drawable frame;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            frame=getResources().getDrawable(R.drawable.a0_0, getTheme());
        }
        else             frame =getResources().getDrawable(R.drawable.a0_0);

        gView.setColumnWidth(frame.getIntrinsicWidth());

        adapter=new ImageAdapter(this,idArray);
        gView.setAdapter(adapter);
        gView.setOnItemClickListener(this);
        gView.setOnItemSelectedListener(this);
    }

    private void fillArray(ArrayList<Integer> idArray) {
        idArray.add(R.drawable.a0_0);
        idArray.add(R.drawable.a0_1);
        idArray.add(R.drawable.a0_2);
        idArray.add(R.drawable.a0_3);
        idArray.add(R.drawable.a1_0);
        idArray.add(R.drawable.a1_1);
        idArray.add(R.drawable.a1_2);
        idArray.add(R.drawable.a1_3);
        idArray.add(R.drawable.a2_0);
        idArray.add(R.drawable.a2_1);
        idArray.add(R.drawable.a2_2);
        idArray.add(R.drawable.a2_3);

        correctArray= (ArrayList<Integer>) idArray.clone();
        correctArray.equals(idArray);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    Integer selected=-1;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (gView.isSelected()){

        }
        else
            Toast.makeText(PuzzleActivity.this, "Select a piece to replace", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selected=-1;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: "+position);

        //checks if this items is already checked
//        if (gView.isItemChecked(position)){
//               gView.setItemChecked(position,false);
//            return;
//        }

        switch (gView.getCheckedItemCount()) {
            case 0:
                Log.d(TAG, "onItemClick: 0 selected");
                //  deselect for ui purpose
                view.setSelected(false);
                break;
            case 1:
                item1=position;
                view.setSelected(true);
                if (firstMove)
                    Toast.makeText(PuzzleActivity.this, "Select a piece to replace", Toast.LENGTH_LONG).show();
                break;
            case 2:
                switchItems(item1,position);
                break;
        }
    }



    private void switchItems(int _item1,int item2) {
        //removes toast message
        if (firstMove) firstMove=false;

         Log.d(TAG, "switchItems: "+_item1+"&"+item2);
        //unchecks items
        gView.setItemChecked(_item1,false);
        gView.setItemChecked(item2, false);
        //replaces items
        Collections.swap(idArray,_item1,item2);
        adapter.notifyDataSetChanged();
        checkComplete();
    }

    private void checkComplete(){
        if (idArray.equals(correctArray)){

            Toast.makeText(PuzzleActivity.this, "All done, Congratulations!", Toast.LENGTH_SHORT).show();
            toggle();

        }
    }

    public void onReshuffleClick(View v){
        Collections.shuffle(idArray);
        adapter.notifyDataSetChanged();
    }

}
