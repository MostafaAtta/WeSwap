package com.atta.weswap.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.atta.weswap.R;


public class SearchFragment extends Fragment {

    View root;

    String keyWord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_search, container, false);

        keyWord = SearchFragmentArgs.fromBundle(getArguments()).getSearchText();

        getActivity().setTitle(keyWord);

        Toast.makeText(getContext(), keyWord, Toast.LENGTH_SHORT).show();

        return root;
    }
}
