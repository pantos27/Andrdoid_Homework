package com.example.veierovioum.lesson15_scrolls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.Scroller;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv= (WebView) findViewById(R.id.webView);

        wv.loadUrl("http://vizts.com/wp-content/uploads/2016/01/Empire-State-Building-view.jpg");
        wv.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
//
//
//        TextView tv= (TextView) findViewById(R.id.textView);
//        tv.setMovementMethod(new ScrollingMovementMethod());
//        tv.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);

    }
}
