package com.example.uitopenremote_group11;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DashBoardActivity extends AppCompatActivity {

    BottomNavigationView bottom_bar;

    FrameLayout map, graph, data;

    TextView welcome_tv, date_tv, my_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        getWindow().setStatusBarColor(ContextCompat.getColor(DashBoardActivity.this, R.color.my_orange));

        Intent intent = getIntent();
        float map_latitude = intent.getFloatExtra("map_latitude", 1234);
        float map_longitude = intent.getFloatExtra("map_longitude", 1234);

        welcome_tv = findViewById(R.id.welcome_tv);
        date_tv = findViewById(R.id.date_tv);
        my_tv = findViewById(R.id.my_tv);

        String username = intent.getStringExtra("username");


        String currentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        String currentMonthYear = new SimpleDateFormat("MMM, yyyy").format(Calendar.getInstance().getTime());

        welcome_tv.setText("Hi " + username + "!");
        date_tv.setText(currentDate);
        my_tv.setText(currentMonthYear);


        bottom_bar = findViewById(R.id.bottom_bar);
        bottom_bar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.logout:
                        Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Animatoo.INSTANCE.animateSlideRight(DashBoardActivity.this);
                        break;

                    case R.id.alarm:
                        Intent intent2 = new Intent(DashBoardActivity.this, SetAlarmActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });

        map = findViewById(R.id.map_Tab);
        graph = findViewById(R.id.graph_Tab);
        data = findViewById(R.id.data_Tab);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, MapActivity.class);
                intent.putExtra("map_latitude", map_latitude);
                intent.putExtra("map_longitude", map_longitude);
                startActivity(intent);
            }
        });

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, Graph_Select.class);
                startActivity(intent);
            }
        });
//
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, Data_Select.class);
                startActivity(intent);
            }
        });
    }
}