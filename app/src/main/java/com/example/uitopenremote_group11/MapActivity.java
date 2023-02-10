package com.example.uitopenremote_group11;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.uitopenremote_group11.Model.Asset;
import com.example.uitopenremote_group11.Model.Asset_attributes;
import com.example.uitopenremote_group11.Model.Asset_humidity;
import com.example.uitopenremote_group11.Model.Asset_location;
import com.example.uitopenremote_group11.Model.Asset_location_value;
import com.example.uitopenremote_group11.Model.Asset_temperature;
import com.example.uitopenremote_group11.Model.Asset_windDirection;
import com.example.uitopenremote_group11.Model.Asset_windSpeed;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity {
    APIInterface apiiInterface;

    private MapView mapView;
    private IMapController iMapController;
    private LinearLayout layoutBottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_map);

        getWindow().setStatusBarColor(ContextCompat.getColor(MapActivity.this, R.color.my_orange));

        apiiInterface = APIClient.getClient().create(APIInterface.class);

        layoutBottomSheet = findViewById(R.id.bottom_sheet_dialog);

        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);

        mapView = findViewById(R.id.mapView);

        Intent intent = getIntent();
        float map_latitude = intent.getFloatExtra("map_latitude", 1234);
        float map_longitude = intent.getFloatExtra("map_longitude", 1234);

