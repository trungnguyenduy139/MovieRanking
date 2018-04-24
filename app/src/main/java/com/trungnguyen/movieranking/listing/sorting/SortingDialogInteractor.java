package com.trungnguyen.movieranking.listing.sorting;

/**
 * @author arun
 */
public interface SortingDialogInteractor {
    int getSelectedSortingOption();

    void setSortingOption(SortType sortType);
}
