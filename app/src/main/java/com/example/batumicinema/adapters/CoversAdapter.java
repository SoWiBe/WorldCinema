package com.example.batumicinema.adapters;

import android.content.Context;
import android.telecom.Call;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batumicinema.R;
import com.example.batumicinema.network.models.MovieCoverResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.security.auth.callback.Callback;

public class CoversAdapter extends RecyclerView.Adapter<CoversAdapter.ViewHolder> {

    private ArrayList<MovieCoverResponse> movieCoverResponses;
    private LayoutInflater inflater;
    private Context context;
    private Callback callback;

    public CoversAdapter(ArrayList<MovieCoverResponse> movieCoverResponses, Context context) {
        this.movieCoverResponses = movieCoverResponses;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CoversAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.cover_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieCoverResponse movieCoverResponse = movieCoverResponses.get(position);
        holder.setTextCinema(movieCoverResponse.getMovieId());
        Picasso.with(context).load("http://cinema.areas.su/up/images/" + movieCoverResponse.getBackgroundImage()).into(holder.coverCinema);
    }

    @Override
    public int getItemCount() {
        return movieCoverResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final private ImageView coverCinema;
        final private TextView txtCinema;

        private ViewHolder(View view) {
            super(view);
            this.coverCinema = (ImageView) view.findViewById(R.id.image_cover_cinema);
            this.txtCinema = (TextView) view.findViewById(R.id.txt_cover_cinema);
        }

        public void setTextCinema(String text){
            this.txtCinema.setText(text);
        }
    }
}
