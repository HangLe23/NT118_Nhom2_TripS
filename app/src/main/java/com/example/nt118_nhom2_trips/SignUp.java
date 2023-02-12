package com.example.nt118_nhom2_trips;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nt118_nhom2_trips.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private EditText Email, Pass, ConfirmPass, FullName, Gender, PhoneNumber;
    private ImageButton SignUp, showPass;
    private ProgressDialog progressDialog;
    private User user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        findViewByIds();
        /*firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");*/
        mAuth = FirebaseAuth.getInstance();
        initListener();
    }

    private void findViewByIds(){
        SignUp = (ImageButton) findViewById(R.id.ibtn_next);
        Email = (EditText) findViewById(R.id.et_Mail);
        Pass = (EditText) findViewById(R.id.et_Password);
        ConfirmPass = (EditText) findViewById(R.id.et_confirmpass);
        FullName = (EditText) findViewById(R.id.et_Fullname);
        Gender = (EditText) findViewById(R.id.et_Gender);
        PhoneNumber = (EditText) findViewById(R.id.et_Phone);
        progressDialog = new ProgressDialog(this);
        showPass = findViewById(R.id.btn_showpass);
    }

    private void initListener(){

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
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

    private void onClickSignUp() {
        String email = Email.getText().toString();
        String pass = Pass.getText().toString();
        String confirmPass = ConfirmPass.getText().toString();
        /*if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPass)){
            Toast.makeText(getApplicationContext(), "Enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }*/
        String fullName = FullName.getText().toString();
        String gender = Gender.getText().toString();
        String phone = PhoneNumber.getText().toString();

        //progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            user = new User(email, pass, fullName, gender, phone);
                            databaseReference = FirebaseDatabase.getInstance().getReference("User");
                            databaseReference.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(
                                    new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(SignUp.this, SignIn.class);
                                        startActivity(intent);
                                        finish();
                                    }else{

                                    }
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
