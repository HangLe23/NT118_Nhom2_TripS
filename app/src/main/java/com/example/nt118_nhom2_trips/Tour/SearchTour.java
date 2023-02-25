package com.example.nt118_nhom2_trips.Tour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.nt118_nhom2_trips.Hotel.Hotel;
import com.example.nt118_nhom2_trips.Hotel.HotelAdapter;
import com.example.nt118_nhom2_trips.Hotel.OnHotelItemClickListener;
import com.example.nt118_nhom2_trips.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchTour extends AppCompatActivity implements OnTourItemClickListener{

    private RecyclerView rcvTour;
    private TourAdapter TourAdapter;
    private List<Tour> mListTour;
    private DatabaseReference mDatabaseTours;
    SearchView search;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tour);

        rcvTour = findViewById(R.id.rcv_catorytour);
        search = findViewById(R.id.search_tour);
        TourAdapter = new TourAdapter (mListTour, this);
        mDatabaseTours = FirebaseDatabase.getInstance().getReference().child("Tours");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mListTour = new ArrayList<Tour>();
        rcvTour.setLayoutManager(linearLayoutManager);
        TourAdapter.setData(mListTour);
        rcvTour.setAdapter(TourAdapter);

        Query query = mDatabaseTours;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Tour tour = ds.getValue(Tour.class);
                    mListTour.add(tour);
                }
                TourAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTour(newText);
                return true;
            }
        });
    }
    public  void searchTour(String text) {
        ArrayList<Tour> searchtour = new ArrayList<>();
        for(Tour tour:mListTour) {
            if (tour.getName().toLowerCase().contains(text.toLowerCase())) {
                searchtour.add(tour);
            }
        }
        TourAdapter.searchHotel(searchtour);
    }

    @Override
    public void onTourItemClick(Tour tour) {
        intent = new Intent(this, Info_tour.class);
        Bundle bundle = new Bundle();
        bundle.putString("tour_id", tour.getId_tour());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}