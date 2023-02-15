package com.example.nt118_nhom2_trips.Hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.nt118_nhom2_trips.CreateNewTrip.CreateNewTrip;
import com.example.nt118_nhom2_trips.CreateNewTrip.InfoTripS;
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

public class Activity_Hotel extends AppCompatActivity implements OnHotelItemClickListener {
    private RecyclerView rcvCategory, rcvCategoryHotel;
    private PlaceNameAdapater placeNameAdapater;
    private HotelAdapter hotelAdapter;
    private Button backhome;
    private DatabaseReference mDatabaseHotels;
    private FirebaseAuth mAuth;
    private List<Hotel> mListHotels;
    private Intent intent;
    SearchView searchHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        rcvCategory = findViewById(R.id.rcv_category);
        backhome = (Button) findViewById(R.id.btn_backhome);
        rcvCategoryHotel = findViewById(R.id.rcv_catoryhotel);
        searchHotel = findViewById(R.id.search_hotel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mListHotels = new ArrayList<Hotel>();

        mAuth = FirebaseAuth.getInstance();
        hotelAdapter = new HotelAdapter (mListHotels, this);
        mDatabaseHotels = FirebaseDatabase.getInstance().getReference().child("Hotels");

        rcvCategoryHotel.setLayoutManager(linearLayoutManager);
        hotelAdapter.setData(mListHotels);
        rcvCategoryHotel.setAdapter(hotelAdapter);

        getList();

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
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(Activity_Hotel.this, MainActivity.class);
                startActivity(iNewActivity);
            }
        });
        searchHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(Activity_Hotel.this, SearchHotel.class);
                startActivity(iNewActivity);
            }
        });

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

    @Override
    public void onHotelItemClick(Hotel mhotel) {
        intent = new Intent(this, InfoHotel.class);
        Bundle bundle = new Bundle();
        bundle.putString("hotel_id", mhotel.getId_hotel());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
