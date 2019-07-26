package com.example.tmdbclient.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.tmdbclient.Beer;

@Database(entities ={Beer.class},version = 1)
public abstract class CartDatabase extends RoomDatabase {


    public abstract CartDAO bookDAO();

    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CartDatabase.class, "cart_database")
                    .fallbackToDestructiveMigration()
               //     .addCallback(callback)
                    .build();
        }
        return instance;
    }
}
