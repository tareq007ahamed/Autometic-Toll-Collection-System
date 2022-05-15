package com.fahimshahrierrasel.mycartoll.data.source.api.service;

import com.fahimshahrierrasel.mycartoll.data.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarService {
    @GET("driver/{driver_id}/cars")
    Call<List<Car>> getDriverCars(@Path("driver_id") int driverId);
}
