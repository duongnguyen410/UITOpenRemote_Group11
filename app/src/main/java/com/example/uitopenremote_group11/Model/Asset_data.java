package com.example.uitopenremote_group11.Model;

public class Asset_data {
    private String id;
    private String name;
    private String temperature;
    private String temp_Timestamp;
    private String humidity;
    private String hum_timestamp;
    private String windSpeed;
    private String windS_timestamp;
    private String date;

    public Asset_data(){}

    public Asset_data(String id, String name, String temperature, String temp_Timestamp, String humidity, String hum_timestamp, String windSpeed, String windS_timestamp, String date, String month, String year) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.temp_Timestamp = temp_Timestamp;
        this.humidity = humidity;
        this.hum_timestamp = hum_timestamp;
        this.windSpeed = windSpeed;
        this.windS_timestamp = windS_timestamp;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemp_Timestamp() {
        return temp_Timestamp;
    }

    public void setTemp_Timestamp(String temp_Timestamp) {
        this.temp_Timestamp = temp_Timestamp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getHum_timestamp() {
        return hum_timestamp;
    }

    public void setHum_timestamp(String hum_timestamp) {
        this.hum_timestamp = hum_timestamp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindS_timestamp() {
        return windS_timestamp;
    }

    public void setWindS_timestamp(String windS_timestamp) {
        this.windS_timestamp = windS_timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String month;
    private String year;
}
