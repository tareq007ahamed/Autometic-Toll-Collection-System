package com.fahimshahrierrasel.mycartoll.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fahimshahrierrasel.mycartoll.R;
import com.fahimshahrierrasel.mycartoll.data.model.Car;
import com.fahimshahrierrasel.mycartoll.data.model.Log;

import java.util.List;
import java.util.Locale;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {
    private List<Log> logs;

    public LogAdapter(List<Log> logs) {
        this.logs = logs;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_log, viewGroup, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder logViewHolder, int i) {
        Log log = logs.get(i);
        logViewHolder.bind(log);
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {
        /**
         * Android Views
         **/
        TextView textViewModel;
        TextView textViewCost;
        TextView textViewLocation;
        TextView textViewTolledAt;

        /**
         * Android Views
         **/

        LogViewHolder(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        /**
         * Binds XML views
         * Call this function after layout is ready.
         **/
        private void bindViews(View rootView) {
            textViewModel = rootView.findViewById(R.id.textView_model);
            textViewCost = rootView.findViewById(R.id.textView_cost);
            textViewLocation = rootView.findViewById(R.id.textView_location);
            textViewTolledAt = rootView.findViewById(R.id.textView_tolled_at);
        }

        void bind(final Log log) {
            if (log.getModel() != null) {
                textViewModel.setVisibility(View.VISIBLE);
                textViewModel.setText(log.getModel());
            }
            textViewCost.setText(String.format(Locale.US, "BDT %d", log.getCost()));
            textViewLocation.setText(log.getLocation());
            textViewTolledAt.setText(log.getTolledAt());
        }
    }
}
