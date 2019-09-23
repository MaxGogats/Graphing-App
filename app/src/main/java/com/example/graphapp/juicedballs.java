package com.example.graphapp;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class juicedballs extends AppCompatActivity {

/*
    private class DownloadFilesTask extends AsyncTask<URL, Void, Void> {

        private Exception exception;


        protected void doInBackground(URL urls) {

            try {
                Document d = Jsoup.connect("https://www.wikihow.com/wikiHowTo?search=signal+wifi").timeout(6000).get();
                Elements e = d.select("div#searchresults_list");

                for (Element element: e.select("div.result")){
                    String img_url = element.select("div.result_thumb img").attr("src");
                    Log.e("IMG_URL", img_url);

                    String title = element.select("div.result_data a").text();
                    Log.e("Title", title);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        protected void onPostExecute() {
            // TODO: check this.exception
            // TODO: do something with the feed
            String tag = "max";
            Log.e("Works", tag);
        }
    }


 */

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicedballs);

        lineChart = (LineChart) findViewById(R.id.lineChart);

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String[]> hrData = new ArrayList<>();
        ArrayList<String> years = new ArrayList<>();
        ArrayList<Integer> hrNum = new ArrayList<>();
        File file = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("juiced")));
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

        for(String[] a: hrData){
            years.add(a[0]);
        }

        for(String[] b: hrData){
            int hrNumber = Integer.parseInt(b[1]);
            hrNum.add(hrNumber);
        }

        for(int i=0; i<hrNum.size(); i++){
            entries.add(new Entry(i, hrNum.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "HR Totals");

        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);

        /*lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);*/

        LineData lineData = new LineData(lineDataSet);
        lineChart.getDescription().setText("HR Totals 1990-2019");
        lineChart.getDescription().setTextSize(12);
        lineChart.setDrawMarkers(true);

        /*lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setLabelCount(lineDataSet.getEntryCount());*/

        lineChart.setData(lineData);
    }






}
