package com.example.nt118_nhom2_trips.ui.profile;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nt118_nhom2_trips.MainActivity;
import com.example.nt118_nhom2_trips.R;
import com.example.nt118_nhom2_trips.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText fullname, birthday, gender, email, phone;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private Button btn_Update, time;
    private ImageView avatar;
    private Spinner btn_gender;
    private DatePickerDialog picker;
    final private MainActivity mainActivity = new MainActivity();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        findViewByIds();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        showProfile(firebaseUser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        btn_gender.setAdapter(adapter);
        initListener();
    }

    private void initListener() {
        /*avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });*/
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(UpdateProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        birthday.setText(i2 + "/" + (i1 + 1) + "/" + i);
                    }
                }, year, month, day);
                picker.show();
            }
        });
        btn_gender.setOnItemSelectedListener(this);
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile(firebaseUser);
            }
        });
    }

    private void updateProfile(FirebaseUser firebaseUser) {
        UpdateEmail(firebaseUser);
        /*UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(fullname.getText().toString())*//*.setPhotoUri(uri)*//*.build();
        firebaseUser.updateProfile(profileChangeRequest);*/
        User user = new User(fullname.getText().toString(), birthday.getText().toString(), gender.getText().toString(), phone.getText().toString());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        String UID = firebaseUser.getUid();
        progressDialog.show();
        reference.child(UID).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(UpdateProfile.this, "Update Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mainActivity.showUserInformation();
    }

    private void UpdateEmail(FirebaseUser user) {
        user.updateEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                        }
                    }
                });
    }


    private void showProfile(FirebaseUser firebaseUser) {
        String id = firebaseUser.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user != null){
                    fullname.setText(user.getFullname());
                    birthday.setText(user.getBirthday());
                    gender.setText(user.getGender());
                    phone.setText(user.getPhone());
                    email.setText(firebaseUser.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void findViewByIds(){
        avatar = (ImageView) findViewById(R.id.img_avatar);
        fullname = (EditText) findViewById(R.id.et_fullname);
        birthday = (EditText) findViewById(R.id.et_birthday);
        gender = (EditText) findViewById(R.id.et_gender);
        email = (EditText) findViewById(R.id.et_email);
        phone = (EditText) findViewById(R.id.et_phone);
        time = (Button) findViewById(R.id.btn_time);
        btn_Update = (Button) findViewById(R.id.btn_update);
        btn_gender = (Spinner) findViewById(R.id.btn_choosegender);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        gender.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
