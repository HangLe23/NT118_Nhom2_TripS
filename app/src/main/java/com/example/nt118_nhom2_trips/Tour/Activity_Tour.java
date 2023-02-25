package com.example.nt118_nhom2_trips.Tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import com.example.nt118_nhom2_trips.Hotel.Activity_Hotel;
import com.example.nt118_nhom2_trips.Hotel.Hotel;
import com.example.nt118_nhom2_trips.Hotel.HotelAdapter;
import com.example.nt118_nhom2_trips.Hotel.InfoHotel;
import com.example.nt118_nhom2_trips.Hotel.OnHotelItemClickListener;
import com.example.nt118_nhom2_trips.Hotel.SearchHotel;
import com.example.nt118_nhom2_trips.MainActivity;
import com.example.nt118_nhom2_trips.PlaceName.PlaceName;
import com.example.nt118_nhom2_trips.PlaceName.PlaceNameAdapater;
import com.example.nt118_nhom2_trips.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Activity_Tour extends AppCompatActivity implements OnTourItemClickListener{
    private PlaceNameAdapater placeNameAdapater;
    private TourAdapter tourAdapter;
    private RecyclerView rcvCategory, rcvCategoryTour;
    private DatabaseReference mDatabaseTours;
    private FirebaseAuth mAuth;
    private List<Tour> mListTours;
    private Intent intent;
    private Button backhome;
    SearchView searchtour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        rcvCategory = findViewById(R.id.rcv_category);
        rcvCategoryTour = findViewById(R.id.rcv_catorytour);
        backhome = (Button) findViewById(R.id.btn_backhome);
        searchtour = findViewById(R.id.search_tour);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mListTours = new ArrayList<Tour>();
        mAuth = FirebaseAuth.getInstance();
        tourAdapter = new TourAdapter (mListTours, this);
        mDatabaseTours = FirebaseDatabase.getInstance().getReference().child("Tours");

        rcvCategoryTour.setLayoutManager(linearLayoutManager);
        tourAdapter.setData(mListTours);
        rcvCategoryTour.setAdapter(tourAdapter);

        Query query = mDatabaseTours;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Tour tour = ds.getValue(Tour.class);
                    mListTours.add(tour);
                }
                tourAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(Activity_Tour.this, MainActivity.class);
                startActivity(iNewActivity);
            }
        });
        searchtour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(Activity_Tour.this, SearchTour.class);
                startActivity(iNewActivity);
            }
        });

        List<PlaceName> list = new ArrayList<>();
        list.add(new PlaceName(R.drawable.catba_haiphong, "Cát Bà, Hải Phòng"));
        list.add(new PlaceName(R.drawable.dalat_lamdong, "Đà Lạt, Lâm Đồng"));
        list.add(new PlaceName(R.drawable.cauvang, "Cầu Vàng, Đà Nẵng"));
        list.add(new PlaceName(R.drawable.daocoto_quangninh, "Cô Tô, Quảng Ninh"));
        list.add(new PlaceName(R.drawable.daothoison_bentre, "Đảo Thới Sơn, Bến Tre"));
        list.add(new PlaceName(R.drawable.mocchau, "Mộc Châu, Sơn La"));
        list.add(new PlaceName(R.drawable.nhatrang_khanhhoa, "Nha Trang, Khánh Hòa"));
        list.add(new PlaceName(R.drawable.tadung_daknong, "Tà Đùng, Đăk Nông"));
        list.add(new PlaceName(R.drawable.sapa, "SaPa, Lào Cai"));
        list.add(new PlaceName(R.drawable.samson_thanhhoa, "Sầm Sơn, Thanh Hóa"));
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        rcvCategory.setLayoutManager(linearLayoutManager1);
        placeNameAdapater = new PlaceNameAdapater(list);

        rcvCategory.setAdapter(placeNameAdapater);
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
