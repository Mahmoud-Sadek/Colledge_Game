package com.example.mahmoud.colledgegame;

import android.content.Context;
import android.media.Image;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Mahmoud on 4/14/2016.
 */
public class GridViewAdapter extends BaseAdapter {
    int [] imagesList;
    Context context;
    int [] value ;

    public GridViewAdapter(int[] imagesList , Context context){
        this.imagesList = new int[imagesList.length*2];
        value = new int[imagesList.length*2];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<=11; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<=11; i++) {
            int item = list.get(i);
            if (item>5)
                item = item-6;
            this.imagesList [i] =imagesList[item];
            value[i] = item;
        }
        MainActivityFragment.value = value;
        this.context = context;
    }
    @Override
    public int getCount() {
        return (imagesList.length);
    }

    @Override
    public Object getItem(int i) {
        return imagesList[i];
    }

    @Override
    public long getItemId(int i) {;
        return i;
    }

    int [] c= new int[12];
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_item,viewGroup, false);
        }
        ImageView imgView = (ImageView) view.findViewById(R.id.list_item_imageview);
        imgView.setImageResource(imagesList[i]);

        return view;
    }
}
