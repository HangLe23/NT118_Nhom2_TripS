package com.example.nt118_nhom2_trips.ui.changepassword;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nt118_nhom2_trips.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangPasswordFragment extends Fragment {
    private View view;
    private EditText newPass, oldPass, confirmPass;
    private Button btnChangePass;
    private ImageButton showOldPass, showNewPass, showConfirmPass;
    private ProgressDialog progressDialog;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_changepass, container, false);
        findViewByIds();
        initListener();
        return view;
    }

    private void initListener() {
        showOldPass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
        showOldPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(oldPass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    oldPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showOldPass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                } else{
                    oldPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showOldPass.setImageResource(R.drawable.ic_hidepass);
                }
            }
        });
        showNewPass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
        showNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newPass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showNewPass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                } else{
                    newPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showNewPass.setImageResource(R.drawable.ic_hidepass);
                }
            }
        });
        showConfirmPass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
        showConfirmPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmPass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showConfirmPass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                } else{
                    confirmPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showConfirmPass.setImageResource(R.drawable.ic_hidepass);
                }
            }
        });
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickChangePass();
            }
        });
    }

    private void onClickChangePass() {
        String NewPass = newPass.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        progressDialog.show();
        if(TextUtils.isEmpty(NewPass) || TextUtils.isEmpty(oldPass.getText().toString()) || TextUtils.isEmpty(confirmPass.getText().toString())){
            Toast.makeText(getActivity(), "Vui lòng không để trống mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        } else if(!NewPass.equals(confirmPass.getText().toString())){
            Toast.makeText(getActivity(), "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            user.updatePassword(NewPass)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "User password updated.", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            } else {
                                //
                            }
                        }
                    });
        }
    }

    private void findViewByIds(){
        progressDialog = new ProgressDialog(getActivity());
        newPass = view.findViewById(R.id.et_newpassword);
        oldPass = view.findViewById(R.id.et_oldpassword);
        confirmPass = view.findViewById(R.id.et_confirmpass);
        btnChangePass = view.findViewById(R.id.btn_changepass);
        showNewPass = view.findViewById(R.id.btn_show_newpass);
        showOldPass = view.findViewById(R.id.btn_show_oldpass);
        showConfirmPass = view.findViewById(R.id.btn_show_confirm);
    }
    private void reAuthenticate(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234");

        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            onClickChangePass();
                        } else {

                        }
                    }
                });


    }
}
