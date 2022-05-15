package com.fahimshahrierrasel.mycartoll.log;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fahimshahrierrasel.mycartoll.R;
import com.fahimshahrierrasel.mycartoll.adapter.LogAdapter;
import com.fahimshahrierrasel.mycartoll.data.model.Log;

import java.util.List;

public class LogFragment extends Fragment implements LogContract.View {
    private LogContract.Presenter logPresenter;

    /**
     * Android Views
     **/
    android.support.v4.widget.SwipeRefreshLayout swipeContainer;
    android.support.v7.widget.RecyclerView recyclerViewLogs;

    /**
     * Android Views
     **/

    public LogFragment() {
    }

    public static LogFragment newInstance(int id, String type) {
        LogFragment logFragment = new LogFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("ID", id);
        arguments.putString("TYPE", type);
        logFragment.setArguments(arguments);
        return logFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_log, container, false);

        bindViews(root);

        swipeContainer.setOnRefreshListener(() -> {
            logPresenter.start();
        });

        return root;
    }

    /**
     * Binds XML views
     * Call this function after layout is ready.
     **/
    private void bindViews(View rootView) {
        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        recyclerViewLogs = rootView.findViewById(R.id.recyclerView_logs);
    }


    @Override
    public Bundle getArgumentsBundle() {
        return getArguments();
    }

    @Override
    public void populateLogs(List<Log> logs) {
        recyclerViewLogs.setAdapter(new LogAdapter(logs));
        recyclerViewLogs.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void stopSwipeRefreshing() {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        logPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logPresenter.destroy();
    }

    @Override
    public void setPresenter(LogContract.Presenter presenter) {
        logPresenter = presenter;
    }
}
