package com.example.nt118_nhom2_trips.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nt118_nhom2_trips.R;
import com.example.nt118_nhom2_trips.UpdateProfile;
import com.example.nt118_nhom2_trips.user.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private View view;
    private CircleImageView imgavatar;
    private TextView fullname, email, birthday, gender, phone;
    private Button btnEdit;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViewByIds();
        initListener();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);
                fullname.setText(user.getFullname());
                email.setText(user.getEmail());
                gender.setText(user.getGender());
                phone.setText(user.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
            }
        });
        return view;
    }

    private void findViewByIds(){
        imgavatar = view.findViewById(R.id.img_avatar);
        fullname = view.findViewById(R.id.et_fullname);
        email = view.findViewById(R.id.et_email);
        birthday = view.findViewById(R.id.et_birthday);
        gender = view.findViewById(R.id.et_gender);
        phone = view.findViewById(R.id.et_phone);
        btnEdit = view.findViewById(R.id.btn_edit);
    }

    private void initListener(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
            }
        });
    }
}
