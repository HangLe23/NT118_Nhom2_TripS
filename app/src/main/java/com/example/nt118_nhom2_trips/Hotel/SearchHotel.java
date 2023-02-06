package com.example.nt118_nhom2_trips.Hotel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.nt118_nhom2_trips.Hotel.Hotel;
import com.example.nt118_nhom2_trips.Hotel.HotelAdapter;
import com.example.nt118_nhom2_trips.R;

public class SearchHotel extends AppCompatActivity {
    private RecyclerView rcvHotel;
    private HotelAdapter hotelAdapter;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_hotel);
        findViewByIds();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvHotel.setLayoutManager(linearLayoutManager);
        //hotelAdapter = new HotelAdapter(getList());
        //rcvHotel.setAdapter(hotelAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvHotel.addItemDecoration(itemDecoration);
    }

    private List<Hotel> getList() {
        List<Hotel> list = new ArrayList<>();
        return list;
    }

    private void findViewByIds(){
        rcvHotel = findViewById(R.id.rcv_catoryhotel);
        search = findViewById(R.id.search_hotel);
    }
}
