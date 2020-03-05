package com.atta.weswap.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atta.weswap.R;
import com.atta.weswap.model.CategoriesAdapter;
import com.atta.weswap.model.Category;
import com.atta.weswap.presenters.HomeContract;
import com.atta.weswap.presenters.HomePresenter;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeContract.View {


    private HomePresenter homePresenter;

    private RecyclerView recyclerView;

    private CategoriesAdapter myAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homePresenter = new HomePresenter(this, getContext());

        homePresenter.getCategories();

        recyclerView = root.findViewById(R.id.recycler);

        return root;
    }

    @Override
    public void showMessage(String error) {

        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRecyclerView(ArrayList<Category> categories) {

        myAdapter = new CategoriesAdapter(categories, getContext());
        //DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        //recyclerView.addItemDecoration(decoration);

        recyclerView.setAdapter(myAdapter);

    }
}