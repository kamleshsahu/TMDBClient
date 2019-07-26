package com.example.tmdbclient.model;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.example.tmdbclient.Beer;
import com.example.tmdbclient.service.BeerDataService;
import com.example.tmdbclient.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerRepository {

    private ArrayList<Beer> beers=new ArrayList<>();

    private MutableLiveData<List<Beer>>  mutableLiveData=new MutableLiveData<>();

    private Application application;


    public BeerRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Beer>> getMutableLiveData(){

        final BeerDataService beerDataService= RetrofitInstance.getService();

        Call<Beer[]> call=beerDataService.getBeerList();

        call.enqueue(new Callback<Beer[]>() {
            @Override
            public void onResponse(Call<Beer[]> call, Response<Beer[]> response) {
               Beer[] BeersResp;
                BeersResp = response.body();

                 if(BeersResp!=null){
                    beers.addAll(Arrays.asList(BeersResp));


                    mutableLiveData.setValue(beers);
                }
            }

            @Override
            public void onFailure(Call<Beer[]> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return mutableLiveData;
    }


}
