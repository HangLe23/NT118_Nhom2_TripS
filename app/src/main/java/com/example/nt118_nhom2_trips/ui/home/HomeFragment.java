package com.example.nt118_nhom2_trips.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nt118_nhom2_trips.Hotel.Activity_Hotel;
import com.example.nt118_nhom2_trips.Activity_Tour;
import com.example.nt118_nhom2_trips.CreateNewTrip.CreateNewTrip;
import com.example.nt118_nhom2_trips.R;

import java.util.ArrayList;
import java.util.List;

import com.example.nt118_nhom2_trips.PlaceName.PlaceName;
import com.example.nt118_nhom2_trips.PlaceName.PlaceNameAdapater;


public class HomeFragment extends Fragment {

    private Button create;
    private ImageButton hotel, tour, youTrip;
    private RecyclerView rcvCategory;
    private PlaceNameAdapater placeNameAdapater;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        create = v.findViewById(R.id.btn_createTrip);
        hotel = v.findViewById(R.id.btn_hotel);
        tour = v.findViewById(R.id.btn_tour);
        youTrip = v.findViewById(R.id.ibtn_yourTrip);
        rcvCategory = v.findViewById(R.id.rcv_category);


        initListener();

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
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        rcvCategory.setLayoutManager(linearLayoutManager);
        placeNameAdapater = new PlaceNameAdapater(list);

        rcvCategory.setAdapter(placeNameAdapater);

        return v;
    }
    private void initListener(){
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateNewTrip.class);
                startActivity(intent);
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_Hotel.class);
                startActivity(intent);
            }
        });
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_Tour.class);
                startActivity(intent);
            }
        });
    }
}


