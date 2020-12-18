package com.example.sqlshop.db;

import java.io.Serializable;

public class Product  implements Serializable {
    public String name;
    public String price;
    public String bmppath;
    private String content;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBmppath() {
        return bmppath;
    }

    public void setBmppath(String bmppath) {
        this.bmppath = bmppath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
