package com.thaison.hyundaithanhcong.controler.item;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by H81 on 3/13/2017.
 */

public class CarItem implements Serializable{
    private int carId;
    private String carImageUrl;
    private String carName;
    private String carSlogan;

    public CarItem(int carId, String carImageUrl, String carName, String carSlogan) {
        this.carId = carId;
        this.carImageUrl = carImageUrl;
        this.carName = carName;
        this.carSlogan = carSlogan;
    }

    public int getCarId() {
        return carId;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarSlogan() {
        return carSlogan;
    }
}
