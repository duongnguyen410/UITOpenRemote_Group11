package com.example.uitopenremote_group11;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.uitopenremote_group11.Helper.Wa1_helper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Wa1_graph extends AppCompatActivity {
    GraphView graphView;
    GraphView graphView2;
    GraphView graphView3;

    LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
    LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>();
    LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>();

    Wa1_helper wa1_helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wa1_graph);

        getWindow().setStatusBarColor(ContextCompat.getColor(Wa1_graph.this, R.color.my_orange));

        graphView = (GraphView) findViewById(R.id.tempGraph);
        graphView2 = (GraphView) findViewById(R.id.humGraph);
        graphView3 = (GraphView) findViewById(R.id.windSGraph);

        wa1_helper = new Wa1_helper(this);

        Spinner spinner = findViewById(R.id.spinner1);
        String[] items = new String[]{"Dec, 2022", "Jan, 2023"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String spinner_text = parent.getItemAtPosition(position).toString();
                if(spinner_text.equals("Dec, 2022")){
                    //Temperature_Graph
                    graphView.removeAllSeries();
                    series = new LineGraphSeries<>();
                    Cursor cursor = wa1_helper.GetData("SELECT * FROM WEATHER WHERE Month='12' AND Year='2022'");
                    cursor.moveToFirst();
                    for(int i=0; i<cursor.getCount(); i++){
                        series.appendData(new DataPoint(Long.parseLong(cursor.getString(8)), Float.parseFloat(cursor.getString(2))), true, 50);
                        cursor.moveToNext();
                    }
                    graphView.addSeries(series);
                    graphView.getGridLabelRenderer().setNumHorizontalLabels(8);
                    graphView.getViewport().setXAxisBoundsManual(true);
                    graphView.getViewport().setMinX(16);
                    graphView.getViewport().setMaxX(23);
                    graphView.getViewport().setScrollable(true);
                    series.setColor(Color.RED);
                    series.setThickness(10);

                    //Humidity_Graph
                    graphView2.removeAllSeries();
                    series2 = new LineGraphSeries<>();
                    Cursor cursor2 = wa1_helper.GetData("SELECT * FROM WEATHER WHERE Month='12' AND Year='2022'");
                    cursor2.moveToFirst();
                    for(int i=0; i<cursor2.getCount(); i++){
                        series2.appendData(new DataPoint(Long.parseLong(cursor2.getString(8)), Float.parseFloat(cursor2.getString(4))), true, 50);
                        cursor2.moveToNext();
                    }
                    graphView2.addSeries(series2);
                    graphView2.getGridLabelRenderer().setNumHorizontalLabels(8);
                    graphView2.getViewport().setXAxisBoundsManual(true);
                    graphView2.getViewport().setMinX(16);
                    graphView2.getViewport().setMaxX(23);
                    graphView2.getViewport().setScrollable(true);
                    series2.setColor(Color.RED);
                    series2.setThickness(10);

                    graphView3.removeAllSeries();
                    series3 = new LineGraphSeries<>();
                    Cursor cursor3 = wa1_helper.GetData("SELECT * FROM WEATHER WHERE Month='12' AND Year='2022'");
                    cursor3.moveToFirst();
                    for(int i=0; i<cursor3.getCount(); i++){
                        graphView3.getGridLabelRenderer().setNumHorizontalLabels(i+1);
                        series3.appendData(new DataPoint(Long.parseLong(cursor3.getString(8)), Float.parseFloat(cursor3.getString(6))), true, 50);
                        cursor3.moveToNext();
                    }
                    graphView3.addSeries(series3);
                    graphView3.getGridLabelRenderer().setNumHorizontalLabels(8);
                    graphView3.getViewport().setXAxisBoundsManual(true);
                    graphView3.getViewport().setMinX(16);
                    graphView3.getViewport().setMaxX(23);
                    graphView3.getViewport().setScrollable(true);
                    series3.setColor(Color.RED);
                    series3.setThickness(10);
                }

                if (spinner_text.equals("Jan, 2023")){
                    //Temperature_Graph
                    graphView.removeAllSeries();
                    series = new LineGraphSeries<>();
                    Cursor cursor = wa1_helper.GetData("SELECT * FROM WEATHER WHERE Month='01' AND Year='2023'");
                    cursor.moveToFirst();
                    for(int i=0; i<cursor.getCount();i++){
                        series.appendData(new DataPoint(Long.parseLong(cursor.getString(8)), Float.parseFloat(cursor.getString(2))), true, 50);
                        cursor.moveToNext();
                    }
                    graphView.getGridLabelRenderer().setNumHorizontalLabels(4);
                    graphView.addSeries(series);
                    graphView.getViewport().setXAxisBoundsManual(true);
                    graphView.getViewport().setMinX(1);
                    graphView.getViewport().setMaxX(4);
                    graphView.getViewport().setScrollable(true);
                    series.setColor(Color.RED);
                    series.setThickness(10);

                    //Humidity_Graph
                    graphView2.removeAllSeries();
                    series2 = new LineGraphSeries<>();
                    Cursor cursor2 = wa1_helper.GetData("SELECT * FROM WEATHER WHERE Month='01' AND Year='2023'");
                    cursor2.moveToFirst();
                    for(int i=0; i<cursor2.getCount();i++){

                        series2.appendData(new DataPoint(Long.parseLong(cursor2.getString(8)), Float.parseFloat(cursor2.getString(4))), true, 50);
                        cursor2.moveToNext();
                    }
                    graphView2.getGridLabelRenderer().setNumHorizontalLabels(4);
                    graphView2.addSeries(series2);
                    graphView2.getViewport().setXAxisBoundsManual(true);
                    graphView2.getViewport().setMinX(1);
                    graphView2.getViewport().setMaxX(4);
                    graphView2.getViewport().setScrollable(true);
                    series2.setColor(Color.RED);
                    series2.setThickness(10);

                    graphView3.removeAllSeries();
                    series3 = new LineGraphSeries<>();
                    Cursor cursor3 = wa1_helper.GetData("SELECT * FROM WEATHER WHERE Month='01' AND Year='2023'");
                    cursor3.moveToFirst();
                    for(int i=0; i<cursor3.getCount();i++){
                        series3.appendData(new DataPoint(Long.parseLong(cursor3.getString(8)), Float.parseFloat(cursor3.getString(6))), true, 50);
                        cursor3.moveToNext();
                    }
                    graphView3.getGridLabelRenderer().setNumHorizontalLabels(4);
                    graphView3.addSeries(series3);
                    graphView3.getViewport().setXAxisBoundsManual(true);
                    graphView3.getViewport().setMinX(1);
                    graphView3.getViewport().setMaxX(4);
                    graphView3.getViewport().setScrollable(true);
                    series3.setColor(Color.RED);
                    series3.setThickness(10);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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