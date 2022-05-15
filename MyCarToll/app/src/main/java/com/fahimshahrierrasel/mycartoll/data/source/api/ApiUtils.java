package com.fahimshahrierrasel.mycartoll.data.source.api;

import com.fahimshahrierrasel.mycartoll.MyCarTollApp;
import com.fahimshahrierrasel.mycartoll.R;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.CarService;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.DriverService;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.LogService;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.LoginService;
import com.fahimshahrierrasel.mycartoll.data.source.api.service.UserService;

public class ApiUtils {
    private static final String BASE_URL = MyCarTollApp.getRes().getString(R.string.api_root_dev);

    private ApiUtils() {
    }

    public static LoginService getLoginService() {
        return RetrofitClient.getClient(BASE_URL).create(LoginService.class);
    }

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

    public static DriverService getDriverService() {
        return RetrofitClient.getClient(BASE_URL).create(DriverService.class);
    }

    public static CarService getCarService() {
        return RetrofitClient.getClient(BASE_URL).create(CarService.class);
    }

    public static LogService getLogService() {
        return RetrofitClient.getClient(BASE_URL).create(LogService.class);
    }

}
