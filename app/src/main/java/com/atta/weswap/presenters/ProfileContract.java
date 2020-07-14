package com.atta.weswap.presenters;

import com.atta.weswap.model.User;

public interface ProfileContract {

    interface View{

        void showProfile(User user);

        void showMessage(String message);

        void moveToMain();

        void hideProgress();
    }

    interface Presenter{

        void getProfile(int userId) ;

        void updateProfile(User user) ;

    }
}
