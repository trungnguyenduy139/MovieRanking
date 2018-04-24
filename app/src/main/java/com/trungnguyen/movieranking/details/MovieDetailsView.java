package com.trungnguyen.movieranking.details;

import com.trungnguyen.movieranking.model.Movie;
import com.trungnguyen.movieranking.model.Review;
import com.trungnguyen.movieranking.model.Video;

import java.util.List;

/**
 * @author arun
 * @author Trung Nguyen
 */
interface MovieDetailsView {
    void showDetails(Movie movie);

    void showTrailers(List<Video> trailers);

    void showReviews(List<Review> reviews);

    void showFavorited();

    void showUnFavorited();

    void onGetDetailFailed(String errorMsg);
}
