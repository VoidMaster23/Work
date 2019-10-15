package com.example.cape_medics;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Home_Screen_Crew extends AppCompatActivity {

    Button attendance, broadcast;
    String name, authorisation, broadcasts, jobs, saved;
    int id;
    Button checkinbtn;
    JSONObject checkin, load;
    public LocationManager locationManager;
    public LocationListener locationListener;
    JSONObject checkinInfo;
    String responseServer;
    String url;
    Cache cache;
    String first;
    String code;
    boolean connected;


    private static final int REQUEST_LOCATION = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                System.exit(0);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_crew);
        checkinbtn = findViewById(R.id.button2);
        attendance = findViewById(R.id.button3);
        broadcast = findViewById(R.id.button8);
        cache = new Cache(getApplicationContext());
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/checkIn.php";

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else connected = false;

        // send through another variable that if true is the first iteration.
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            first = bundle.getString("first");
            code = bundle.getString("code");
        }

        if(first.equals("true")) {

            name = bundle.getString("Crew name");
            cache.setStringProperty("name"+code, name);

            authorisation = bundle.getString("Authorisation");
            cache.setStringProperty("authorisation"+code, authorisation);

            id = bundle.getInt("Crew ID");
            cache.setStringProperty("id"+code, Integer.toString(id));

            broadcasts = bundle.getString("broadcasts");
            cache.setStringProperty("broadcasts"+code, broadcasts);

            jobs = bundle.getString("jobs");
            cache.setStringProperty("jobs"+code, jobs);
        }
        else{

            name = cache.getStringProperty("name"+code);
            authorisation = cache.getStringProperty("authorisation"+code);
            id = Integer.parseInt(cache.getStringProperty("id "+code));
            broadcasts = cache.getStringProperty("broadcasts"+code);
            jobs = cache.getStringProperty("jobs"+code);

        }

        if(connected){
            saved = cache.getStringProperty("checkIn"+code);
            if (saved != null){
                try {
                    checkin = new JSONObject(saved);
                    AsyncT send = new AsyncT();
                    send.execute();
                    cache.removeStringProperty("checkIn"+code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        if(!authorisation.equals("Manager")){
            attendance.setVisibility(View.INVISIBLE);
            broadcast.setVisibility(View.INVISIBLE);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());


                try {
                    List<Address> addressList = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                    if (addressList != null && addressList.size() > 0) {


                        StringBuilder address = new StringBuilder();
                        if (addressList.get(0).getAddressLine(0) != null) {
                            if (addressList.get(0).getSubThoroughfare() != null) {
                                address.append(addressList.get(0).getSubThoroughfare() + " ");
                            }

                            //Street
                            if (addressList.get(0).getThoroughfare() != null) {
                                address.append(addressList.get(0).getThoroughfare() + " ");
                            }

                            //City
                            if (addressList.get(0).getLocality() != null) {
                                address.append(addressList.get(0).getLocality() + " ");
                            }

                            //province
                            if (addressList.get(0).getAdminArea() != null) {
                                address.append(addressList.get(0).getAdminArea() + " ");
                            }

                            if (addressList.get(0).getCountryName() != null) {
                                address.append(addressList.get(0).getCountryName() + " ");
                            }
                        } else {
                            address.append(java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime()));
                        }

                    }
                } catch (Exception ed) {
                    ed.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.new_game) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Log out")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getApplicationContext(), Login_Page.class);
                            startActivity(i);
                        }
                    }).show();

            return true;
        }
        return true;
    }


    public void Dash(View v) {
        Intent i = new Intent(getApplicationContext(), DashBoard.class);
        i.putExtra("broadcasts",broadcasts);
        i.putExtra("jobs",jobs);
        startActivity(i);
    }

    public void CheckIn(View v) {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else connected = false;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        checkin = new JSONObject();
        try {
            if(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) == null){
                checkin.put("Check-in",addressBuild(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)));
            }else {
                checkin.put("Check-in", addressBuild(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)));
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        if(connected) {
            AsyncT send = new AsyncT();
            send.execute();
        }
        else{
            cache.setStringProperty("checkIn"+code,checkin.toString());
        }

        Log.i("JSON",checkin.toString());
    }

    public void vehicle(View v){

        Intent i = new Intent(getApplicationContext(),VehicleCheckList.class);
        i.putExtra("code",code);
        startActivity(i);

    }

    public void MedRec(View v){
    Intent i = new Intent(getApplicationContext(), categoryType.class);
    i.putExtra("code",code);
    startActivity(i);

    }

    public void TimeSheet(View v){
        Intent i = new Intent(getApplicationContext(), TimeSheet.class);
        startActivity(i);

    }

    public void Attendance (View v){
        Intent i = new Intent(getApplicationContext(), ViewAttendance.class);
        startActivity(i);

    }

    public void BroadCast (View v){
        Intent i = new Intent(getApplicationContext(), ChooseBroadCast.class);
        startActivity(i);

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(),Login_Page.class);
                        startActivity(i);
                    }
                }).show();
    }

    public JSONObject addressBuild(Location location){

       JSONObject checkinInfo = new JSONObject();

        Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
        Log.i("Latitude",location.toString());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        Date time = new Date();

        try {
            List<Address> addressList = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addressList != null && addressList.size() > 0) {


                StringBuilder address = new StringBuilder();
                if (addressList.get(0).getAddressLine(0) != null) {
                    if (addressList.get(0).getSubThoroughfare() != null) {
                        address.append(addressList.get(0).getSubThoroughfare() + " ");
                    }

                    //Street
                    if (addressList.get(0).getThoroughfare() != null) {
                        address.append(addressList.get(0).getThoroughfare() + ", ");
                    }

                    //City
                    if (addressList.get(0).getLocality() != null) {
                        address.append(addressList.get(0).getLocality() + ", ");
                    }

                    //province
                    if (addressList.get(0).getAdminArea() != null) {
                        address.append(addressList.get(0).getAdminArea() + ", ");
                    }

                    if (addressList.get(0).getCountryName() != null) {
                        address.append(addressList.get(0).getCountryName());
                    }

                    //address.append(dateFormat.format(date));
                    //address.append(java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime()));
                }
               // address.append(dateFormat.format(date));

                checkinInfo.put("Location", address.toString());
                checkinInfo.put("Date", dateFormat.format(date));
                checkinInfo.put("Time",timeFormat.format(time));


            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return checkinInfo;
    }

    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", checkin.toString()));

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
            Toast.makeText(getApplicationContext(), checkin.toString(), Toast.LENGTH_SHORT).show();
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
