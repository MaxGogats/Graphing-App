package com.example.graphapp;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barChart = (BarChart) findViewById(R.id.barGraph);
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(.5f, 80f));
        entries.add(new BarEntry(1.5f, 60f));
        entries.add(new BarEntry(2.5f, 50f));
        // gap of 2f
        entries.add(new BarEntry(4.5f, 70f));
        entries.add(new BarEntry(5.5f, 60f));

        List<String> dates = new ArrayList<>();
        dates.add("May1");
        dates.add("May2");
        dates.add("May3");
        dates.add("May4");
        dates.add("May5");


        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData theData = new BarData(set);

        set.setColor(Color.BLUE);

        theData.setBarWidth(0.9f); // set custom bar width
        barChart.setData(theData);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh

        barChart.setScaleEnabled(true);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);

    }
}