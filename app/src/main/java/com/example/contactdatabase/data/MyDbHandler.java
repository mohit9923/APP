package com.example.contactdatabase.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.contactdatabase.model.Contact;
import com.example.contactdatabase.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE "+ Params.TABLE_NAME+" ( "+Params.KEY_ID+" INTEGER, "+ Params.KEY_NAME+" TEXT, "+
                        Params.KEY_PHONE +" TEXT"+")";
        Log.d("test2","Query being executed is "+create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNewContact(Contact contact){
        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Params.KEY_ID,contact.getId());
        values.put(Params.KEY_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhoneNumber());

        database.insert(Params.TABLE_NAME,null,values);
        Log.d("test","Contact Added Successfully");
        database.close();
    }

    public List<Contact> userList(){
        List<Contact> allUsers= new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();

        String select="SELECT * FROM "+Params.TABLE_NAME;
        Cursor cursor=db.rawQuery(select,null);

        if(cursor.moveToFirst())
        {
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                allUsers.add(contact);
            }while(cursor.moveToNext());
        }
        return allUsers;
    }

}
