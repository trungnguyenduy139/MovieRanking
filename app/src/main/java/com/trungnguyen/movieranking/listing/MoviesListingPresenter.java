package com.trungnguyen.movieranking.listing;

/**
 * @author arun
 */
public interface MoviesListingPresenter
{
    void firstPage();

    void nextPage();

    void setView(MoviesListingView view, boolean isReload);

    void destroy();
}
