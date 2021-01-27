package com.example.demo;

public class FoodName {

    private String title,description,address,rating,image;
    public FoodName(String title, String description, String address, String rating, String image) {
        this.title=title;
        this.description=description;
        this.address=address;
        this.rating=rating;
        this.image=image;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
