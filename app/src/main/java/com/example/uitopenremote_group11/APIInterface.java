package com.example.uitopenremote_group11;

import com.example.uitopenremote_group11.Model.Asset;
import com.example.uitopenremote_group11.map.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("api/master/asset/{assetID}")
    Call<Asset> getAsset(@Path("assetID") String assetID);//, @Header("Authorization") String auth);

    @GET("api/master/map")
    Call<Map> getMap();

}
