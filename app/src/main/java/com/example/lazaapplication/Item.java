package com.example.lazaapplication;

public class Item {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int imageResId; // Resource ID for the image

    public Item(int id,String name, int price, int quantity, int imageResId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public int getImageResId() {
        return imageResId;
    }
}