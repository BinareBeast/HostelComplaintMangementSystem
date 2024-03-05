package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;

home home =new home();

staff staff = new staff();
about about = new about();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navi);
        getSupportFragmentManager().beginTransaction().replace(R.id.frm,home).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                if(item.getItemId()==R.id.home) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frm,home).commit();
                        return true;
                }

                else if( item.getItemId()==   R.id.stf){
                        getSupportFragmentManager().beginTransaction().replace(R.id.frm,staff).commit();
                        return true;
                }
                else if( item.getItemId()== R.id.prf){
                     getSupportFragmentManager().beginTransaction().replace(R.id.frm,about).commit();

                        return true;
                }
                return false;
            }
        });
    }
}