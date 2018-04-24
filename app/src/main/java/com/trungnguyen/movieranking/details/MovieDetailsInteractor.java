package com.trungnguyen.movieranking.details;

import com.trungnguyen.movieranking.model.Review;
import com.trungnguyen.movieranking.model.Video;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
public interface MovieDetailsInteractor {
    Observable<List<Video>> getTrailers(String id);

    Observable<List<Review>> getReviews(String id);
}
