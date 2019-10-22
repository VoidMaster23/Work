package cape_medics;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cape_medics.Home_Screen_Crew;
import com.example.cape_medics.Login_Page;
import com.example.cape_medics.R;

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
import java.util.List;

public class TimeSheet extends AppCompatActivity {
    Spinner jobType;
    Spinner unitType;
    Spinner serviceProvided;
    EditText callTime;
    EditText jobName;
    EditText Location;
    JSONObject timeSheet, response;
    String responseServer,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheet);

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

    }

    public void StartShift(View v){
        AsyncT send = new AsyncT();
        send.execute();

        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        startActivity(i);
    }

    public void Cancel(View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        startActivity(i);
    }

    /*public String[] TimeCreator(){
        String Final = "";
        String min = "";
        String h = "";
        String d = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String DateTime = dateFormat.format(date);
        String Date = DateTime.substring(0,10);
        String Time = DateTime.substring(11,16);

        String[] TimeArray = Time.split(":");
        String[] DateArray = Date.split("/");
        int minutes = Integer.parseInt(TimeArray[1]);
        int hours = Integer.parseInt(TimeArray[0]);
        int days = Integer.parseInt(DateArray[2]);

        //initial rounding
        if (minutes<15){
            minutes = 15;
            min = "15";
        }
        else if (minutes<30){
            minutes = 30;
            min = "30";
        }
        else if (minutes<45){
            minutes = 45;
            min = "45";
        }
        else{
            minutes = 0;
            min = "00";
        }

        Time = TimeArray[0]+":"+min;
        Final = Final + "CallTime: "+Time+"-"+Date;

        for(int i=0;i<96;i++){
            minutes+=15;
            if(minutes == 60){
                minutes = 0;
                hours+=1;
                if (hours>9) {
                    h = Integer.toString(hours);
                }
                else{
                    h = Integer.toString(hours);
                    h = "0"+h;
                }
                min = "00";
            }
            if(hours == 24){
                minutes = 0;
                hours = 0;
                min = "00";
                h = "00";
                days+= 1;
                if (days>9) {
                    d = Integer.toString(days);
                }
                else{
                    d = Integer.toString(days);
                    d = "0"+h;
                }
            }

        }



        return null;
    }*/

    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {

                timeSheet.put("Job Name",jobName.getText().toString());
                timeSheet.put("Location",Location.getText().toString());
                timeSheet.put("Call Time",callTime.getText().toString());
                timeSheet.put("Job Type",jobType.getSelectedItem().toString());
                timeSheet.put("Unit Type",unitType.getSelectedItem().toString());
                timeSheet.put("Service Provided",serviceProvided.getSelectedItem().toString());
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
                response = new JSONObject(responseServer);
            } catch (JSONException e) {
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
