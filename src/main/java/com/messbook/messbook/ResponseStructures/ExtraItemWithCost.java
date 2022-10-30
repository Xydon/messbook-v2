package com.messbook.messbook.ResponseStructures;

public class ExtraItemWithCost {
    private String item_name;
    private int price;

    public ExtraItemWithCost() {}

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ExtraItemWithCost(String item_name, int price) {
        this.item_name = item_name;
        this.price = price;
    }
}
