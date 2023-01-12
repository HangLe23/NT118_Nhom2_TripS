package com.example.nt118_nhom2_trips;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewActivity extends AppCompatActivity {
    private Button btn_Sign_in,btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        /*Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user == null){
                    Intent intent = new Intent(NewActivity.this, NewActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(NewActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });*/
        findViewByIds();
        initListener();

    }

    private void initListener() {
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        btn_Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewActivity.this, SignIn.class);
                startActivity(intent);

            }
        });
    }

    private void findViewByIds() {
        btn_Sign_in = (Button) findViewById(R.id.btn_Sign_in);
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
    }
}
