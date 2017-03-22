package com.thaison.hyundaithanhcong.controler.item;

import java.io.Serializable;

/**
 * Created by H81 on 3/21/2017.
 */

public class CarLogo implements Serializable {
    private int id;
    private String carLogoUrl;

    public CarLogo(int id, String carLogoUrl) {
        this.id = id;
        this.carLogoUrl = carLogoUrl;
    }

    public int getId() {
        return id;
    }

    public String getCarLogoUrl() {
        return carLogoUrl;
    }
}
