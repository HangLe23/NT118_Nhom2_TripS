package com.example.nt118_nhom2_trips.ui.changepassword;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private ProgressDialog progressDialog;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_changepass, container, false);
        findViewByIds();
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickChangePass();
            }
        });
        return view;
    }

    private void onClickChangePass() {
        String NewPass = newPass.getText().toString().trim();
        if(TextUtils.isEmpty(NewPass) || TextUtils.isEmpty(oldPass.getText().toString()) || TextUtils.isEmpty(confirmPass.getText().toString())){
            Toast.makeText(getActivity(), "Vui lòng không để trống mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        } else if(confirmPass.getText().toString() != NewPass){
            Toast.makeText(getActivity(), "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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

    private void findViewByIds(){
        progressDialog = new ProgressDialog(getActivity());
        newPass = view.findViewById(R.id.et_newpassword);
        oldPass = view.findViewById(R.id.et_oldpassword);
        confirmPass = view.findViewById(R.id.et_confirmpass);
        btnChangePass = view.findViewById(R.id.btn_changepass);
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
