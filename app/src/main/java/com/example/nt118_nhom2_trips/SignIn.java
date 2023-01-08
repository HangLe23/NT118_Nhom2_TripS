package com.example.nt118_nhom2_trips;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class SignIn extends AppCompatActivity {

    EditText et_Mail, et_Password;
    TextView tv_ForgetPass;
    Button btn_Enter,btn_back;
    private FirebaseAuth mAuth;
    CheckBox btn_show_pass;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign in");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        et_Mail =(EditText)  findViewById(R.id.et_Mail);
        et_Password =(EditText) findViewById(R.id.et_Password);

        tv_ForgetPass = (TextView) findViewById(R.id.tv_ForgetPass);

        btn_back =(Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, MainActivity.class));
            }
        });
        btn_show_pass = (CheckBox) findViewById(R.id.btn_show_pass);

        mAuth = FirebaseAuth.getInstance();

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

        btn_Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_Mail.getText().toString();
                String pass = et_Password.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    et_Mail.setError("Invalid email");
                    et_Mail.setFocusable(true);
                }
                else {
                    loginUser(email,pass);
                }
            }
        });
        tv_ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecoverPassword();

            }
        });
        pd =new ProgressDialog(this);
    }
    private void showRecoverPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recover password");
        LinearLayout linearLayout = new LinearLayout(this);
        EditText email_et = new EditText(this);
        email_et.setHint("Email");
        email_et.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        email_et.setMinEms(10);

        linearLayout.addView(email_et);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email = email_et.getText().toString().trim();

                beginRecovery(email);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    private void beginRecovery(String email) {
        pd.setMessage("Sendding email...");

        pd.show();
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        pd.dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(SignIn.this, "Email sent", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignIn.this, "Failed...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        pd.dismiss();
                        Toast.makeText(SignIn.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loginUser(String email, String pass) {
        pd.setMessage("Logging in...");

        pd.show();
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            pd.dismiss();

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignIn.this, "Sign in...\n"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignIn.this, MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            pd.dismiss();
                            Toast.makeText(SignIn.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(SignIn.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
