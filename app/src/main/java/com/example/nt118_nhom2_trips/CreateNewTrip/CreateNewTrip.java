package com.example.nt118_nhom2_trips.CreateNewTrip;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.nt118_nhom2_trips.MainActivity;
import com.example.nt118_nhom2_trips.PlanTrip;
import com.example.nt118_nhom2_trips.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateNewTrip extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{
    DatabaseReference mDatabase, mDatabaseTrip, mDatabaseUser;
    FirebaseAuth mAuth;
    String id;
    Intent intent;
    EditText et_place,et_time,et_name,editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_newtrip);

        Button button = (Button) findViewById(R.id.btn_time);
        Button button_enter = (Button) findViewById(R.id.btn_Enter);
        Button button_back = (Button) findViewById(R.id.btn_back);

        et_place = (EditText) findViewById(R.id.et_place);
        et_time = (EditText) findViewById(R.id.et_time);
        et_name = (EditText) findViewById(R.id.et_name_trip);
        editText = (EditText) findViewById(R.id.et_time);
        Spinner spinner = (Spinner) findViewById(R.id.btn_place);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.place, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        spinner.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User");

        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveInfoTrip();
            }
        });



        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(CreateNewTrip.this, MainActivity.class);
                startActivity(iNewActivity);
            }
        });
    }

    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String StartDay = DateFormat.getDateInstance().format(c.getTime());
        editText.setText(editText.getText() + StartDay + "  ");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        EditText editText = (EditText) findViewById(R.id.et_place);
        editText.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void SaveInfoTrip() {
        String user_id = mAuth.getCurrentUser().getUid();
        //mDatabaseUser = mDatabase.child(user_id);
        //id = mDatabaseTrip.child("Trips").push().getKey();
        mDatabaseTrip = FirebaseDatabase.getInstance().getReference().child("Trips");
        id = mDatabaseTrip.push().getKey();
        String[] date = et_time.getText().toString().split("\\s\\s");
        Trips trip = new Trips(id ,user_id, date[0],  date[1], et_name.getText().toString(), et_place.getText().toString());
        mDatabaseTrip.child(id).setValue(trip);
        mDatabaseTrip.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                intent = new Intent(CreateNewTrip.this, InfoTripS.class);
                Bundle bundle = new Bundle();
                bundle.putString("trip_id", id);
                //bundle.putString("user_id", user_id);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
