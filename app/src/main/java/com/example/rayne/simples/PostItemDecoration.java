package com.example.rayne.simples;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by RayneSim on 17/07/29.
 */

public class PostItemDecoration extends RecyclerView.ItemDecoration{

    private int space;
    private @NonNull GridLayoutManager.SpanSizeLookup spanSizeLookup;

    public PostItemDecoration(int space, @NonNull GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        this.space = space;
        this.spanSizeLookup = spanSizeLookup;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (spanSizeLookup.getSpanSize(position) == 1){
            outRect.left = space;
            if (position % 2 == 0){
                outRect.right = space;
            }
        }
    }
}
