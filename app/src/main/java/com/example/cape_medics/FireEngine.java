package com.example.cape_medics;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cape_medics.TableLayout.Row;

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
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FireEngine extends AppCompatActivity {
    JSONObject fireEngine, response, save, load;
    Spinner engine,fuelSpinner;
    TextView dateView,licenceExpire;
    String Date;
    CheckBox air,antennae,battery,body,brake,branding,dashboard,emergency,exhaust,oil,fuelLevel,headlights,leftIndicator,rightIndicator,back,front,jack,leds,licence,plates,radio,rear,reverse,side,siren,spare,tread,pressure,windows,windscreen,waterSpritzer,c02,dcp,fuelFunnel,awgPencil,gekkaTwister,Pistol65,falseSpindles,layflat25,short65,duraflex65,duraflex38,afff,boltCutter,wireCutter,crowBar,hondaKey,hondaPump,highPump,rakeHoe,bushBeater,jerryCan,softSuction,strainer,towRope,meterStandpipe,standpipeKey,blankCoupling65,blankCoupling25,controlDivider,gekkaReducer,gekkaDivider,baBottles,baSets,trafficCones,bobejaan,toolbox,trashpump,combiTool,batteries,charger,chains,cutters,batteriesCutters,chargerCutter,ram,batteriesRam,ramExtension,saw,spareBlades,batteriesSaw,chargerSaw,chockBlocks,shovel;
    EditText airComment,antennaeComment,batteryComment,bodyComment,brakeComment,brandingComment,dashboardComment,emergencyComment,exhaustComment,oilComment,fuelLevelComment,headlightsComment,leftIndicatorComment,rightIndicatorComment,backComment,frontComment,jackComment,ledsComment,licenceComment,platesComment,radioComment,rearComment,reverseComment,sideComment,sirenComment,spareComment,treadComment,pressureComment,windowsComment,windscreenComment,waterSpritzerComment,c02Comment,dcpComment,fuelFunnelComment,awgPencilComment,gekkaTwisterComment,Pistol65Comment,falseSpindlesComment,layflat25Comment,short65Comment,duraflex65Comment,duraflex38Comment,afffComment,boltCutterComment,wireCutterComment,crowBarComment,hondaKeyComment,hondaPumpComment,highPumpComment,rakeHoeComment,bushBeaterComment,jerryCanComment,softSuctionComment,strainerComment,towRopeComment,meterStandpipeComment,standpipeKeyComment,blankCoupling65Comment,blankCoupling25Comment,controlDividerComment,gekkaReducerComment,gekkaDividerComment,baBottlesComment,baSetsComment,trafficConesComment,bobejaanComment,toolboxComment,trashpumpComment,combiToolComment,batteriesComment,chargerComment,chainsComment,cuttersComment,batteriesCuttersComment,chargerCutterComment,ramComment,batteriesRamComment,ramExtensionComment,sawComment,spareBladesComment,batteriesSawComment,chargerSawComment,chockBlocksComment,shovelComment;
    List<CheckBox> checkBoxList;
    List<EditText> commentList;
    EditText driver,mileage,oils,reg,location,producion,crew;
    String url, responseServer, code, saved;
    Cache cache;
    ScheduledExecutorService scheduledExecutorService;
    boolean connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_engine2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        fireEngine = new JSONObject();
        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");
        cache = new Cache(getApplicationContext());
        save = new JSONObject();

        licenceExpire = findViewById(R.id.licenceExpireEdit);
        driver = findViewById(R.id.driverEdit);
        mileage = findViewById(R.id.mileageEdit);
        oils = findViewById(R.id.oilsEdit);
        reg = findViewById(R.id.regEdit);
        location = findViewById(R.id.locationEdit);
        producion = findViewById(R.id.productionEdit);
        crew = findViewById(R.id.crewEdit);

        fuelSpinner = findViewById(R.id.fuelSpinner);
        engine = findViewById(R.id.vehicleNumberSpinner);
        dateView = findViewById(R.id.dateView);
        //air = findViewById(R.id.air); antennae = findViewById(R.id.antennae); battery= findViewById(R.id.battery);body= findViewById(R.id.body);brake= findViewById(R.id.brake);branding = findViewById(R.id.branding);dashboard = findViewById(R.id.dashboard);emergency = findViewById(R.id.emergency);exhaust = findViewById(R.id.exhaust);oil = findViewById(R.id.oil);fuelLevel = findViewById(R.id.fuelLevel);headlights = findViewById(R.id.headlights);leftIndicator = findViewById(R.id.leftIndicator);rightIndicator = findViewById(R.id.rightIndicator);back = findViewById(R.id.back);front = findViewById(R.id.front);jack = findViewById(R.id.jack);leds = findViewById(R.id.leds);licence = findViewById(R.id.licence);plates = findViewById(R.id.plates);radio = findViewById(R.id.radio);rear = findViewById(R.id.rear);reverse = findViewById(R.id.reverse);side = findViewById(R.id.side);siren = findViewById(R.id.siren);spare = findViewById(R.id.spare);tread = findViewById(R.id.tread);pressure = findViewById(R.id.pressure);windows = findViewById(R.id.windows);windscreen = findViewById(R.id.windscreen);waterSpritzer = findViewById(R.id.waterSpritzer);c02 = findViewById(R.id.c02);dcp = findViewById(R.id.dcp);fuelFunnel = findViewById(R.id.fuelFunnel);awgPencil = findViewById(R.id.awgPencil);gekkaTwister = findViewById(R.id.gekkaTwister);Pistol65 = findViewById(R.id.Pistol65);falseSpindles = findViewById(R.id.falseSpindles);layflat25 = findViewById(R.id.layflat25);short65 = findViewById(R.id.short65);duraflex65 = findViewById(R.id.duraflex65);duraflex38 = findViewById(R.id.duraflex38);afff = findViewById(R.id.afff);boltCutter = findViewById(R.id.boltCutter);wireCutter = findViewById(R.id.wireCutter);crowBar = findViewById(R.id.crowBar);hondaKey = findViewById(R.id.hondaKey);hondaPump = findViewById(R.id.hondaPump);highPump = findViewById(R.id.highPump);rakeHoe = findViewById(R.id.rakeHoe);bushBeater = findViewById(R.id.bushBeater);jerryCan = findViewById(R.id.jerryCan);softSuction = findViewById(R.id.softSuction);strainer = findViewById(R.id.strainer);towRope = findViewById(R.id.towRope);meterStandpipe = findViewById(R.id.meterStandpipe);standpipeKey = findViewById(R.id.standpipeKey);blankCoupling65 = findViewById(R.id.blankCoupling65);blankCoupling25 = findViewById(R.id.blankCoupling25);controlDivider = findViewById(R.id.controlDivider);gekkaReducer = findViewById(R.id.gekkaReducer);gekkaDivider = findViewById(R.id.gekkaDivider);baBottles = findViewById(R.id.baBottles);baSets = findViewById(R.id.baSets);trafficCones = findViewById(R.id.trafficCones);bobejaan = findViewById(R.id.bobejaan);toolbox = findViewById(R.id.toolbox);trashpump = findViewById(R.id.trashpump);combiTool = findViewById(R.id.combiTool);batteries = findViewById(R.id.batteries);charger = findViewById(R.id.charger);chains = findViewById(R.id.chains);cutters = findViewById(R.id.cutters);batteriesCutters = findViewById(R.id.batteriesCutters);chargerCutter = findViewById(R.id.chargerCutter);ram = findViewById(R.id.ram);batteriesRam = findViewById(R.id.batteriesRam);ramExtension = findViewById(R.id.ramExtension);saw = findViewById(R.id.saw);spareBlades = findViewById(R.id.spareBlades);batteriesSaw = findViewById(R.id.batteriesSaw);chargerSaw = findViewById(R.id.chargerSaw);chockBlocks = findViewById(R.id.chockBlocks);shovel = findViewById(R.id.shovel);
        //airComment = findViewById(R.id.airComment); antennaeComment = findViewById(R.id.antennaeComment); batteryComment = findViewById(R.id.batteryComment);bodyComment= findViewById(R.id.bodyComment);brakeComment = findViewById(R.id.brakeComment);brandingComment = findViewById(R.id.brandingComment);dashboardComment = findViewById(R.id.dashboardComment);emergencyComment = findViewById(R.id.emergencyComment);exhaustComment = findViewById(R.id.exhaustComment);oilComment = findViewById(R.id.oilComment);fuelLevelComment = findViewById(R.id.fuelLevelComment);headlightsComment = findViewById(R.id.headlightsComment);leftIndicatorComment = findViewById(R.id.leftIndicatorComment);rightIndicatorComment = findViewById(R.id.rightIndicatorComment);backComment = findViewById(R.id.backComment);frontComment = findViewById(R.id.frontComment);jackComment = findViewById(R.id.jackComment);ledsComment = findViewById(R.id.ledsComment);licenceComment = findViewById(R.id.licenceComment);platesComment = findViewById(R.id.platesComment);radioComment = findViewById(R.id.radioComment);rearComment = findViewById(R.id.rearComment);reverseComment = findViewById(R.id.reverseComment);sideComment = findViewById(R.id.sideComment);sirenComment = findViewById(R.id.sirenComment);spareComment = findViewById(R.id.spareComment);treadComment = findViewById(R.id.treadComment);pressureComment = findViewById(R.id.pressureComment);windowsComment = findViewById(R.id.windowsComment);windscreenComment = findViewById(R.id.windscreenComment);waterSpritzerComment = findViewById(R.id.waterSpritzerComment);c02Comment = findViewById(R.id.c02Comment);dcpComment = findViewById(R.id.dcpComment);fuelFunnelComment = findViewById(R.id.fuelFunnelComment);awgPencilComment = findViewById(R.id.awgPencilComment);gekkaTwisterComment = findViewById(R.id.gekkaTwisterComment);Pistol65Comment = findViewById(R.id.Pistol65Comment);falseSpindlesComment = findViewById(R.id.falseSpindlesComment);layflat25Comment = findViewById(R.id.layflat25Comment);short65Comment = findViewById(R.id.short65Comment);duraflex65Comment = findViewById(R.id.duraflex65Comment);duraflex38Comment = findViewById(R.id.duraflex38Comment);afffComment = findViewById(R.id.afffComment);boltCutterComment = findViewById(R.id.boltCutterComment);wireCutterComment = findViewById(R.id.wireCutterComment);crowBarComment = findViewById(R.id.crowBarComment);hondaKeyComment = findViewById(R.id.hondaKeyComment);hondaPumpComment = findViewById(R.id.hondaPumpComment);highPumpComment = findViewById(R.id.highPumpComment);rakeHoeComment = findViewById(R.id.rakeHoeComment);bushBeaterComment = findViewById(R.id.bushBeaterComment);jerryCanComment = findViewById(R.id.jerryCanComment);softSuctionComment = findViewById(R.id.softSuctionComment);strainerComment = findViewById(R.id.strainerComment);towRopeComment = findViewById(R.id.towRopeComment);meterStandpipeComment = findViewById(R.id.meterStandpipeComment);standpipeKeyComment = findViewById(R.id.standpipeKeyComment);blankCoupling65Comment = findViewById(R.id.blankCoupling65Comment);blankCoupling25Comment = findViewById(R.id.blankCoupling25Comment);controlDividerComment = findViewById(R.id.controlDividerComment);gekkaReducerComment = findViewById(R.id.gekkaReducerComment);gekkaDividerComment = findViewById(R.id.gekkaDividerComment);baBottlesComment = findViewById(R.id.baBottlesComment);baSetsComment = findViewById(R.id.baSetsComment);trafficConesComment = findViewById(R.id.trafficConesComment);bobejaanComment = findViewById(R.id.bobejaanComment);toolboxComment = findViewById(R.id.toolboxComment);trashpumpComment = findViewById(R.id.trashpumpComment);combiToolComment = findViewById(R.id.combiToolComment);batteriesComment = findViewById(R.id.batteriesComment);chargerComment = findViewById(R.id.chargerComment);chainsComment = findViewById(R.id.chainsComment);cuttersComment = findViewById(R.id.cuttersComment);batteriesCuttersComment = findViewById(R.id.batteriesCuttersComment);chargerCutterComment = findViewById(R.id.chargerCutterComment);ramComment = findViewById(R.id.ramComment);batteriesRamComment = findViewById(R.id.batteriesRamComment);ramExtensionComment = findViewById(R.id.ramExtensionComment);sawComment = findViewById(R.id.sawComment);spareBladesComment = findViewById(R.id.spareBladesComment);batteriesSawComment = findViewById(R.id.batteriesSawComment);chargerSawComment = findViewById(R.id.chargerSawComment);chockBlocksComment = findViewById(R.id.chockBlocksComment);shovelComment = findViewById(R.id.shovelComment);
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/vehicleChecklist.php";
        String[] Engine = {"ISUZU","FUSO"};
        ArrayAdapter<String> engineAdapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner,Engine);

        engineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        engine.setAdapter(engineAdapter);


        String[] Fuel = {"Empty","Half","Full"};

        ArrayAdapter<String> fuelAdapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner,Fuel);

        fuelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fuelSpinner.setAdapter(fuelAdapter);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String DateTime = dateFormat.format(date);
        Date = DateTime.substring(0,10);
        dateView.setText(Date);

        checkBoxList = Arrays.asList(air,antennae,battery,body,brake,branding,dashboard,emergency,exhaust,oil,fuelLevel,headlights,leftIndicator,rightIndicator,back,front,jack,leds,licence,plates,radio,rear,reverse,side,siren,spare,tread,pressure,windows,windscreen,waterSpritzer,c02,dcp,fuelFunnel,awgPencil,gekkaTwister,Pistol65,falseSpindles,layflat25,short65,duraflex65,duraflex38,afff,boltCutter,wireCutter,crowBar,hondaKey,hondaPump,highPump,rakeHoe,bushBeater,jerryCan,softSuction,strainer,towRope,meterStandpipe,standpipeKey,blankCoupling65,blankCoupling25,controlDivider,gekkaReducer,gekkaDivider,baBottles,baSets,trafficCones,bobejaan,toolbox,trashpump,combiTool,batteries,charger,chains,cutters,batteriesCutters,chargerCutter,ram,batteriesRam,ramExtension,saw,spareBlades,batteriesSaw,chargerSaw,chockBlocks,shovel);
        commentList = Arrays.asList(airComment,antennaeComment,batteryComment,bodyComment,brakeComment,brandingComment,dashboardComment,emergencyComment,exhaustComment,oilComment,fuelLevelComment,headlightsComment,leftIndicatorComment,rightIndicatorComment,backComment,frontComment,jackComment,ledsComment,licenceComment,platesComment,radioComment,rearComment,reverseComment,sideComment,sirenComment,spareComment,treadComment,pressureComment,windowsComment,windscreenComment,waterSpritzerComment,c02Comment,dcpComment,fuelFunnelComment,awgPencilComment,gekkaTwisterComment,Pistol65Comment,falseSpindlesComment,layflat25Comment,short65Comment,duraflex65Comment,duraflex38Comment,afffComment,boltCutterComment,wireCutterComment,crowBarComment,hondaKeyComment,hondaPumpComment,highPumpComment,rakeHoeComment,bushBeaterComment,jerryCanComment,softSuctionComment,strainerComment,towRopeComment,meterStandpipeComment,standpipeKeyComment,blankCoupling65Comment,blankCoupling25Comment,controlDividerComment,gekkaReducerComment,gekkaDividerComment,baBottlesComment,baSetsComment,trafficConesComment,bobejaanComment,toolboxComment,trashpumpComment,combiToolComment,batteriesComment,chargerComment,chainsComment,cuttersComment,batteriesCuttersComment,chargerCutterComment,ramComment,batteriesRamComment,ramExtensionComment,sawComment,spareBladesComment,batteriesSawComment,chargerSawComment,chockBlocksComment,shovelComment);

        saved = cache.getStringProperty("vehicleSaveEngine"+code);
        if( saved != null){
            try {
                load = new JSONObject(saved);
                engine.setSelection(load.getInt("Fire_Engine"));
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
                FireEngine.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            save.put("Licence_Expire", licenceExpire.getText().toString());
                            save.put("Date", Date);
                            save.put("Driver", driver.getText().toString());
                            save.put("Fire_Engine", engine.getSelectedItemPosition());
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
                        cache.setStringProperty("vehicleSaveEngine"+code,save.toString());
                    }
                });
            }
        },30,30, TimeUnit.SECONDS);

        TableSync();
        DatePicker();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public void DatePicker(){

        licenceExpire.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    FireEngine.this,
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
            licenceExpire.setText(date);
        };
    }

    public void Send(View v){

        if(validate()){
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
                cache.removeStringProperty("vehicleSaveEngine"+code);
                scheduledExecutorService.shutdown();
                i.putExtra("code",code);
                i.putExtra("first","not");
                startActivity(i);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please establish an internet connection", Toast.LENGTH_SHORT).show();
            }
        }

    }

    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {
                fireEngine.put("Fire_Vehicle_Type", "Fire Engine");
                fireEngine.put("Vehicle_Type", "Fire");
                fireEngine.put("Licence_Expire", licenceExpire.toString());
                fireEngine.put("Date", Date);
                fireEngine.put("Driver", driver.toString());
                fireEngine.put("Fire_Engine", engine.getSelectedItem().toString());
                fireEngine.put("Mileage", mileage.toString());
                fireEngine.put("Fuel", fuelSpinner.getSelectedItem().toString());
                fireEngine.put("Oils", oils.toString());
                fireEngine.put("Reg_No.", reg.toString());
                fireEngine.put("Location.", location.toString());
                fireEngine.put("Production", producion.toString());
                fireEngine.put("Crew", crew.toString());

                for (int i = 0; i<checkBoxList.size();i++) {
                    if (!checkBoxList.get(i).isChecked()) {
                        fireEngine.put(checkBoxList.get(i).getText().toString(), commentList.get(i).toString());

                    }
                }

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", fireEngine.toString()));

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

    public boolean validate(){
        boolean valid  = true;
        if(licenceExpire.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter a licence expiry date",Toast.LENGTH_SHORT).show();
        }else if(valid){

            try {
                String date = licenceExpire.getText().toString();
                date = date.trim();
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date d = sdf.parse(date);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Please ensure that licence expire is in dd/MM/yyyy format", Toast.LENGTH_SHORT).show();
                valid = false;
            }

        }else if (driver.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter a driver name", Toast.LENGTH_SHORT).show();

        }else if(mileage.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter a mileage value", Toast.LENGTH_SHORT).show();

        }else if(oils.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter oils name", Toast.LENGTH_SHORT).show();

        }else if(reg.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter a registration number", Toast.LENGTH_SHORT).show();

        }else if(location.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter location", Toast.LENGTH_SHORT).show();

        }else if(producion.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter a production name", Toast.LENGTH_SHORT).show();

        }else if(crew.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter a crew name", Toast.LENGTH_SHORT).show();

        }


        try{
            int mile = Integer.parseInt(mileage.getText().toString());
        }catch (Exception e){
            valid = false;
            Toast.makeText(getApplicationContext(),"Please enter only the km value", Toast.LENGTH_SHORT).show();

        }

        // Vehicle

        if(valid){
            if (!air.isChecked() && airComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that air conditioner is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!antennae.isChecked() && antennaeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that antennae is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!battery.isChecked() && batteryComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that battery secured is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!body.isChecked() && bodyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that body work is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!brake.isChecked() && brakeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that brake lights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!branding.isChecked() && brandingComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that branding is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!dashboard.isChecked() && dashboard.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that dashboard lights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!emergency.isChecked() && emergencyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that emergency is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!exhaust.isChecked() && exhaustComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that exhaust is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!oil.isChecked() && oilComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that oil level is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!fuelLevel.isChecked() && fuelLevelComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that fuel level is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!headlights.isChecked() && headlightsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that headlights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!leftIndicator.isChecked() && leftIndicatorComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that left indicator is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!rightIndicator.isChecked() && rightIndicatorComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that right indicator is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!back.isChecked() && backComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that interior light back is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!front.isChecked() && frontComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that interior light front is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!jack.isChecked() && jackComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that jack and tools is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!licence.isChecked() && licenceComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that licence is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!leds.isChecked() && ledsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that LED lights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!plates.isChecked() && platesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that number plates is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!radio.isChecked() && radioComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that radio is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!rear.isChecked() && rear.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that rear view mirror is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!reverse.isChecked() && reverseComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that reverse lights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!side.isChecked() && sideComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that side mirror is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!siren.isChecked() && sirenComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that siren is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!spare.isChecked() && spareComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that spare wheel is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tread.isChecked() && treadComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that tyre tread is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!pressure.isChecked() && pressureComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that tyre pressure is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!windows.isChecked() && windowsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that windows is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!windscreen.isChecked() && windscreenComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that windscreen is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        //Equipment
        if(valid){
            if (!waterSpritzer.isChecked() && waterSpritzerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that water spritzer is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!c02.isChecked() && c02Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that Co2 Extinguisher is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!dcp.isChecked() && dcpComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that DCP Extinguishers is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!fuelFunnel.isChecked() && fuelFunnelComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that fuel funnel is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!awgPencil.isChecked() && awgPencilComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that Gekka AWG Pencil Nozzle is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!gekkaTwister.isChecked() && gekkaTwisterComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that Gekka Twister Nozzle is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!Pistol65.isChecked() && Pistol65Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 65mm Pistol Nozzle is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!falseSpindles.isChecked() && falseSpindlesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that false spindles is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!layflat25.isChecked() && layflat25Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 25mm Layflat is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!short65.isChecked() && short65Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 65mm short length is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }
        if(valid){
            if (!duraflex65.isChecked() && duraflex65Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 65mm duraflex is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!duraflex38.isChecked() && duraflex38Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 38mm duraflex is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!afff.isChecked() && afffComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 5l afff is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!boltCutter.isChecked() && boltCutterComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that bolt cutter is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!wireCutter.isChecked() && wireCutterComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that wire cutter is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!crowBar.isChecked() && crowBarComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that crowbar is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!hondaKey.isChecked() && hondaKeyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that honda pump key is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!hondaPump.isChecked() && hondaPumpComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that honda/davey pump is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!highPump.isChecked() && highPumpComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that pump high pressure is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!rakeHoe.isChecked() && rakeHoeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that rake hoe is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!bushBeater.isChecked() && bushBeaterComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that bush beater is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!jerryCan.isChecked() && jerryCanComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that jerry can is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!softSuction.isChecked() && softSuctionComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that soft suction hose is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!strainer.isChecked() && strainerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that strainer is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!towRope.isChecked() && towRopeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that tow rope is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!meterStandpipe.isChecked() && meterStandpipeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that meter standpipe is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!standpipeKey.isChecked() && standpipeKeyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that standpipe key and blank coupling is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!blankCoupling65.isChecked() && blankCoupling65Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 65mm blank coupling is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!blankCoupling25.isChecked() && blankCoupling25.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 25mm blank coupling is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!controlDivider.isChecked() && controlDividerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 65mm control divider is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!gekkaReducer.isChecked() && gekkaReducerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 65mm to 25mm gekka reducer is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!gekkaDivider.isChecked() && gekkaDivider.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that gekka divider is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!baBottles.isChecked() && baBottlesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ba bottles is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!baSets.isChecked() && baSetsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that BA sets complete masks/air is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!trafficCones.isChecked() && trafficConesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that traffic cones is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!bobejaan.isChecked() && bobejaanComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that bobejaan spanner is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!toolbox.isChecked() && toolboxComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that toolbox is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!trashpump.isChecked() && trashpumpComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that trashpump is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!combiTool.isChecked() && combiToolComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that combi tool is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!batteries.isChecked() && batteriesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 2 batteries is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!charger.isChecked() && chargerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 220V cutter is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!chains.isChecked() && chainsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that chains is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!cutters.isChecked() && cuttersComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that hydraulic cutters is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!ram.isChecked() && ramComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that hydraulic ram is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!battery.isChecked() && batteryComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that battery is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!ramExtension.isChecked() && ramExtensionComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ram rod extension is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!saw.isChecked() && sawComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that reciprocal saw is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!spareBlades.isChecked() && spareBladesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that spare blades is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!chockBlocks.isChecked() && chockBlocksComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that step and chock blocks is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }



        if(valid){
            if (!shovel.isChecked() && shovelComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that shovel is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }



        return valid;
    }


    private static final int WrapContent = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static final int MatchParent = ViewGroup.LayoutParams.MATCH_PARENT;
    private RelativeLayout root,root2;

    private void TableSync()
    {
        root = findViewById(R.id.content);
        root2 = findViewById(R.id.content2);

        View table = getVehicleTable();
        View table2 = getEquipmentTable();

        root.addView(table);
        root2.addView(table2);
    }

    private View getVehicleTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("Response Car M05");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"AIRCONDITIONER","ANTENNAE\'S","BATTERY SECURED","BODY WORK","BRAKE LIGHTS","BRANDING","DASHBOARD LIGHTS",
                "EMERGENCY LIGHTS","EXHAUST","FUEL LEVEL","HEADLIGHTS","INDICATORS LEFT","INDICATORS RIGHT","INTERIOR LIGHT BACK","INTERIOR LIGHT FRONT",
                "JACK AND TOOLS","LED'S","LICENSE DISK X2 & EXP DATE","NUMBER PLATES","RADIO CD","REAR VIEW MIRROR","REVERSE LIGHTS","SIDE MIRRORS","SIREN",
                "SPARE WHEEL","TYRE THREAD","TYRE PRESSURE","WINDOWS","WINDSCREEN"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Need Repair"};
        String[] fuelLevelSpinnerOptions = new String[]{"Full", "3/4", "1/2", "1/4", "Empty"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            Row item = new Row(this, st, "1", i == 9? fuelLevelSpinnerOptions : qtySpinnerOptions, "OKAY", i == 9? "FULL   3/4   1/2   1/4": i == 17? "dd/mm/yyyy": i == 18? "CA 757-267":"No COMMENT","Comment here" , i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }

    private View getEquipmentTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("DESCRIPTION");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"Water Spritzers","Co2 Extingushers","DCP Extingushers","Fuel Funnel","Gekka AWG Pencil Nozzel","False Spindles (1 med and 1 Lg)",
                "25mm Layflat (30 Meters)","65mmShort Length","65mm Duraflex 30m","5L AFFF","Bolt Cutter","Wire Cutter",
                "Crowbar","Honda Pump Key","Pump Honda/Davey","Pump High Pressure","Rake Hoe","Bush Beater","5L Jerry Can",
                "Soft Suction Hose 5m","Strainer","Tow Rope","Meter Standpipe ","Standpipe Key and Bar","65mm Blank coupling",
                "25mm gekka blank coupling","Traffic Cones","Bobejaan Spanner","Toolbox","Shovel"
        };

        String[] _qtylist = new String[]{"2","2","2","1","2","2","2","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","4","1","1","1"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Need Repair", "Resupply"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            String qtyst = _qtylist[i];
            Row item = new Row(this, st, qtyst, qtySpinnerOptions, "OKAY","No COMMENT", "Comment here", i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }
}
