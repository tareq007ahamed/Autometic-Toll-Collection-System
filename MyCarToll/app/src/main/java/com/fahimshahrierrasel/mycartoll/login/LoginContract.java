package com.fahimshahrierrasel.mycartoll.login;

import android.content.Context;
import android.widget.TextView;

import com.fahimshahrierrasel.mycartoll.BasePresenter;
import com.fahimshahrierrasel.mycartoll.BaseView;
import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.model.User;

public interface LoginContract {
    interface Presenter extends BasePresenter {
        void onLoginClicked(TextView emailTextView, TextView passwordTextView);
        void onRegisterClicked(Context context);
        void checkLoginInfo(String email, String password);
        void getUserInformation(int userId);
        void saveUserInformation(User user);
        void getDriverInformation(int userId);
        void saveDriverInformation(Driver driver);
        void makeAuthenticationComplete();
        void isAuthenticated();
    }

    interface View extends BaseView<LoginContract.Presenter> {
        void showLoginError(String msg);
        void goToMainActivity();
    }
}
