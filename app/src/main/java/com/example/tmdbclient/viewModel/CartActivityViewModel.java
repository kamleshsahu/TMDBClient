package com.example.tmdbclient.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.tmdbclient.Beer;
import com.example.tmdbclient.model.CartRepository;

import java.util.List;

public class CartActivityViewModel extends AndroidViewModel {

    private CartRepository eBookShopRepository;
    private LiveData<List<Beer>> allCategories;


    public CartActivityViewModel(@NonNull Application application) {
        super(application);

        eBookShopRepository=new CartRepository(application);
    }

    public LiveData<List<Beer>> getAllCategories() {

        allCategories=eBookShopRepository.getBeers();
        return allCategories;
    }



    public void addNewBook(Beer book){
        eBookShopRepository.insertBeer(book);
    }

    public void deleteBook(Beer book){
        eBookShopRepository.deleteBeer(book);
    }
}
