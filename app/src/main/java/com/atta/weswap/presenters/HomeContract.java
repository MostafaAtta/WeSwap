package com.atta.weswap.presenters;

import com.atta.weswap.model.Category;

import java.util.ArrayList;

public interface HomeContract {

    interface View{

        void showMessage(String error);

        void showRecyclerView(ArrayList<Category> categories);

    }

    interface Presenter{
        void getCategories() ;

    }
}
