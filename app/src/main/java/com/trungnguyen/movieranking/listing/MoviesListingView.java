package com.trungnguyen.movieranking.listing;

import com.trungnguyen.movieranking.model.Movie;
import com.trungnguyen.movieranking.network.NetworkError;

import java.util.List;

/**
 * @author arun
 */
interface MoviesListingView {
    void showMovies(List<Movie> movies, boolean isPagingSupported);

    void loadingStarted();

    void loadingFailed(NetworkError networkError);

    void onMovieClicked(Movie movie);
}
