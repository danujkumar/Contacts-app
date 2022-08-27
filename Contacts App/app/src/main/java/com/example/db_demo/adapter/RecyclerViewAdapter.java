package com.example.db_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db_demo.R;
import com.example.db_demo.models.Contacts;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList <Contacts>contactList;

    public RecyclerViewAdapter(Context context, ArrayList <Contacts> c) {
        this.context = context;
        contactList = c;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
            Contacts contacts = contactList.get(position);
            holder.name.setText(contacts.getName());
            holder.phone.setText(contacts.getPhone());
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "mesage",Toast.LENGTH_LONG).show();

                }
            });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView phone;
        ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_heres);
            phone = itemView.findViewById(R.id.phone_heres);
            photo = itemView.findViewById(R.id.imageViewHere);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

        }
    }
}
