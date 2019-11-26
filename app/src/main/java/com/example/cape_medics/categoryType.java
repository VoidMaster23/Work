package com.example.cape_medics;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.cape_medics.DateAndTimePicker.TimePickerFragment;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class categoryType extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;
    CheckBox ortia,ctia,ksia,inemergency,airemergency,organtransfer,landemergency,airtransfer,acsastaff,otherstakeholder;
    CheckBox publicpassenger, film, event, cticc, gcc, firstaid, prf, gccstaff, other, publicPatron, home2hospital;
    CheckBox facility2facility, house, office, publicVenue;
    EditText callLocation, name, company, organiser, callLocation2, callLocation3;
    Cache cache;

    TextView dateView, reportTime;

    JSONObject acsa, load;
    String airport,callType_acsa,general_acsa,code ;

    JSONObject events;
    String categoryType, role, general_events;
    JSONObject eventDetails;

    JSONObject primary;
    String callType_primary, saved;

    JSONObject CategoryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_category_type);
        reportTime = findViewById(R.id.reportTime);
        CategoryType = new JSONObject();
        dateView = findViewById(R.id.dateView);
        cache = new Cache(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");

        //first tab
        acsa = new JSONObject();
        ortia = findViewById(R.id.ortiaChk);
        ctia = findViewById(R.id.ctiaChk);
        ksia = findViewById(R.id.ksiaChk);
        inemergency = findViewById(R.id.inflightChk);
        airemergency = findViewById(R.id.airsideEmergenctChk);
        organtransfer = findViewById(R.id.organChk);
        landemergency = findViewById(R.id.landsideChk);
        airtransfer = findViewById(R.id.airsideChk);
        acsastaff = findViewById(R.id.acsaStaffChk);
        otherstakeholder = findViewById(R.id.stakeholderChk);
        publicpassenger = findViewById(R.id.publicPassChk);
        callLocation = findViewById(R.id.location1edt);

        //second tab
        events = new JSONObject();
        eventDetails = new JSONObject();
        film = findViewById(R.id.filmChk);
        event = findViewById(R.id.eventChk);
        cticc = findViewById(R.id.cticcChk);
        gcc = findViewById(R.id.gccChk);
        name = findViewById(R.id.edtEventName);
        company = findViewById(R.id.edtEventComp);
        organiser = findViewById(R.id.edtOrganiser);
        firstaid = findViewById(R.id.firstAidChk);
        prf = findViewById(R.id.prfChk);
        gccstaff = findViewById(R.id.gccStaffChk);
        other = findViewById(R.id.otherChk);
        publicPatron = findViewById(R.id.patronChk);
        callLocation2 = findViewById(R.id.edtLoc2);

        //third tab
        primary = new JSONObject();
        home2hospital = findViewById(R.id.homeHosChk);
        facility2facility = findViewById(R.id.facilityChk);
        house = findViewById(R.id.houseChk);
        office = findViewById(R.id.officeChk);
        publicVenue = findViewById(R.id.publicChk);
        callLocation3 = findViewById(R.id.edtLoc4);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String DateTime = dateFormat.format(date);
        String Date = DateTime.substring(0,10);
        dateView.setText(Date);

        saved = cache.getStringProperty("categoryType"+code);
        if ( saved != null){
            try {
                load = new JSONObject(saved);
                //remove each value from JSON object and make set edit text or checkbox to it (checkbox code in fireEngine activity)
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        reportTime.setOnClickListener(view -> {
            DialogFragment timePickerFragment = new TimePickerFragment();
            timePickerFragment.setCancelable(false);
            timePickerFragment.show(getSupportFragmentManager(), "timePicker");
        });

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        reportTime.setText("Hour = " + hourOfDay + " Minute = " + minute);
    }

    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expLay1);
        expandableLayout1.setMinimumWidth(view.getWidth());
        expandableLayout1.toggle(); // toggle expand and collapse


    }

    public void expandableButton2(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expLay2);
        expandableLayout2.setMinimumWidth(view.getWidth());
        expandableLayout2.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expLay3);
        expandableLayout3.setMinimumWidth(view.getWidth());
        expandableLayout3.toggle(); // toggle expand and collapse
    }

    public void Next (View v){

        //Airport
        if(ortia.isChecked()) airport = "ORTIA";
        if(ctia.isChecked()) airport = "CTIA";
        if(ksia.isChecked()) airport = "KSIA";

        //Call Type
        if(inemergency.isChecked()) callType_acsa = "Inflight Emergency";
        if(airtransfer.isChecked()) callType_acsa = "Airside Transfer";
        if(airemergency.isChecked()) callType_acsa = "Airside Emergency";
        if(landemergency.isChecked()) callType_acsa = "Land Emergency";
        if(organtransfer.isChecked()) callType_acsa = "Organ Transfer";

        //General
        if(acsastaff.isChecked()) general_acsa = "ASCA Staff";
        if(otherstakeholder.isChecked()) general_acsa = "Other Stakeholder";
        if(publicpassenger.isChecked()) general_acsa = "Public/Passenger";


        //Defining ACSA JSON
        try {
            acsa.put("TIme", reportTime.toString());
            acsa.put("Airport", airport);
            acsa.put("Call Type", callType_acsa);
            acsa.put("General", general_acsa);
            acsa.put("Call Location", callLocation.toString());

        }catch (Exception e){}


        //Category Type
        if(film.isChecked()) categoryType = "Film & TV";
        if(event.isChecked()) categoryType = "Event";
        if(cticc.isChecked()) categoryType = "CTICC";
        if(gcc.isChecked()) categoryType = "GCC";

        //Defining Event Details JSON
        try {
            eventDetails.put("Name of Event", name.toString());
            eventDetails.put("Event Company", company.toString());
            eventDetails.put("Organiser Name", organiser.toString());

        }catch (Exception e){}

        //Role
        if(firstaid.isChecked()) role = "First Aid";
        if(prf.isChecked()) role = "PRF";

        //General
        if(gccstaff.isChecked()) general_events = "CTICC/GCC Staff";
        if(otherstakeholder.isChecked()) general_events = "Other Stakeholders/Contractors";
        if(publicPatron.isChecked()) general_events = "Public/Patron";

        //Defining Event JSON
        try {
            acsa.put("TIme", reportTime.toString());
            events.put("Category Type", categoryType);
            events.put("Event Details", eventDetails); //this is a JSON object
            events.put("Role", role);
            events.put("General", general_events);
            events.put("Call Location", callLocation2.toString());

        }catch (Exception e){}


        // Call Type
        if(home2hospital.isChecked()) callType_primary = home2hospital.getText().toString(); //probably easier to do this
        if(facility2facility.isChecked()) callType_primary = facility2facility.getText().toString();
        if(house.isChecked()) callType_primary = house.getText().toString();
        if(office.isChecked()) callType_primary = office.getText().toString();
        if(publicVenue.isChecked()) callType_primary = publicVenue.getText().toString();

        //Defining Primary JSON
        try {
            acsa.put("TIme", reportTime.toString());
            primary.put("Call Type", callType_primary);
            primary.put("Call Location",callLocation3.toString());

        }catch (Exception e){}


        //placing JSONs into a JSON array
        try {
            CategoryType.put("ACSA",acsa);
            CategoryType.put("Events",events);
            CategoryType.put("Primary",primary);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        cache.setStringProperty("categoryType"+code, CategoryType.toString());

        //sends JSONArray to
        Intent intent= new Intent(getApplicationContext(), Death.class);
        intent.putExtra("Category Type", CategoryType.toString());

        Intent i = new Intent(getApplicationContext(), medicalTabbedView.class);
        startActivity(i);
    }

}
