package com.example.nt118_nhom2_trips;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {
    private Button next;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);
        email = (EditText) findViewById(R.id.et_Email);
        next = (Button) findViewById(R.id.btn_Enter);
        ProgressDialog progressDialog = new ProgressDialog(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress= email.getText().toString();
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(ForgotPass.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(ForgotPass.this, "Email sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
