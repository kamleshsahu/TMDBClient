package com.example.tmdbclient.service;

import com.example.tmdbclient.Beer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerDataService {

    @GET("beercraft")
    Call<Beer[]> getBeerList();
}
