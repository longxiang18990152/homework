package com.example.sqlshop.db;

import java.io.Serializable;

public class ShopCar extends Product implements Serializable {
    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
