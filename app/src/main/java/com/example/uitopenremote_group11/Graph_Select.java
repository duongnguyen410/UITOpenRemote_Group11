package com.example.uitopenremote_group11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Graph_Select extends AppCompatActivity {
    FrameLayout wa1_graph, wa2_graph, wa3_graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_select);

        getWindow().setStatusBarColor(ContextCompat.getColor(Graph_Select.this, R.color.my_orange));

        wa1_graph = findViewById(R.id.wa1_graph);
        wa2_graph = findViewById(R.id.wa2_graph);
        wa3_graph = findViewById(R.id.wa3_graph);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wa1_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Graph_Select.this, Wa1_graph.class);
                startActivity(intent);
            }
        });

        wa2_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Graph_Select.this, Wa2_graph.class);
                startActivity(intent);
            }
        });

        wa3_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Graph_Select.this, Wa3_graph.class);
                startActivity(intent);
            }
        });
    }
}