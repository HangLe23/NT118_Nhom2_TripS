package com.example.nt118_nhom2_trips;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nt118_nhom2_trips.CreateNewTrip.CreateNewTrip;

public class PlanTrip extends AppCompatActivity {
    private Button back;
    private ImageButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_trip);
        findViewByIds();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(PlanTrip.this, CreateNewTrip.class);
                startActivity(iNewActivity);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(PlanTrip.this, AddActivity.class);
                startActivity(iNewActivity);
            }
        });
    }
    private void findViewByIds(){
        back = (Button) findViewById(R.id.btn_back);
        add = (ImageButton) findViewById(R.id.ibtn_add);
    }
}
