package com.atta.weswap.presenters;

import android.content.Context;

import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.Ad;
import com.atta.weswap.model.AdsResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdsPresenter implements MyAdsContract.Presenter {

    private MyAdsContract.View mView;

    private Context mContext;

    public MyAdsPresenter(MyAdsContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getMyAds(int userId) {

        //defining the call
        Call<AdsResult> call = APIClient.getInstance().getApi().getMyAds(userId);

        //calling the api
        call.enqueue(new Callback<AdsResult>() {
            @Override
            public void onResponse(Call<AdsResult> call, Response<AdsResult> response) {

                if (response.body() != null){
                    if (response.body().getAds() != null){

                        ArrayList<Ad> ads = response.body().getAds();

                        if (ads.size() > 0){

                            mView.showRecyclerView(ads);
                        }else {
                            mView.showMessage("No Ads");
                        }

                    }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<AdsResult> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }


}
