package com.example.graphapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.data.BarData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class juicedballs extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicedballs);


        new DownloadFilesTask().execute();
    }
}
