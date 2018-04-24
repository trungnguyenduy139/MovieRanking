package com.trungnguyen.movieranking.listing;

import com.trungnguyen.movieranking.listing.sorting.SortingDialogFragment;
import com.trungnguyen.movieranking.listing.sorting.SortingModule;

import dagger.Subcomponent;

/**
 * @author arunsasidharan
 */
@ListingScope
@Subcomponent(modules = {ListingModule.class, SortingModule.class})
public interface ListingComponent {
    MoviesListingFragment inject(MoviesListingFragment fragment);

    SortingDialogFragment inject(SortingDialogFragment fragment);
}
