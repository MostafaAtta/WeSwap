package com.atta.weswap.presenters;

import android.content.Context;

import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.CategoriesResult;
import com.atta.weswap.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    private Context mContext;

    public HomePresenter(HomeContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getCategories() {

        //defining the call
        Call<CategoriesResult> call = APIClient.getInstance().getApi().getCategories();

        //calling the api
        call.enqueue(new Callback<CategoriesResult>() {
            @Override
            public void onResponse(Call<CategoriesResult> call, Response<CategoriesResult> response) {

                if (response.body() != null){
                    if (response.body().getCategories() != null){

                        ArrayList<Category> categories = response.body().getCategories();

                        if (categories.size() > 0){

                            mView.showRecyclerView(categories);
                        }else {
                            mView.showMessage("Categories not found , try again later");
                        }

                    }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<CategoriesResult> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }


}
