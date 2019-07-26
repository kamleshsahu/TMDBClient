package com.example.tmdbclient;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tmdbclient.adapter.BeerAdapter;
import com.example.tmdbclient.adapter.CartAdapter;
import com.example.tmdbclient.viewModel.CartActivityViewModel;
import com.example.tmdbclient.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding activityCartBinding;

    private CartActivityViewModel cartActivityViewModel;
    private RecyclerView recyclerView;
    private CartAdapter beerAdapter;
    ArrayList<Beer> beerslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setTitle("Crafts Brewery:Cart");
        Intent intent = getIntent();



        activityCartBinding= DataBindingUtil.setContentView(this,R.layout.activity_cart);

        cartActivityViewModel =ViewModelProviders.of(this).get(CartActivityViewModel.class);


        cartActivityViewModel.getAllCategories().observe(this, new Observer<List<Beer>>() {
            @Override
            public void onChanged(@Nullable List<Beer> beers) {
                beerslist=(ArrayList<Beer>) beers;
                showOnRecyclerView();
            }

        });
        if (intent.hasExtra("beer")) {

            Beer beer = getIntent().getParcelableExtra("beer");
//            activityMovieBinding.setMovie(movie);
//            getSupportActionBar().setTitle(movie.getTitle());

            cartActivityViewModel.addNewBook(beer);

            Toast.makeText(this,beer.getName()+" added in Cart",Toast.LENGTH_LONG).show();
        }


    }



    private void showOnRecyclerView() {

        recyclerView=activityCartBinding.rvMovies;
        beerAdapter=new CartAdapter(this,beerslist);

        //  if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT)
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        //   else  recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(beerAdapter);
        beerAdapter.notifyDataSetChanged();


    }
}
