package com.fahimshahrierrasel.mycartoll.data.source.api.service;

import com.fahimshahrierrasel.mycartoll.data.model.Driver;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DriverService {
    @GET("driver/{user_id}")
    Call<Driver> getDriver(@Path("user_id") int userId);
}
