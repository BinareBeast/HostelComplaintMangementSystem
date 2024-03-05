package com.example.demo;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class electrical extends AppCompatActivity {
    EditText room, roll, hostel, disp, name;
    Button submit;
    DatabaseReference push2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical);
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
                String rl = String.valueOf(roll.getText());
                String host = String.valueOf(hostel.getText());
                String disc = String.valueOf(disp.getText());
                push2 = FirebaseDatabase.getInstance().getReference("2Electrical_complaints").child(rom);

                if (TextUtils.isEmpty(rl)) {
                    roll.setError("Required");
                    Toast.makeText(electrical.this, "Roll no is required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(rom)) {
                    room.setError("Required");
                    Toast.makeText(electrical.this, "Room no is required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(host)) {
                    hostel.setError("Required");
                    Toast.makeText(electrical.this, "Hostel name is required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(disc)) {
                    disp.setError("Required");
                    Toast.makeText(electrical.this, "discription is required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Required");
                    Toast.makeText(electrical.this, "name is required", Toast.LENGTH_LONG).show();
                    return;
                }
                insertcomp();
                Toast.makeText(electrical.this, "Complaint is Raised", Toast.LENGTH_LONG).show();
                retuntohome();
            }
        });

    }

    public void retuntohome() {
        startActivity(new Intent(electrical.this, MainActivity.class));
    }

    private void insertcomp() {
        String roomno = room.getText().toString();
        String rollno = roll.getText().toString();
        String hos = hostel.getText().toString();
        String disc = disp.getText().toString();
        String name = this.name.getText().toString();

        complaints ct = new complaints(roomno, rollno, hos, disc, name);
        push2.setValue(ct);

    }
}

