package com.thaison.hyundaithanhcong.controler.item;

import java.io.Serializable;

/**
 * Created by H81 on 3/21/2017.
 */

public class Color implements Serializable {
    private int colorId;

    public Color(int colorId) {
        this.colorId = colorId;
    }

    public int getColorId() {
        return colorId;
    }
}
