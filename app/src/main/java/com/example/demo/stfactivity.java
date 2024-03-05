package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class stfactivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
electriclog  electriclog  = new electriclog();
carpenteringlog carpenteringlog = new carpenteringlog();
plumbinglog plumbinglog  = new plumbinglog();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stfactivity);

        bottomNavigationView = findViewById(R.id.bnavi);
        getSupportFragmentManager().beginTransaction().replace(R.id.lfrm,electriclog).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.elog){
                    getSupportFragmentManager().beginTransaction().replace(R.id.lfrm,electriclog).commit();
                    return true;
                }
                if(item.getItemId()== R.id.clog){
                    getSupportFragmentManager().beginTransaction().replace(R.id.lfrm,carpenteringlog).commit();
                    return true;
                }
                if(item.getItemId()== R.id.plog){
                    getSupportFragmentManager().beginTransaction().replace(R.id.lfrm,plumbinglog).commit();
                    return true;
                }

                return false;
            }
        });
    }
}