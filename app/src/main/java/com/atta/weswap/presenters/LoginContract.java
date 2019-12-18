package com.atta.weswap.presenters;

public interface LoginContract {

    interface View{

        void showError(String error);

        void showViewError(String view, String error);

        void showMessage();

        void navigateToMain();

        void navigateToRegister();

        boolean validate(String email, String password);
    }

    interface Presenter{

        void login(String email, String password);

    }
}
