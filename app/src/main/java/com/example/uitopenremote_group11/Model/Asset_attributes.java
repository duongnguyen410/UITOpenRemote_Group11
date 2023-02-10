package com.example.uitopenremote_group11.Model;

import com.google.gson.annotations.SerializedName;

public class Asset_attributes {
    @SerializedName("weatherData")
    public Object weatherData;
    @SerializedName("location")
    public Object location;
    @SerializedName("temperature")
    public Object temperature;
    @SerializedName("humidity")
    public Object humidity;
    @SerializedName("windSpeed")
    public Object windSpeed;

    public Object getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Object windDirection) {
        this.windDirection = windDirection;
    }

    @SerializedName("windDirection")
    public Object windDirection;
    @SerializedName("timestamp")
    public String timestamp;

    public Object getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(Object weatherData) {
        this.weatherData = weatherData;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getTemperature() {
        return temperature;
    }

    public void setTemperature(Object temperature) {
        this.temperature = temperature;
    }

    public Object getHumidity() {
        return humidity;
    }

    public void setHumidity(Object humidity) {
        this.humidity = humidity;
    }

    public Object getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Object windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
