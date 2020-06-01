package com.example.nsmproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Accessories> {

    Context context;
    List<Accessories> arrayListAccessories;
    String a = "";

    public MyAdapter(@NonNull Context context, List<Accessories> arrayListAccessories) {
        super(context, R.layout.custom_list_item, arrayListAccessories);
        this.context = context;
        this.arrayListAccessories = arrayListAccessories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);
        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);
        TextView tvZone = view.findViewById(R.id.txt_zone);
        TextView tvStatus = view.findViewById(R.id.txt_status);

        tvID.setText("เลขครุภัณฑ์ : "+arrayListAccessories.get(position).getAc_id());
        tvName.setText("ชื่อครุภัณฑ์ : "+arrayListAccessories.get(position).getAc_name());
        tvZone.setText("โซนครุภัณฑ์ : "+arrayListAccessories.get(position).getAc_zone());
        if (arrayListAccessories.get(position).getAc_status().equals("1")){
            a = "Active ";
        }else if (arrayListAccessories.get(position).getAc_status().equals("0")){
            a = "Inactive ";
        }

        tvStatus.setText("สถานะครุภัณฑ์ : "+a);

        return view;
    }
}
