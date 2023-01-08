package com.example.nt118_nhom2_trips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.List;

import PlaceName.PlaceName;
import PlaceName.PlaceNameAdapater;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvCategory;
    private PlaceNameAdapater placeNameAdapater;
    private Button create;
    private ImageButton hotel, tour, yourTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(MainActivity.this, CreateNewTrip.class);
                startActivity(iNewActivity);
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(MainActivity.this, Hotel.class);
                startActivity(iNewActivity);
            }
        });
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(MainActivity.this, Tour.class);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        rcvCategory.setLayoutManager(linearLayoutManager);
        placeNameAdapater = new PlaceNameAdapater(list);

        rcvCategory.setAdapter(placeNameAdapater);
    }
    private void findViewByIds(){
        create = (Button) findViewById(R.id.btn_CreateTrip);
        hotel = (ImageButton) findViewById(R.id.ibtn_hotel);
        tour = (ImageButton) findViewById(R.id.ibtn_tour);
        yourTrip = (ImageButton) findViewById(R.id.ibtn_yourTrip);
        rcvCategory = findViewById(R.id.rcv_category);
    }

}