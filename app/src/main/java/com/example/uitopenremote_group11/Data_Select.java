package com.example.uitopenremote_group11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Data_Select extends AppCompatActivity {
    FrameLayout wa1_db, wa2_db, wa3_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_select);

        getWindow().setStatusBarColor(ContextCompat.getColor(Data_Select.this, R.color.my_orange));

        wa1_db = findViewById(R.id.wa1_db);
        wa2_db = findViewById(R.id.wa2_db);
        wa3_db = findViewById(R.id.wa3_db);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wa1_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Data_Select.this, Wa1_sqlite.class);
                startActivity(intent);
            }
        });

        wa2_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Data_Select.this, Wa2_sqlite.class);
                startActivity(intent);
            }
        });

        wa3_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Data_Select.this, Wa3_sqlite.class);
                startActivity(intent);
            }
        });
    }
}