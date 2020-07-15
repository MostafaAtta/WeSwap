package com.atta.weswap.presenters;

import com.atta.weswap.model.Ad;

import java.util.ArrayList;

public interface MyAdsContract {

    interface View{

        void showMessage(String error);

        void showRecyclerView(ArrayList<Ad> ads);

    }

    interface Presenter{
        void getMyAds(int userId) ;

    }
}
