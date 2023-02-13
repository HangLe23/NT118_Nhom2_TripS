package com.example.nt118_nhom2_trips.Hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nt118_nhom2_trips.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InfoRoom extends AppCompatActivity {

    private TextView tv_email, tv_phone, tv_rate, tv_name, tv_address, tv_start, tv_end, tv_guest, tv_price, tv_name1, tv_guest1, tv_size, tv_breakfast, tv_wifi, tv_bathroom, tv_price2;
    private ImageView img_room;
    private DatabaseReference mDatabaseHotels, mDatabaseRooms;
    String HotelID, RoomID, StartDay, EndDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_room);

        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        tv_rate = findViewById(R.id.tv_rate);
        tv_name = findViewById(R.id.tv_nameroom);
        img_room = findViewById(R.id.img_room);
        tv_address = findViewById(R.id.tv_address);
        tv_start = findViewById(R.id.tv_startday);
        tv_end = findViewById(R.id.tv_endday);
        tv_guest = findViewById(R.id.tv_guest);
        tv_guest1 = findViewById(R.id.tv_guest1);
        tv_price = findViewById(R.id.tv_price);
        tv_name1 = findViewById(R.id.tv_nameroom1);
        tv_size = findViewById(R.id.tv_size);
        tv_breakfast = findViewById(R.id.tv_breakfast);
        tv_wifi = findViewById(R.id.tv_wifi);
        tv_bathroom = findViewById(R.id.tv_bath);
        tv_price2 = findViewById(R.id.tv_price2);


        mDatabaseHotels = FirebaseDatabase.getInstance().getReference().child("Hotels");
        mDatabaseRooms = FirebaseDatabase.getInstance().getReference().child("Rooms");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            HotelID = bundle.getString("hotel_id", "");
            RoomID = bundle.getString("room_id", "");
            StartDay = bundle.getString("start_day", "");
            EndDay = bundle.getString("end_day", "");
        }

        tv_start.setText(StartDay);
        tv_end.setText(EndDay);

        Query query = mDatabaseHotels.orderByChild("id_hotel").equalTo(HotelID);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Hotel hotel = ds.getValue(Hotel.class);
                    if(hotel != null) {
                        tv_phone.setText(hotel.getPhone());
                        tv_email.setText(hotel.getEmail());
                        tv_rate.setText(hotel.getRate());
                        tv_address.setText(hotel.getAddress());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Query queryRoom = mDatabaseRooms.orderByChild("id_room").equalTo(RoomID);
        queryRoom.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Room room = ds.getValue(Room.class);
                    if(room != null) {
                        tv_name.setText(room.getRoom_name());
                        String url = room.getImg_room();
                        Picasso.get().load(url).into(img_room);
                        tv_guest.setText(room.getGuest() + "khách");
                        int price = room.getPrice() - room.getSale()*room.getPrice()/100;
                        tv_price.setText(price + "");
                        tv_name1.setText(room.getRoom_name());
                        tv_guest1.setText("Giá cho " + room.getGuest() + " người lớn");
                        tv_size.setText("Kích thước: " + room.getSize() +"m2");
                        if(room.isBreakfast())
                            tv_breakfast.setText("Bao gồm bữa sáng");
                        else
                            tv_breakfast.setText("Không bao gồm bữa sáng");
                        if(room.isWifi())
                            tv_wifi.setText("Wifi miễn phí");
                        else
                            tv_wifi.setText("Không có wifi");
                        if(room.isBathroom())
                            tv_bathroom.setText("Phòng tắm riêng");
                        else
                            tv_bathroom.setText("Phòng tắm chung");
                        tv_price2.setText(price + "");
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}