//        Log.d("Map location", String.valueOf(map_latitude));
//        Log.d("Map location", String.valueOf(map_longitude));

        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        iMapController = mapView.getController();
        iMapController.setZoom(20);
        GeoPoint startPoint = new GeoPoint(map_latitude,  map_longitude);
        iMapController.setCenter(startPoint);
        Marker marker = new Marker(mapView);
        marker.setPosition(startPoint);
        mapView.getOverlays().add(marker);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);

        Call<Asset> assetCall = apiiInterface.getAsset("6H4PeKLRMea1L0WsRXXWp9");
        assetCall.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();

                String json = gson.toJson(asset.attributes);
                Asset_attributes asset_attributes = gson.fromJson(json, Asset_attributes.class);

                json = gson.toJson(asset_attributes.humidity);
                Asset_humidity asset_humidity = gson.fromJson(json, Asset_humidity.class);

                json = gson.toJson(asset_attributes.temperature);
                Asset_temperature asset_temperature = gson.fromJson(json, Asset_temperature.class);

                json = gson.toJson(asset_attributes.windSpeed);
                Asset_windSpeed asset_windSpeed = gson.fromJson(json, Asset_windSpeed.class);

                json = gson.toJson(asset_attributes.windDirection);
                Asset_windDirection asset_windDirection = gson.fromJson(json, Asset_windDirection.class);

                json = gson.toJson(asset_attributes.location);
                Asset_location asset_location = gson.fromJson(json, Asset_location.class);

                json = gson.toJson(asset_location.value);
                Asset_location_value asset_location_value = gson.fromJson(json, Asset_location_value.class);

                float asset_latitude = asset_location_value.coordinates[1];
                float asset_longitude = asset_location_value.coordinates[0];

                GeoPoint asset_point = new GeoPoint(asset_latitude, asset_longitude);
                Marker marker2 = new Marker(mapView);
                marker2.setPosition(asset_point);
                mapView.getOverlays().add(marker2);

                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet, (LinearLayout) findViewById(R.id.bottom_sheet_dialog));

                TextView id_value = (TextView) view.findViewById(R.id.obj1_value);
                TextView version_value = (TextView) view.findViewById(R.id.obj2_value);
                TextView createOn_value = (TextView) view.findViewById(R.id.obj3_value);
                TextView longitude_value = (TextView) view.findViewById(R.id.obj4_value);
                TextView latitude_value = (TextView) view.findViewById(R.id.obj5_value);

                Button detailsBtn = (Button) view.findViewById(R.id.detailsBtn);

                marker2.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                        id_value.setText(asset.id);
                        version_value.setText(asset.version);
                        createOn_value.setText(asset.createdOn);
                        longitude_value.setText(String.valueOf(asset_location_value.coordinates[0]));
                        latitude_value.setText(String.valueOf(asset_location_value.coordinates[1]));

                        if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        } else{
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }

                        detailsBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MapActivity.this, Wa1_details_if.class);
                                intent.putExtra("humValue", asset_humidity.value);
                                intent.putExtra("tempValue", asset_temperature.value);
                                intent.putExtra("windSValue", asset_windSpeed.value);
                                intent.putExtra("windDValue", asset_windDirection.value);
                                startActivity(intent);
                            }
                        });
                        return true;
                    }
                });
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });

        Call<Asset> assetCall2 = apiiInterface.getAsset("2UZPM2Mvu11Xyq5jCWNMX1");
        assetCall2.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();

                String json = gson.toJson(asset.attributes);
                Asset_attributes asset_attributes = gson.fromJson(json, Asset_attributes.class);

                json = gson.toJson(asset_attributes.humidity);
                Asset_humidity asset_humidity = gson.fromJson(json, Asset_humidity.class);

                json = gson.toJson(asset_attributes.temperature);
                Asset_temperature asset_temperature = gson.fromJson(json, Asset_temperature.class);

                json = gson.toJson(asset_attributes.windSpeed);
                Asset_windSpeed asset_windSpeed = gson.fromJson(json, Asset_windSpeed.class);

                json = gson.toJson(asset_attributes.windDirection);
                Asset_windDirection asset_windDirection = gson.fromJson(json, Asset_windDirection.class);

                json = gson.toJson(asset_attributes.location);
                Asset_location asset_location = gson.fromJson(json, Asset_location.class);

                json = gson.toJson(asset_location.value);
                Asset_location_value asset_location_value = gson.fromJson(json, Asset_location_value.class);

                float asset_latitude = asset_location_value.coordinates[1];
                float asset_longitude = asset_location_value.coordinates[0];

                GeoPoint asset_point = new GeoPoint(asset_latitude, asset_longitude);
                Marker marker3 = new Marker(mapView);
                marker3.setPosition(asset_point);
                mapView.getOverlays().add(marker3);

                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet, (LinearLayout) findViewById(R.id.bottom_sheet_dialog));

                TextView id_value = (TextView) view.findViewById(R.id.obj1_value);
                TextView version_value = (TextView) view.findViewById(R.id.obj2_value);
                TextView createOn_value = (TextView) view.findViewById(R.id.obj3_value);
                TextView longitude_value = (TextView) view.findViewById(R.id.obj4_value);
                TextView latitude_value = (TextView) view.findViewById(R.id.obj5_value);

                Button detailsBtn = (Button) view.findViewById(R.id.detailsBtn);

                marker3.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                        id_value.setText(asset.id);
                        version_value.setText(asset.version);
                        createOn_value.setText(asset.createdOn);
                        longitude_value.setText(String.valueOf(asset_location_value.coordinates[0]));
                        latitude_value.setText(String.valueOf(asset_location_value.coordinates[1]));

                        if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        } else{
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }

                        detailsBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MapActivity.this, Wa2_details_if.class);
                                intent.putExtra("humValue", asset_humidity.value);
                                intent.putExtra("tempValue", asset_temperature.value);
                                intent.putExtra("windSValue", asset_windSpeed.value);
                                intent.putExtra("windDValue", asset_windDirection.value);
                                startActivity(intent);
                            }
                        });
                        return true;
                    }
                });
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });

        Call<Asset> assetCall3 = apiiInterface.getAsset("4cdWlxEvmDRBBDEc2HRsaF");
        assetCall3.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Gson gson = new Gson();

                String json = gson.toJson(asset.attributes);
                Asset_attributes asset_attributes = gson.fromJson(json, Asset_attributes.class);

                json = gson.toJson(asset_attributes.humidity);
                Asset_humidity asset_humidity = gson.fromJson(json, Asset_humidity.class);

                json = gson.toJson(asset_attributes.temperature);
                Asset_temperature asset_temperature = gson.fromJson(json, Asset_temperature.class);

                json = gson.toJson(asset_attributes.windSpeed);
                Asset_windSpeed asset_windSpeed = gson.fromJson(json, Asset_windSpeed.class);

                json = gson.toJson(asset_attributes.windDirection);
                Asset_windDirection asset_windDirection = gson.fromJson(json, Asset_windDirection.class);

                json = gson.toJson(asset_attributes.location);
                Asset_location asset_location = gson.fromJson(json, Asset_location.class);

                json = gson.toJson(asset_location.value);
                Asset_location_value asset_location_value = gson.fromJson(json, Asset_location_value.class);

                float asset_latitude = asset_location_value.coordinates[1];
                float asset_longitude = asset_location_value.coordinates[0];

                GeoPoint asset_point = new GeoPoint(asset_latitude, asset_longitude);
                Marker marker4 = new Marker(mapView);
                marker4.setPosition(asset_point);
                mapView.getOverlays().add(marker4);

                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet, (LinearLayout) findViewById(R.id.bottom_sheet_dialog));

                TextView id_value = (TextView) view.findViewById(R.id.obj1_value);
                TextView version_value = (TextView) view.findViewById(R.id.obj2_value);
                TextView createOn_value = (TextView) view.findViewById(R.id.obj3_value);
                TextView longitude_value = (TextView) view.findViewById(R.id.obj4_value);
                TextView latitude_value = (TextView) view.findViewById(R.id.obj5_value);

                Button detailsBtn = (Button) view.findViewById(R.id.detailsBtn);

                marker4.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                        id_value.setText(asset.id);
                        version_value.setText(asset.version);
                        createOn_value.setText(asset.createdOn);
                        longitude_value.setText(String.valueOf(asset_location_value.coordinates[0]));
                        latitude_value.setText(String.valueOf(asset_location_value.coordinates[1]));

                        if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        } else{
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }

                        detailsBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MapActivity.this, Wa3_details_if.class);
                                intent.putExtra("humValue", asset_humidity.value);
                                intent.putExtra("tempValue", asset_temperature.value);
                                intent.putExtra("windSValue", asset_windSpeed.value);
                                intent.putExtra("windDValue", asset_windDirection.value);
                                startActivity(intent);
                            }
                        });
                        return true;
                    }
                });
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}