package com.trungnguyen.movieranking.listing;

import com.trungnguyen.movieranking.model.Movie;

import java.util.List;

/**
 * @author arun
 */
interface MoviesListingView {
    void showMovies(List<Movie> movies, boolean isPagingSupported);

    void loadingStarted();

    void loadingFailed(String errorMsg);

    void onMovieClicked(Movie movie);
}
