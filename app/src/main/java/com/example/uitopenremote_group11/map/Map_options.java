package com.example.uitopenremote_group11.map;

import com.google.gson.annotations.SerializedName;

public class Map_options {

    @SerializedName("options")
    public Object options;

    @SerializedName("default")
    public Object _default;

    public Map_options(Object options) {
        this.options = options;
    }

    public Object getOptions() {
        return options;
    }

    public void setOptions(Object options) {
        this.options = options;
    }

    public Object getMap_default() {
        return _default;
    }

    public void setMap_default(Object map_default) {
        this._default = map_default;
    }

    @Override
    public String toString() {
        return "Map_options{" +
                "map_default=" + _default +
                '}';
    }
}
