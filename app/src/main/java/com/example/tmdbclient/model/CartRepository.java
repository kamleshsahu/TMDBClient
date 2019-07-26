package com.example.tmdbclient.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.tmdbclient.Beer;

import java.util.List;

public class CartRepository {


    private CartDAO cartDAO;
    private LiveData<List<Beer>> beerAdded;


    public CartRepository(Application application) {

        CartDatabase booksDatabase=CartDatabase.getInstance(application);
        cartDAO=booksDatabase.bookDAO();
    }


    public LiveData<List<Beer>> getBeers() {
        return cartDAO.getAllBeers();
    }

    public void insertBeer(Beer beer){

        new InsertCategoryAsyncTask(cartDAO).execute(beer);
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Beer,Void,Void> {
        private CartDAO categoryDAO;

        public InsertCategoryAsyncTask(CartDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Beer... categories) {
            try {
                categoryDAO.insert(categories[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }



    public void deleteBeer(Beer category){

        new DeleteCategoryAsyncTask(cartDAO).execute(category);
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Beer,Void,Void>{
        private CartDAO categoryDAO;

        public DeleteCategoryAsyncTask(CartDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Beer... categories) {

            categoryDAO.delete(categories[0]);
            return null;
        }
    }

}
