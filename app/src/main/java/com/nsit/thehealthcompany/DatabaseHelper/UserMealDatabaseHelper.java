package com.nsit.thehealthcompany.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nsit.thehealthcompany.Utils.UserMealTable;
import com.nsit.thehealthcompany.Utils.UserMealTable;
import com.nsit.thehealthcompany.Utils.UserMealTable;

import java.util.ArrayList;
import java.util.List;

public class UserMealDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usermeal_db";

    public UserMealDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserMealTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ UserMealTable.TABLE_NAME);
        onCreate(db);
    }

    public void insertUserMeal(int id, String date_recorded, String breakfast, String lunch, String dinner, String snacks, int total_calories, int current_weight){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserMealTable.COLUMN_ID, id);
        values.put(UserMealTable.COLUMN_DATE_RECORDED, date_recorded);
        values.put(UserMealTable.COLUMN_BREAKFAST, breakfast);
        values.put(UserMealTable.COLUMN_LUNCH, lunch);
        values.put(UserMealTable.COLUMN_DINNER,dinner);
        values.put(UserMealTable.COLUMN_SNACKS, snacks);
        values.put(UserMealTable.COLUMN_TOTAL_CALORIES, total_calories);
        values.put(UserMealTable.COLUMN_CURRENT_WEIGHT, current_weight);

        db.insert(UserMealTable.TABLE_NAME, null, values);
    }

    public List<UserMealTable> getUserDetails(){
        List<UserMealTable> userMealDetails=new ArrayList<>();

        String selectQuery= "SELECT * FROM " + UserMealTable.TABLE_NAME + " ORDER BY "
                + UserMealTable.COLUMN_ID + " DESC ";

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                UserMealTable userMealTable=new UserMealTable();
                userMealTable.setId(cursor.getInt(cursor.getColumnIndex(UserMealTable.COLUMN_ID)));
                userMealTable.setDate_recorded(cursor.getString(cursor.getColumnIndex(UserMealTable.COLUMN_DATE_RECORDED)));
                userMealTable.setBreakfast(cursor.getString(cursor.getColumnIndex(UserMealTable.COLUMN_BREAKFAST)));
                userMealTable.setLunch(cursor.getString(cursor.getColumnIndex(UserMealTable.COLUMN_LUNCH)));
                userMealTable.setDinner(cursor.getString(cursor.getColumnIndex(UserMealTable.COLUMN_DINNER)));
                userMealTable.setSnacks(cursor.getString(cursor.getColumnIndex(UserMealTable.COLUMN_SNACKS)));
                userMealTable.setTotal_calories(cursor.getInt(cursor.getColumnIndex(UserMealTable.COLUMN_TOTAL_CALORIES)));
                userMealTable.setCurrent_weight(cursor.getInt(cursor.getColumnIndex(UserMealTable.COLUMN_CURRENT_WEIGHT)));

                userMealDetails.add(userMealTable);

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return userMealDetails;
    }
}
