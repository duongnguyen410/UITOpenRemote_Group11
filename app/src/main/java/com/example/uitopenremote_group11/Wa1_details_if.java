package com.example.uitopenremote_group11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Wa1_details_if extends AppCompatActivity {
    TextView hum_value;
    TextView temp_value ;
    TextView windS_value;
    TextView windD_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wa1_details_if);

        getWindow().setStatusBarColor(ContextCompat.getColor(Wa1_details_if.this, R.color.my_orange));

        hum_value = (TextView) findViewById(R.id.hum_value);
        temp_value = (TextView) findViewById(R.id.temp_value);
        windS_value = (TextView) findViewById(R.id.windS_value);
        windD_value = (TextView) findViewById(R.id.windD_value);

        Intent intent = getIntent();
        String hum = intent.getStringExtra("humValue");
        String temp = intent.getStringExtra("tempValue");
        String windS = intent.getStringExtra("windSValue");
        String windD = intent.getStringExtra("windDValue");

        hum_value.setText(hum);
        temp_value.setText(temp);
        windS_value.setText(windS);
        windD_value.setText(windD);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}