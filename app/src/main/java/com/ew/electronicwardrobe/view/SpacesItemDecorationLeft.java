package com.ew.electronicwardrobe.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecorationLeft extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecorationLeft(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        if (childLayoutPosition != 0) {
            outRect.left = space;
        } else {
            outRect.left = 2 * space;
        }

    }
}
