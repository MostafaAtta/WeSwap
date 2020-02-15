package com.atta.weswap.presenters;

import com.atta.weswap.model.User;

public interface RegisterContract {

    interface View{

        void showMessage(String error);

        void showViewError(String view, String oldView, String error);

        void navigateToMain();


        boolean validate(String name, String email, String password, String passwordConfirm, String phone);

    }

    interface Presenter{

        void register(User user);



    }
}
