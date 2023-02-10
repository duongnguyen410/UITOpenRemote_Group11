package com.example.uitopenremote_group11.Model;

import com.google.gson.annotations.SerializedName;

public class Asset_windDirection {
    @SerializedName("type")
    public String type;
    @SerializedName("value")
    public String value;
    @SerializedName("name")
    public String name;
    @SerializedName("meta")
    public Object meta;
    @SerializedName("timestamp")
    public String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }
}
