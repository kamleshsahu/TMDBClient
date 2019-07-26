package com.example.tmdbclient.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.tmdbclient.Beer;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insert(Beer beer);

    @Delete
    void delete(Beer beer);

    @Query("SELECT * FROM cart_table")
    LiveData<List<Beer>> getAllBeers();
}
