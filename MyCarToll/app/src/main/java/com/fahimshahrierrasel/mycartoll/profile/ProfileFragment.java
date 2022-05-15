package com.fahimshahrierrasel.mycartoll.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fahimshahrierrasel.mycartoll.R;
import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.data.model.User;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    private ProfileContract.Presenter profilePresenter;
    /**
     * Android Views
     **/
    TextView textViewName;
    TextView textViewEmail;
    TextView textViewLicense;
    TextView textViewBalance;
    TextView textViewAddress;

    /**
     * Android Views
     **/

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        bindViews(root);
        return root;
    }

    /**
     * Binds XML views
     * Call this function after layout is ready.
     **/
    private void bindViews(View rootView) {
        textViewName = rootView.findViewById(R.id.textView_name);
        textViewEmail = rootView.findViewById(R.id.textView_email);
        textViewLicense = rootView.findViewById(R.id.textView_license);
        textViewBalance = rootView.findViewById(R.id.textView_balance);
        textViewAddress = rootView.findViewById(R.id.textView_address);
    }


    @Override
    public void populateUserInfo(User user) {
        textViewEmail.setText(user.getEmail());
    }

    @Override
    public void populateDriverInfo(Driver driver) {
        textViewName.setText(driver.getName());
        textViewLicense.setText(driver.getLicenseNo());
        textViewAddress.setText(driver.getAddress());
    }

    @Override
    public void populateDriverBalance(String balance) {
        textViewBalance.setText(balance);
    }

    @Override
    public void onResume() {
        super.onResume();
        profilePresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profilePresenter.destroy();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        profilePresenter = presenter;
    }
}
