package com.taosearch.model;

public class Shop {
    private long id;
    private String name;
    private String address;
    private String link;
    private int item_num;
    private int real_item_num;
    private double price;
    private double settlement;
    private double total_settlement;
    private String add_time;
    private String user_id;
    private String username;

    public double getTotal_settlement() {
        return total_settlement;
    }

    public void setTotal_settlement(double total_settlement) {
        this.total_settlement = total_settlement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public int getReal_item_num() {
        return real_item_num;
    }

    public void setReal_item_num(int real_item_num) {
        this.real_item_num = real_item_num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSettlement() {
        return settlement;
    }

    public void setSettlement(double settlement) {
        this.settlement = settlement;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

