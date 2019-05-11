package com.nsit.thehealthcompany.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlogPostDTO {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("btitle")
    @Expose
    private String title;
    @SerializedName("intro")
    @Expose
    private String description;
    @SerializedName("photo1")
    @Expose
    private String photo;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("url_field")
    @Expose
    private String redirectURL;

    public BlogPostDTO(String id, String title, String description, String photo, String datePublished, String redirectURL) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.datePublished = datePublished;
        this.redirectURL = redirectURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    @Override
    public String toString() {
        return "BlogPostDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", datePublished='" + datePublished + '\'' +
                ", redirectURL='" + redirectURL + '\'' +
                '}';
    }
}
