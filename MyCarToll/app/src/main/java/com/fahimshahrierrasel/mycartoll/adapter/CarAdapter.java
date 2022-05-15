package com.fahimshahrierrasel.mycartoll.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fahimshahrierrasel.mycartoll.R;
import com.fahimshahrierrasel.mycartoll.data.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> cars;
    private Listener listener;

    public CarAdapter(List<Car> cars, Listener listener) {
        this.cars = cars;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_car, viewGroup, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int i) {
        Car car = cars.get(i);
        carViewHolder.bind(car, listener);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }


    public interface Listener {
        void onCarClicked(Car car);
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        /**
         * Android Views
         **/
        TextView textViewCarModel;
        TextView textViewNoPlate;
        TextView textViewCarType;
        TextView textViewRfid;

        /**
         * Android Views
         **/

        CarViewHolder(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        /**
         * Binds XML views
         * Call this function after layout is ready.
         **/
        private void bindViews(View rootView) {
            textViewCarModel = rootView.findViewById(R.id.textView_carModel);
            textViewNoPlate = rootView.findViewById(R.id.textView_noPlate);
            textViewCarType = rootView.findViewById(R.id.textView_carType);
            textViewRfid = rootView.findViewById(R.id.textView_rfid);
        }

        public void bind(final Car car, final Listener listener) {
            textViewCarModel.setText(car.getModel());
            textViewCarType.setText(car.getType());
            textViewNoPlate.setText(car.getNoPlate());
            textViewRfid.setText(car.getRfid());
            itemView.setOnClickListener(view -> listener.onCarClicked(car));
        }
    }
}
