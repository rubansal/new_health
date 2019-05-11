package com.nsit.thehealthcompany.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItemDTO implements Parcelable {

    private int id;
    private int quantity;
    private int protein;
    private int carbs;
    private int fat;

    public FoodItemDTO(){}

    protected FoodItemDTO(Parcel in) {
        id = in.readInt();
        quantity = in.readInt();
        protein = in.readInt();
        carbs = in.readInt();
        fat = in.readInt();
    }

    public static final Creator<FoodItemDTO> CREATOR = new Creator<FoodItemDTO>() {
        @Override
        public FoodItemDTO createFromParcel(Parcel in) {
            return new FoodItemDTO(in);
        }

        @Override
        public FoodItemDTO[] newArray(int size) {
            return new FoodItemDTO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    @Override
    public String toString() {
        return "FoodItemDTO{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", protein=" + protein +
                ", carbs=" + carbs +
                ", fat=" + fat +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(quantity);
        parcel.writeInt(protein);
        parcel.writeInt(carbs);
        parcel.writeInt(fat);
    }

}
