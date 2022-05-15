package com.fahimshahrierrasel.mycartoll.log;

import android.os.Bundle;

import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.model.Log;
import com.fahimshahrierrasel.mycartoll.data.source.api.ApiUtils;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.LogService;

import java.util.List;

import nouri.in.goodprefslib.GoodPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogPresenter implements LogContract.Presenter {
    private LogContract.View logView;
    private GoodPrefs goodPrefs;
    private Driver driver;

    public LogPresenter(LogContract.View logView){
        this.logView = logView;
        this.logView.setPresenter(this);
        goodPrefs = GoodPrefs.getInstance();
    }

    @Override
    public void getDriverLogs(int driverId) {
        LogService logService = ApiUtils.getLogService();
        Call<List<Log>> call = logService.getDriverLogs(driverId);

        call.enqueue(new Callback<List<Log>>() {
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                if(response.code() == 200){
                    List<Log> logs = response.body();
                    logView.populateLogs(logs);
                    logView.stopSwipeRefreshing();
                }
            }

            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getCarLogs(int carId) {
        LogService logService = ApiUtils.getLogService();
        Call<List<Log>> call = logService.getCarLogs(carId);

        call.enqueue(new Callback<List<Log>>() {
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                if(response.code() == 200){
                    List<Log> logs = response.body();
                    logView.populateLogs(logs);
                    logView.stopSwipeRefreshing();
                }
            }

            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {

            }
        });
    }


    @Override
    public void start() {
        Bundle bundle = logView.getArgumentsBundle();
        int id = bundle.getInt("ID");
        String type = bundle.getString("TYPE");
        if (type != null) {
            if(type.equals("CAR"))
                getCarLogs(id);
            else
                getDriverLogs(id);
        }
    }

    @Override
    public void destroy() {
        logView = null;
    }
}
