package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<complaints> arrayList;
    Context context;
public void setfilterlist(ArrayList<complaints> filteredlist){
    this.arrayList = filteredlist;
    notifyDataSetChanged();
}
    public Adapter(ArrayList<complaints> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public Adapter(ArrayList<complaints> arrayList, electriclog electriclog) {

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stfcdt,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        complaints getcom = arrayList.get(position);
        holder.name.setText(getcom.getName());
        holder.hos.setText(getcom.getHostel());
        holder.rl.setText(getcom.getRollNo());
        holder.dis.setText(getcom.getDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView hos,name,rl,dis;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hos = itemView.findViewById(R.id.htl);
            name = itemView.findViewById(R.id.nm);
            rl = itemView.findViewById(R.id.rn);
            dis = itemView.findViewById(R.id.ds);

        }
    }

}
