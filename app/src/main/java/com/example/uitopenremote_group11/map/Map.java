package com.example.uitopenremote_group11.map;

import com.google.gson.annotations.SerializedName;

public class Map {
    @SerializedName("options")
    public Object options;

    @SerializedName("version")
    public float version;

    public Object getOptions() {
        return options;
    }

    public void setOptions(Object options) {
        this.options = options;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Map{" +
                "options=" + options +
                ", version=" + version +
                '}';
    }
}
