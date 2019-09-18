package com.example.graphapp;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    BarChart barChart;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barChart = (BarChart) findViewById(R.id.barGraph);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String[]> hrData = new ArrayList<>();
        File file = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("hr.txt")));
            String line;
           // Log.e("Reader Stuff",reader.readLine());
            while ((line = reader.readLine()) != null) {
               // Log.e("code",line);
                String[] hrLeaders = line.split(", ");
                hrData.add(hrLeaders);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        List<BarEntry> entries = new ArrayList<>();

        List<String> names = new ArrayList<>();
        List<Integer> hrNumber = new ArrayList<>();

        for(String[] a: hrData)
            names.add(a[0]);


        for(String[] b: hrData){
            if(!b[1].isEmpty())
            {
                try
                {
                    int hrNum = Integer.parseInt(b[1]);
                    hrNumber.add(hrNum);
                }catch (NumberFormatException e)
                {
                    Log.e("Error",e.getMessage());
                }
            }
        }



        entries.add(new BarEntry(.5f, hrNumber.get(0)));
        entries.add(new BarEntry(1.5f, hrNumber.get(1)));
        entries.add(new BarEntry(2.5f, hrNumber.get(2)));
        entries.add(new BarEntry(3.5f, hrNumber.get(3)));
        entries.add(new BarEntry(4.5f, hrNumber.get(4)));


        BarDataSet set = new BarDataSet(entries, "HomeRun Leaders");
        BarData theData = new BarData(set);

        barChart.getDescription().setText("2019 Homerun Leaders");


        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(names));

        theData.setBarWidth(0.9f);
        barChart.setData(theData);




        /*
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
        barChart.setDragEnabled(true);*/





    }
}