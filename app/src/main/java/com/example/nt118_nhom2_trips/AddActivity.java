package com.example.nt118_nhom2_trips;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        findViewByIds();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(AddActivity.this, PlanTrip.class);
                startActivity(iNewActivity);
            }
        });
    }
    private void findViewByIds(){
        back = (Button) findViewById(R.id.btn_back);
    }
}
