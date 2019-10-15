package com.example.cape_medics;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewAttendance extends AppCompatActivity {
    Spinner jobType;
    Spinner unitType;
    ListView Jobs;
    String job;
    String unit, code;
    JSONObject viewAttendance;
    String url, responseServer;
    JSONObject response;
    List currentJobs;
    boolean connected;
    ArrayAdapter<String> CurrentJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_attendance);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            code = bundle.getString("code");
        }
        viewAttendance = new JSONObject();
        jobType = findViewById(R.id.spinner9);
        unitType = findViewById(R.id.spinner10);
        Jobs = findViewById(R.id.listView);
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/viewAttendance.php";
        currentJobs = new ArrayList();

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else connected = false;

        String[] JobType = {"Film","EVENT","COMMERCIAL","REHEARSAL","RIGGING","TRAINING","OFFICE STAFF"};
        String[] UnitType = {"Main Unit","2ND UNIT","SPLINTER UNIT","REHEARSAL","RIGGING","CONSTRUCTION",
                "OFFICE STAFF","BUILD UP","STRIKE","RECCE","SAFETY INSPECTION","ACCIDENT INVESTIGATION",
                "AIR MONITORING","EAP","RISK ASSESSMENT"};

        ArrayAdapter<String> JobTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,JobType);
        ArrayAdapter<String> UnitTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,UnitType);

        JobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UnitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        jobType.setAdapter(JobTypeAdapter);
        unitType.setAdapter(UnitTypeAdapter);



        //fill from db and have them change on spinner change (needs to always be running)
        jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                job = jobType.getSelectedItem().toString();
                Log.e("job", job);
                if(connected) {
                    if (job != null && unit != null && job.equals("Film") && unit.equals("Main Unit")) {
                        AsyncT send = new AsyncT();
                        send.execute();
                    }
                }
                else Toast.makeText(getApplicationContext(),"No internet connection", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                job = "";
            }
        });

        unitType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit = unitType.getSelectedItem().toString();
                Log.e("unit", unit);
                if (connected) {
                    if (job != null && unit != null && job.equals("Film") && unit.equals("Main Unit")) {
                        AsyncT send = new AsyncT();
                        send.execute();

                    }
                }
                else Toast.makeText(getApplicationContext(),"No internet connection", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                unit = "";
            }
        });




        Jobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ViewAttendanceCrew.class);
                i.putExtra("job", String.valueOf(Jobs.getItemAtPosition(position)));
                i.putExtra("names", responseServer);
                i.putExtra("code",code);
                startActivity(i);
            }
        });

    }

    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {

                viewAttendance.put("Job_Name", job);
                viewAttendance.put("Unit_Type", unit);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", viewAttendance.toString()));

                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                Login_Page.InputStreamToStringExample str = new Login_Page.InputStreamToStringExample();
                responseServer = str.getStringFromInputStream(inputStream);
                Log.e("response", "cake -----" + responseServer);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                response = new JSONObject(responseServer);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (response.length() < 3) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
           else {
                Iterator<String> keys = response.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    currentJobs.add(key);
                }
                CurrentJobs = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_row, currentJobs);
                Jobs.setAdapter(CurrentJobs);
            }
        }
    }

    public static class InputStreamToStringExample {

        public static void main(String[] args) throws IOException {

            // intilize an InputStream
            InputStream is = new ByteArrayInputStream("file content..blah blah".getBytes());

            String result = getStringFromInputStream(is);

            System.out.println(result);
            System.out.println("Done");

        }

        // convert InputStream to String
        public static String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }

    }
}
