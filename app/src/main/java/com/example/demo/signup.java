package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class signup extends AppCompatActivity {
    EditText name, phone, mail, hostel, password, roll;
    Button signup;
    FirebaseAuth auth;
    ProgressBar progressBar;
    TextView redirecttologin;


    DatabaseReference push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.nme);
        phone = findViewById(R.id.ph);
        mail = findViewById(R.id.em);
        hostel = findViewById(R.id.ht);
        password = findViewById(R.id.pwd);
        roll = findViewById(R.id.rollnumber);
        signup = findViewById(R.id.sbtn);
        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.pb);
        redirecttologin = findViewById(R.id.slogin);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String nm = String.valueOf(name.getText());
                String ph = String.valueOf(phone.getText());
                String em = String.valueOf(mail.getText());
                String ht = String.valueOf(hostel.getText());
                String pd = String.valueOf(password.getText());
                String rol = String.valueOf(roll.getText());


                if (TextUtils.isEmpty(nm)) {
                    Toast.makeText(signup.this, "Name is needed", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(rol)) {
                    Toast.makeText(signup.this, "roll no is needed", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ph)) {
                    Toast.makeText(signup.this, "number is needed", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(em)) {
                    Toast.makeText(signup.this, "mail is needed", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ht)) {
                    Toast.makeText(signup.this, "hostel name is needed", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pd)) {
                    Toast.makeText(signup.this, "password is compulsory", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(em, pd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    auth.signInWithEmailAndPassword(em, pd).addOnCompleteListener(t -> {
                                        push = FirebaseDatabase.getInstance().getReference("1STUDENT_DATA").child(Objects.requireNonNull(auth.getCurrentUser()).getUid());

                                        insertdata();
                                        Toast.makeText(signup.this, "account is created", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(signup.this, login.class));

                                    });
                                } else {
                                    Toast.makeText(signup.this, "failed to create account", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }

        });

        redirecttologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, login.class));
            }
        });
    }

    private void insertdata() {
        String nm = name.getText().toString();
        String em = mail.getText().toString();
        String rol = roll.getText().toString();
        String ht = hostel.getText().toString();
        String ph = phone.getText().toString();

        student st = new student(nm, em, rol, ph, ht);
        push.setValue(st);
    }
}