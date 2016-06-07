package com.example.eugene.replacing_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene on 6/4/2016.
 */
public class Fragment3 extends Fragment implements SearchView.OnQueryTextListener {

    //Thành phố
    public RecyclerView mRecyclerView;

    public RecyclerView.Adapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public RecyclerView.Adapter mAdapter;
    public LinearLayoutManager mLayoutManager;
    private ArrayList<ItemDataCity> myDataset = new ArrayList<ItemDataCity>();


    public Fragment3() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3, container, false);
        v.getTag();
        ItemDataCity itemDataCity = new ItemDataCity();
        ItemDataCity itemDataCity1 = new ItemDataCity("Hai Phong", "30-35", R.drawable.rainy1);
        ItemDataCity itemDataCity2 = new ItemDataCity("Khanh Hoa", "30-35", R.drawable.snowy1);

        myDataset.add(itemDataCity);
        myDataset.add(itemDataCity);
        myDataset.add(itemDataCity);
        myDataset.add(itemDataCity);
        myDataset.add(itemDataCity2);
        myDataset.add(itemDataCity1);
        myDataset.add(itemDataCity1);
        myDataset.add(itemDataCity2);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CityAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever

                        TextView t = (TextView)view.findViewById(R.id.tvCityName);
                        String s = t.getText().toString();
                        Toast.makeText(getContext(),"RVClicked at position " + position + "City: "  + s,Toast.LENGTH_SHORT).show();
                        if(s == "Hà Nội") {
                            ((MainActivity)getActivity()).changeCity(1581129);
                        }
                        else {
                            ((MainActivity)getActivity()).changeCity(1581129);
                        }
                    }
                })
        );
        return v;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItem changeCity = menu.findItem(R.id.change_city);
        searchItem.setVisible(true);
        changeCity.setVisible(false);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);
//        searchItem.expandActionView();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(getContext(), "Fragment", Toast.LENGTH_SHORT).show();
        final ArrayList<ItemDataCity> filteredModelList = filter(myDataset, newText);
        ((CityAdapter)mAdapter).setFilter(filteredModelList);
        mAdapter = new CityAdapter(filteredModelList);
        mRecyclerView.setAdapter(mAdapter);
        return true;
    }

    private ArrayList<ItemDataCity> filter(List<ItemDataCity> models, String query) {
        query = query.toLowerCase();

        final ArrayList<ItemDataCity> filteredModelList = new ArrayList<>();
        for (ItemDataCity model : models) {
            final String text = model.getCityName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


}

