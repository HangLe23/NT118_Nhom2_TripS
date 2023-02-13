package com.example.nt118_nhom2_trips;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nt118_nhom2_trips.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    private Button btn_back;
    private ImageButton btn_Enter, showPass;
    private EditText Email, Pass;
    private TextView tv_ForgetPass;

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        findViewByIds();
        mAuth = FirebaseAuth.getInstance();
        initListener();
    }
    private void findViewByIds(){
        tv_ForgetPass = (TextView) findViewById(R.id.forgotpass);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_Enter = (ImageButton) findViewById(R.id.ibtn_next);
        Email = (EditText) findViewById(R.id.et_Mail);
        Pass = (EditText) findViewById(R.id.et_Password);
        progressDialog = new ProgressDialog(this);
        showPass = findViewById(R.id.btn_showpass);
    }
    private void initListener() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, NewActivity.class));
            }
        });
        btn_Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickSignIn();
            }
        });
        tv_ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, ForgotPass.class);
                startActivity(intent);
            }
        });
        showPass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Pass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    Pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                } else{
                    Pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPass.setImageResource(R.drawable.ic_hidepass);
                }
            }
        });
    }

    private void onclickSignIn() {
        String email = Email.getText().toString().trim();
        String pass = Pass.getText().toString().trim();
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
