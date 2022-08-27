package com.example.db_demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowContacts extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_contacts);
        Intent intent = getIntent();
        ArrayList <String> arrayList = intent.getStringArrayListExtra(getPackageName() + ".contacts");
        listView = findViewById(R.id.contact_list);
        if (arrayList.isEmpty())
        {
            arrayList.add("Nothing to show here, please go back and say hi....");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ShowContacts.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            ArrayList <String> raw_contacts = intent.getStringArrayListExtra(getPackageName()+".raw_contacts");
            Intent intent1 = new Intent(ShowContacts.this,UpdateContact.class);
            intent1.putExtra(getPackageName() + ".update", raw_contacts.get(i));
            startActivity(intent1);
        });
    }
}
