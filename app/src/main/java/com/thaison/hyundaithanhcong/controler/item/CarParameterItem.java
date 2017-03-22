package com.thaison.hyundaithanhcong.controler.item;

import java.io.Serializable;

/**
 * Created by H81 on 3/16/2017.
 */

public class CarParameterItem implements Serializable{
    private int carParameterLogo;
    private String dongCo;
    private String dongCoChiTiet;

    public CarParameterItem(int carParameterLogo, String dongCo, String dongCoChiTiet) {
        this.carParameterLogo = carParameterLogo;
        this.dongCo = dongCo;
        this.dongCoChiTiet = dongCoChiTiet;
    }

    public int getCarParameterLogo() {
        return carParameterLogo;
    }

    public String getDongCo() {
        return dongCo;
    }

    public String getDongCoChiTiet() {
        return dongCoChiTiet;
    }
}
