package com.atta.weswap.presenters;

import android.content.Context;

import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.Result;
import com.atta.weswap.model.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mView;

    private Context mContext;

    public LoginPresenter(LoginContract.View view, Context context) {

        mView = view;

        mContext = context;
    }


    @Override
    public void login(String email, String password) {



        Call<Result> call = APIClient.getInstance().getApi().userLogin(email, password);


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {


                if (response.body() != null) {
                    //mView.dismissProgressDialog();
                    if (!response.body().getError()) {
                        mView.showMessage();
                        SessionManager.getInstance(mContext).createLoginSession(response.body().getUser());
                        mView.navigateToMain();
                    } else {

                        mView.showError(response.body().getMessage());
                    }

                }


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {


                //mView.dismissProgressDialog();

                mView.showError(t.getMessage());



            }
        });

    }

}
