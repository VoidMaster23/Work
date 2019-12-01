package com.example.cape_medics;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;

import static android.support.v4.content.ContextCompat.getSystemService;

public class Death extends Fragment {
    JSONArray CategoryType;
    Button send, go;
    JSONObject death, response;
    EditText location,  place, name, post;
    TextView time,time2,date;
    CheckBox carotidPulseLeft, carotidPulseRight, breathingYes, breathingNo, eyeYes, eyeNo, ecgYes, ecgNo, pupilsYes, pupilsNo;
    String carotidPulse, breathing, dollEyeMovements, ecgStraightLine, bilateralFixedDilatedPupils, responseServer;
    private static final String IMAGE_DIRECTORY = "/Pictures";
    String url;
    boolean connected;
    Cache cache;
    ScheduledExecutorService scheduledExecutorService;

    public Death(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_death,container,false);
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/.php";
        cache = new Cache(getContext());


        death = new JSONObject();

        send = view.findViewById(R.id.send);
        go = view.findViewById(R.id.signatureButton);
        location = view.findViewById(R.id.locationEdit);
        time = view.findViewById(R.id.timeEdit);
        place = view.findViewById(R.id.placeEdit);
        date = view.findViewById(R.id.dateEdit);
        name = view.findViewById(R.id.nameEdit);
        post = view.findViewById(R.id.postEdit);
        time2 = view.findViewById(R.id.timeEdit2);

        carotidPulseLeft = view.findViewById(R.id.leftChk);
        carotidPulseRight = view.findViewById(R.id.rightChk);
        breathingYes = view.findViewById(R.id.breathingChkYes);
        breathingNo = view.findViewById(R.id.breathingChkNo);
        eyeYes = view.findViewById(R.id.dollChkYes);
        eyeNo = view.findViewById(R.id.dollChkNo);
        ecgYes = view.findViewById(R.id.ecgChkYes);
        ecgNo = view.findViewById(R.id.ecgChkNo);
        pupilsYes = view.findViewById(R.id.dilatedChkYes);
        pupilsNo = view.findViewById(R.id.dilatedChkNo);
        CategoryType = new JSONArray();

        TimePicker();
        DatePicker();


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Death ");
                startActivity(i);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // boolean flag = medicalTabbedView.medicalFormCalls.validate() && medicalTabbedView.patientDetails.validate() && medicalTabbedView.primarySurvey.validate() && medicalTabbedView.presentingCondition.validate()
                      //  && medicalTabbedView.presentingCondition.validate() && medicalTabbedView.sampleHistory.validate() &&  medicalTabbedView.resuscitation.validate() && medicalTabbedView.score.validate()
                     //   && medicalTabbedView.apgarScore.validate() && medicalTabbedView.employerDetails.validate() && medicalTabbedView.paymentMethod.validate() && medicalTabbedView.payment.validate();
             //Add this later

                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                else connected = false;
                if (connected) {
                    Death.AsyncT send = new Death.AsyncT();
                    send.execute();
//                    scheduledExecutorService.shutdown();

                }
                else {
                    Toast.makeText(getContext(), "Please establish an internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });



        try {
            CategoryType = new JSONArray(getActivity().getIntent().getStringExtra("Category Type"));
        }catch (Exception e) {}

        return view;
    }

    Context mContext;
    private void TimePicker() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        mContext = getActivity();

        time.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> time.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        time2.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> time2.setText(hourOfDay + ":" + minute1), hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public void DatePicker(){

        mContext = getActivity();

        date.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    mContext,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            Log.d("tag", "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);

            String ddate = day + "/" + month + "/" + year;
            date.setText(ddate);
        };
    }
    public void removeStringPropertys(String code, Cache cache, Context context){
        //use this method to remove all string caches
        cache = new Cache(context);
        cache.removeStringProperty("categoryType"+code);
    }

    public JSONObject createJson (){

        //carotid pulse
        if(carotidPulseLeft.isChecked()) carotidPulse = carotidPulseLeft.getText().toString();
        if(carotidPulseRight.isChecked()) carotidPulse = carotidPulseRight.getText().toString();

        //breathing
        if(breathingNo.isChecked()) breathing = breathingNo.getText().toString();
        if(breathingYes.isChecked()) breathing = breathingYes.getText().toString();

        //Doll eye movements
        if(eyeYes.isChecked()) dollEyeMovements = eyeYes.getText().toString();
        if(eyeNo.isChecked()) dollEyeMovements = eyeNo.getText().toString();

        //Ecg straight Line
        if(ecgYes.isChecked()) ecgStraightLine = ecgYes.getText().toString();
        if(ecgNo.isChecked()) ecgStraightLine = ecgNo.getText().toString();

        //bilateral fixed dilated pupils
        if(pupilsNo.isChecked()) bilateralFixedDilatedPupils = pupilsNo.getText().toString();
        if(pupilsYes.isChecked()) bilateralFixedDilatedPupils = pupilsYes.getText().toString();

        try{
            death.put("Commissioner of Oaths",loadImage());
            death.put("Carotid Pulse",carotidPulse);
            death.put("Breathing",breathing);
            death.put("Doll Eye Movements",dollEyeMovements);
            death.put("ECG Straight Line",ecgStraightLine);
            death.put("Bilateral Fixed Dilated Pupils",bilateralFixedDilatedPupils);
            death.put("Location of Deceased",location.toString());
            death.put("Time of Examination",time.toString());
            death.put("Place",place.toString());
            death.put("Date",date.toString());
            death.put("Full Name",name.toString());
            death.put("Post",post.toString());
            death.put("Time",time2.toString());

        }catch (Exception e){}

        return death;

    }

    public Bitmap loadImage(){
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY,"Death.jpg");

        if(wallpaperDirectory.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(wallpaperDirectory.getAbsolutePath());
            return myBitmap;
        }
        else{
            return null;
        }



    }


