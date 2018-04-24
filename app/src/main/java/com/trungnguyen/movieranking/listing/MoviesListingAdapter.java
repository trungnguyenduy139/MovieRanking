package com.trungnguyen.movieranking.listing;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.trungnguyen.movieranking.util.Api;
import com.trungnguyen.movieranking.model.Movie;
import com.trungnguyen.movieranking.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author arun
 */
public class MoviesListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movies;
    private Context context;
    private MoviesListingView view;
    public static final int LOADING_TYPE = -1;
    public static final int NORMAL_TYPE = 1;
    private boolean showLoader = true;
    private boolean isPagingSupported = true;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_poster)
        ImageView poster;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.movie_name)
        TextView name;

        public Movie movie;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            MoviesListingAdapter.this.view.onMovieClicked(movie);
        }
    }

    public class LoaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loading_more_progress_bar)
        ProgressBar progressBar;

        public LoaderViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        public void setVisible(int status) {
            progressBar.setVisibility(status);
        }
    }

    public MoviesListingAdapter(List<Movie> movies, MoviesListingView moviesView) {
        this.movies = movies;
        view = moviesView;
    }

    public void setFavoritesList(boolean isFavorite) {
        isPagingSupported = isFavorite;
    }

    public void showLoadingView(boolean status) {
        showLoader = status;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView;
        switch (viewType) {
            case NORMAL_TYPE:
                rootView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false);
                break;
            case LOADING_TYPE:
                rootView = LayoutInflater.from(context).inflate(R.layout.loading_more, parent, false);
                return new LoaderViewHolder(rootView);
            default:
                rootView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false);
                break;
        }
        return new ViewHolder(rootView);
    }

    @Override
    public int getItemViewType(int position) {
        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1 && isPagingSupported) {
            return LOADING_TYPE;
        }

        return NORMAL_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Loader ViewHolder

        if (holder instanceof LoaderViewHolder) {
//            ((LoaderViewHolder) holder).setVisible(showLoader ? View.VISIBLE : View.INVISIBLE);
            return;
        }
        ViewHolder movieViewHolder = (ViewHolder) holder;
        movieViewHolder.itemView.setOnClickListener(movieViewHolder);
        movieViewHolder.movie = movies.get(position);
        movieViewHolder.name.setText(movieViewHolder.movie.getTitle());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .asBitmap()
                .load(Api.getPosterPath(movieViewHolder.movie.getPosterPath()))
                .apply(options).into(movieViewHolder.poster);
//                .into(new BitmapImageViewTarget(movieViewHolder.poster) {
//                    @Override
//                    public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
//                        super.onResourceReady(bitmap, transition);
//                        Palette.from(bitmap).generate(palette -> setBackgroundColor(palette, movieViewHolder));
//                    }
//                });
    }

    private void setBackgroundColor(Palette palette, ViewHolder holder) {
        holder.titleBackground.setBackgroundColor(palette.getVibrantColor(context
                .getResources().getColor(R.color.black_translucent_60)));
    }

    @Override
    public int getItemCount() {
        // If no items are present, there's no need for loader
        if (movies == null || movies.size() == 0) {
            return 0;
        }

        // +1 for loader
        return isPagingSupported ? movies.size() + 1 : movies.size();
    }
}
