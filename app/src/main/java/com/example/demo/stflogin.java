package com.example.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class stflogin extends Fragment {

    EditText mail, password;
    Button login;
    FirebaseAuth auth;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view = inflater.inflate(R.layout.fragment_stflogin, container, false);

        mail = view.findViewById(R.id.em);
        password =view.findViewById(R.id.pd);
        login =view.findViewById(R.id.lbtn);
        auth = FirebaseAuth.getInstance();
        progressBar = view.findViewById(R.id.pb);
        String em = String.valueOf(mail.getText());
        TextView redirecttosignup=view.findViewById(R.id.rbtn);
        TextView forgetpassword = view.findViewById(R.id.fp);

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = String.valueOf(mail.getText());
                String fp = String.valueOf(forgetpassword.getText());
                if(TextUtils.isEmpty(em)){
                    mail.setError("Required");
                }else{
                 auth.sendPasswordResetEmail(em)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getActivity(), "Mail is sent to your email id", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getActivity(),"enter mail id"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }});

        redirecttosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), staffsignup.class));
                getActivity().finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String em = String.valueOf(mail.getText());
                String pd = String.valueOf(password.getText());

                if (TextUtils.isEmpty(em)) {
                    mail.setError("enter mail id");
                    Toast.makeText(getActivity(), "mail is needed", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pd)) {
                    Toast.makeText(getActivity(), "password is compulsory", Toast.LENGTH_LONG).show();
                }
                auth.signInWithEmailAndPassword(em, pd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Authentication Sucessful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getActivity(), stfactivity.class));
                           getActivity(). finish();
                        } else {
                            Toast.makeText(getActivity(), "failed to login to account", Toast.LENGTH_LONG).show();
                            if (task.getException() != null)
                                Log.e("Login error", task.getException().getMessage());
                        }
                    }
                });

            }
        });
    return view;
    }
}