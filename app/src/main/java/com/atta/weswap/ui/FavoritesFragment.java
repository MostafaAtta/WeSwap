package com.atta.weswap.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.weswap.R;
import com.atta.weswap.model.Ad;
import com.atta.weswap.model.SessionManager;
import com.atta.weswap.model.adapters.AdsAdapter;
import com.atta.weswap.presenters.FavoritesContract;
import com.atta.weswap.presenters.FavoritesPresenter;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment implements FavoritesContract.View {


    private View root;

    private RecyclerView recyclerView;

    private AdsAdapter myAdapter;

    private FavoritesPresenter favoritesPresenter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = root.findViewById(R.id.ads_recycler);

        favoritesPresenter = new FavoritesPresenter(this, getContext());
        favoritesPresenter.getFavAds(SessionManager.getInstance(getContext()).getUserId());

        return root;
    }

    @Override
    public void showMessage(String error) {

        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRecyclerView(ArrayList<Ad> ads ) {
        myAdapter = new AdsAdapter(ads, getContext(), getActivity(), "fav");

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(decoration);

        recyclerView.setAdapter(myAdapter);
    }
}