package com.example.cape_medics;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FireSkid extends AppCompatActivity {
    JSONObject fireSkid, response, save, load;
    Spinner skid, fuelSpinner;
    TextView dateView;
    CheckBox air,antennae,battery,body,brake,branding,dashboard,emergency,exhaust,oil,fuelLevel,headlights,leftIndicator,rightIndicator,back,front,jack,leds,licence,plates,radio,rear,reverse,side,siren,spare,tread,pressure,windows,windscreen,waterSpritzer,c02,dcp,fuelFunnel,awgPencil,falseSpindles,layflat25,short65,duraflex38,afff,boltCutter,wireCutter,crowBar,hondaKey,hondaPump,highPump,rakeHoe,bushBeater,jerryCan,softSuction,strainer,towRope,meterStandpipe,standpipeKey,blankCoupling65,blankCoupling25,trafficCones,bobejaan,toolbox,shovel;
    EditText airComment,antennaeComment,batteryComment,bodyComment,brakeComment,brandingComment,dashboardComment,emergencyComment,exhaustComment,oilComment,fuelLevelComment,headlightsComment,leftIndicatorComment,rightIndicatorComment,backComment,frontComment,jackComment,ledsComment,licenceComment,platesComment,radioComment,rearComment,reverseComment,sideComment,sirenComment,spareComment,treadComment,pressureComment,windowsComment,windscreenComment,waterSpritzerComment,c02Comment,dcpComment,fuelFunnelComment,awgPencilComment,falseSpindlesComment,layflat25Comment,short65Comment,duraflex38Comment,afffComment,boltCutterComment,wireCutterComment,crowBarComment,hondaKeyComment,hondaPumpComment,highPumpComment,rakeHoeComment,bushBeaterComment,jerryCanComment,softSuctionComment,strainerComment,towRopeComment,meterStandpipeComment,standpipeKeyComment,blankCoupling65Comment,blankCoupling25Comment,trafficConesComment,bobejaanComment,toolboxComment,shovelComment;
    List<CheckBox> checkBoxList;
    List<EditText> commentList;
    EditText licenceExpire,driver,mileage,oils,reg,location,producion,crew;
    String Date, responseServer, url;
    boolean connected;
    ScheduledExecutorService scheduledExecutorService;
    Cache cache;
    String saved;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_skid);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");

        licenceExpire = findViewById(R.id.licenceExpireEdit);
        driver = findViewById(R.id.driverEdit);
        mileage = findViewById(R.id.mileageEdit);
        oils = findViewById(R.id.oilsEdit);
        reg = findViewById(R.id.regEdit);
        location = findViewById(R.id.locationEdit);
        producion = findViewById(R.id.productionEdit);
        crew = findViewById(R.id.crewEdit);
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/vehicleChecklist.php";
        cache = new Cache(getApplicationContext());
        save = new JSONObject();

        skid = findViewById(R.id.vehicleNumberSpinner);
        fuelSpinner = findViewById(R.id.fuelSpinner);
        dateView = findViewById(R.id.dateView);
        air = findViewById(R.id.air); antennae = findViewById(R.id.antennae); battery= findViewById(R.id.battery);body= findViewById(R.id.body);brake= findViewById(R.id.brake);branding = findViewById(R.id.branding);dashboard = findViewById(R.id.dashboard);emergency = findViewById(R.id.emergency);exhaust = findViewById(R.id.exhaust);oil = findViewById(R.id.oil);fuelLevel = findViewById(R.id.fuelLevel);headlights = findViewById(R.id.headlights);leftIndicator = findViewById(R.id.leftIndicator);rightIndicator = findViewById(R.id.rightIndicator);back = findViewById(R.id.back);front = findViewById(R.id.front);jack = findViewById(R.id.jack);leds = findViewById(R.id.leds);licence = findViewById(R.id.licence);plates = findViewById(R.id.plates);radio = findViewById(R.id.radio);rear = findViewById(R.id.rear);reverse = findViewById(R.id.reverse);side = findViewById(R.id.side);siren = findViewById(R.id.siren);spare = findViewById(R.id.spare);tread = findViewById(R.id.tread);pressure = findViewById(R.id.pressure);windows = findViewById(R.id.windows);windscreen = findViewById(R.id.windscreen);waterSpritzer = findViewById(R.id.waterSpritzer);c02 = findViewById(R.id.c02);dcp = findViewById(R.id.dcp);fuelFunnel = findViewById(R.id.fuelFunnel);awgPencil = findViewById(R.id.awgPencil);falseSpindles = findViewById(R.id.falseSpindles);layflat25 = findViewById(R.id.layflat25);short65 = findViewById(R.id.short65);duraflex38 = findViewById(R.id.duraflex38);afff = findViewById(R.id.afff);boltCutter = findViewById(R.id.boltCutter);wireCutter = findViewById(R.id.wireCutter);crowBar = findViewById(R.id.crowBar);hondaKey = findViewById(R.id.hondaKey);hondaPump = findViewById(R.id.hondaPump);highPump = findViewById(R.id.highPump);rakeHoe = findViewById(R.id.rakeHoe);bushBeater = findViewById(R.id.bushBeater);jerryCan = findViewById(R.id.jerryCan);softSuction = findViewById(R.id.softSuction);strainer = findViewById(R.id.strainer);towRope = findViewById(R.id.towRope);meterStandpipe = findViewById(R.id.meterStandpipe);standpipeKey = findViewById(R.id.standpipeKey);blankCoupling65 = findViewById(R.id.blankCoupling65);blankCoupling25 = findViewById(R.id.blankCoupling25);trafficCones = findViewById(R.id.trafficCones);bobejaan = findViewById(R.id.bobejaan);toolbox = findViewById(R.id.toolbox);shovel = findViewById(R.id.shovel);
        airComment = findViewById(R.id.airComment); antennaeComment = findViewById(R.id.antennaeComment); batteryComment = findViewById(R.id.batteryComment);bodyComment= findViewById(R.id.bodyComment);brakeComment = findViewById(R.id.brakeComment);brandingComment = findViewById(R.id.brandingComment);dashboardComment = findViewById(R.id.dashboardComment);emergencyComment = findViewById(R.id.emergencyComment);exhaustComment = findViewById(R.id.exhaustComment);oilComment = findViewById(R.id.oilComment);fuelLevelComment = findViewById(R.id.fuelLevelComment);headlightsComment = findViewById(R.id.headlightsComment);leftIndicatorComment = findViewById(R.id.leftIndicatorComment);rightIndicatorComment = findViewById(R.id.rightIndicatorComment);backComment = findViewById(R.id.backComment);frontComment = findViewById(R.id.frontComment);jackComment = findViewById(R.id.jackComment);ledsComment = findViewById(R.id.ledsComment);licenceComment = findViewById(R.id.licenceComment);platesComment = findViewById(R.id.platesComment);radioComment = findViewById(R.id.radioComment);rearComment = findViewById(R.id.rearComment);reverseComment = findViewById(R.id.reverseComment);sideComment = findViewById(R.id.sideComment);sirenComment = findViewById(R.id.sirenComment);spareComment = findViewById(R.id.spareComment);treadComment = findViewById(R.id.treadComment);pressureComment = findViewById(R.id.pressureComment);windowsComment = findViewById(R.id.windowsComment);windscreenComment = findViewById(R.id.windscreenComment);waterSpritzerComment = findViewById(R.id.waterSpritzerComment);c02Comment = findViewById(R.id.c02Comment);dcpComment = findViewById(R.id.dcpComment);fuelFunnelComment = findViewById(R.id.fuelFunnelComment);awgPencilComment = findViewById(R.id.awgPencilComment);falseSpindlesComment = findViewById(R.id.falseSpindlesComment);layflat25Comment = findViewById(R.id.layflat25Comment);short65Comment = findViewById(R.id.short65Comment);duraflex38Comment = findViewById(R.id.duraflex38Comment);afffComment = findViewById(R.id.afffComment);boltCutterComment = findViewById(R.id.boltCutterComment);wireCutterComment = findViewById(R.id.wireCutterComment);crowBarComment = findViewById(R.id.crowBarComment);hondaKeyComment = findViewById(R.id.hondaKeyComment);hondaPumpComment = findViewById(R.id.hondaPumpComment);highPumpComment = findViewById(R.id.highPumpComment);rakeHoeComment = findViewById(R.id.rakeHoeComment);bushBeaterComment = findViewById(R.id.bushBeaterComment);jerryCanComment = findViewById(R.id.jerryCanComment);softSuctionComment = findViewById(R.id.softSuctionComment);strainerComment = findViewById(R.id.strainerComment);towRopeComment = findViewById(R.id.towRopeComment);meterStandpipeComment = findViewById(R.id.meterStandpipeComment);standpipeKeyComment = findViewById(R.id.standpipeKeyComment);blankCoupling65Comment = findViewById(R.id.blankCoupling65Comment);blankCoupling25Comment = findViewById(R.id.blankCoupling25Comment);trafficConesComment = findViewById(R.id.trafficConesComment);bobejaanComment = findViewById(R.id.bobejaanComment);toolboxComment = findViewById(R.id.toolboxComment);shovelComment = findViewById(R.id.shovelComment);

        checkBoxList = Arrays.asList(air,antennae,battery,body,brake,branding,dashboard,emergency,exhaust,oil,fuelLevel,headlights,leftIndicator,rightIndicator,back,front,jack,leds,licence,plates,radio,rear,reverse,side,siren,spare,tread,pressure,windows,windscreen,waterSpritzer,c02,dcp,fuelFunnel,awgPencil,falseSpindles,layflat25,short65,duraflex38,afff,boltCutter,wireCutter,crowBar,hondaKey,hondaPump,highPump,rakeHoe,bushBeater,jerryCan,softSuction,strainer,towRope,meterStandpipe,standpipeKey,blankCoupling65,blankCoupling25,trafficCones,bobejaan,toolbox,shovel);
        commentList = Arrays.asList(airComment,antennaeComment,batteryComment,bodyComment,brakeComment,brandingComment,dashboardComment,emergencyComment,exhaustComment,oilComment,fuelLevelComment,headlightsComment,leftIndicatorComment,rightIndicatorComment,backComment,frontComment,jackComment,ledsComment,licenceComment,platesComment,radioComment,rearComment,reverseComment,sideComment,sirenComment,spareComment,treadComment,pressureComment,windowsComment,windscreenComment,waterSpritzerComment,c02Comment,dcpComment,fuelFunnelComment,awgPencilComment,falseSpindlesComment,layflat25Comment,short65Comment,duraflex38Comment,afffComment,boltCutterComment,wireCutterComment,crowBarComment,hondaKeyComment,hondaPumpComment,highPumpComment,rakeHoeComment,bushBeaterComment,jerryCanComment,softSuctionComment,strainerComment,towRopeComment,meterStandpipeComment,standpipeKeyComment,blankCoupling65Comment,blankCoupling25Comment,trafficConesComment,bobejaanComment,toolboxComment,shovelComment);

        fireSkid = new JSONObject();
        String[] Skid = {"GWM","ISUZU BAKKIE"};
        String[] Fuel = {"Empty","Half","Full"};

        ArrayAdapter<String> skidAdapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner,Skid);
        ArrayAdapter<String> fuelAdapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner,Fuel);

        skidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        skid.setAdapter(skidAdapter);
        fuelSpinner.setAdapter(fuelAdapter);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String DateTime = dateFormat.format(date);
        Date = DateTime.substring(0,10);

        dateView.setText(Date);

        saved = cache.getStringProperty("vehicleSaveSkid"+code);
        if( saved != null){
            try {
                load = new JSONObject(saved);
                skid.setSelection(load.getInt("Fire_Engine"));
                fuelSpinner.setSelection(load.getInt("Fuel"));
                licenceExpire.setText(load.getString("Licence_Expire"));
                driver.setText(load.getString("Driver"));
                mileage.setText(load.getString("Mileage"));
                oils.setText(load.getString("Oils"));
                reg.setText(load.getString("Reg_No."));
                location.setText(load.getString("Location."));
                producion.setText(load.getString("Production"));
                crew.setText(load.getString("Crew"));
                Iterator<String> keys = load.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    String item = load.getString(key);
                    for (int j = 0; j<checkBoxList.size();j++) {
                        if (checkBoxList.get(j) != null) {
                            if (key.equals(checkBoxList.get(j).getText().toString())) {
                                checkBoxList.get(j).setChecked(true);
                                commentList.get(j).setText(item);

                            }
                        }
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                FireSkid.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            save.put("Licence_Expire", licenceExpire.getText().toString());
                            save.put("Date", Date);
                            save.put("Driver", driver.getText().toString());
                            save.put("Fire_Engine", skid.getSelectedItemPosition());
                            save.put("Mileage", mileage.getText().toString());
                            save.put("Fuel", fuelSpinner.getSelectedItemPosition());
                            save.put("Oils", oils.getText().toString());


                            save.put("Reg_No.", reg.getText().toString());
                            save.put("Location.", location.getText().toString());
                            save.put("Production", producion.getText().toString());
                            save.put("Crew", crew.getText().toString());

                            for (int i = 0; i<checkBoxList.size();i++) {
                                if (checkBoxList.get(i) != null) {
                                    if (checkBoxList.get(i).isChecked()) {
                                        if (commentList.get(i).getText() != null) {
                                            save.put(checkBoxList.get(i).getText().toString(), commentList.get(i).getText().toString());
                                        }

                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        cache.setStringProperty("vehicleSaveSkid"+code,save.toString());
                    }
                });
            }
        },30,30, TimeUnit.SECONDS);




    }

    public void Send(View v){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else connected = false;
        if (connected) {
            AsyncT send = new AsyncT();
            send.execute();
            Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
            cache.removeStringProperty("vehicleSaveSkid"+code);
            scheduledExecutorService.shutdown();
            i.putExtra("code",code);
            i.putExtra("first","not");
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please establish an internet connection", Toast.LENGTH_SHORT).show();
        }

    }
    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {
                fireSkid.put("Fire_Vehicle_Type", "Fire Skid");
                fireSkid.put("Vehicle_Type", "Fire");
                fireSkid.put("Licence_Expire", licenceExpire.getText().toString());
                fireSkid.put("Date", Date);
                fireSkid.put("Driver", driver.getText().toString());
                fireSkid.put("Fire_Engine", skid.getSelectedItem().toString());
                fireSkid.put("Mileage", mileage.getText().toString());
                fireSkid.put("Fuel", fuelSpinner.getSelectedItem().toString());
                fireSkid.put("Oils", oils.getText().toString());
                fireSkid.put("Reg_No.", reg.getText().toString());
                fireSkid.put("Location.", location.getText().toString());
                fireSkid.put("Production", producion.getText().toString());
                fireSkid.put("Crew", crew.getText().toString());

                for (int i = 0; i<checkBoxList.size();i++) {
                    if (checkBoxList.get(i) != null) {
                        if (!checkBoxList.get(i).isChecked()) {
                            if (commentList.get(i).getText() != null) {
                                fireSkid.put(checkBoxList.get(i).getText().toString(), commentList.get(i).getText().toString());
                            } else
                                fireSkid.put(checkBoxList.get(i).getText().toString(), "No Comment");

                        }
                    }
                }
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", fireSkid.toString()));

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

          /*  try {
                response = new JSONObject(responseServer);
            } catch (JSONException e) {
                e.printStackTrace();
            }
           if (response.length() < 3) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
           else {
                Toast.makeText(getApplicationContext(), "sent", Toast.LENGTH_SHORT).show();
            }*/
            Toast.makeText(getApplicationContext(), "sent", Toast.LENGTH_SHORT).show();
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
