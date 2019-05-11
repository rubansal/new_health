package com.nsit.thehealthcompany.DTO;

public class Item {
    private int first_image;
    private String first_text;
    private String second_text;

    public Item(int first_image, String first_text, String second_text) {
        this.first_image = first_image;
        this.first_text = first_text;
        this.second_text = second_text;
    }

    public int getFirst_image() {
        return first_image;
    }

    public void setFirst_image(int first_image) {
        this.first_image = first_image;
    }

    public String getFirst_text() {
        return first_text;
    }

    public void setFirst_text(String first_text) {
        this.first_text = first_text;
    }

    public String getSecond_text() {
        return second_text;
    }

    public void setSecond_text(String second_text) {
        this.second_text = second_text;
    }

}
