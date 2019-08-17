package com.beecoder.madesubmission3.layout.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.beecoder.madesubmission3.R;
import com.beecoder.madesubmission3.adapter.ItemClickSupport;
import com.beecoder.madesubmission3.adapter.MovieAdapter;
import com.beecoder.madesubmission3.layout.activity.MovieActivity;
import com.beecoder.madesubmission3.model.Item;
import com.beecoder.madesubmission3.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    MovieAdapter movieAdapter;
    MovieViewModel movieViewModel;
    ProgressBar progressBar;
    ImageView photo;
    Context mContext;
    private RecyclerView recyclerViewMovie;
    private ArrayList<Item> itemArrayList = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        progressBar = view.findViewById(R.id.pb_movie);
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewMovie = view.findViewById(R.id.rv_movie);
        photo = view.findViewById(R.id.img_movie);

        prepare();
        showRecyclerView();

        recyclerViewMovie.addOnItemTouchListener(new ItemClickSupport(getContext(), recyclerViewMovie, new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item item = new Item(movieViewModel.itemArrayList.get(position).getTitle(), movieViewModel.itemArrayList.get(position).getPhoto(), movieViewModel.itemArrayList.get(position).getDescription());
                Intent detailMovie = new Intent(getContext(), MovieActivity.class);
                detailMovie.putExtra(MovieActivity.EXTRA_MOVIE, item);
                startActivity(detailMovie);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Item item = new Item(movieViewModel.itemArrayList.get(position).getPhoto(), movieViewModel.itemArrayList.get(position).getTitle(), movieViewModel.itemArrayList.get(position).getDescription());

                Intent detailMovie = new Intent(getContext(), MovieActivity.class);
                detailMovie.putExtra(MovieActivity.EXTRA_MOVIE, item);
                startActivity(detailMovie);
            }
        }));

        return  view;
    }

    private void prepare() {
        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        movieViewModel.movie().observe(MovieFragment.this, getMovie);
        movieViewModel.getData();
    }

    private void showRecyclerView() {
        movieAdapter = new MovieAdapter(getContext());
        movieAdapter.notifyDataSetChanged();
        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewMovie.setAdapter(movieAdapter);
    }

    private Observer<ArrayList<Item>> getMovie = new Observer<ArrayList<Item>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Item> items) {
            if(items != null){
                movieAdapter.setItemArrayList(items);
                progressBar.setVisibility(View.GONE);
            }
        }
    };
}
