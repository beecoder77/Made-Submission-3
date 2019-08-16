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

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    ArrayList<Item> itemArrayList = new ArrayList<>();
    Context context;

    public TvAdapter(Context context){
        this.context = context;
    }

    ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        itemArrayList.clear();
        this.itemArrayList = itemArrayList;
        notifyDataSetChanged();
    }

    public void AddItems(final Item item){
        itemArrayList.add(item);
        notifyDataSetChanged();
    }

    public void clear(){
        itemArrayList.clear();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_tab, viewGroup, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder tvViewHolder, int i) {
        Item item = itemArrayList.get(i);
        tvViewHolder.title.setText(item.getTitle());
        Picasso.get().load("https://image.tmdb.org/p/w500" + item.getPhoto()).into(tvViewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_tv);
            photo = itemView.findViewById(R.id.img_tv);
        }
    }
}
