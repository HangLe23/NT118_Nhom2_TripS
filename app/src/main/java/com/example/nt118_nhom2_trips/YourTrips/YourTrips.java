package com.example.nt118_nhom2_trips.YourTrips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.nt118_nhom2_trips.CreateNewTrip.Trips;
import com.example.nt118_nhom2_trips.Hotel.Hotel;
import com.example.nt118_nhom2_trips.Hotel.HotelAdapter;
import com.example.nt118_nhom2_trips.Hotel.OnHotelItemClickListener;
import com.example.nt118_nhom2_trips.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class YourTrips extends AppCompatActivity implements OnTripItemClickListener {
    private RecyclerView rcv_trips;
    private TripAdapter tripAdapter;
    private DatabaseReference mDatabaseTrips;
    private List<Trips> mListTrips;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_trips);

        rcv_trips = findViewById(R.id.rcv_trips);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mListTrips = new ArrayList<Trips>();
        tripAdapter = new TripAdapter (mListTrips, this);
        mDatabaseTrips = FirebaseDatabase.getInstance().getReference().child("Trips");
        rcv_trips.setLayoutManager(linearLayoutManager);
        tripAdapter.setData(mListTrips);
        rcv_trips.setAdapter(tripAdapter);
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = mDatabaseTrips.orderByChild("id").equalTo(user_id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Trips hotel = ds.getValue(Trips.class);
                    mListTrips.add(hotel);
                }
                tripAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onTripItemClick(Trips trips) {

    }
}