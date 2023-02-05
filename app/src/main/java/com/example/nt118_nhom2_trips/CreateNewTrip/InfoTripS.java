package com.example.nt118_nhom2_trips.CreateNewTrip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nt118_nhom2_trips.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InfoTripS extends AppCompatActivity implements OnItemClickListener{
    String tripId, userId, id;
    DatabaseReference mDatabase, mDatabaseUser, mDatabaseTrip, mDatabaseActivity;
    FirebaseAuth mAuth;
    Trips trip;
    int numberOfDays, hour, minute, day = 1;
    RecyclerView rcv_day, rcv_activity;
    DateAdapter dateAdapter;
    ActivityAdapter activityAdapter;
    Date StartDate, EndDate;
    Button Btn_Open_View, Btn_Save_Infotrip;
    LinearLayout linearLayout;
    EditText time_text, name_text, detail_text;
    List<Activity> mlistActivity;
    List<com.example.nt118_nhom2_trips.CreateNewTrip.Date> mlistDate;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_trip_s);

        TextView tv = findViewById(R.id.textView3);
        rcv_day = findViewById(R.id.rcv_date);
        rcv_activity = findViewById(R.id.rcv_activity);
        Btn_Open_View = findViewById(R.id.btn_Enter);
        Btn_Save_Infotrip = findViewById(R.id.btn_Save_activity);
        linearLayout = findViewById(R.id.View_activity);
        time_text = (EditText) findViewById(R.id.et_time_activity);
        name_text = (EditText) findViewById(R.id.et_name_activity);
        detail_text = (EditText) findViewById(R.id.et_detail_activity);

        linearLayout.setVisibility(View.GONE);
        dateAdapter = new DateAdapter(mlistDate, this);
        activityAdapter = new ActivityAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rcv_day.setLayoutManager(linearLayoutManager);
        rcv_activity.setLayoutManager(linearLayoutManager1);
        mlistActivity = new ArrayList<Activity>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            tripId = bundle.getString("trip_id","");
        }

        mAuth = FirebaseAuth.getInstance();
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("User");
        //mDatabaseUser = mDatabase.child(mAuth.getCurrentUser().getUid());
        //mDatabaseTrip = mDatabaseUser.child("Trips").child(tripId);
        mDatabaseTrip = FirebaseDatabase.getInstance().getReference().child("Trips");
        mDatabaseActivity = FirebaseDatabase.getInstance().getReference().child("Activity");
        mDatabaseTrip.child(tripId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trip = snapshot.getValue(Trips.class);
                tv.setText(trip.getName_trip());
                StartDate = StringToDate(trip.getStart_date_trip());
                EndDate = StringToDate(trip.getEnd_date_trip());
                numberOfDays = NumberOfDays(StartDate,EndDate);
                mlistDate = getListDate();
                dateAdapter.setData(mlistDate);
                rcv_day.setAdapter(dateAdapter);
                activityAdapter.setData(mlistActivity);
                rcv_activity.setAdapter(activityAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Btn_Open_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearLayout.getVisibility() != View.VISIBLE) {
                    linearLayout.setVisibility(View.VISIBLE);
                    Btn_Open_View.setVisibility(View.GONE);
                }
            }
        });

        time_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popTimePicker();
            }
        });

        Btn_Save_Infotrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save_Activity();
            }
        });
    }

    public Date StringToDate(String StrDate) {
        SimpleDateFormat formatter=new SimpleDateFormat("MMM dd, yyyy");
        Date date = null;
        try {
            date = formatter.parse(StrDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String DateToString(Date date) {
        String StrDate = "";
        SimpleDateFormat formatter=new SimpleDateFormat("MMM dd, yyyy");
        try {
            StrDate = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StrDate;
    }

    public int NumberOfDays (Date start, Date end) {
        long l_number = (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
        int number = (int) l_number + 1;
        return number;
    }

    public List<com.example.nt118_nhom2_trips.CreateNewTrip.Date> getListDate() {
        Date date;
        String strDate;
        List<com.example.nt118_nhom2_trips.CreateNewTrip.Date> list = new ArrayList<>();
        for (int i = 1; i <= numberOfDays; i++) {
            if (StartDate.before(EndDate)) {
                date = StartDate;
                strDate = DateToString(date);
                list.add(new com.example.nt118_nhom2_trips.CreateNewTrip.Date("Day " + i, strDate));
                Long l = StartDate.getTime() + TimeUnit.DAYS.toMillis(1);
                StartDate.setTime(l);
            } else {
                strDate = DateToString(StartDate);
                list.add(new com.example.nt118_nhom2_trips.CreateNewTrip.Date("Day " + i, strDate));
            }
        }
        return list;
    }

    public void popTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int cHour, int cMinute) {
                hour = cHour;
                minute = cMinute;
                time_text.setText(String.format(Locale.getDefault(),"%02d:%02d", hour, minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void Save_Activity() {
        userId = mAuth.getCurrentUser().getUid();
        id = mDatabaseActivity.push().getKey();
        activity = new Activity(id, tripId, userId, day + "", time_text.getText().toString(), name_text.getText().toString(), detail_text.getText().toString());
        mDatabaseActivity.child(id).setValue(activity);
        time_text.setText("");
        name_text.setText("");
        detail_text.setText("");

        Query query = mDatabaseActivity.orderByChild("activity_id").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Activity activity1 = snapshot.getValue(Activity.class);
                if (activity1 != null) {
                    mlistActivity.add(activity1);
                    activityAdapter.notifyDataSetChanged();
                }
                String list = "";
                for (int i = 0; i<mlistActivity.size(); i++){
                    list += mlistActivity.get(i);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (linearLayout.getVisibility() == View.VISIBLE) {
            linearLayout.setVisibility(View.GONE);
            Btn_Open_View.setVisibility(View.VISIBLE);
        }
    }

    public void ChangeDay() {
        Query query = mDatabaseActivity.orderByChild("day").equalTo(day + "");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mlistActivity.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Activity activity1 = ds.getValue(Activity.class);
                    if (activity1.getId().contains(userId) && activity1.getTrip_id().contains(tripId)) {
                        mlistActivity.add(activity1);
                    }
                }
                activityAdapter.notifyDataSetChanged();
                //rcv_activity.setAdapter(activityAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(com.example.nt118_nhom2_trips.CreateNewTrip.Date date) {
        String[] strDay = date.getDay().split(" ");
        day = Integer.parseInt(strDay[1]);
        ChangeDay();
    }
}