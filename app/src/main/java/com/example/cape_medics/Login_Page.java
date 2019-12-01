package com.example.cape_medics;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import org.apache.http.util.EntityUtils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.RequestQueue;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.http.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login_Page extends AppCompatActivity {
    JSONObject loginPage;
    JSONObject response;
    String broadcasts;
    String jobs;
    int id;
    Button login;
    EditText username;
    EditText password;
    String responseServer;
    String url;
    String auhtorisation, name;
    boolean connected;
    int times = 0;
    Cache cache;
    com.android.volley.RequestQueue requestQueue;
    JsonObjectRequest jsonRequest;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        loginPage = new JSONObject();
        cache = new Cache(getApplicationContext());
        login = findViewById(R.id.LoginButton);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        username.setPaintFlags(0);
        password.setPaintFlags(0);
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/loginPage.php";


    }

    public void Login (View v) throws JSONException {

        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);


                broadcasts  = "new first aid kits now available";
                jobs = "job 1, job 2";
                name = "Bob";
                auhtorisation = "Manager";


                i.putExtra("broadcasts",broadcasts);
                i.putExtra("jobs",jobs);
                i.putExtra("Crew ID",id);
                i.putExtra("Crew name",name);
                i.putExtra("Authorisation",auhtorisation);
                i.putExtra("first", "true");
                i.putExtra("code",password.getText().toString().trim()+username.getText().toString().trim());
                startActivity(i);

//        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//            //we are connected to a network
//            connected = true;
//        }
//        else connected = false;
//
//        if(connected) {
//            AsyncT send = new AsyncT();
//            send.execute();
//
//        }
//
//        else{
//            String value = cache.getStringProperty(password.getText().toString().trim()+username.getText().toString().trim());
//            if (value == null){
//                Toast.makeText(getApplicationContext(), "no internet connection", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                response = new JSONObject(value);
//
//                name = response.getString("Crew Name");
//                auhtorisation = response.getString("Authorisation Level");
//                broadcasts = response.getString("Broadcasts");
//                jobs = response.getString("Job ID's");
//                id = response.getInt("Crew ID");
//
//                Toast.makeText(getApplicationContext(),"Welcome "+ name, Toast.LENGTH_SHORT).show();
//
//                Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
//                i.putExtra("broadcasts",broadcasts);
//                i.putExtra("jobs",jobs);
//                i.putExtra("Crew ID",id);
//                i.putExtra("Crew name",name);
//                i.putExtra("Authorisation",auhtorisation);
//                i.putExtra("first", "true");
//                i.putExtra("code",password.getText().toString().trim()+username.getText().toString().trim());
//                startActivity(i);
//            }
//        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }


    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {

                loginPage.put("Username",username.getText().toString().trim());
                loginPage.put("Password",password.getText().toString().trim());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", loginPage.toString()));

                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
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
            if(response != null) {
                if (response.length() < 3) {
                    Toast.makeText(getApplicationContext(), "incorrect username or password", Toast.LENGTH_SHORT).show();
                }

                else {
                    try {
                        name = response.getString("Crew Name");
                        auhtorisation = response.getString("Authorisation Level");
                        broadcasts = response.getString("Broadcasts");
                        jobs = response.getString("Job ID's");
                        id = response.getInt("Crew ID");
                        Toast.makeText(getApplicationContext(),"Welcome "+ name, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Intent dash = new Intent(getApplicationContext(), DashBoard.class);

                    cache.setStringProperty(password.getText().toString().trim()+username.getText().toString().trim(), response.toString());
                    Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
                    i.putExtra("broadcasts",broadcasts);
                    i.putExtra("jobs",jobs);
                    i.putExtra("Crew ID",id);
                    i.putExtra("Crew name",name);
                    i.putExtra("Authorisation",auhtorisation);
                    i.putExtra("first", "true");
                    i.putExtra("code",password.getText().toString().trim()+username.getText().toString().trim());
                    startActivity(i);
                }
            }

            else{
                Toast.makeText(getApplicationContext(), "incorrect username or password", Toast.LENGTH_SHORT).show();
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

        /*try {
            loginPage.put("Username",username.toString());
            loginPage.put("Password",password.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //ignore this
        try {
            request.put("req",loginPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

//Json object within json object with req as key
        /*requestQueue = Volley.newRequestQueue(this);
        jsonRequest =  new JsonObjectRequest(Request.Method.POST,url, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "cake -----" + response.toString());
                Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("response", "pie ----- You Failed "+ error.toString());
            }
        });
        requestQueue.add(jsonRequest);
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                Map<String, String>  cake = new HashMap<String, String>();
                cake.put("Username",username.toString());
                cake.put("Password",password.toString());
                params.put("req",loginPage.toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);*/
