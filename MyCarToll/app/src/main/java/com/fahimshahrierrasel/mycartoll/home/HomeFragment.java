package com.fahimshahrierrasel.mycartoll.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fahimshahrierrasel.mycartoll.R;
import com.fahimshahrierrasel.mycartoll.adapter.CarAdapter;
import com.fahimshahrierrasel.mycartoll.data.model.Car;
import com.fahimshahrierrasel.mycartoll.data.model.Driver;
import com.fahimshahrierrasel.mycartoll.log.LogFragment;
import com.fahimshahrierrasel.mycartoll.log.LogPresenter;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {
    private HomeContract.Presenter homePresenter;

    /**
     * Android Views
     **/
    SwipeRefreshLayout swipeContainer;
    TextView textViewDriverName;
    TextView textViewLicense;
    TextView textViewBalance;
    RecyclerView recyclerViewCars;
    private LogPresenter logPresenter;

    /**
     * Android Views
     **/

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        bindViews(root);

        swipeContainer.setOnRefreshListener(() -> {
            homePresenter.start();
        });

        return root;
    }

    /**
     * Binds XML views
     * Call this function after layout is ready.
     **/
    private void bindViews(View rootView) {
        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        textViewDriverName = rootView.findViewById(R.id.textView_driver_name);
        textViewLicense = rootView.findViewById(R.id.textView_license);
        textViewBalance = rootView.findViewById(R.id.textView_balance);
        recyclerViewCars = rootView.findViewById(R.id.recyclerView_cars);
    }

    @Override
    public void populateDriverInfo(Driver driver) {
        textViewDriverName.setText(driver.getName());
        textViewLicense.setText(driver.getLicenseNo());
    }

    @Override
    public void setDriverCurrentBalance(String balance) {
        textViewBalance.setText(balance);
    }

    @Override
    public void stopSwipeRefreshing() {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void populateDriverCars(List<Car> cars) {
        recyclerViewCars.setAdapter(new CarAdapter(cars, homePresenter::onCarClicked));
        recyclerViewCars.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showCarLog(Car car) {

        LogFragment logFragment = LogFragment.newInstance(car.getId(), "CAR");

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, logFragment)
                .addToBackStack(null)
                .commit();

        logPresenter = new LogPresenter(logFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        homePresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.destroy();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        homePresenter = presenter;
    }
}
