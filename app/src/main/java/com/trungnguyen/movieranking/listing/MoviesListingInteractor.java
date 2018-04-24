package com.trungnguyen.movieranking.listing;

import com.trungnguyen.movieranking.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
public interface MoviesListingInteractor {
    boolean isPaginationSupported();

    Observable<List<Movie>> fetchMovies(int page);
}
