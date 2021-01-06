package com.example.demo;

public class Product {

    private String first_name,last_name,address;
    private String image;

    public Product(String first_name, String last_name, String address, String image) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.image = image;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
