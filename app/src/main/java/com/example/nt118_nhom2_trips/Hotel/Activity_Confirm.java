package com.example.nt118_nhom2_trips.Hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nt118_nhom2_trips.R;
import com.example.nt118_nhom2_trips.user.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

import com.example.nt118_nhom2_trips.Helpers.CreateOrder;
import com.example.nt118_nhom2_trips.Helpers.AppInfo;

public class Activity_Confirm extends AppCompatActivity {
    private TextView tv_email, tv_phone, tv_name, tv_address, tv_start, tv_end, tv_guest, tv_price, tv_name1, tv_guest1, tv_size, tv_breakfast, tv_wifi, tv_bathroom, tv_price2, tv_birthday;
    private DatabaseReference mDatabaseRooms, mDatabaseUser;
    private Button btn_book;
    private FirebaseAuth mAuth;
    String HotelID, RoomID, StartDay, EndDay, Address, User_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ZaloPaySDK.init(AppInfo.APP_ID, Environment.SANDBOX);

        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        tv_birthday = findViewById(R.id.tv_birthday);
        tv_name = findViewById(R.id.tv_fullname);
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
        btn_book = findViewById(R.id.btn_book);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mDatabaseRooms = FirebaseDatabase.getInstance().getReference().child("Rooms");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("User");
        mAuth = FirebaseAuth.getInstance();
        User_id = mAuth.getCurrentUser().getUid();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            Address = bundle.getString("address", "");
            HotelID = bundle.getString("hotel_id", "");
            RoomID = bundle.getString("room_id", "");
            StartDay = bundle.getString("start_day", "");
            EndDay = bundle.getString("end_day", "");
        }

        tv_address.setText(Address);
        tv_start.setText(StartDay);
        tv_end.setText(EndDay);

        String fullname = mAuth.getCurrentUser().getDisplayName();
        tv_name.setText("Họ tên: " + fullname);
        String email = mAuth.getCurrentUser().getEmail();
        tv_email.setText("Email: "+ email);
        mDatabaseUser.child(User_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user!=null) {
                    tv_phone.setText("Điện thoại: " + user.getPhone());
                    tv_birthday.setText("Ngày sinh: " + user.getBirthday());
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
                        tv_guest.setText(room.getGuest() + " khách");
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
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(tv_price2.getText().toString());
                    String code = data.getString("returncode");

                    if (code.equals("1")) {

                        String token = data.getString("zptranstoken");

                        ZaloPaySDK.getInstance().payOrder(Activity_Confirm.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                                Toast.makeText(Activity_Confirm.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPaymentCanceled(String zpTransToken, String appTransID) {
                                Toast.makeText(Activity_Confirm.this, "Thanh toán bị hủy", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                                Toast.makeText(Activity_Confirm.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}