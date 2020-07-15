package com.atta.weswap.presenters;

import com.atta.weswap.model.Ad;

import java.util.ArrayList;

public interface FavoritesContract {

    interface View{

        void showMessage(String error);

        void showRecyclerView(ArrayList<Ad> ads);

    }

    interface Presenter{
        void getFavAds(int userId) ;

    }
}
