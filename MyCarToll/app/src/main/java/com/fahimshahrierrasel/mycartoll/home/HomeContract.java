package com.fahimshahrierrasel.mycartoll.home;

import com.fahimshahrierrasel.mycartoll.BasePresenter;
import com.fahimshahrierrasel.mycartoll.BaseView;
import com.fahimshahrierrasel.mycartoll.data.model.Car;
import com.fahimshahrierrasel.mycartoll.data.model.Driver;

import java.util.List;

public interface HomeContract {
    interface Presenter extends BasePresenter {
        void getDriverInfo();
        void getUserInfo();
        void setCurrentBalance();
        void getDriverCars();
        void onCarClicked(Car car);
    }

    interface View extends BaseView<HomeContract.Presenter> {
        void populateDriverInfo(Driver driver);
        void setDriverCurrentBalance(String balance);
        void stopSwipeRefreshing();
        void populateDriverCars(List<Car> cars);
        void showCarLog(Car car);
    }
}
