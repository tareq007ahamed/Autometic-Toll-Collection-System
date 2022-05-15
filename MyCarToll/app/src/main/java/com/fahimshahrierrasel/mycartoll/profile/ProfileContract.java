package com.fahimshahrierrasel.mycartoll.profile;

import com.fahimshahrierrasel.mycartoll.BasePresenter;
import com.fahimshahrierrasel.mycartoll.BaseView;
import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.model.User;

public interface ProfileContract {
    interface Presenter extends BasePresenter {
        void getUserInfo();
        void getDriverInfo();
        void setDriverBalance();
    }

    interface View extends BaseView<ProfileContract.Presenter> {
        void populateUserInfo(User user);
        void populateDriverInfo(Driver driver);
        void populateDriverBalance(String balance);
    }
}
