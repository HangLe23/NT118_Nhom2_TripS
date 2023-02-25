package com.example.nt118_nhom2_trips.Tour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nt118_nhom2_trips.R;
import com.example.nt118_nhom2_trips.user.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Activity_Tour_Confirm extends AppCompatActivity {
    private TextView tv_email, tv_phone, tv_name, tv_birthday, tv_name_tour, tv_price;
    private DatabaseReference mDatabaseTours, mDatabaseUser;
    private Button btn_book;
    private FirebaseAuth mAuth;
    String TourId, User_id, name_tour, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_confirm);

        tv_name = findViewById(R.id.tv_fullname);
        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        tv_birthday = findViewById(R.id.tv_birthday);
        tv_name_tour = findViewById(R.id.tv_name_tour);
        tv_price = findViewById(R.id.tv_price2);
        btn_book = findViewById(R.id.btn_book);

        mDatabaseTours = FirebaseDatabase.getInstance().getReference().child("Tours");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("User");
        mAuth = FirebaseAuth.getInstance();
        User_id = mAuth.getCurrentUser().getUid();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            TourId = bundle.getString("tour_id", "");
        }

        String fullname = mAuth.getCurrentUser().getDisplayName();
        //tv_name.setText("Họ tên: " + fullname);
        String email = mAuth.getCurrentUser().getEmail();
        tv_email.setText("Email: "+ email);
        mDatabaseUser.child(User_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user!=null) {
                    tv_name.setText("Họ tên: " +user.getFullname());
                    tv_phone.setText("Điện thoại: " + user.getPhone());
                    tv_birthday.setText("Ngày sinh: " + user.getBirthday());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query queryTour = mDatabaseTours.orderByChild("id_tour").equalTo(TourId);
        queryTour.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Tour tour = ds.getValue(Tour.class);
                    if(tour != null) {
                        mail = tour.getEmail();
                        name_tour = tour.getName() + " - " + tour.getDay();
                        tv_name_tour.setText(tour.getName() + "\n" + tour.getDay());
                        tv_price.setText(tour.getPrice());
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
            public void onClick(View view) {
                String subject = "Đặt vé cho " + name_tour;
                String mes = "Xin cho tôi thông tin chi tiết về tour này! Xin cảm ơn!";
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
                intent1.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent1.putExtra(Intent.EXTRA_TEXT, mes);
                intent1.setType("message/rfc822");
                startActivity(intent1);
                startActivity(Intent.createChooser(intent1, "Choose an email client"));
            }
        });
    }
}