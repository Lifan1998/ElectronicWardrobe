package com.ew.electronicwardrobe.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing; //间隔

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) != 0)
            outRect.left = spacing;
    }
}
