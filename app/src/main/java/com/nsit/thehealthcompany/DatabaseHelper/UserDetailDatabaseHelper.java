package com.nsit.thehealthcompany.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nsit.thehealthcompany.Utils.UserDetailTable;

import java.util.ArrayList;
import java.util.List;

public class UserDetailDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userdetails_db";

    public UserDetailDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDetailTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ UserDetailTable.TABLE_NAME);
        onCreate(db);
    }

    public void insertUserDetails(int id, String username, String first_name, String email, int phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserDetailTable.COLUMN_ID, id);
        values.put(UserDetailTable.COLUMN_USERNAME, username);
        values.put(UserDetailTable.COLUMN_FIRST_NAME, first_name);
        values.put(UserDetailTable.COLUMN_EMAIL,email);
        values.put(UserDetailTable.COLUMN_PHONE, phone);

        db.insert(UserDetailTable.TABLE_NAME, null, values);
    }

    public List<UserDetailTable> getUserDetails(){
        List<UserDetailTable> userDetails=new ArrayList<>();

        String selectQuery= "SELECT * FROM " + UserDetailTable.TABLE_NAME + " ORDER BY "
                + UserDetailTable.COLUMN_ID + " DESC ";

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                UserDetailTable userDetailTable=new UserDetailTable();
                userDetailTable.setId(cursor.getInt(cursor.getColumnIndex(UserDetailTable.COLUMN_ID)));
                userDetailTable.setUsername(cursor.getString(cursor.getColumnIndex(UserDetailTable.COLUMN_USERNAME)));
                userDetailTable.setFirst_name(cursor.getString(cursor.getColumnIndex(UserDetailTable.COLUMN_FIRST_NAME)));
                userDetailTable.setEmail(cursor.getString(cursor.getColumnIndex(UserDetailTable.COLUMN_EMAIL)));
                userDetailTable.setPhone(cursor.getLong(cursor.getColumnIndex(UserDetailTable.COLUMN_PHONE)));

                userDetails.add(userDetailTable);

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return userDetails;
    }
}
