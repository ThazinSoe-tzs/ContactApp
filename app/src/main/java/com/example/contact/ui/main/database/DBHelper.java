package com.example.contact.ui.main.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contact.Contact;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact.db";
    public static final String TABLE_NAME = "contacts";

    //column names
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String PHONE = "phone";
    public static final String IS_RECENT = "is_recent";
    public static final String IS_FAV = "is_fav";
    public static final String LAST_NAME = "second_name";
    public static final String PHONE1 = "phone1";
    public static final String COMPANY = "company_name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "( "
                + ID + " INTEGER PRIMARY KEY, "
                + FIRST_NAME + " TEXT, "
                + LAST_NAME + " TEXT, "
                + PHONE + " TEXT, "
                + PHONE1 + " TEXT, "
                + COMPANY + " TEXT, "
                + IS_RECENT + " INTEGER, "
                + IS_FAV + " INTEGER);";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setFirstName(cursor.getString(1));
                contact.setLastName(cursor.getString(2));
                contact.setPhone(cursor.getString(3));
                contact.setPhone1(cursor.getString(4));
                contact.setCompanyName(cursor.getString(5));
                contact.setRecent(cursor.getInt(6));
                contact.setFavourite(cursor.getInt(7));
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return contacts;
    }

    public ArrayList<Contact> getRecentList() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + IS_RECENT + " = 1";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setFirstName(cursor.getString(1));
                contact.setLastName(cursor.getString(2));
                contact.setPhone(cursor.getString(3));
                contact.setPhone1(cursor.getString(4));
                contact.setCompanyName(cursor.getString(5));
                contact.setRecent(cursor.getInt(6));
                contact.setFavourite(cursor.getInt(7));
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return contacts;
    }

    public ArrayList<Contact> getFavList() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + IS_FAV +  "= 1";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setFirstName(cursor.getString(1));
                contact.setLastName(cursor.getString(2));
                contact.setPhone(cursor.getString(3));
                contact.setPhone1(cursor.getString(4));
                contact.setCompanyName(cursor.getString(5));
                contact.setRecent(cursor.getInt(6));
                contact.setFavourite(cursor.getInt(7));
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return contacts;
    }


    public void insertData(Contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, contact.getFirstName());
        values.put(LAST_NAME, contact.getLastName());
        values.put(PHONE, contact.getPhone());
        values.put(PHONE1, contact.getPhone1());
        values.put(COMPANY, contact.getCompanyName());
        values.put(IS_RECENT, contact.isRecent());
        values.put(IS_FAV, contact.isFavourite());
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        Log.d("DB helper", "onCreate: insert success");
        sqLiteDatabase.close();
    }

    public boolean updateContact(Contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, contact.getFirstName());
        contentValues.put(PHONE, contact.getPhone());
        contentValues.put(IS_FAV, contact.isFavourite());
        contentValues.put(IS_RECENT, contact.isRecent());
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + "=" + contact.getId(), null);
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //do nothing
    }
}
