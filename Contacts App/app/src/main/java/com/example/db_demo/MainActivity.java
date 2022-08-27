package com.example.db_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db_demo.data.MyDbHandler;
import com.example.db_demo.models.Contacts;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button save_Button, show_Button, update_button, delete_button;
    EditText textViewPhone;
    EditText textViewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save_Button = findViewById(R.id.button);
        textViewContact = findViewById(R.id.contact_name);
        textViewPhone = findViewById(R.id.contact_phone);
        show_Button = findViewById(R.id.show_button);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        save_Button.setOnClickListener(view -> {
            MyDbHandler myDbHandler = new MyDbHandler(MainActivity.this);
            if (textViewPhone.getText().toString().equals("") || textViewContact.getText().toString().equals(""))
            {
                Toast.makeText(this,R.string.save_failed,Toast.LENGTH_LONG).show();
            }
            else
            {
                Contacts contacts = new Contacts(textViewContact.getText().toString(), textViewPhone.getText().toString());
                myDbHandler.addContacts(contacts);
                textViewContact.setText("");
                textViewContact.setHint(R.string.contacts_name_hint);
                textViewPhone.setText("");
                textViewPhone.setHint(R.string.contacts_phone_hint);
                Toast.makeText(this,R.string.save_successful,Toast.LENGTH_LONG).show();
            }

        });

        show_Button.setOnClickListener(view -> {
            MyDbHandler myDbHandler = new MyDbHandler(MainActivity.this);
            ArrayList<Contacts> contactss = myDbHandler.getAllContacts();
            ArrayList<String> contacts = new ArrayList<>();
            ArrayList<String> raw_contacts = new ArrayList<>();
            for (Contacts contact : contactss )
            {
                String contact_string = "Id : " + contact.getId() + ", Name : "
                        + contact.getName() + ", Phone no. is " +
                        contact.getPhone();
                raw_contacts.add(contact.getId()+","+contact.getName()+","+contact.getPhone());
                Toast.makeText(MainActivity.this,contact_string,Toast.LENGTH_LONG).show();
                contacts.add(contact_string);
            }

            Toast.makeText(MainActivity.this, "Total rows fetched using this operation " +
                    "is : " + myDbHandler.getCount(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,ShowContacts.class);
            intent.putExtra(getPackageName() + ".contacts",contacts);
            intent.putExtra(getPackageName()+".raw_contacts",raw_contacts);
            startActivity(intent);
        });

//        update_button.setOnClickListener(view -> {
//            MyDbHandler myDbHandler = new MyDbHandler(MainActivity.this);
//            Contacts contacts = new Contacts(1,"Kiran","7000761747");
//            myDbHandler.updateContacts(contacts);
//            Toast.makeText(MainActivity.this,R.string.update_message,Toast.LENGTH_LONG).show();
//        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewContacts.class);
                startActivity(intent);
            }
        });
        delete_button.setOnClickListener(view -> {
            MyDbHandler myDbHandler = new MyDbHandler(MainActivity.this);
            Contacts contacts = new Contacts("Anuj","9685798207");
            myDbHandler.deleteContacts(contacts);
            Toast.makeText(MainActivity.this,R.string.delete_message,Toast.LENGTH_LONG).show();
        });

    }
}