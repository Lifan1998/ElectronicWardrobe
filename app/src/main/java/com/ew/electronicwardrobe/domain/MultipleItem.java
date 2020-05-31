package com.ew.electronicwardrobe.domain;

import androidx.annotation.DrawableRes;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ew.electronicwardrobe.entity.Cloths;
import com.ew.electronicwardrobe.entity.Outfit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MultipleItem implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    private int itemType;
    private String text = "";
    private @DrawableRes
    int image;
    private Cloths cloths;
    private Outfit outfit;

    public MultipleItem(int itemType, String text, int image) {
        this.itemType = itemType;
        this.text = text;
        this.image = image;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}