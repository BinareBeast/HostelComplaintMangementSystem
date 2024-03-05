package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class staffsignup extends AppCompatActivity {
    EditText name,phone,mail,hostel,password,desigination;
    Button signup;
    FirebaseAuth auth;
    ProgressBar progressBar;
    TextView redirecttologin;
    DatabaseReference push;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffsignup);
        name = findViewById(R.id.nme);
        phone = findViewById(R.id.ph);
        mail = findViewById(R.id.em);
        hostel = findViewById(R.id.ht);
        password = findViewById(R.id.pwd);
        desigination = findViewById(R.id.dsg);
        signup = findViewById(R.id.sbtn);
        auth  = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.pb);
        redirecttologin=findViewById(R.id.slogin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String nm = String.valueOf(name.getText());
                String ph = String.valueOf(phone.getText());
                String em = String.valueOf(mail.getText());
                String ht = String.valueOf(hostel.getText());
                String pd = String.valueOf(password.getText());
                String ds = String.valueOf(desigination.getText());
                push= FirebaseDatabase.getInstance().getReference("2Staff_data").child(nm);

                if(TextUtils.isEmpty(ds)){
                    desigination.setError("Required");}
                if(TextUtils.isEmpty(nm)){
                    name.setError("Required");}
                if(TextUtils.isEmpty(ph)){
                    phone.setError("Required");}
                if(TextUtils.isEmpty(em)){
                    mail.setError("Required");}
                if(TextUtils.isEmpty(ht)){
                   hostel.setError("Required");}
                if(TextUtils.isEmpty(pd)){
                    password.setError("Required");}

                if(TextUtils.isEmpty(nm)){
                    Toast.makeText(staffsignup.this,"Name is needed",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(ph)){
                    Toast.makeText(staffsignup.this,"number is needed",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(em)){
                    Toast.makeText(staffsignup.this,"mail is needed",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(ds)){
                    Toast.makeText(staffsignup.this,"desigination is needed",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(ht)){
                    Toast.makeText(staffsignup.this,"hostel name is needed",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pd)){
                    Toast.makeText(staffsignup.this,"password is compulsory",Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(em,pd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    insertdata();
                                    Toast.makeText(staffsignup.this,"account is created",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(staffsignup.this, login.class));

                                }
                                else{
                                    Toast.makeText(staffsignup.this,"failed to create account",Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }

        });
        redirecttologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(staffsignup.this, stflogin.class));
            }
        });
    }
    private void insertdata(){
        String nm = name.getText().toString();
        String em = mail.getText().toString();
        String ht = hostel.getText().toString();
        String ph = phone.getText().toString();
        String ds = desigination.getText().toString();

        staffdata stf = new staffdata(nm,ph,em,ht,ds);
        push.setValue(stf);
    }
}
