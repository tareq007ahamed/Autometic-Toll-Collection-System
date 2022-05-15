package com.fahimshahrierrasel.mycartoll.car;

import com.fahimshahrierrasel.mycartoll.BasePresenter;
import com.fahimshahrierrasel.mycartoll.BaseView;
import com.fahimshahrierrasel.mycartoll.data.model.Car;

import java.util.List;

public interface CarContract {
    interface Presenter extends BasePresenter {
        void getDriverInfo();
        void getDriverCars();
        void onCarClicked(Car car);
    }
    interface View extends BaseView<CarContract.Presenter> {
        void populateDriverCars(List<Car> cars);
        void stopSwipeRefreshing();
        void showCarLog(Car car);
    }
}
