package com.fahimshahrierrasel.mycartoll.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Log {

    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("cost")
    @Expose
    private int cost;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("tolled_at")
    @Expose
    private String tolledAt;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTolledAt() {
        return tolledAt;
    }

    public void setTolledAt(String tolledAt) {
        this.tolledAt = tolledAt;
    }

}