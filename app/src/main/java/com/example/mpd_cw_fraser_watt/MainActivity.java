package com.example.mpd_cw_fraser_watt;         //FRASER WATT - S1620179

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



////////////////////////////////////////////////////////////////////////////////////////////////////



public class MainActivity extends AppCompatActivity {

    private EditText editSearchTxt;
    private TextView acknowledgement;

    private String result;
    private String result_2;
    private String result_3;

    private Button startButton;
    private Button roadworksButton;
    private Button plannedRoadworksButton;
    private Button search_button;
    private ScrollView print_results;

    // Traffic Scotland URLs
    private String roadworksURL = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    private String plannedroadworksURL = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";
    private String urlSource = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";

    public int i;
    private String userSearch;



////////////////////////////////////////////////////////////////////////////////////////////////////



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        print_results = (ScrollView)findViewById(R.id.print_results);

        editSearchTxt = (EditText)findViewById(R.id.editSearchTxt);

        acknowledgement = (TextView)findViewById(R.id.acknowledgement);

        search_button = (Button)findViewById(R.id.search_button);

        startButton = (Button)findViewById(R.id.startButton);

        roadworksButton = (Button)findViewById(R.id.roadworksButton);

        plannedRoadworksButton = (Button)findViewById(R.id.plannedRoadworksButton);


    }



////////////////////////////////////////////////////////////////////////////////////////////////////



    public void onClick_CI(View aview) {
        i = 0;
        startProgress1();
    }

    public void onClick_RW(View aview) {
        i = 1;
        startProgress2();
    }

    public void onClick_PW(View aview) {
        i = 2;
        startProgress3();
    }

    public void onClick_Search(View aview) {
        i = 3;
        startProgress4();
    }



////////////////////////////////////////////////////////////////////////////////////////////////////



    public void startProgress1() {
        // Run network access on a separate thread;
        new Thread(new Task(urlSource, this)).start();
    }

    public void startProgress2() {
        // Run network access on a separate thread
        new Thread(new Task(roadworksURL, this)).start();
    }

    public void startProgress3() {
        // Run network access on a separate thread;
        new Thread(new Task(plannedroadworksURL, this)).start();
    }

    public void startProgress4() {
        // Run network access on a separate thread;
        new Thread(new Task(urlSource, roadworksURL, plannedroadworksURL, this)).start();
    }



////////////////////////////////////////////////////////////////////////////////////////////////////



    private class Task implements Runnable {
        private String url;
        private String url1;
        private String url2;
        private MainActivity ma;
        ParseData processing = new ParseData();

        public Task(String aurl, MainActivity ma) {
            url = aurl;
            this.ma = ma;
        }

        public Task(String aurl1, String aurl2, String aurl3, MainActivity ma){
            url = aurl1;
            url1 = aurl2;
            url2 = aurl3;
            this.ma = ma;
        }



        @Override
        public void run() {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";
            String search = editSearchTxt.getText().toString();

            Log.e("MyTag","in run");

            if(i==3){
                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url);
                    yc = aurl.openConnection();
                    in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                    //
                    // Throw away the first 2 header lines before parsing
                    //

                    result = in.readLine();
                    while ((inputLine = in.readLine()) != null)
                    {
                        result = result + inputLine;
                        Log.e("MyTag",inputLine);

                    }
                    in.close();
                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception");
                }

                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url1);
                    yc = aurl.openConnection();
                    in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                    //
                    // Throw away the first 2 header lines before parsing
                    //

                    result_2 = in.readLine();
                    while ((inputLine = in.readLine()) != null)
                    {
                        result_2 = result_2 + inputLine;
                        Log.e("MyTag",inputLine);

                    }
                    in.close();
                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception");
                }

                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url2);
                    yc = aurl.openConnection();
                    in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                    //
                    // Throw away the first 2 header lines before parsing
                    //

                    result_3 = in.readLine();
                    while ((inputLine = in.readLine()) != null)
                    {
                        result_3 = result_3 + inputLine;
                        Log.e("MyTag",inputLine);

                    }
                    in.close();
                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception");
                }


            } else {

                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url);
                    yc = aurl.openConnection();
                    in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                    //
                    // Throw away the first 2 header lines before parsing
                    //

                    result = in.readLine();
                    while ((inputLine = in.readLine()) != null)
                    {
                        result = result + inputLine;
                        Log.e("MyTag",inputLine);

                    }
                    in.close();
                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception");
                }
            }

            if(i == 0){
                processing.parseIncidents(this.ma, result);
            }else if(i == 1){
                processing.parseRoadworks(this.ma, result);
            }else if(i == 2){
                processing.parsePlannedRoadworks(this.ma, result);
            }else if(i == 3){
                processing.parseAllSearch(this.ma, result, result_2, result_3, search);
            }
        }
    }
} // End of MainActivity