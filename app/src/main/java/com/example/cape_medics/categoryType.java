package com.example.cape_medics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class categoryType extends AppCompatActivity {
    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;
    CheckBox ortia,ctia,ksia,inemergency,airemergency,organtransfer,landemergency,airtransfer,acsastaff,otherstakeholder;
    CheckBox publicpassenger, film, event, cticc, gcc, firstaid, prf, gccstaff, other, publicPatron, home2hospital;
    CheckBox facility2facility, house, office, publicVenue;
    EditText callLocation, name, company, organiser, callLocation2, callLocation3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //first tab
        setContentView(R.layout.activity_category_type);
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
        home2hospital = findViewById(R.id.homeHosChk);
        facility2facility = findViewById(R.id.facilityChk);
        house = findViewById(R.id.houseChk);
        office = findViewById(R.id.officeChk);
        publicVenue = findViewById(R.id.publicChk);
        callLocation3 = findViewById(R.id.edtLoc4);

    }

    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expLay1);
        expandableLayout1.toggle(); // toggle expand and collapse


    }

    public void expandableButton2(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expLay2);
        expandableLayout2.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expLay3);
        expandableLayout3.toggle(); // toggle expand and collapse
    }

    public void Next (View v){
        Intent i = new Intent(getApplicationContext(), medicalTabbedView.class);
        startActivity(i);
    }


}
