package com.fahimshahrierrasel.mycartoll.car;

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

import com.fahimshahrierrasel.mycartoll.R;
import com.fahimshahrierrasel.mycartoll.adapter.CarAdapter;
import com.fahimshahrierrasel.mycartoll.data.model.Car;
import com.fahimshahrierrasel.mycartoll.log.LogFragment;
import com.fahimshahrierrasel.mycartoll.log.LogPresenter;

import java.util.List;

public class CarFragment extends Fragment implements CarContract.View {
    private CarContract.Presenter carPresenter;
    /**
     * Android Views
     **/
    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerViewCars;
    private LogPresenter logPresenter;

    /**
     * Android Views
     **/

    public CarFragment() {
    }

    public static CarFragment newInstance() {
        return new CarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_car, container, false);

        bindViews(root);

        swipeContainer.setOnRefreshListener(() -> {
            carPresenter.start();
        });

        return root;
    }

    /**
     * Binds XML views
     * Call this function after layout is ready.
     **/
    private void bindViews(View rootView) {
        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        recyclerViewCars = rootView.findViewById(R.id.recyclerView_cars);
    }

    @Override
    public void populateDriverCars(List<Car> cars) {
        recyclerViewCars.setAdapter(new CarAdapter(cars, carPresenter::onCarClicked));
        recyclerViewCars.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void stopSwipeRefreshing() {
        swipeContainer.setRefreshing(false);
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
        carPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        carPresenter.destroy();
    }

    @Override
    public void setPresenter(CarContract.Presenter presenter) {
        carPresenter = presenter;
    }
}
