package com.thaison.hyundaithanhcong.controler.item;

/**
 * Created by H81 on 3/17/2017.
 */

public class Colors {
    private String imageUrl;
    private int colorId;

    public Colors(String imageUrl, int color) {
        this.imageUrl = imageUrl;
        this.colorId = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getColorId() {
        return colorId;
    }
}
