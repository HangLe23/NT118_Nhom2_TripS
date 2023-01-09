package com.example.nt118_nhom2_trips;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Hotel.Hotel;
import Hotel.HotelAdapter;

public class SearchHotel extends AppCompatActivity {
    private RecyclerView rcvHotel;
    private HotelAdapter hotelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        findViewByIds();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvHotel.setLayoutManager(linearLayoutManager);
        hotelAdapter = new HotelAdapter(getList());

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvHotel.addItemDecoration(itemDecoration);
    }

    private List<Hotel> getList() {
        List<Hotel> list = new ArrayList<>();
        list.add(new Hotel(R.drawable.catba_haiphong, "Cát Bà, Hải Phòng", "5"));
        list.add(new Hotel(R.drawable.dalat_lamdong, "Đà Lạt, Lâm Đồng", "5"));
        list.add(new Hotel(R.drawable.cauvang, "Cầu Vàng, Đà Nẵng", "5"));
        return list;
    }

    private void findViewByIds(){
        rcvHotel = findViewById(R.id.rcv_catoryhotel);
    }
}
