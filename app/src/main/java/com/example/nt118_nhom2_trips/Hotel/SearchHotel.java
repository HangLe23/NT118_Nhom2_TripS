package com.example.nt118_nhom2_trips.Hotel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.nt118_nhom2_trips.Hotel.Hotel;
import com.example.nt118_nhom2_trips.Hotel.HotelAdapter;
import com.example.nt118_nhom2_trips.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchHotel extends AppCompatActivity implements OnHotelItemClickListener{
    private RecyclerView rcvHotel;
    private HotelAdapter hotelAdapter;
    private List<Hotel> mListHotels;
    private DatabaseReference mDatabaseHotels;
    SearchView search;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_hotel);

        rcvHotel = findViewById(R.id.rcv_catoryhotel);
        search = findViewById(R.id.search_hotel);
        hotelAdapter = new HotelAdapter (mListHotels, this);
        mDatabaseHotels = FirebaseDatabase.getInstance().getReference().child("Hotels");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mListHotels = new ArrayList<Hotel>();
        rcvHotel.setLayoutManager(linearLayoutManager);
        hotelAdapter.setData(mListHotels);
        rcvHotel.setAdapter(hotelAdapter);

        Query query = mDatabaseHotels;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Hotel hotel = ds.getValue(Hotel.class);
                    mListHotels.add(hotel);
                }
                hotelAdapter.notifyDataSetChanged();
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
                searchHotel(newText);
                return true;
            }
        });

    }

    public  void searchHotel(String text) {
        ArrayList<Hotel> searchhotel = new ArrayList<>();
        for(Hotel hotel:mListHotels) {
            if (hotel.getName().toLowerCase().contains(text.toLowerCase())) {
                searchhotel.add(hotel);
            }
        }
        hotelAdapter.searchHotel(searchhotel);
    }

    @Override
    public void onHotelItemClick(Hotel hotel) {
        intent = new Intent(this, InfoHotel.class);
        Bundle bundle = new Bundle();
        bundle.putString("hotel_id", hotel.getId_hotel());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
