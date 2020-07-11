package com.atta.weswap.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.weswap.R;
import com.atta.weswap.model.adapters.SubcategoriesAdapter;
import com.atta.weswap.model.Subcategory;
import com.atta.weswap.presenters.SubcategoriesContract;

import java.util.ArrayList;


public class AdsFragment extends Fragment implements SubcategoriesContract.View {

    private View root;

    private RecyclerView recyclerView;

    private SubcategoriesAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_ads, container, false);

        recyclerView = root.findViewById(R.id.ads_recycler);
/*
        int catId = SubcategoriesFragmentArgs.fromBundle(getArguments()).getCatId();

        SubcategoriesPresenter subcategoriesPresenter = new SubcategoriesPresenter(this, getContext());
        subcategoriesPresenter.getSubcategories(catId);*/

        return root;
    }

    @Override
    public void showMessage(String error) {

        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRecyclerView(ArrayList<Subcategory> subcategories) {

        myAdapter = new SubcategoriesAdapter(subcategories, getContext(), getActivity());

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(decoration);

        recyclerView.setAdapter(myAdapter);
    }
}
