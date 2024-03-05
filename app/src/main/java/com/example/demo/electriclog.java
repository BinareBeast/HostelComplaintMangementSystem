package com.example.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class electriclog extends Fragment {
    RecyclerView recyclerView;
    SearchView searchView;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("2Electrical_complaints");
    Adapter adapter;
    ArrayList<complaints> arrayList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_electriclog, container, false);
        recyclerView = view.findViewById(R.id.recy);
        searchView = view.findViewById(R.id.sv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtertext(newText);
                return true;
            }
        });
        arrayList = new ArrayList<>();
        adapter = new Adapter(arrayList, getActivity());
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    complaints getcomplaints = dataSnapshot.getValue(com.example.demo.complaints.class);
                    arrayList.add(getcomplaints);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;

    }

    private void filtertext(String newText) {
        ArrayList<complaints> filteredlist = new ArrayList<>();
        for (complaints cts : arrayList) {
            if (cts.getHostel().toLowerCase().contains(newText.toLowerCase())) {
                filteredlist.add(cts);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(getActivity(), "No such data found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setfilterlist(filteredlist);
        }

    }
}
