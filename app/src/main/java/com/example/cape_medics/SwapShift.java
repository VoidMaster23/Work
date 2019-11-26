package com.example.cape_medics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class SwapShift extends AppCompatActivity {
    JSONObject swapShift;
    EditText name;
    CheckBox early;

    CheckBox late;
    String jobName;
    String url;
    String responseServer, code, authorisation;
    String earlyOrlate;
    String myName, theDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        swapShift = new JSONObject();
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/dashboard.php";
        name = findViewById(R.id.spinner);
        early = findViewById(R.id.checkBox);
        late = findViewById(R.id.checkBox2);
        Bundle bundle = getIntent().getExtras();
        jobName = bundle.getString("job");
        code = bundle.getString("code");
        authorisation = bundle.getString("Authorisation");

        DatePicker();
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
                    SwapShift.this,
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

    public void Send (View v){
        //code get all the info
        if(early.isChecked()){
            earlyOrlate = early.getText().toString();
        }
        else if(late.isChecked()){
            earlyOrlate = late.getText().toString();

        }

        myName = name.getText().toString();
        theDate = mDisplayDate.getText().toString();

        AsyncT send = new AsyncT();
        send.execute();
    }

    public void Cancel (View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        i.putExtra("first","not");
        startActivity(i);
    }
    // make sure that both boxes arent checked
    public void uncheck(View v){
        if(v.getId() == R.id.checkBox){
            late.setChecked(false);
        }else{
            early.setChecked(false);
        }

    }

    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {

                swapShift.put("Job_Name",jobName);
                swapShift.put("Name",myName );
                swapShift.put("Date", theDate);
                swapShift.put("Time",earlyOrlate);



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", swapShift.toString()));

                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                Login_Page.InputStreamToStringExample str = new Login_Page.InputStreamToStringExample();
                responseServer = Login_Page.InputStreamToStringExample.getStringFromInputStream(inputStream);
                Log.e("response", "cake -----" + responseServer);



            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) { // check boxes
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), responseServer, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
            i.putExtra("first","not");
            i.putExtra("code",code);
            i.putExtra("Authorisation",authorisation);
            startActivity(i);


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