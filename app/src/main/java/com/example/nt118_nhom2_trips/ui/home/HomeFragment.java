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

import com.example.nt118_nhom2_trips.Activity_Hotel;
import com.example.nt118_nhom2_trips.Activity_Tour;
import com.example.nt118_nhom2_trips.CreateNewTrip;
import com.example.nt118_nhom2_trips.R;
import com.example.nt118_nhom2_trips.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Button create;
    private ImageButton hotel, tour, youTrip;
    //private RecyclerView rcvCategory;
    //private PlaceNameAdapater placeNameAdapater;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        create = v.findViewById(R.id.btn_createTrip);
        hotel = v.findViewById(R.id.btn_hotel);
        tour = v.findViewById(R.id.btn_tour);
        youTrip = v.findViewById(R.id.ibtn_yourTrip);


        initListener();

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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


