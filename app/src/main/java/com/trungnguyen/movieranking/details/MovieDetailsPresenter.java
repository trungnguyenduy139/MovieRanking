package com.trungnguyen.movieranking.details;

import com.trungnguyen.movieranking.model.Movie;

/**
 * @author arun
 */
public interface MovieDetailsPresenter {
    void showDetails(Movie movie);

    void showTrailers(Movie movie);

    void showReviews(Movie movie);

    void showFavoriteButton(Movie movie);

    void onFavoriteClick(Movie movie);

    void setView(MovieDetailsView view);

    void destroy();
}
