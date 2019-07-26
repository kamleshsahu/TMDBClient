package com.example.tmdbclient.adapter;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.tmdbclient.Beer;
import com.example.tmdbclient.CartActivity;
import com.example.tmdbclient.R;
import com.example.tmdbclient.databinding.CartListItemBinding;
import com.example.tmdbclient.databinding.CartListItemBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MovieViewHolder>  {


    private Context context;
    private ArrayList<Beer> beerList;


    public CartAdapter(Context context, ArrayList<Beer> beerList) {
        this.context = context;

        this.beerList = beerList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartListItemBinding movieListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cart_list_item,parent,false);
        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Beer beer=beerList.get(position);
        holder.movieListItemBinding.setBeer(beer);
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }



    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private CartListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull CartListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding=movieListItemBinding;

        }

        
    }


}
