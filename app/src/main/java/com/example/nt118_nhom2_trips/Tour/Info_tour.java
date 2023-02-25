package com.example.nt118_nhom2_trips.Tour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nt118_nhom2_trips.Hotel.Hotel;
import com.example.nt118_nhom2_trips.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Info_tour extends AppCompatActivity {
    private DatabaseReference mDatabaseTour;
    private ImageView iv_tour;
    private TextView tv_name, tv_email, tv_phone, tv_title, tv_detail, tv_price;
    private Button btn_book;
    private String TourId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tour);

        iv_tour = findViewById(R.id.img_tour);
        tv_name = findViewById(R.id.tv_nameTour);
        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        tv_title = findViewById(R.id.tv_title);
        tv_detail = findViewById(R.id.tv_detail);
        tv_price = findViewById(R.id.tv_price2);
        btn_book = findViewById(R.id.btn_book);

        mDatabaseTour = FirebaseDatabase.getInstance().getReference().child("Tours");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            TourId = bundle.getString("tour_id", "");
        }


        Query query = mDatabaseTour.orderByChild("id_tour").equalTo(TourId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Tour tour = ds.getValue(Tour.class);
                    if(tour != null) {
                        String url = tour.getImage_tour();
                        Picasso.get().load(url).into(iv_tour);
                        tv_name.setText(tour.getName() + " - \n" + tour.getDay());
                        tv_email.setText(tour.getEmail());
                        tv_phone.setText(tour.getPhone());
                        tv_title.setText(tour.getTitle());
                        tv_price.setText(tour.getPrice());
                        String[] details = tour.getDetail().toString().split("//");
                        String detail = "";
                        for(int i = 0; i<details.length; i++) {
                        detail += details[i] + "\n\n";}
                        tv_detail.setText(detail);
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
                Intent intent = new Intent(view.getContext(), Activity_Tour_Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("tour_id", TourId);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}