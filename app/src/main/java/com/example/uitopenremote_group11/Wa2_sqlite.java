package com.example.uitopenremote_group11;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.uitopenremote_group11.Adapter.Wa2_adapter;
import com.example.uitopenremote_group11.Helper.Wa2_helper;
import com.example.uitopenremote_group11.Model.Asset_data;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Wa2_sqlite extends AppCompatActivity {
    APIInterface apiInterface;
    Wa2_helper db = new Wa2_helper(this);

    ListView lv_assetData;
    ArrayList<Asset_data> assetDataArrayList;
    Wa2_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wa2_sqlite);

        getWindow().setStatusBarColor(ContextCompat.getColor(Wa2_sqlite.this, R.color.my_orange));

        lv_assetData = (ListView) findViewById(R.id.assetData_lv);
        assetDataArrayList = new ArrayList<>();

        adapter = new Wa2_adapter(this, R.layout.item_wa1, assetDataArrayList);
        lv_assetData.setAdapter(adapter);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        //TextView dataAsset = (TextView) findViewById(R.id.dataAsset);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        Cursor dataAsset1 = db.GetData("SELECT * FROM WEATHER2");
        while (dataAsset1.moveToNext()) {
            String id = dataAsset1.getString(0);
            String assetName = dataAsset1.getString(1);
            String temp = dataAsset1.getString(2);
            String temp_ts = dataAsset1.getString(3);
            String hum = dataAsset1.getString(4);
            String hum_ts = dataAsset1.getString(5);
            String windS = dataAsset1.getString(6);
            String windS_ts = dataAsset1.getString(7);
            String date = dataAsset1.getString(8);
            String month = dataAsset1.getString(9);
            String year = dataAsset1.getString(10);
            assetDataArrayList.add(new Asset_data(id, assetName, temp, temp_ts, hum, hum_ts, windS, windS_ts, date, month, year));
        }
        adapter.notifyDataSetChanged();

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}