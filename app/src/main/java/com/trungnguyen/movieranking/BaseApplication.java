package com.trungnguyen.movieranking;

import android.app.Application;
import android.os.StrictMode;

import com.trungnguyen.movieranking.details.DetailsComponent;
import com.trungnguyen.movieranking.details.DetailsModule;
import com.trungnguyen.movieranking.favorites.FavoritesModule;
import com.trungnguyen.movieranking.listing.ListingComponent;
import com.trungnguyen.movieranking.listing.ListingModule;
import com.trungnguyen.movieranking.network.NetworkModule;

/**
 * @author arun
 * @author Trung Nguyen
 */
public class BaseApplication extends Application {
    private AppComponent appComponent;
    private DetailsComponent detailsComponent;
    private ListingComponent listingComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .favoritesModule(new FavoritesModule())
                .build();
    }

    public DetailsComponent createDetailsComponent() {
        detailsComponent = appComponent.plus(new DetailsModule());
        return detailsComponent;
    }

    public void releaseDetailsComponent() {
        detailsComponent = null;
    }

    public ListingComponent createListingComponent() {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public void releaseListingComponent() {
        listingComponent = null;
    }

    public ListingComponent getListingComponent() {
        return listingComponent;
    }
}
