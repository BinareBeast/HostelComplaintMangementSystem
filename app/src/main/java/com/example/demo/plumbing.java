package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class plumbing extends AppCompatActivity {
    EditText room,roll,hostel,disp,name;
    Button submit;
    DatabaseReference push4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing);

        room = findViewById(R.id.rm);
        roll = findViewById(R.id.rn);
        hostel = findViewById(R.id.hst);
        disp = findViewById(R.id.dsp);
        name = findViewById(R.id.name);

        submit = findViewById(R.id.st);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rom = String.valueOf(room.getText());
                String nm = String.valueOf(name.getText());
                String rl = String.valueOf(roll.getText());
                String host = String.valueOf(hostel.getText());
                String disc = String.valueOf(disp.getText());
                push4 = FirebaseDatabase.getInstance().getReference("4plumbing_complaints").child(rl);

                if (TextUtils.isEmpty(rl)) {
                    roll.setError("Required");
                    Toast.makeText(plumbing.this, "Roll no is required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(nm)) {
                    name.setError("Required");
                    Toast.makeText(plumbing.this, "Name is required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(rom)) {
                    room.setError("Required");
                    Toast.makeText(plumbing.this, "Room no is required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(host)) {
                    hostel.setError("Required");
                    Toast.makeText(plumbing.this, "Hostel name is required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(disc)) {
                    disp.setError("Required");
                    Toast.makeText(plumbing.this, "discription is required", Toast.LENGTH_LONG).show();
                    return;
                }
                insertcomp();
                Toast.makeText(plumbing.this,"Complaint is Raised",Toast.LENGTH_LONG).show();
                retuntohome();
            }
        });

    }
    public void retuntohome(){
        startActivity(new Intent(plumbing.this,MainActivity.class));
    }
    private void insertcomp(){
        String roomno = room.getText().toString();
        String nm = name.getText().toString();
        String rollno = roll.getText().toString();
        String hos = hostel.getText().toString();
        String disc = disp.getText().toString();

        complaints ct = new complaints(roomno,rollno,hos,disc,nm);
        push4.setValue(ct);
    }

    }
