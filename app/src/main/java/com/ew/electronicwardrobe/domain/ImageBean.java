package com.ew.electronicwardrobe.domain;

import androidx.annotation.DrawableRes;

public class ImageBean {

    private @DrawableRes
    int image;

    public ImageBean(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
