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
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.nt118_nhom2_trips.ui.changepassword.ChangPasswordFragment;
import com.example.nt118_nhom2_trips.ui.home.HomeFragment;
import com.example.nt118_nhom2_trips.ui.profile.ProfileFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    public static final int MY_REQUEST_CODE = 10;
    final private ProfileFragment profileFragment = new ProfileFragment();
    /*final private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        if (intent == null){
                            return;
                        }
                        Uri uri = intent.getData();
                        profileFragment.setmUri(uri);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            profileFragment.setBitmapImageView(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });*/


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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if(name == null){
            tvname.setVisibility(View.GONE);
        } else {
            tvname.setVisibility(View.VISIBLE);
            tvname.setText(name);
        }
        tvmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.ic_avatar).into(avatar);
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

    /*@Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
            else {
                //
            }
        }
    }*/

    /*public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select picture"));
    }*/
}