package com.example.db_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db_demo.data.MyDbHandler;
import com.example.db_demo.models.Contacts;

public class UpdateContact extends AppCompatActivity {

    TextView name,phone;
    Button update,delete;
    EditText new_name,new_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);
        Intent intent = getIntent();
        String user_data = intent.getStringExtra(getPackageName() + ".update");
        String[] user_details = user_data.split(",");
        name = findViewById(R.id.current_name);
        phone = findViewById(R.id.current_phone);
        update = findViewById(R.id.updateContact);
        delete = findViewById(R.id.deleteContact);
        new_name = findViewById(R.id.newName);
        new_phone = findViewById(R.id.newPhone);
        String toDisplayName = "Current Name : " + user_details[1];
        name.setText(toDisplayName);
        String toDisplayPhone = "Current Phone no. : " + user_details[2];
        phone.setText(toDisplayPhone);

        update.setOnClickListener(view -> {
            if (new_name.getText().toString().trim().isEmpty())
            {
                Toast.makeText(UpdateContact.this,R.string.save_failed,Toast.LENGTH_LONG).show();
            }
            else
            {
                update_contact(Integer.parseInt(user_details[0]), new_name.getText().toString(),new_phone.getText().toString());
                String newDisplayName = "Current Name : " + new_name.getText().toString();
                String newDisplayPhone = "Current Phone no. : "  + new_phone.getText().toString();
                new_name.setText(null);
                new_phone.setText(null);
                new_phone.setHint(R.string.new_phone);
                new_name.setHint(R.string.new_name);
                name.setText(newDisplayName);
                phone.setText(newDisplayPhone);
                Toast.makeText(UpdateContact.this,R.string.update_message,Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(view -> {
            delete_contact(Integer.parseInt(user_details[0]));
            Toast.makeText(UpdateContact.this,R.string.delete_message,Toast.LENGTH_LONG).show();
        });

    }
    public void update_contact(int id, String name, String phone)
    {
        MyDbHandler myDbHandler = new MyDbHandler(UpdateContact.this);
        Contacts contacts = new Contacts(id,name,phone);
        myDbHandler.updateContacts(contacts);
    }
    public void delete_contact(int id)
    {
        MyDbHandler myDbHandler = new MyDbHandler(UpdateContact.this);
        Contacts contacts = new Contacts();
        contacts.setId(id);
        myDbHandler.deleteContacts(contacts);
    }
}