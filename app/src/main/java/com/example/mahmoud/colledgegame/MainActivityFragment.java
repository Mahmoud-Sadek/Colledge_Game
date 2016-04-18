package com.example.mahmoud.colledgegame;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    String startKey;
    GridView gridView;
    GridViewAdapter mGridViewAdapter;
    private boolean running;
    TextView watchText;
    TextView shotText;
    TextView finshGame;
    int shot = 0;
    int complete = 0;
    public static int [] value ;
    int pastImage;
    View pastImgView;
    ViewGroup pastImgViewGroup;
    int begin = 0;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) view.findViewById(R.id.gridview);
        finshGame = (TextView) view.findViewById(R.id.gameOverText);
        watchText = (TextView) view.findViewById(R.id.timeText);
        shotText = (TextView) view.findViewById(R.id.shotText);
        Intent start = getActivity().getIntent();
        startKey = start.getStringExtra("key");
        if (startKey.equals("time")){
            watchText.setVisibility(View.VISIBLE);
            runTimer();
            running = true;
        }else if(startKey.equals("shot")) {
            shotText.setVisibility(View.VISIBLE);
            shotText.setText("Num Of Shots: 0");
        }

        final int[] images = {
                R.drawable.chosse1,
                R.drawable.chosse2,
                R.drawable.chosse3,
                R.drawable.chosse4,
                R.drawable.chosse5,
                R.drawable.chosse6
        };

        mGridViewAdapter = new GridViewAdapter(images, getActivity());
        gridView.setAdapter(mGridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int i, long l) {
                if(startKey.equals("shot")) {
                    shotText.setText("Num Of Shots: " + ++shot);

                    if(shot == 20){
                        gridView.setVisibility(View.GONE);
                        finshGame.setVisibility(View.VISIBLE);
                    }
                }
                if(mGridViewAdapter.getView(i, view, adapterView).isClickable()==false) {
                    mGridViewAdapter.getView(i, view, adapterView).findViewById(R.id.list_item_imageview).setVisibility(View.VISIBLE);
                    mGridViewAdapter.getView(i, view, adapterView).setClickable(true);

                    if (begin == 0) {
                        pastImage = value[i];
                        pastImgView = view;
                        pastImgViewGroup = adapterView;
                        begin = 1;
                    } else if (pastImage == value[i]) {
                        ++complete;
                        begin = 0;
                        if (complete == images.length){
                            gridView.setVisibility(View.GONE);
                            finshGame.setText("Congatulation *** \n Perfect Work");
                            finshGame.setVisibility(View.VISIBLE);
                            running=false;
                        }
                    } else if (pastImage != value[i]) {
                        begin = 0;
                        CountDownTimer c = new CountDownTimer(1000, 1000) {
                            @Override
                            public void onTick(long l) {
                            }

                            @Override
                            public void onFinish() {
                                mGridViewAdapter.getView(i, view, adapterView).findViewById(R.id.list_item_imageview).setVisibility(View.INVISIBLE);
                                mGridViewAdapter.getView(pastImage, pastImgView, pastImgViewGroup).findViewById(R.id.list_item_imageview).setVisibility(View.INVISIBLE);
                                mGridViewAdapter.getView(i, view, adapterView).setClickable(false);
                                mGridViewAdapter.getView(pastImage, pastImgView, pastImgViewGroup).setClickable(false);
                            }
                        }.start();
                    }
                }
            }
        });


        if (savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }
        return view;
    }

    private int seconds = 35;
    int sec;
    public void runTimer(){

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                sec = seconds % 60;

                String time = String.format("%02d" , sec);
                watchText.setText(time + "s");
                if(sec == 0){
                    gridView.setVisibility(View.GONE);
                    finshGame.setVisibility(View.VISIBLE);
                    running = false;
                }
                if (running)
                    seconds--;

                handler.postDelayed(this, 1000);
            }
        });


    }
    @Override
    public void onSaveInstanceState(Bundle savedState) {

        savedState.putInt("seconds",seconds);
        savedState.putBoolean("running", running);

    }

}
