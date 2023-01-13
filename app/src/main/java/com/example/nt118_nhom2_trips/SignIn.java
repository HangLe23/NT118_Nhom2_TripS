package com.example.nt118_nhom2_trips;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private Button btn_Enter;
    private EditText Email, Pass;
    private ProgressDialog progressDialog;

    private TextView tv_ForgetPass;
    private CheckBox btn_show_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        findViewByIds();
        initListener();


    }
    private void findViewByIds(){
        btn_Enter = (Button) findViewById(R.id.btn_next);
        Email = (EditText) findViewById(R.id.et_Mail);
        Pass = (EditText) findViewById(R.id.et_Password);

        tv_ForgetPass = (TextView) findViewById(R.id.tv_ForgetPass);

        progressDialog = new ProgressDialog(this);
    }
    private void initListener() {

        btn_Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickSignIn();
            }
        });
        /*tv_ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickForgetPass();
            }
        });*/

    }

    /*private void onClickForgetPass() {
        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress= Email.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();

                            Toast.makeText(SignIn.this, "Email sent", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }*/

    private void onclickSignIn() {
        String email = Email.getText().toString().trim();
        String pass = Pass.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
