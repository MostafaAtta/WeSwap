package com.atta.weswap.presenters;

import com.atta.weswap.model.Subcategory;

import java.util.ArrayList;

public interface AdsContract {

    interface View{

        void showMessage(String error);

        void showRecyclerView(ArrayList<Subcategory> subcategories);

    }

    interface Presenter{
        void getSubcategories(int catId) ;

    }
}
