package com.fahimshahrierrasel.mycartoll.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Car {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("rfid")
    @Expose
    private String rfid;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("no_plate")
    @Expose
    private String noPlate;
    @SerializedName("type")
    @Expose
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNoPlate() {
        return noPlate;
    }

    public void setNoPlate(String noPlate) {
        this.noPlate = noPlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}