package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
  BottomNavigationView bottomNavigationView;
  stlogin stlogin = new stlogin();
  stflogin stflogin = new stflogin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bottomNavigationView = findViewById(R.id.bn);
        getSupportFragmentManager().beginTransaction().replace(R.id.frl,stlogin).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.stl){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frl,stlogin).commit();
                    return true;
                }
                if(item.getItemId()== R.id.stfl){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frl,stflogin).commit();
                    return true;
                }

                return false;
            }
        });
    }
}