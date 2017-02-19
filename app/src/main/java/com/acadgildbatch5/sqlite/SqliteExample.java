package com.acadgildbatch5.sqlite;

import android.app.Activity;
import android.os.Bundle;

import com.acadgildbatch5.R;

import java.util.List;

/**
 * Created by Divyanshu on 18-12-2016.
 */

public class SqliteExample extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_example);

        DatabaseHandle db = new DatabaseHandle(SqliteExample.this);

        db.addContacts(new Contacts("Ritesh","+9738938923"));
        db.addContacts(new Contacts("Arvind","+782637632636"));
        db.addContacts(new Contacts("Sumeet","+33277332323232"));
        db.addContacts(new Contacts("Arjun","+328989322323"));
        db.addContacts(new Contacts("Prashanth","+917836873223932923"));


        db.addContacts(new Contacts("Ritesh  2","+9738938923"));
        db.addContacts(new Contacts("Arvind  2","+782637632636"));
        db.addContacts(new Contacts("Sumeet  2","+33277332323232"));
        db.addContacts(new Contacts("Arjun  2","+328989322323"));
        db.addContacts(new Contacts("Prashanth  2","+917836873223932923"));

        List<Contacts> conta = db.getAllContacts();
        for(Contacts mContacts  : conta){
            System.out.println("ID :-"+mContacts.getId()+" NAME:- "+mContacts.getName()+" Pho NO:- "+mContacts.getPhone_number());
        }
    }
}
