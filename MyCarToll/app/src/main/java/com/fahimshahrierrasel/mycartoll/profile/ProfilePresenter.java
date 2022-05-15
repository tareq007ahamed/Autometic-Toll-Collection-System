package com.fahimshahrierrasel.mycartoll.profile;

import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.model.User;
import com.fahimshahrierrasel.mycartoll.data.source.api.ApiUtils;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.DriverService;

import nouri.in.goodprefslib.GoodPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View profileView;
    GoodPrefs goodPrefs;
    User user;
    Driver driver;

    public ProfilePresenter(ProfileContract.View profileView) {
        this.profileView = profileView;
        this.profileView.setPresenter(this);
        goodPrefs = GoodPrefs.getInstance();
        getUserInfo();
        getDriverInfo();
    }

    @Override
    public void getUserInfo() {
        user = goodPrefs.getObject("user", User.class);
    }

    @Override
    public void getDriverInfo() {
        driver = goodPrefs.getObject("driver", Driver.class);
    }

    @Override
    public void setDriverBalance() {
        DriverService driverService = ApiUtils.getDriverService();
        Call<Driver> call = driverService.getDriver(user.getId());

        call.enqueue(new Callback<Driver>() {
            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                if (response.code() == 200) {
                    Driver driver = response.body();
                    if (driver != null) {
                        profileView.populateDriverBalance("BDT " + driver.getBalance());

                    }
                }
            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {
            }
        });
    }

    @Override
    public void start() {
        profileView.populateDriverInfo(driver);
        profileView.populateUserInfo(user);
        setDriverBalance();
    }

    @Override
    public void destroy() {
        profileView = null;
    }
}
