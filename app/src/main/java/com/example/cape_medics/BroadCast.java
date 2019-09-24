package com.example.cape_medics;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import java.util.Iterator;
import java.util.List;

public class BroadCast extends AppCompatActivity {
    JSONObject broadcast;
    JSONObject response;
    JSONObject jobJson;
    ListView crewName;
    CheckBox selectAll;
    List<String> Names;
    String jobName;
    Dialog popup;
    EditText message;
    String responseString;
    List CrewName;
    String url, code;
    String responseServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        broadcast = new JSONObject();
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/sendBroadcast.php";
        CrewName = new ArrayList();
        crewName = findViewById(R.id.listView);
        selectAll = findViewById(R.id.checkBox);
        Names = new ArrayList<String>();

        Bundle bundle = getIntent().getExtras();
        //use this to pull list from db
        jobName = bundle.getString("job");
        responseString = bundle.getString("names");
        code = bundle.getString("code");
        try {
            response = new JSONObject(responseString);
            String item = response.getString(jobName);
            jobJson = new JSONObject(item);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Iterator<String> keys = jobJson.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            try {
                String item = jobJson.getString(key);
                CrewName.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        crewName.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //String[] CrewName = {"Bob", "Jim", "Carrol"}; // pull from db

        ArrayAdapter<String> CrewNameAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_checked_list,CrewName);

        crewName.setAdapter(CrewNameAdapter);

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectAll.isChecked()) {

                    for (int i = 0; i < crewName.getChildCount(); i++) {
                        crewName.setItemChecked(i, true);
                    }
                }
                else{
                    for (int i = 0; i < crewName.getChildCount(); i++) {
                        crewName.setItemChecked(i, false);
                    }
                }

            }
        });
    }

    public void Choose (View v){
        //send to db
        for (int i = 0; i < crewName.getChildCount(); i++) {

            if (crewName.isItemChecked(i)){
                Names.add(crewName.getItemAtPosition(i).toString());
            }
        }


        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        // create the popup window
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, getResources().getDisplayMetrics());
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 475, getResources().getDisplayMetrics());
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, (int)width, (int)height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // dismiss the popup window when X touched
        popupView.findViewById(R.id.txtclose).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        message = popupView.findViewById(R.id.message);

        //send the message
        popupView.findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send from here
                for (int i=0;i<Names.size();i++){
                    try {
                        broadcast.put(Names.get(i), message.toString());
                    }catch(Exception e){}
                }
                AsyncT send = new AsyncT();
                send.execute();
                popupWindow.dismiss();

            }
        });

        //send Names array list to other activity

        //Intent i = new Intent(getApplicationContext(), BroadCastMessage.class);
        // startActivity(i);


    }

    public void Cancel (View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        i.putExtra("first","not");
        i.putExtra("code", code);
        startActivity(i);
    }

    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", broadcast.toString()));

                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                httppost.addHeader("Cookie", "__test=fdeabe1d0dca3968e3da6a69937fc2e6; expires=Thu, 31-Dec-37 23:55:55 GMT; path=/");

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
            Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
            i.putExtra("first","not");
            i.putExtra("code", code);
            Toast.makeText(getApplicationContext(),"Message Sent!", Toast.LENGTH_SHORT).show();
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
