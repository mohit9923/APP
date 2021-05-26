package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.contactdatabase.data.MyDbHandler;
import com.example.contactdatabase.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDbHandler myDbHandler=new MyDbHandler(MainActivity.this);

        Contact mohit=new Contact();
        mohit.setId(10);
        mohit.setName("Mohit");
        mohit.setPhoneNumber("8872871671");

        myDbHandler.addNewContact(mohit);

        List<Contact> allContacts = myDbHandler.userList();
        for(Contact contact:allContacts)
        {
            Log.d("test1","Id: "+contact.getId()+"\n Name: "+contact.getName()+"\n Phone Number: "+contact.getPhoneNumber());
        }
    }
}