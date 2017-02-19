package com.acadgildbatch5.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyanshu on 18-12-2016.
 */

public class DatabaseHandle extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "acadgildContact"; // Database name

    private static final String TABLE_CONTACTS = "contacts";

    private static  final  String KEY_ID = "id";
    private static  final  String KEY_NAME = "name";
    private static  final  String KEY_PH_NO = "phone_number";

    public DatabaseHandle(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // CREATE TABLE TABLENAME

        String CREATE_TABLE = "CREATE TABLE "+TABLE_CONTACTS+ " ("+ KEY_ID +" INTEGER PRIMARY KEY,"
                +KEY_NAME +" TEXT,"+ KEY_PH_NO + " TEXT"+")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContacts(Contacts contacts){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName());
        values.put(KEY_PH_NO, contacts.getPhone_number());

        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }

    Contacts getContacts(int id){
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,KEY_NAME,KEY_PH_NO}, KEY_ID +" =?", new String[]{String.valueOf(id)},null,null,null,null );

        if(cursor != null)
            cursor.moveToFirst();

        Contacts contacts = new Contacts(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

        return contacts;
    }


    public int updateContacts(Contacts contacts){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName());
        values.put(KEY_PH_NO, contacts.phone_number);

        return  db.update(TABLE_CONTACTS, values,KEY_ID + " =?",  new String[]{String.valueOf(contacts.getId())});

    }


    public void deleteContacts(Contacts contacts){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " =?", new String[]{String.valueOf(contacts.getId())});
        db.close();
    }

    public  int getContactsCount(){
        String queryConatcts = "SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryConatcts,null);
        cursor.close();
        return cursor.getCount();
    }

    public List<Contacts> getAllContacts(){
        List<Contacts> contactList = new ArrayList<Contacts>();
        String selectQuery = "SELECT * FROM "+TABLE_CONTACTS;

        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{

                Contacts contacts = new Contacts();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setPhone_number(cursor.getString(2));
                contactList.add(contacts);
            }while (cursor.moveToNext());
        }
        return contactList;
    }
}
