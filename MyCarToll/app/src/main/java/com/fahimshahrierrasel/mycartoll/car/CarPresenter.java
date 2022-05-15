package com.fahimshahrierrasel.mycartoll.car;

import com.fahimshahrierrasel.mycartoll.data.model.Car;
import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.source.api.ApiUtils;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.CarService;

import java.util.List;

import nouri.in.goodprefslib.GoodPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarPresenter implements CarContract.Presenter {
    private CarContract.View carView;
    GoodPrefs goodPrefs;
    private Driver driver;

    public CarPresenter(CarContract.View carView) {
        this.carView = carView;
        this.carView.setPresenter(this);
        goodPrefs = GoodPrefs.getInstance();
        getDriverInfo();
    }

    @Override
    public void getDriverInfo() {
        driver = goodPrefs.getObject("driver", Driver.class);
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
                    carView.populateDriverCars(cars);
                }
                carView.stopSwipeRefreshing();
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                carView.stopSwipeRefreshing();
            }
        });
    }

    @Override
    public void onCarClicked(Car car) {
        carView.showCarLog(car);
    }

    @Override
    public void start() {
        getDriverCars();
    }

    @Override
    public void destroy() {
        carView = null;
    }
}
