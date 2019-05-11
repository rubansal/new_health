package com.nsit.thehealthcompany.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserCalorieDTO implements Parcelable{

    private String id;
    private String dateRecorded;
    private ArrayList breakfast;
    private ArrayList lunch;
    private ArrayList snacks;
    private ArrayList dinner;
    private int calories;
    private int weight;
    private long totalProtein;
    private long totalCarbs;
    private long totalFat;

    public UserCalorieDTO(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(String dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public ArrayList<FoodItemDTO> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(ArrayList<FoodItemDTO> breakfast) {
        this.breakfast = breakfast;
    }

    public ArrayList<FoodItemDTO> getLunch() {
        return lunch;
    }

    public void setLunch(ArrayList<FoodItemDTO> lunch) {
        this.lunch = lunch;
    }

    public ArrayList<FoodItemDTO> getSnacks() {
        return snacks;
    }

    public void setSnacks(ArrayList<FoodItemDTO> snacks) {
        this.snacks = snacks;
    }

    public ArrayList<FoodItemDTO> getDinner() {
        return dinner;
    }

    public void setDinner(ArrayList<FoodItemDTO> dinner) {
        this.dinner = dinner;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(long totalProtein) {
        this.totalProtein = totalProtein;
    }

    public long getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(long totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public long getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(long totalFat) {
        this.totalFat = totalFat;
    }

    private UserCalorieDTO(Parcel in) {
        id = in.readString();
        dateRecorded = in.readString();
        breakfast = in.readArrayList(FoodItemDTO.class.getClassLoader());
        lunch = in.readArrayList(FoodItemDTO.class.getClassLoader());
        snacks = in.readArrayList(FoodItemDTO.class.getClassLoader());
        dinner = in.readArrayList(FoodItemDTO.class.getClassLoader());
        calories = in.readInt();
        weight = in.readInt();
        totalProtein = in.readLong();
        totalCarbs = in.readLong();
        totalFat = in.readLong();
    }

    public static final Creator<UserCalorieDTO> CREATOR = new Creator<UserCalorieDTO>() {
        @Override
        public UserCalorieDTO createFromParcel(Parcel in) {
            return new UserCalorieDTO(in);
        }

        @Override
        public UserCalorieDTO[] newArray(int size) {
            return new UserCalorieDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(dateRecorded);
        parcel.writeList(breakfast);
        parcel.writeList(lunch);
        parcel.writeList(snacks);
        parcel.writeList(dinner);
        parcel.writeInt(calories);
        parcel.writeInt(weight);
        parcel.writeLong(totalProtein);
        parcel.writeLong(totalCarbs);
        parcel.writeLong(totalFat);
    }

    @Override
    public String toString() {
        return "UserCalorieDTO{" +
                "id='" + id + '\'' +
                ", dateRecorded='" + dateRecorded + '\'' +
                ", breakfast=" + breakfast +
                ", lunch=" + lunch +
                ", snacks=" + snacks +
                ", dinner=" + dinner +
                ", calories=" + calories +
                ", weight=" + weight +
                ", totalProtein=" + totalProtein +
                ", totalCarbs=" + totalCarbs +
                ", totalFat=" + totalFat +
                '}';
    }
}
