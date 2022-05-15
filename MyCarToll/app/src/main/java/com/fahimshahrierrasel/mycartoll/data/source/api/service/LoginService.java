package com.fahimshahrierrasel.mycartoll.data.source.api.service;

import com.fahimshahrierrasel.mycartoll.data.model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("/login")
    Call<Login> loginUser(@Field("email") String email, @Field("password") String password);
}
