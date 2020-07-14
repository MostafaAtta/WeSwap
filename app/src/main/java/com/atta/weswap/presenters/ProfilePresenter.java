package com.atta.weswap.presenters;

import android.content.Context;

import com.atta.weswap.model.APIClient;
import com.atta.weswap.model.Profile;
import com.atta.weswap.model.Result;
import com.atta.weswap.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View mView;

    private Context mContext;

    public ProfilePresenter(ProfileContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getProfile(int userId) {

        Call<Profile> call = APIClient.getInstance().getApi().getProfile(userId);

        //calling the api
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

                if (response.body() != null){
                    if (!response.body().getError()){

                        User user = response.body().getUser();

                        mView.showProfile(user);

                    }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void updateProfile(User user) {
        //defining the call
        Call<Result> call = APIClient.getInstance().getApi().updateProfile(
                user.getId(),
                user.getName(),
                user.getPhone()
        );

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                mView.hideProgress();
                //displaying the message from the response as toast
                if (response.body() != null) {


                    mView.showMessage(response.body().getMessage());


                    if (!response.body().getError()){


                        mView.moveToMain();
                    }


                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                mView.hideProgress();
                mView.showMessage(t.getMessage());
            }
        });
    }


}
