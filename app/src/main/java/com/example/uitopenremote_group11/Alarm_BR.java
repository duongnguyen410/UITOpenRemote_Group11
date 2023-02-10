package com.example.uitopenremote_group11;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.uitopenremote_group11.Helper.Wa1_helper;
import com.example.uitopenremote_group11.Helper.Wa2_helper;
import com.example.uitopenremote_group11.Helper.Wa3_helper;
import com.example.uitopenremote_group11.Model.Asset;
import com.example.uitopenremote_group11.Model.Asset_attributes;
import com.example.uitopenremote_group11.Model.Asset_data;
import com.example.uitopenremote_group11.Model.Asset_humidity;
import com.example.uitopenremote_group11.Model.Asset_temperature;
import com.example.uitopenremote_group11.Model.Asset_windDirection;
import com.example.uitopenremote_group11.Model.Asset_windSpeed;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Alarm_BR extends BroadcastReceiver {
    APIInterface apiInterface;
    @Override
    public void onReceive(Context context, Intent intent) {

        apiInterface = APIClient.getClient().create(APIInterface.class);
//        MediaPlayer mp = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
//        mp.start();

        Calendar calendar = Calendar.getInstance();
        String currentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        String currentMonth = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
        String currentYear = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());

        Wa1_helper wa1_db = new Wa1_helper(context);
        Wa2_helper wa2_db = new Wa2_helper(context);
        Wa3_helper wa3_db = new Wa3_helper(context);

        Call<Asset> assetCall = apiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");
        assetCall.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();

                String json = gson.toJson(asset.attributes);
                Asset_attributes attr = gson.fromJson(json, Asset_attributes.class);

                json = gson.toJson(attr.temperature);
                Asset_temperature temp = gson.fromJson(json, Asset_temperature.class);

                json = gson.toJson(attr.humidity);
                Asset_humidity hum = gson.fromJson(json, Asset_humidity.class);

                json = gson.toJson(attr.windSpeed);
                Asset_windSpeed windS = gson.fromJson(json, Asset_windSpeed.class);

                json = gson.toJson(attr.windDirection);
                Asset_windDirection windD = gson.fromJson(json, Asset_windDirection.class);

                Log.d("API CALL", currentYear);
                Asset_data assetData = new Asset_data(asset.id, asset.name, temp.getValue(), String.valueOf(temp.getTimestamp()), hum.getValue(),String.valueOf(hum.getTimestamp()), windS.getValue(), String.valueOf((windS.getTimestamp())), currentDate, currentMonth, currentYear);
                //wa1_db.deleteAssetData("06");
                //wa1_db.deleteTableAssetData();
                //wa1_db.insertAssetData(assetData);
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });

        Call<Asset> assetCall2 = apiInterface.getAsset("2UZPM2Mvu11Xyq5jCWNMX1");
        assetCall2.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();

                String json = gson.toJson(asset.attributes);
                Asset_attributes attr = gson.fromJson(json, Asset_attributes.class);

                json = gson.toJson(attr.temperature);
                Asset_temperature temp = gson.fromJson(json, Asset_temperature.class);

                json = gson.toJson(attr.humidity);
                Asset_humidity hum = gson.fromJson(json, Asset_humidity.class);

                json = gson.toJson(attr.windSpeed);
                Asset_windSpeed windS = gson.fromJson(json, Asset_windSpeed.class);

                json = gson.toJson(attr.windDirection);
                Asset_windDirection windD = gson.fromJson(json, Asset_windDirection.class);

                Log.d("API CALL", currentYear);
                Asset_data assetData = new Asset_data(asset.id, asset.name, temp.getValue(), String.valueOf(temp.getTimestamp()), hum.getValue(),String.valueOf(hum.getTimestamp()), windS.getValue(), String.valueOf((windS.getTimestamp())), currentDate, currentMonth, currentYear);
                //wa2_db.deleteAssetData("06");
                //wa2_db.deleteTableAssetData();
                //wa2_db.insertAssetData(assetData);
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });

        Call<Asset> assetCall3 = apiInterface.getAsset("4cdWlxEvmDRBBDEc2HRsaF");
        assetCall3.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();

                String json = gson.toJson(asset.attributes);
                Asset_attributes attr = gson.fromJson(json, Asset_attributes.class);

                json = gson.toJson(attr.temperature);
                Asset_temperature temp = gson.fromJson(json, Asset_temperature.class);

                json = gson.toJson(attr.humidity);
                Asset_humidity hum = gson.fromJson(json, Asset_humidity.class);

                json = gson.toJson(attr.windSpeed);
                Asset_windSpeed windS = gson.fromJson(json, Asset_windSpeed.class);

                json = gson.toJson(attr.windDirection);
                Asset_windDirection windD = gson.fromJson(json, Asset_windDirection.class);

                Log.d("API CALL", currentYear);
                Asset_data assetData = new Asset_data(asset.id, asset.name, temp.getValue(), String.valueOf(temp.getTimestamp()), hum.getValue(),String.valueOf(hum.getTimestamp()), windS.getValue(), String.valueOf((windS.getTimestamp())), currentDate, currentMonth, currentYear);
                //wa3_db.deleteAssetData("06");
                //wa3_db.deleteTableAssetData();
                //wa3_db.insertAssetData(assetData);
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("uitopenremote", "uitopenremote", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "uitopenremote")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("UIT OpenRemote")
                .setContentText("Call Successfully!")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, builder.build());
    }
}
