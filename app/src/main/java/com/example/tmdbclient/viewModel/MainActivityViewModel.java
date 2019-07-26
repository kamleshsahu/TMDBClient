package com.example.tmdbclient.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.tmdbclient.Beer;
import com.example.tmdbclient.model.BeerRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
   // private MovieRepository movieRepository;

    private BeerRepository beerRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

      //  movieRepository=new MovieRepository(application);
        beerRepository=new BeerRepository(application);
    }

//    public LiveData<List<Movie>> getAllMovies(){
//        return movieRepository.getMutableLiveData();
//    }

    public LiveData<List<Beer>> getAllBeers(){
        return beerRepository.getMutableLiveData();
    }
}
