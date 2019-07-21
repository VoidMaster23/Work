package com.example.cape_medics;

import android.Manifest;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Home_Screen_Crew extends AppCompatActivity {
    /*
    Button dashboard;
    Button HRform;
    Button MedRecords;
    Button timesheet;*/

    Button checkin;
    public LocationManager locationManager;
    public LocationListener locationListener;
    JSONObject checkinInfo;

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
        checkin = findViewById(R.id.button2);
        /*
        dashboard = findViewById(R.id.DashBoard);
        HRform = findViewById(R.id.HR_Forms);
        MedRecords = findViewById(R.id.MedicalRecords);
        timesheet = findViewById(R.id.TimeSheet);*/
        //create pull down menu with logout option


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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 50, locationListener);

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
        startActivity(i);
    }

    public void CheckIn(View v) {
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
       JSONObject checkin = new JSONObject();
        try {
            checkin.put("Check-in",addressBuild(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)));
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        Log.i("JSON",checkin.toString());
    }

    public void vehicle(View v){

        Intent i = new Intent(getApplicationContext(),VehicleCheckList.class);
        startActivity(i);

    }

    public void MedRec(View v){
    Intent i = new Intent(getApplicationContext(), categoryType.class);
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
}
