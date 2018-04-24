package com.trungnguyen.movieranking.util;

import android.support.v7.widget.RecyclerView;

/**
 * @author Trung Nguyen
 */
public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private Runnable loadMoreRunnable = () -> onLoadMore();

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (!recyclerView.canScrollVertically(1)) {
            recyclerView.post(loadMoreRunnable);
        }
    }

    protected abstract void onLoadMore();

}
