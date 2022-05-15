package com.fahimshahrierrasel.mycartoll.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.model.Login;
import com.fahimshahrierrasel.mycartoll.data.model.User;
import com.fahimshahrierrasel.mycartoll.data.source.api.ApiUtils;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.DriverService;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.LoginService;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.UserService;

import nouri.in.goodprefslib.GoodPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View loginView;
    private GoodPrefs goodPrefs;

    LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        this.loginView.setPresenter(this);
        this.goodPrefs = GoodPrefs.getInstance();
    }

    @Override
    public void onLoginClicked(TextView emailTextView, TextView passwordTextView) {
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();

        if (isValidEmail(email) && !TextUtils.isEmpty(password))
            checkLoginInfo(email, password);
        else
            loginView.showLoginError("Please insert valid email and password");

    }

    private static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    public void onRegisterClicked(Context context) {
        Toast.makeText(context, "No Registration Option Now", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkLoginInfo(String email, String password) {
        LoginService loginService = ApiUtils.getLoginService();
        Call<Login> call = loginService.loginUser(email, password);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.code() == 200) {
                    Login loginInfo = response.body();
                    if (loginInfo != null) {
                        if (loginInfo.getStatus().equals("authenticated")) {
                            getUserInformation(loginInfo.getId());
                            getDriverInformation(loginInfo.getId());
                            makeAuthenticationComplete();
                        } else
                            loginView.showLoginError("User not found");
                    } else {
                        loginView.showLoginError("User not found");
                    }
                } else {
                    loginView.showLoginError("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                loginView.showLoginError("Something went wrong");
            }
        });
    }

    @Override
    public void getUserInformation(int userId) {
        UserService userService = ApiUtils.getUserService();
        Call<User> call = userService.getUser(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    if (user != null) {
                        saveUserInformation(user);
                    } else {
                        loginView.showLoginError("User not found");
                    }
                } else {
                    loginView.showLoginError("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginView.showLoginError("Something went wrong");
            }
        });
    }

    @Override
    public void saveUserInformation(User user) {
        goodPrefs.saveObject("user", user);
    }

    @Override
    public void getDriverInformation(int userId) {
        DriverService driverService = ApiUtils.getDriverService();
        Call<Driver> call = driverService.getDriver(userId);

        call.enqueue(new Callback<Driver>() {
            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                if (response.code() == 200) {
                    Driver driver = response.body();
                    if (driver != null) {
                        saveDriverInformation(driver);
                    } else {
                        loginView.showLoginError("Driver not found");
                    }
                } else {
                    loginView.showLoginError("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {
                loginView.showLoginError("Something went wrong");
            }
        });
    }

    @Override
    public void saveDriverInformation(Driver driver) {
        goodPrefs.saveObject("driver", driver);
    }

    @Override
    public void makeAuthenticationComplete() {
        goodPrefs.saveBoolean("authenticated", true);
        loginView.goToMainActivity();
    }

    @Override
    public void isAuthenticated() {
        boolean authenticated = goodPrefs.getBoolean("authenticated", false);
        if (authenticated)
            loginView.goToMainActivity();
    }

    @Override
    public void start() {
    }

    @Override
    public void destroy() {
        loginView = null;
    }
}
