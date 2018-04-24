package com.trungnguyen.movieranking.listing;

import com.trungnguyen.movieranking.model.Movie;
import com.trungnguyen.movieranking.network.NetworkError;
import com.trungnguyen.movieranking.util.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author arun
 */
class MoviesListingPresenterImpl implements MoviesListingPresenter {
    private MoviesListingView view;
    private MoviesListingInteractor moviesInteractor;
    private Disposable fetchSubscription;
    private int currentPage = 1;
    private List<Movie> loadedMovies = new ArrayList<>();

    MoviesListingPresenterImpl(MoviesListingInteractor interactor) {
        moviesInteractor = interactor;
    }

    @Override
    public void setView(MoviesListingView view, boolean isReload) {
        this.view = view;
        if (isReload)
            displayMovies();
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }

    private void displayMovies() {
        fetchSubscription = moviesInteractor.fetchMovies(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> onMovieFetchSuccess(movies), throwable -> onMovieFetchFailed(throwable));
    }

    @Override
    public void firstPage() {
        currentPage = 1;
        loadedMovies.clear();
        displayMovies();
    }

    @Override
    public void nextPage() {
        showLoading();
        if (moviesInteractor.isPaginationSupported()) {
            currentPage++;
            displayMovies();
        }
    }


    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onMovieFetchSuccess(List<Movie> movies) {
        boolean isPagingSupported = moviesInteractor.isPaginationSupported();
        if (isPagingSupported) {
            loadedMovies.addAll(movies);
        } else {
            loadedMovies = new ArrayList<>(movies);
        }
        if (isViewAttached()) {
            view.showMovies(loadedMovies, isPagingSupported);
        }
    }

    private void onMovieFetchFailed(Throwable error) {
        currentPage--;
        view.loadingFailed(new NetworkError(error));
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
