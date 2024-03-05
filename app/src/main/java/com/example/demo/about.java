package com.example.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class about extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("1STUDENT_DATA");
    private FirebaseUser user;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        databaseReference = databaseReference.child(user.getUid());

        TextView name = view.findViewById(R.id.nm1);
        TextView mail = view.findViewById(R.id.em1);
        TextView rollno = view.findViewById(R.id.rn1);
        TextView phone = view.findViewById(R.id.ph1);
        TextView hostel = view.findViewById(R.id.hs1);
        Log.e("Path", databaseReference.getPath().toString());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1 = snapshot.child("name").getValue(String.class);
                String mail1 = snapshot.child("mail").getValue(String.class);
                String rollno1 = snapshot.child("rollno").getValue(String.class);
                String phone1 = snapshot.child("ph").getValue(String.class);
                String hostel1 = snapshot.child("hostel").getValue(String.class);

                name.setText(name1);
                mail.setText(mail1);
                rollno.setText(rollno1);
                phone.setText(phone1);
                hostel.setText(hostel1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}