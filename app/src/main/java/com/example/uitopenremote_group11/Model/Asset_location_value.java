package com.example.uitopenremote_group11.Model;

import com.google.gson.annotations.SerializedName;

public class Asset_location_value {
    @SerializedName("coordinates")
    public Float[] coordinates;

    public Float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Float[] coordinates) {
        this.coordinates = coordinates;
    }
}
