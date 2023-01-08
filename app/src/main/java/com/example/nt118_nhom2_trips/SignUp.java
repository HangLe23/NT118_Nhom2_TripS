package com.example.nt118_nhom2_trips;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    EditText et_Name, et_LastName, editTextTextPersonName8, et_Password, et_Confirm_pass,et_Email, et_Phone;
    Button btn_Signup, btn_back;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    CheckBox btn_show_pass, btn_show_confirm;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Account");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        btn_back=(Button) findViewById(R.id.btn_back);
        et_Name = (EditText) findViewById(R.id.et_Name);
        editTextTextPersonName8 = (EditText) findViewById(R.id.editTextTextPersonName8);
        et_Password = (EditText) findViewById(R.id.et_Password);
        et_Confirm_pass = (EditText) findViewById(R.id.et_Confirm_pass);
        et_Email = (EditText) findViewById(R.id.et_Email);
        et_Phone = (EditText) findViewById(R.id.et_Phone);

        btn_Signup = (Button) findViewById(R.id.btn_Signup);
        et_LastName = (EditText) findViewById(R.id.et_LastName);
        btn_show_pass = (CheckBox) findViewById(R.id.btn_show_pass);
        btn_show_confirm = (CheckBox) findViewById(R.id.btn_show_confirm);



        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing up... ");

        btn_show_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    et_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    et_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btn_show_confirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    et_Confirm_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    et_Confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= et_Email.getText().toString().trim();
                String password = et_Password.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    et_Email.setError("Invalid Email");
                    et_Email.setFocusable(true);

                }
                else  if (password.length() < 6) {
                    et_Password.setError("Password length at least 6 character");
                    et_Password.setFocusable(true);
                } else {
                    registerUser(email, password);

                }

            }
        });

    }

    private void registerUser(String email, String password) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUp.this, "Sign up...\n"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
