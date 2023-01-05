package com.example.nt118_nhom2_trips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    private Button create;
    private ImageButton hotel, tour, yourTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(MainActivity.this, CreateNewTrip.class);
                startActivity(iNewActivity);
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(MainActivity.this, Hotel.class);
                startActivity(iNewActivity);
            }
        });
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(MainActivity.this, Tour.class);
                startActivity(iNewActivity);
            }
        });
    }
    private void findViewByIds(){
        create = (Button) findViewById(R.id.btn_CreateTrip);
        hotel = (ImageButton) findViewById(R.id.ibtn_hotel);
        tour = (ImageButton) findViewById(R.id.ibtn_tour);
        yourTrip = (ImageButton) findViewById(R.id.ibtn_yourTrip);
    }

}