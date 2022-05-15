package com.fahimshahrierrasel.mycartoll.log;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.fahimshahrierrasel.mycartoll.BasePresenter;
import com.fahimshahrierrasel.mycartoll.BaseView;
import com.fahimshahrierrasel.mycartoll.data.model.Log;

import java.util.List;

public interface LogContract {
    interface Presenter extends BasePresenter {
        void getDriverLogs(int driverId);
        void getCarLogs(int carId);
    }

    interface View extends BaseView<LogContract.Presenter> {
        Bundle getArgumentsBundle();
        void populateLogs(List<Log> logs);
        void stopSwipeRefreshing();
    }
}
