package com.thaison.hyundaithanhcong.controler.item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by H81 on 3/20/2017.
 */

public class CarParameter implements Serializable {
    private int id;
    private ArrayList<CarParameterItem> carParameterItems;

    public CarParameter(int id, ArrayList<CarParameterItem> carParameterItems) {
        this.id = id;
        this.carParameterItems = carParameterItems;
    }

    public int getId() {
        return id;
    }

    public ArrayList<CarParameterItem> getCarParameterItems() {
        return carParameterItems;
    }
}
