package com.example.nt118_nhom2_trips;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nt118_nhom2_trips.ui.changepassword.ChangPasswordFragment;
import com.example.nt118_nhom2_trips.ui.home.HomeFragment;
import com.example.nt118_nhom2_trips.ui.profile.ProfileFragment;
import com.example.nt118_nhom2_trips.ui.profile.UpdateProfile;
import com.example.nt118_nhom2_trips.user.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ImageView avatar;
    private TextView tvname, tvmail;
    private NavigationView navigationView;
    private static final int Fragment_home = 0;
    private static final int Fragment_profile = 1;
    private static final int Fragment_changepass = 2;
    private int numFragment = Fragment_home;
    private DatabaseReference databaseReference;
    private String Email;
    //public static final int MY_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        showUserInformation();
    }

    private void findViewByIds(){
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        avatar = navigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvname = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvmail = navigationView.getHeaderView(0).findViewById(R.id.tv_email);
    }

    public void showUserInformation(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user != null){
                    //Fullname = firebaseUser.getDisplayName();
                    tvname.setText(user.getFullname());
                    Email = firebaseUser.getEmail();
                    tvmail.setText(Email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*if(user == null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        //Uri photoUrl = user.getPhotoUrl();

        if(name == null){
            tvname.setVisibility(View.GONE);
        } else {
            tvname.setVisibility(View.VISIBLE);
            tvname.setText(name);
        }
        tvmail.setText(email);*/
        //Glide.with(this).load(photoUrl).error(R.drawable.ic_avatar).into(avatar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(numFragment != Fragment_home){
                replaceFragment(new HomeFragment());
                numFragment = Fragment_home;
            }
        } else if(id == R.id.nav_Profile){
            if(numFragment != Fragment_profile){
                replaceFragment(new ProfileFragment());
                numFragment = Fragment_profile;
            }
        }
        else if(id == R.id.nav_ChangePassword) {
            if (numFragment != Fragment_changepass) {
                replaceFragment(new ChangPasswordFragment());
                numFragment = Fragment_changepass;
            }
        } else if(id == R.id.nav_SignOut){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, NewActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
}