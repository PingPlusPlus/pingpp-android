package com.pingxx.demoapp;

/**
 * Created by sunkai on 15/3/18.
 */
public class Good {
    private String name;
    private int imgRes;
    private int count;
    private float price;

    public Good(String name,int imgRes,int count,float price){
        setName(name);
        setCount(count);
        setImgRes(imgRes);
        setPrice(price);
    }
    public int getCount() {
        return count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setImgRes(int imgRes) {

        this.imgRes = imgRes;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getImgRes() {

        return imgRes;
    }

    public String getName() {
        return name;
    }
}
