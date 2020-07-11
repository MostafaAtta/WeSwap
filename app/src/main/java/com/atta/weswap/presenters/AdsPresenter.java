package com.atta.weswap.presenters;

import android.content.Context;

import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.SubcategoriesResult;
import com.atta.weswap.model.Subcategory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsPresenter implements AdsContract.Presenter {

    private AdsContract.View mView;

    private Context mContext;

    public AdsPresenter(AdsContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getSubcategories(int catId) {

        //defining the call
        Call<SubcategoriesResult> call = APIClient.getInstance().getApi().getSubcategories(catId);

        //calling the api
        call.enqueue(new Callback<SubcategoriesResult>() {
            @Override
            public void onResponse(Call<SubcategoriesResult> call, Response<SubcategoriesResult> response) {

                if (response.body() != null){
                    if (response.body().getSubcategories() != null){

                        ArrayList<Subcategory> subcategories = response.body().getSubcategories();

                        if (subcategories.size() > 0){

                            mView.showRecyclerView(subcategories);
                        }else {
                            mView.showMessage("Categories not found , try again later");
                        }

                    }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<SubcategoriesResult> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }


}
