package com.example.nt118_nhom2_trips.Hotel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nt118_nhom2_trips.CreateNewTrip.Activity;
import com.example.nt118_nhom2_trips.CreateNewTrip.DatePickerFragment;
import com.example.nt118_nhom2_trips.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InfoHotel extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, OnRoomItemClickListener{
    private DatabaseReference mDatabaseHotels, mDatabaseRooms;
    private String HotelID, Start, End;
    private TextView tv_rate, tv_name, tv_detail, tv_address, tv_phone, tv_email;
    private EditText et_time;
    private Button btn_time;
    private ImageView iv_hotel;
    private List<Hotel> mListHotel;
    private List<Room> mListRoom;
    private RecyclerView rcv_room;
    private RoomAdapter roomAdapter;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hotel);

        tv_rate = findViewById(R.id.tv_rate);
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        tv_detail = findViewById(R.id.tv_detail);
        iv_hotel = findViewById(R.id.img_hotel);
        tv_phone= findViewById(R.id.phone);
        tv_email= findViewById(R.id.tv_email);
        et_time = findViewById(R.id.et_time);
        btn_time = findViewById(R.id.btn_time);
        rcv_room = findViewById(R.id.rcv_room);
        mListHotel = new ArrayList<>();
        mListRoom = new ArrayList<Room>();
        roomAdapter = new RoomAdapter(mListRoom, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_room.setLayoutManager(linearLayoutManager);

        mDatabaseHotels = FirebaseDatabase.getInstance().getReference().child("Hotels");
        mDatabaseRooms = FirebaseDatabase.getInstance().getReference().child("Rooms");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            HotelID = bundle.getString("hotel_id", "");
        }

        Query query = mDatabaseHotels.orderByChild("id_hotel").equalTo(HotelID);
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
                        tv_phone.setText(hotel.getPhone());
                        tv_email.setText(hotel.getEmail());
                        String url = hotel.getImageUrl();
                        Picasso.get().load(url).into(iv_hotel);
                        roomAdapter.setData(mListRoom);
                        rcv_room.setAdapter(roomAdapter);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Query queryRoom = mDatabaseRooms.orderByChild("id_hotel").equalTo(HotelID);
        queryRoom.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Room room = ds.getValue(Room.class);
                    mListRoom.add(room);
                }
                roomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String StartDay = DateFormat.getDateInstance().format(c.getTime());
        et_time.setText(et_time.getText() + StartDay + "  ");
    }

    @Override
    public void onHotelItemClick(Room room) {
       if(et_time.getText().length() != 28){
           Toast.makeText(this, "Vui lòng chọn đúng ngày đặt và trả phòng", Toast.LENGTH_SHORT).show();
       }
       else {
           String[] day = et_time.getText().toString().split("  ");
           intent = new Intent(this, InfoRoom.class);
           Bundle bundle = new Bundle();
           bundle.putString("hotel_id", room.getId_hotel());
           bundle.putString("start_day", day[0]);
           bundle.putString("end_day", day[1]);
           bundle.putString("room_id", room.getId_room());
           intent.putExtras(bundle);
           startActivity(intent);
           finish();
       }
    }
}