    class AsyncT extends AsyncTask<Void, Void , Void>{
        @Override
        protected Void doInBackground(Void... voids){
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {

                    medicalTabbedView.map.put("Category Type", medicalTabbedView.category);
                    medicalTabbedView.map.put("Call Details", medicalTabbedView.medicalFormCalls.createJson());
                    medicalTabbedView.map.put("Patient Details", medicalTabbedView.patientDetails.createJson());
                    medicalTabbedView.map.put("Primary Survey", medicalTabbedView.primarySurvey.createJson());
                   medicalTabbedView.map.put("Presenting Condition", medicalTabbedView.presentingCondition.createJson());
                    medicalTabbedView.map.put("Sample History", medicalTabbedView.sampleHistory.createJson());
                    //vital signs needs validation maybe
//                    medicalTabbedView.map.put("Vital Signs", medicalTabbedView.vitalSigns.createJson());
                    medicalTabbedView.map.put("Airway & Breathing Management", medicalTabbedView.airwayBreathing.createJson());
                   medicalTabbedView.map.put("Airway Adjunct", medicalTabbedView.airwayAdjunct.createJson());
                      medicalTabbedView.map.put("Resuscitation", medicalTabbedView.resuscitation.createJson());
                      //cannulation has no validation
                  medicalTabbedView.map.put("Cannulation", medicalTabbedView.cannulation.createJson());

                  //might need validation
                   medicalTabbedView.map.put("Drugs Administered", medicalTabbedView.drugsAdministered.createJson());

                    medicalTabbedView.map.put("Injury Matrix", medicalTabbedView.injuryMatrix.createJson());
                    medicalTabbedView.map.put("Cardiac Monitoring", medicalTabbedView.cardiacMonitoring.createJson());
                  medicalTabbedView.map.put("Glasgow Coma Score", medicalTabbedView.comaScore.createJson());
                   medicalTabbedView.map.put("Wound Care", medicalTabbedView.woundCare.createJson());
                    medicalTabbedView.map.put("Burns", medicalTabbedView.burns.createJson());
//                    medicalTabbedView.map.put("Pain Score", medicalTabbedView.score.createJson());
//                    medicalTabbedView.map.put("Stroke", medicalTabbedView.stroke.createJson());
//                    medicalTabbedView.map.put("APGAR Score", medicalTabbedView.apgarScore.createJson());
//                    medicalTabbedView.map.put("BLS/ILS Aid", medicalTabbedView.blsAid.createJson());
//                    medicalTabbedView.map.put("Treatment", medicalTabbedView.treatment.createJson());
//                    medicalTabbedView.map.put("Ventilator Settings", medicalTabbedView.ventilatorSettings.createJson());
//                    medicalTabbedView.map.put("Employers Details", medicalTabbedView.employerDetails.createJson());
//                    medicalTabbedView.map.put("Payment Method", medicalTabbedView.paymentMethod.createJson());
//                    medicalTabbedView.map.put("Guarantee of payment", medicalTabbedView.payment.createJson());
//                    medicalTabbedView.map.put("Refusal of care", medicalTabbedView.refusalofCare.createJson());
//                    medicalTabbedView.map.put("Mobility", medicalTabbedView.mobility.createJson());
//                    medicalTabbedView.map.put("Disposal", medicalTabbedView.disposal.createJson());
//                    medicalTabbedView.map.put("Crew Details", medicalTabbedView.crewDetails.createJson());
//                    medicalTabbedView.map.put("Accompanying Practitioner", medicalTabbedView.acompPrac.createJson());
//                    medicalTabbedView.map.put("Handover/disposal", medicalTabbedView.handoff_frag.createJson());
//                    medicalTabbedView.map.put("Notes", medicalTabbedView.notes.createJson());
//                    medicalTabbedView.map.put("Death", createJson());
//                    Log.e("MEDICAL DATA", medicalTabbedView.map.toString());

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("req", medicalTabbedView.map.toString()));

                    Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                    // Use UrlEncodedFormEntity to send in proper format which we need
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);
                    InputStream inputStream = response.getEntity().getContent();
                    Login_Page.InputStreamToStringExample str = new Login_Page.InputStreamToStringExample();
                    responseServer = str.getStringFromInputStream(inputStream);
                    Log.e("response", "cake -----" + responseServer);




            }catch (Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent i = new Intent(getContext(), Home_Screen_Crew.class);
            Toast.makeText(getContext(), "sent", Toast.LENGTH_SHORT).show();
            i.putExtra("code",medicalTabbedView.code);
            i.putExtra("first","not");
            i.putExtra("Authorisation",medicalTabbedView.authorisation);
            startActivity(i);
        }
    }






}
