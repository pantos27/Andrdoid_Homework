package com.example.veierovioum.lesson19_puzzle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Veierovioum on 20/02/2016.
 */
public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<Integer> idList;

    public ImageAdapter(Context context, ArrayList<Integer> idList) {
        this.context = context;
        this.idList = idList;
    }

    @Override
    public int getCount() {
        return idList.size();
    }

    @Override
    public Integer getItem(int position) {
        return idList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView==null) {
            imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.puzzle_image_view, null);
//            imageView.setAdjustViewBounds(false);

        }
        else {
            imageView=(ImageView) convertView;
        }
//        imageView.setMaxHeight();
        imageView.setImageResource(idList.get(position));

        return imageView;
    }
}
