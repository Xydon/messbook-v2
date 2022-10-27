package com.messbook.messbook.Entities;

public class Extra_Item_Menu {
    private String name;
    private int price;

    public Extra_Item_Menu() {}

    public Extra_Item_Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
