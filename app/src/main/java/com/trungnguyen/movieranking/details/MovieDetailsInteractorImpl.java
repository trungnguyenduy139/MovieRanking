package com.trungnguyen.movieranking.details;

import com.trungnguyen.movieranking.model.Review;
import com.trungnguyen.movieranking.model.ReviewsWrapper;
import com.trungnguyen.movieranking.model.Video;
import com.trungnguyen.movieranking.model.VideoWrapper;
import com.trungnguyen.movieranking.network.TmdbWebService;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
class MovieDetailsInteractorImpl implements MovieDetailsInteractor {

    private TmdbWebService tmdbWebService;

    MovieDetailsInteractorImpl(TmdbWebService tmdbWebService) {
        this.tmdbWebService = tmdbWebService;
    }

    @Override
    public Observable<List<Video>> getTrailers(final String id) {
        return tmdbWebService.trailers(id).map(VideoWrapper::getVideos);
    }

    @Override
    public Observable<List<Review>> getReviews(final String id) {
        return tmdbWebService.reviews(id).map(ReviewsWrapper::getReviews);
    }

}
