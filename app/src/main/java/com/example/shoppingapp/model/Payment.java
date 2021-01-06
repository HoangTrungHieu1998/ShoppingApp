package com.example.shoppingapp.model;

public class Payment {
    private String itemName;
    private String itemImage;
    private int itemID;
    private int itemNumber;
    private Integer price;

    public Payment(String itemName, String itemImage, int itemID, int itemNumber, Integer price) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemID = itemID;
        this.itemNumber = itemNumber;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
