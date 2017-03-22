package com.thaison.hyundaithanhcong.controler.others;

import com.thaison.hyundaithanhcong.R;

/**
 * Created by H81 on 3/17/2017.
 */

public class ColorDrawableResource {
    private int colorId;

    public ColorDrawableResource(int colorId) {
        this.colorId = colorId;
    }

    public int getDrawableResource() {
        switch (colorId) {
            case 1:
                return R.drawable.color1;

            case 2:
                return R.drawable.color2;

            case 3:
                return R.drawable.color3;

            case 4:
                return R.drawable.color4;

            case 5:
                return R.drawable.color5;

            case 6:
                return R.drawable.color6;

            case 7:
                return R.drawable.color7;

            case 8:
                return R.drawable.color8;

            case 9:
                return R.drawable.color9;

            case 10:
                return R.drawable.color10;

            case 11:
                return R.drawable.color11;

            case 12:
                return R.drawable.color12;

            case 13:
                return R.drawable.color13;

            case 14:
                return R.drawable.color14;

            case 15:
                return R.drawable.color15;

            case 16:
                return R.drawable.color16;
        }
        return 0;
    }
}
