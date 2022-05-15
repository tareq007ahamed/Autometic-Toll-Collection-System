package com.fahimshahrierrasel.mycartoll.home;

import com.fahimshahrierrasel.mycartoll.data.model.Car;
import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.model.User;
import com.fahimshahrierrasel.mycartoll.data.source.api.ApiUtils;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.CarService;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.DriverService;

import java.util.List;

import nouri.in.goodprefslib.GoodPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View homeView;
    private GoodPrefs goodPrefs;
    private Driver driver;
    private User user;

    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
        this.homeView.setPresenter(this);
        goodPrefs = GoodPrefs.getInstance();
        getUserInfo();
        getDriverInfo();
    }


    @Override
    public void getDriverInfo() {
        driver = goodPrefs.getObject("driver", Driver.class);

    }

    @Override
    public void getUserInfo() {
        user = goodPrefs.getObject("user", User.class);
    }

    @Override
    public void setCurrentBalance() {
        DriverService driverService = ApiUtils.getDriverService();
        Call<Driver> call = driverService.getDriver(user.getId());

        call.enqueue(new Callback<Driver>() {
            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                if (response.code() == 200) {
                    Driver driver = response.body();
                    if (driver != null) {
                        homeView.setDriverCurrentBalance("BDT " + driver.getBalance());

                    }
                }
                homeView.stopSwipeRefreshing();
            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {
                homeView.stopSwipeRefreshing();
            }
        });
    }

    @Override
    public void getDriverCars() {
        CarService carService = ApiUtils.getCarService();
        Call<List<Car>> call = carService.getDriverCars(driver.getId());

        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if(response.code() == 200) {
                    List<Car> cars = response.body();
                    homeView.populateDriverCars(cars);
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCarClicked(Car car) {
        homeView.showCarLog(car);
    }

    @Override
    public void start() {
        homeView.populateDriverInfo(driver);
        setCurrentBalance();
        getDriverCars();
    }

    @Override
    public void destroy() {
        homeView = null;
    }
}
