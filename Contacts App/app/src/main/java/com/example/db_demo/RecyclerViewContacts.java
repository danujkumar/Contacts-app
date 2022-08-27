package com.example.db_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.db_demo.adapter.RecyclerViewAdapter;
import com.example.db_demo.data.MyDbHandler;
import com.example.db_demo.models.Contacts;

import java.util.ArrayList;

public class RecyclerViewContacts extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        recyclerView = findViewById(R.id.recyclerviews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyDbHandler myDbHandler = new MyDbHandler(this);


        //Using our recycler view here
        recyclerViewAdapter = new RecyclerViewAdapter(RecyclerViewContacts.this,myDbHandler.getAllContacts());
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}