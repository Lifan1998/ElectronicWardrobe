package com.ew.electronicwardrobe.domain;

import androidx.annotation.DrawableRes;

public class ColorBean {

    private boolean checked;
    private @DrawableRes
    int image;

    public ColorBean(boolean checked, int image) {
        this.checked = checked;
        this.image = image;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
