package com.thaison.hyundaithanhcong.controler.item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by H81 on 3/20/2017.
 */

public class CarFeature implements Serializable {
    private int id;

    private ArrayList<String> features;

    public CarFeature(int id, ArrayList<String> features) {
        this.id = id;
        this.features = features;
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }
}
