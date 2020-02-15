package com.atta.weswap.presenters;

import android.content.Context;

import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.Result;
import com.atta.weswap.model.SessionManager;
import com.atta.weswap.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.Presenter{

    private RegisterContract.View mView;

    private Context mContext;


    public RegisterPresenter(RegisterContract.View view, Context context) {

        mView = view;

        mContext = context;
    }

    @Override
    public void register(User user) {

        //defining the call
        Call<Result> call = APIClient.getInstance().getApi().createUser(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone()
        );

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //hiding progress dialog


                //displaying the message from the response as toast
                mView.showMessage(response.body().getMessage());
                //if there is no error
                if (!response.body().getError()) {
                    //starting profile activity
                    SessionManager.getInstance(mContext).createLoginSession(response.body().getUser());
                    mView.navigateToMain();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }

}
