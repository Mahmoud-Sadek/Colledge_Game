package com.example.mahmoud.colledgegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Button startTime = (Button) findViewById(R.id.starTimetButton);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(getBaseContext(), MainActivity.class);
                start.putExtra("key", "time");
                startActivity(start);
            }
        });
        Button startShot = (Button) findViewById(R.id.startShotButton);
        startShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(getBaseContext(), MainActivity.class);
                start.putExtra("key","shot");
                startActivity(start);
            }
        });
    }

}
