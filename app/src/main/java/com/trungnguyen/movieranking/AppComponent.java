package com.trungnguyen.movieranking;

import com.trungnguyen.movieranking.details.DetailsComponent;
import com.trungnguyen.movieranking.details.DetailsModule;
import com.trungnguyen.movieranking.favorites.FavoritesModule;
import com.trungnguyen.movieranking.listing.ListingComponent;
import com.trungnguyen.movieranking.listing.ListingModule;
import com.trungnguyen.movieranking.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author arunsasidharan
 * @author pulkitkumar
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        FavoritesModule.class})
public interface AppComponent {
    DetailsComponent plus(DetailsModule detailsModule);

    ListingComponent plus(ListingModule listingModule);
}
