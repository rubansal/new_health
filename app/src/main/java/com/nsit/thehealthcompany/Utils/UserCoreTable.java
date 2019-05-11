package com.nsit.thehealthcompany.Utils;

public class UserCoreTable {

    public static final String TABLE_NAME = "usercore";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_GOAL = "goal";

    private int id;
    private String gender;
    private int height;
    private int weight;
    private int age;
    private String goal;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_GENDER + " TEXT,"
                    + COLUMN_HEIGHT + " INTEGER,"
                    + COLUMN_WEIGHT + " INTEGER,"
                    + COLUMN_AGE + " INTEGER,"
                    + COLUMN_GOAL + " TEXT"
                    + ")";

    public UserCoreTable() {
    }

    public UserCoreTable(int id, String gender, int height, int weight, int age, String goal) {
        this.id = id;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.goal = goal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "UserCoreTable{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", goal=" + goal +
                '}';
    }

}
