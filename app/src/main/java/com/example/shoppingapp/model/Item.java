package com.example.shoppingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    private int id;
    private String itemname;
    private Integer itemprice;
    private String itemimage;
    private String itemdescribe;
    private int itemid;


    public Item() {
    }

    public Item(int id, String itemname, Integer itemprice, String itemimage, String itemdescribe, int itemid) {
        super();
        this.id = id;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemimage = itemimage;
        this.itemdescribe = itemdescribe;
        this.itemid = itemid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getItemprice() {
        return itemprice;
    }

    public void setItemprice(Integer itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemimage() {
        return itemimage;
    }

    public void setItemimage(String itemimage) {
        this.itemimage = itemimage;
    }

    public String getItemdescribe() {
        return itemdescribe;
    }

    public void setItemdescribe(String itemdescribe) {
        this.itemdescribe = itemdescribe;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }
}