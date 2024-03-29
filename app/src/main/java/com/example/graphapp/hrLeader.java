package com.example.graphapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.github.mikephil.charting.components.XAxis;
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

public class hrLeader extends AppCompatActivity {

    BarChart barChart;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_leader);

        barChart = (BarChart) findViewById(R.id.barGraph);
        Button toLine = (Button) findViewById(R.id.toLine);

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

        for (String[] a : hrData)
            names.add(a[0]);


        for (String[] b : hrData) {
            if (!b[1].isEmpty()) {
                try {
                    int hrNum = Integer.parseInt(b[1]);
                    hrNumber.add(hrNum);
                } catch (NumberFormatException e) {
                    Log.e("Error", e.getMessage());
                }
            }
        }


        entries.add(new BarEntry(0f, hrNumber.get(0)));
        entries.add(new BarEntry(1, hrNumber.get(1)));
        entries.add(new BarEntry(2, hrNumber.get(2)));
        entries.add(new BarEntry(3f, hrNumber.get(3)));
        entries.add(new BarEntry(4f, hrNumber.get(4)));


        BarDataSet set = new BarDataSet(entries, "Players");
        BarData theData = new BarData(set);

        barChart.getDescription().setText("");
        barChart.getDescription().setPosition(750, 1000);
        barChart.getDescription().setTextSize(15);


        barChart.getXAxis().setGranularity(1);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(names));
        XAxis xax = barChart.getXAxis();
        xax.setDrawGridLines(false);

        theData.setBarWidth(0.9f);
        barChart.setData(theData);
    }
}
