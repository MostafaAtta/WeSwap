package com.atta.weswap.presenters;

import android.content.Context;

import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.CategoriesResult;
import com.atta.weswap.model.Category;
import com.atta.weswap.model.SubcategoriesResult;
import com.atta.weswap.model.Subcategory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAdPresenter implements NewAdContract.Presenter{

    private NewAdContract.View mView;

    private Context mContext;

    public NewAdPresenter(NewAdContract.View view, Context context) {

        mView = view;

        mContext = context;
    }


    @Override
    public void getCategories() {



        Call<CategoriesResult> call = APIClient.getInstance().getApi().getCategories();


        call.enqueue(new Callback<CategoriesResult>() {
            @Override
            public void onResponse(Call<CategoriesResult> call, Response<CategoriesResult> response) {


                if (response.body() != null){
                    if (response.body().getCategories() != null){

                        ArrayList<Category> categories = response.body().getCategories();

                        if (categories.size() > 0){

                            mView.showCategoriesDialog(categories);
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


                //mView.dismissProgressDialog();

                mView.showMessage(t.getMessage());



            }
        });

    }


    @Override
    public void getSubCategories(int catId) {



        Call<SubcategoriesResult> call = APIClient.getInstance().getApi().getSubcategories(catId);


        call.enqueue(new Callback<SubcategoriesResult>() {
            @Override
            public void onResponse(Call<SubcategoriesResult> call, Response<SubcategoriesResult> response) {


                if (response.body() != null){
                    if (response.body().getSubcategories() != null){

                        ArrayList<Subcategory> subcategories = response.body().getSubcategories();

                        if (subcategories.size() > 0){

                            mView.showSubcategoriesDialog(subcategories);
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


                //mView.dismissProgressDialog();

                mView.showMessage(t.getMessage());



            }
        });

    }

}
