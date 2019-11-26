package com.example.cape_medics;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TimeSheet extends AppCompatActivity {
    Spinner jobType;
    Spinner unitType;
    Spinner serviceProvided;
    TextView callTime;
    EditText jobName;
    EditText Location;
    JSONObject timeSheet, response;
    String responseServer,url,code,authorisation;
    String jobN, loc, callT, jobT, unit, service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheet);

        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");
        authorisation = bundle.getString("Authorisation");

        timeSheet = new JSONObject();
        jobName = findViewById(R.id.editText);
        Location = findViewById(R.id.editText2);
        callTime = findViewById(R.id.editText3);
        jobType = findViewById(R.id.spinner4);
        unitType = findViewById(R.id.spinner5);
        serviceProvided = findViewById(R.id.spinner6);
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/timeSheet.php";


        String[] JobType = {"FILM","EVENT","COMMERCIAL","REHEARSAL","RIGGING","TRAINING","OFFICE STAFF"};
        String[] UnitType = {"MAIN UNIT","2ND UNIT","SPLINTER UNIT","REHEARSAL","RIGGING","CONSTRUCTION",
                "OFFICE STAFF","BUILD UP","STRIKE","RECCE","SAFETY INSPECTION","ACCIDENT INVESTIGATION",
                "AIR MONITORING","EAP","RISK ASSESSMENT"};
        String[] ServiceProvided = {"SKID UNIT","MEDIC","AMBULANCE","FIRE MARSHAL","FIRE ENGINE","SAFETY ADVISOR","" +
                "SAFETY MARSHAL","RESCUE","RESPONSE CAR","WATER TANKER","4X4 SKID","OFFICE STAFF"};
        //  String[] CallTime = {""};

        ArrayAdapter<String> JobTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,JobType);
        ArrayAdapter<String> UnitTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,UnitType);
        ArrayAdapter<String> ServiceProvidedAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner,ServiceProvided);
        // ArrayAdapter<String> CallTimeAdapter = new ArrayAdapter<String>(this,
        //       android.R.layout.simple_spinner_item,CallTime);

        JobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UnitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ServiceProvidedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // CallTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        jobType.setAdapter(JobTypeAdapter);
        unitType.setAdapter(UnitTypeAdapter);
        serviceProvided.setAdapter(ServiceProvidedAdapter);
        //callTime.setAdapter(CallTimeAdapter);
        DatePicker();
        TimePicker();
    }

    Context mContext=this;

    private void TimePicker()
    {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        callTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> callTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

    }

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public void DatePicker(){

        mDisplayDate = findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    TimeSheet.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            Log.d("tag", "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

            String date = day + "/" + month + "/" + year;
            mDisplayDate.setText(date);
        };
    }

    public void StartShift(View v){
        jobN = jobName.getText().toString();
        loc = Location.getText().toString();
        callT = callTime.getText().toString();
        jobT = jobType.getSelectedItem().toString();
        unit = unitType.getSelectedItem().toString();
        service = serviceProvided.getSelectedItem().toString();

        AsyncT send = new AsyncT();
        send.execute();

    }

    public void Cancel(View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        startActivity(i);
    }


    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {

                timeSheet.put("Job Name",jobN);
                timeSheet.put("Location",loc);
                timeSheet.put("Call Time", callT);
                timeSheet.put("Job Type",jobT);
                timeSheet.put("Unit Type", unit);
                timeSheet.put("Service Provided",service);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("req", timeSheet.toString()));

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
                Toast.makeText(getApplicationContext(), responseServer, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
                i.putExtra("first","not");
                i.putExtra("code",code);
                i.putExtra("Authorisation",authorisation);
                startActivity(i);

            } catch (Exception e) {
                e.printStackTrace();
            }
           /* if (response.length() < 3) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
           else {
                Toast.makeText(getApplicationContext(), responseServer, Toast.LENGTH_SHORT).show();
            }*/
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