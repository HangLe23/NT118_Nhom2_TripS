package com.example.nt118_nhom2_trips;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import PlaceName.PlaceName;
import PlaceName.PlaceNameAdapater;

public class Hotel extends AppCompatActivity {
    private RecyclerView rcvCategory, rcvCategoryHotel;
    private PlaceNameAdapater placeNameAdapater;
    private Button backhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        findViewByIds();

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(Hotel.this, MainActivity.class);
                startActivity(iNewActivity);
            }
        });
        getList();

    }
    private void findViewByIds(){
        rcvCategory = findViewById(R.id.rcv_category);
        backhome = (Button) findViewById(R.id.btn_backhome);
        rcvCategoryHotel = findViewById(R.id.rcv_catoryhotel);
    }
    private void getList(){
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
        LinearLayoutManager horizontallinearLayoutManager = new LinearLayoutManager(
                this, RecyclerView.HORIZONTAL, false);

        rcvCategory.setLayoutManager(horizontallinearLayoutManager);
        placeNameAdapater = new PlaceNameAdapater(list);

        rcvCategory.setAdapter(placeNameAdapater);
    }
}
