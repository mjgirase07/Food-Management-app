package com.example.sample.model;

public class Item {
    private int item_id;
    private String item_name;
    private String item_weight;

    private String item_expiry;

    private Integer item_frequency;

    public Item(int item_id, String item_name, String item_weight) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_weight = item_weight;
    }

    public Item(int item_id, String item_name, String item_weight,String item_expiry,int item_frequency) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_weight = item_weight;
        this.item_expiry = item_expiry;
        this.item_frequency = item_frequency;
    }

    public String getItem_expiry() {
        return item_expiry;
    }

    public void setItem_expiry(String item_expiry) {
        this.item_expiry = item_expiry;
    }

    public int getItem_frequency() {
        return item_frequency;
    }

    public void setItem_frequency(int item_frequency) {
        this.item_frequency = item_frequency;
    }

    public Item(String item_name, String item_weight) {
        this.item_name = item_name;
        this.item_weight = item_weight;
    }

    public Item() {

    }

    public int getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_weight() {
        return item_weight;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_weight(String item_weight) {
        this.item_weight = item_weight;
    }
}
