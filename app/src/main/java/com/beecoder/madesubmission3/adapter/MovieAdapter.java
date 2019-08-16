package com.beecoder.madesubmission3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beecoder.madesubmission3.R;
import com.beecoder.madesubmission3.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    ArrayList<Item> itemArrayList = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        itemArrayList.clear();
        this.itemArrayList = itemArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_tab, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Item item = itemArrayList.get(i);
        movieViewHolder.title.setText(item.getTitle());
        Picasso.get().load("https://image.tmdb.org/p/w500" + item.getPhoto()).into(movieViewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_movie);
            photo = itemView.findViewById(R.id.img_movie);
        }
    }
}
