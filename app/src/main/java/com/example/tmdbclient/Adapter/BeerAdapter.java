package com.example.tmdbclient.Adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.tmdbclient.Beer;
import com.example.tmdbclient.Model.BeerStyle;
import com.example.tmdbclient.R;
import com.example.tmdbclient.databinding.BeerListItemBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.MovieViewHolder> implements Filterable {


    private Context context;
    private ArrayList<Beer> beerList;
    private ArrayList<Beer> filteredData;
    private ItemFilter mFilter = new ItemFilter();
    public static FilterBy filterBy=FilterBy.SearchKeyword;


    public BeerAdapter(Context context, ArrayList<Beer> beerList) {
        this.context = context;
        this.filteredData=beerList;
        this.beerList = beerList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BeerListItemBinding movieListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.beer_list_item,parent,false);
        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Beer beer=filteredData.get(position);
        holder.movieListItemBinding.setBeer(beer);
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private BeerListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull BeerListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding=movieListItemBinding;
//            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position=getAdapterPosition();
//                    if(position!=RecyclerView.NO_POSITION){
//                        Beer selectedMovie= beerList.get(position);
//
//                        Intent intent=new Intent(context, MovieActivity.class);
//                        intent.putExtra("movie",selectedMovie);
//                        context.startActivity(intent);
//                    }
//                }
//            });


        }

        
    }

    public void sortAssending(){
        Collections.sort(filteredData, new Comparator<Beer>() {
            @Override
            public int compare(Beer o1, Beer o2) {

                return Float.compare(Float.parseFloat(o1.getAbv()),Float.parseFloat(o2.getAbv()));
            }
        });
        notifyDataSetChanged();
    }

    public void sortDescending(){
        Collections.sort(filteredData, new Comparator<Beer>() {
            @Override
            public int compare(Beer o1, Beer o2) {
                return Float.compare(Float.parseFloat(o2.getAbv()),Float.parseFloat(o1.getAbv()));
            }
        });
        notifyDataSetChanged();
    }


    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Beer> list = beerList;

            int count = list.size();
            final ArrayList<Beer> nlist = new ArrayList<Beer>(count);

            Beer filterableString ;

            if(filterBy == FilterBy.SearchKeyword) {
                for (int i = 0; i < count; i++) {
                    filterableString = list.get(i);
                    if (filterableString.getName().toLowerCase().contains(filterString)) {
                        nlist.add(filterableString);
                    }
                }
            }else{
                for (int i = 0; i < count; i++) {
                    filterableString = list.get(i);
                    if (filterableString.getStyle().toLowerCase().contains(filterString)) {
                        nlist.add(filterableString);
                    }
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Beer>) results.values;
            notifyDataSetChanged();
        }

    }
}
