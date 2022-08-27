package com.example.db_demo.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.db_demo.models.Contacts;
import com.example.db_demo.params.Params;

import java.util.ArrayList;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context)
    {
        super(context, Params.DB_NAME,null,Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + " ( " + Params.KEY_ID +
                " INTEGER PRIMARY KEY, " + Params.KEY_NAME + " TEXT , " + Params.KEY_PHONE +
                " TEXT " + " )" ;
        Log.d("Anujdb","The following command executed successfully " + create);
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContacts(Contacts contacts)
    {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_NAME,contacts.getName());
        contentValues.put(Params.KEY_PHONE, contacts.getPhone());
        sq.insert(Params.TABLE_NAME,null,contentValues);
    }

    public ArrayList<Contacts> getAllContacts()
    {
        SQLiteDatabase sq = getReadableDatabase();
        ArrayList<Contacts> contacts_list = new ArrayList<>();
        String query = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = sq.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            Contacts contacts = new Contacts(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            contacts_list.add(contacts);
        }
        return contacts_list;
    }

    public void updateContacts(Contacts contacts)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_NAME,contacts.getName());
        contentValues.put(Params.KEY_PHONE,contacts.getPhone());
        db.update(Params.TABLE_NAME,contentValues,Params.KEY_ID + "=?",new String[]{String.valueOf(contacts.getId())});
    }
    public void deleteContacts(Contacts contacts)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID + "=?",new String[]{String.valueOf(contacts.getId())});
    }
    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }
}
