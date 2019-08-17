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
import com.beecoder.madesubmission3.adapter.TvAdapter;
import com.beecoder.madesubmission3.layout.activity.TvActivity;
import com.beecoder.madesubmission3.model.Item;
import com.beecoder.madesubmission3.viewmodel.MovieViewModel;
import com.beecoder.madesubmission3.viewmodel.TvViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    TvAdapter tvAdapter;
    TvViewModel tvViewModel;
    ProgressBar progressBar;
    ImageView photo;
    Context mContext;
    private RecyclerView recyclerViewTV;
    private ArrayList<Item> itemArrayList = new ArrayList<>();

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv, container, false);

        progressBar = view.findViewById(R.id.pb_tv);
        progressBar.setVisibility(View.VISIBLE);

        recyclerViewTV = view.findViewById(R.id.rv_tv);
        photo = view.findViewById(R.id.img_tv);

        prepare();
//        addItem();
        showRecyclerView();
        recyclerViewTV.addOnItemTouchListener(new ItemClickSupport(getContext(), recyclerViewTV, new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item item = new Item(tvViewModel.itemArrayList.get(position).getTitle(), tvViewModel.itemArrayList.get(position).getPhoto(), tvViewModel.itemArrayList.get(position).getDescription());

                Intent detailTv = new Intent(getContext(), TvActivity.class);
                detailTv.putExtra(TvActivity.EXTRA_TV, item);
                startActivity(detailTv);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Item item = new Item(tvViewModel.itemArrayList.get(position).getTitle(), tvViewModel.itemArrayList.get(position).getPhoto(), tvViewModel.itemArrayList.get(position).getDescription());

                Intent detailTv = new Intent(getContext(), TvActivity.class);
                detailTv.putExtra(TvActivity.EXTRA_TV, item);
                startActivity(detailTv);
            }
        }));

        return  view;
    }

//    private void addItem() {
//        itemArrayList = new ArrayList<>();
//        int length = tvViewModel.itemArrayList.size();
//        for(int i = 0; i<length; i++){
//            Item item = new Item();
//            item.setTitle(itemArrayList.get(i).getTitle());
//            Picasso.get().load("https://image.tmdb.org/t/p/w185" + itemArrayList.get(i).getPhoto()).into(photo);
//            itemArrayList.add(item);
//        }
//    }

    private void prepare() {
        tvViewModel = ViewModelProviders.of(getActivity()).get(TvViewModel.class);
        tvViewModel.tv().observe(TvFragment.this, getTv);
        tvViewModel.getData();
    }

    private void showRecyclerView() {
        tvAdapter = new TvAdapter(getContext());
        tvAdapter.notifyDataSetChanged();
        recyclerViewTV.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTV.setAdapter(tvAdapter);
    }

    private Observer<ArrayList<Item>> getTv = new Observer<ArrayList<Item>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Item> items) {
            if(items != null){
                tvAdapter.setItemArrayList(items);
                progressBar.setVisibility(View.GONE);
            }
        }
    };

}
