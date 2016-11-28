package com.mokinjazz.and.martian.presentation.view.impl;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mokinjazz.and.martian.presentation.R;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vvasilyev on 11/8/16.
 */
class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private static final int FAKE = 0;
    private static final int REGULAR = 1;

    private List<MovieModel> movieList = new ArrayList<>();

    private OnClickListener clickListener;

    MovieListAdapter(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    void display(List<MovieModel> movieList) {
        this.movieList = movieList;
        this.movieList.add(0, new FakeMovieModel());
        this.movieList.add(1, new FakeMovieModel());
        notifyDataSetChanged();
    }

    private MovieModel getMovie(int position) {
        return movieList.get(position);
    }

    private ViewHolder createFakeViewHolder(ViewGroup parent) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fake_item, parent, false)
        );
    }

    private ViewHolder createMovieViewHolder(ViewGroup parent) {
        return new MovieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie_item, parent, false)
        );
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FAKE) {
            return createFakeViewHolder(parent);
        }
        return createMovieViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieModel movie = getMovie(position);
        holder.bind(movie);
    }

    @Override
    public int getItemViewType(int position) {
        if (getMovie(position) instanceof FakeMovieModel) {
            return FAKE;
        } else {
            return REGULAR;
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    private class FakeMovieModel extends MovieModel {
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(MovieModel movie) {
        }
    }

    private class MovieViewHolder extends ViewHolder implements View.OnClickListener {

        TextView titleView;
        ImageView imageView;
        private MovieModel movie;

        MovieViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.movie_title_view);
            imageView = (ImageView) itemView.findViewById(R.id.movie_image_view);
            itemView.setOnClickListener(this);
        }

        public void bind(MovieModel movie) {
            this.movie = movie;
            titleView.setText(movie.title);
            Picasso.with(imageView.getContext())
                    .load(movie.imageUrl)
                    .into(imageView);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            clickListener.onClick(movie);
        }
    }

    interface OnClickListener {

        void onClick(MovieModel movie);
    }
}
