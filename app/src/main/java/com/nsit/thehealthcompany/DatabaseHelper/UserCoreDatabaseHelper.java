package com.nsit.thehealthcompany.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nsit.thehealthcompany.Utils.UserCoreTable;
import com.nsit.thehealthcompany.Utils.UserCoreTable;
import com.nsit.thehealthcompany.Utils.UserCoreTable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class UserCoreDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usercore_db";

    public UserCoreDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserCoreTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ UserCoreTable.TABLE_NAME);
        onCreate(db);
    }

    public void insertUserData(int id, String gender, int height, int weight, int age, String goal){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserCoreTable.COLUMN_ID, id);
        values.put(UserCoreTable.COLUMN_GENDER, gender);
        values.put(UserCoreTable.COLUMN_HEIGHT, height);
        values.put(UserCoreTable.COLUMN_WEIGHT, weight);
        values.put(UserCoreTable.COLUMN_AGE,age);
        values.put(UserCoreTable.COLUMN_GOAL, goal);

        db.insert(UserCoreTable.TABLE_NAME, null, values);
    }

    public List<UserCoreTable> getUserData(){
        List<UserCoreTable> userData=new ArrayList<>();

        String selectQuery= "SELECT * FROM " + UserCoreTable.TABLE_NAME + " ORDER BY "
                + UserCoreTable.COLUMN_ID + " DESC ";

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                UserCoreTable userCoreTable=new UserCoreTable();
                userCoreTable.setId(cursor.getInt(cursor.getColumnIndex(UserCoreTable.COLUMN_ID)));
                userCoreTable.setGender(cursor.getString(cursor.getColumnIndex(UserCoreTable.COLUMN_GENDER)));
                userCoreTable.setHeight(cursor.getInt(cursor.getColumnIndex(UserCoreTable.COLUMN_HEIGHT)));
                userCoreTable.setWeight(cursor.getInt(cursor.getColumnIndex(UserCoreTable.COLUMN_WEIGHT)));
                userCoreTable.setAge(cursor.getInt(cursor.getColumnIndex(UserCoreTable.COLUMN_AGE)));
                userCoreTable.setGoal(cursor.getString(cursor.getColumnIndex(UserCoreTable.COLUMN_GOAL)));

                userData.add(userCoreTable);

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return userData;
    }
}
