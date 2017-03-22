package com.thaison.hyundaithanhcong.controler.item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by H81 on 3/21/2017.
 */

public class CarColor implements Serializable{
    private int id;
    private ArrayList<Color> colors;

    public CarColor(int id, ArrayList<Color> colors) {
        this.id = id;
        this.colors = colors;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Color> getColors() {
        return colors;
    }
}
