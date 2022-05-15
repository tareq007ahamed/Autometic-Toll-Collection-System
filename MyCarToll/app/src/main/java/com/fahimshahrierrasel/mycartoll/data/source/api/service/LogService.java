package com.fahimshahrierrasel.mycartoll.data.source.api.service;

import com.fahimshahrierrasel.mycartoll.data.model.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LogService {
    @GET("driver/car/{car_id}/logs")
    Call<List<Log>> getCarLogs(@Path("car_id") int carId);

    @GET("driver/{driver_id}/logs")
    Call<List<Log>> getDriverLogs(@Path("driver_id") int driverId);
}
