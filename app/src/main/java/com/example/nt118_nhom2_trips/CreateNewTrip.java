package com.example.nt118_nhom2_trips;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewTrip extends AppCompatActivity {
    private Button back;
    private ImageButton next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_newtrip);
        findViewByIds();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(CreateNewTrip.this, MainActivity.class);
                startActivity(iNewActivity);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(CreateNewTrip.this, PlanTrip.class);
                startActivity(iNewActivity);
            }
        });
    }
    private void findViewByIds(){
        back = (Button) findViewById(R.id.btn_back);
        next = (ImageButton) findViewById(R.id.ibtn_next);
    }
}
