package com.example.tmdbclient;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.tmdbclient.Adapter.BeerAdapter;

import com.example.tmdbclient.Adapter.FilterBy;
import com.example.tmdbclient.Model.BeerStyle;
import com.example.tmdbclient.ViewModel.MainActivityViewModel;
import com.example.tmdbclient.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ArrayList<Beer> beers;
    private RecyclerView recyclerView;
    private BeerAdapter beerAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;

    private ActivityMainBinding activityMainBinding;
    private SearchView searchView;
    private BeerStyle selectedStyle;
    private MainActivityClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Crafts Brewery");

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        handlers=new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);

        searchView=activityMainBinding.searchBeer;

        searchView.setOnQueryTextListener(this);
        getPopularMovies();

        swipeRefreshLayout=activityMainBinding.swipeLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
        showOnSpinner();
    }

    private void getPopularMovies() {


        mainActivityViewModel.getAllBeers().observe(this, new Observer<List<Beer>>() {
            @Override
            public void onChanged(@Nullable List<Beer> moviesFromLiveData) {
                beers =(ArrayList<Beer>) moviesFromLiveData;
                showOnRecyclerView();
            }
        });

    }

    private void showOnSpinner(){

        ArrayAdapter<BeerStyle> categoryArrayAdapter=new ArrayAdapter<BeerStyle>(this,R.layout.spinner_item,BeerStyle.values());
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);



    }

    public class MainActivityClickHandlers{


        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {

            selectedStyle = (BeerStyle) parent.getItemAtPosition(pos);

           // String message = " id is " + selectedStyle.getId() + "\n name is " + selectedCategory.getCategoryName() + "\n email is " + selectedCategory.getCategoryDescription();

            System.out.println("seleted :"+selectedStyle.toString()+" ");
            // Showing selected spinner item
            Toast.makeText(parent.getContext(),"seleted :"+selectedStyle.toString()+" ", Toast.LENGTH_LONG).show();

            beerAdapter.filterBy =FilterBy.Style;

            if(!selectedStyle.equals(BeerStyle.All)){
                beerAdapter.getFilter().filter(selectedStyle.name());
            }else {
                if(beerAdapter!=null)
                beerAdapter.getFilter().filter("");
            }

        }

        public void sort(View view){
            if(view.getTag().equals("0")) {
                beerAdapter.sortAssending();
                view.setTag("1");
                ((Button)view).setText("Assending");
            }else {
                beerAdapter.sortDescending();
                view.setTag("0");
                ((Button)view).setText("Descending");
            }
        }

    }




    private void showOnRecyclerView() {

        recyclerView=activityMainBinding.rvMovies;
        beerAdapter=new BeerAdapter(this,beers);

      //  if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT)
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
     //   else  recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(beerAdapter);
        beerAdapter.notifyDataSetChanged();

        if(swipeRefreshLayout!=null && swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        beerAdapter.filterBy =FilterBy.SearchKeyword;
        beerAdapter.getFilter().filter(newText);

        return false;
    }
}
