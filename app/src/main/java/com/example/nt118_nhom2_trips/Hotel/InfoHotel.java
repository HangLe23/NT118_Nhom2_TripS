package com.example.nt118_nhom2_trips.Hotel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nt118_nhom2_trips.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InfoHotel extends AppCompatActivity {
    private DatabaseReference mDatabaseHotels;
    private String HotelID;
    private TextView tv_rate, tv_name, tv_detail, tv_address;
    private ImageView iv_hotel;
    private List<Hotel> mListHotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hotel);

        tv_rate = findViewById(R.id.tv_rate);
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        tv_detail = findViewById(R.id.tv_detail);
        iv_hotel = findViewById(R.id.img_hotel);
        mListHotel = new ArrayList<>();

        mDatabaseHotels = FirebaseDatabase.getInstance().getReference().child("Hotels");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            HotelID = bundle.getString("hotel_id", "1");
        }

        Query query = mDatabaseHotels.orderByChild("id_hotel").equalTo("1");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Hotel hotel = ds.getValue(Hotel.class);
                    if(hotel != null) {
                        tv_address.setText(hotel.getAddress());
                        tv_name.setText(hotel.getName());
                        tv_rate.setText(hotel.getRate());
                        tv_detail.setText(hotel.getDetail());
                        String url = hotel.getImageUrl();
                        Picasso.get().load(url).into(iv_hotel);
                        break;
                    }
                }
                //  tv_address.setText(mListHotel.get(0).getAddress() + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}