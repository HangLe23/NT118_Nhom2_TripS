package com.example.nt118_nhom2_trips.ui.profile;

import static com.example.nt118_nhom2_trips.MainActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.nt118_nhom2_trips.MainActivity;
import com.example.nt118_nhom2_trips.R;
import com.example.nt118_nhom2_trips.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private View view;
    private CircleImageView imgavatar;
    private EditText fullname, email, birthday, gender, phone;
    private Button btnEdit;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String key;
    private Uri mUri;
    //MainActivity mainActivity;
    //@Nullable
    //@Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViewByIds();
        //mainActivity = (MainActivity) getActivity();
        /*firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);
                fullname.setText(user.getFullname());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
            }
        });*/

        //setUserInformation();
        //initListener();
        return view;
    }

    /*private void initListener() {
        imgavatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateProfile();
            }
        });
    }

    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String FullName = fullname.getText().toString().trim();
        String Birthday = birthday.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(FullName)
                .setPhotoUri(mUri)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                            mainActivity.showUserInformation();
                        }
                    }
                });
    }

    private void onClickRequestPermission() {
        if (mainActivity == null){
            return;
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mainActivity.openGallery();
            return;
        }
        if(getActivity().checkSelfPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            mainActivity.openGallery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }
        fullname.setText(user.getDisplayName());
        email.setText(user.getEmail());
        birthday.setText(user.getDisplayName());
        phone.setText(user.getPhoneNumber());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.ic_avatar).into(imgavatar);
    }*/

    private void findViewByIds(){
        imgavatar = view.findViewById(R.id.img_avatar);
        fullname = view.findViewById(R.id.et_fullname);
        email = view.findViewById(R.id.et_email);
        birthday = view.findViewById(R.id.et_birthday);
        gender = view.findViewById(R.id.et_gender);
        phone = view.findViewById(R.id.et_phone);
        btnEdit = view.findViewById(R.id.btn_edit);
    }
    /*public void setBitmapImageView(Bitmap bitmap){
        imgavatar.setImageBitmap(bitmap);
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }*/
}
