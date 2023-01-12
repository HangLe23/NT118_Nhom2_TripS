package com.example.nt118_nhom2_trips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.nt118_nhom2_trips.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import PlaceName.PlaceName;
import PlaceName.PlaceNameAdapater;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private RecyclerView rcvCategory;
    private PlaceNameAdapater placeNameAdapater;
    //private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        /*binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        rcvCategory = findViewById(R.id.rcv_category);

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        rcvCategory.setLayoutManager(linearLayoutManager);
        placeNameAdapater = new PlaceNameAdapater(list);

        rcvCategory.setAdapter(placeNameAdapater);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}