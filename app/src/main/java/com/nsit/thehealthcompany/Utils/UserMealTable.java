package com.nsit.thehealthcompany.Utils;

import java.util.Date;

public class UserMealTable {

    public static final String TABLE_NAME = "usermeal";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE_RECORDED = "date_recorded";
    public static final String COLUMN_BREAKFAST = "breakfast";
    public static final String COLUMN_LUNCH = "lunch";
    public static final String COLUMN_DINNER = "dinner";
    public static final String COLUMN_SNACKS = "snacks";
    public static final String COLUMN_TOTAL_CALORIES = "total_calories";
    public static final String COLUMN_CURRENT_WEIGHT = "current_weight";

    private int id;
    private String date_recorded;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String snacks;
    private int total_calories;
    private int current_weight;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DATE_RECORDED + " TEXT,"
                    + COLUMN_BREAKFAST + " TEXT,"
                    + COLUMN_LUNCH + " TEXT,"
                    + COLUMN_DINNER + " TEXT,"
                    + COLUMN_SNACKS + " TEXT,"
                    + COLUMN_TOTAL_CALORIES + " INTEGER,"
                    + COLUMN_CURRENT_WEIGHT + " INTEGER"
                    + ")";

    public UserMealTable() {
    }

    public UserMealTable(int id, String date_recorded, String breakfast, String lunch, String dinner, String snacks, int total_calories, int current_weight) {
        this.id = id;
        this.date_recorded = date_recorded;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snacks = snacks;
        this.total_calories = total_calories;
        this.current_weight = current_weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_recorded() {
        return date_recorded;
    }

    public void setDate_recorded(String date_recorded) {
        this.date_recorded = date_recorded;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getSnacks() {
        return snacks;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public int getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(int total_calories) {
        this.total_calories = total_calories;
    }

    public int getCurrent_weight() {
        return current_weight;
    }

    public void setCurrent_weight(int current_weight) {
        this.current_weight = current_weight;
    }

    @Override
    public String toString() {
        return "UserMealTable{" +
                "id=" + id +
                ", date_recorded='" + date_recorded + '\'' +
                ", breakfast='" + breakfast + '\'' +
                ", lunch='" + lunch + '\'' +
                ", dinner='" + dinner + '\'' +
                ", snacks='" + snacks + '\'' +
                ", total_calories=" + total_calories +
                ", current_weight=" + current_weight +
                '}';
    }
}
