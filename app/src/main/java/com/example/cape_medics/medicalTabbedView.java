package com.example.cape_medics;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class medicalTabbedView extends AppCompatActivity {

    private TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 33;


    public static MedicalFormCalls medicalFormCalls;
    public static PatientDetails patientDetails;
    public static PrimarySurvey primarySurvey;
    public static PresentingCondition presentingCondition;
    public static SampleHistory sampleHistory;
    public static VitalSigns vitalSigns;
    public static AirwayBreathing airwayBreathing;
    public static AirwayAdjunct airwayAdjunct;
    public static Resuscitation resuscitation;
    public static CardiacMonitoring cardiacMonitoring;
    public static Cannulation cannulation;
    public static DrugsAdministered drugsAdministered;
    public static InjuryMatrix injuryMatrix;
    public static ComaScore comaScore;
    public static WoundCare woundCare;
    public static Burns burns;
    public static PainScore score;
    public static Stroke stroke;
    public static ApgarScore apgarScore;
    public static BLSAid blsAid;
    public static Treatment treatment;
    public static VentilatorSettings ventilatorSettings;
    public static EmployerDetails employerDetails;
    public static PaymentMethod paymentMethod;
    public static GuaranteeOfPayment payment;
    public static RefusalofCare refusalofCare;
    public static Mobility mobility;
    public static Disposal disposal;
    public static CrewDetails crewDetails;
    public static AcompPrac acompPrac;

    public static Handoff_frag handoff_frag;
    public static Notes notes;
    public static Death death;
    public static int current;
    Cache cache;
    static String code, authorisation;
    //JSON for category type
    public static JSONObject category;


    static HashMap<String,JSONObject> map;

    String[] tabNames = {"Call Details","Patient Details","Primary Survey","Presenting Condition","Sample History","Vital Signs","Airway & Breathing Management","Airway Adjunct","Resuscitation","Cardiac Monitoring","Cannulation","Drugs Administered","Injury Matrix","Glasgow Coma Score","Wound Care","Burns","Pain Score","Stroke","APGAR Score","BLS/ILS Aid To Patient","Treatment","Ventilator Settings","Employers Details","Payment Method","Guarantee of Payment","Refusal of Care","Mobility","Disposal","Crew Details","Accompanying Practitioner","Items Handed Over","Handover/Disposal","Notes","Death","Death2"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_tabbed_view);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        //instantiate the categoty for later
        category = categoryType.CategoryType;

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        Intent i = getIntent();

        code = i.getStringExtra("code");
        authorisation = i.getStringExtra("Authorisation");
        Log.i("Authorisation",""+authorisation);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tabLayout.post(() -> tabLayout.setupWithViewPager(viewPager));

        medicalFormCalls = new MedicalFormCalls();
        patientDetails = new PatientDetails();
        primarySurvey = new PrimarySurvey();
        presentingCondition = new PresentingCondition();
        sampleHistory = new SampleHistory();
        vitalSigns = new VitalSigns();
        airwayBreathing = new AirwayBreathing();
        airwayAdjunct = new AirwayAdjunct();
        resuscitation = new Resuscitation();
        cardiacMonitoring = new CardiacMonitoring();
        cannulation = new Cannulation();
        drugsAdministered = new DrugsAdministered();
        injuryMatrix = new InjuryMatrix();
        comaScore = new ComaScore();
        woundCare = new WoundCare();
        burns = new Burns();
        score = new PainScore();
        stroke = new Stroke();
        apgarScore = new ApgarScore();
        blsAid = new BLSAid();
        treatment = new Treatment();
        ventilatorSettings = new VentilatorSettings();
        employerDetails = new EmployerDetails();
        paymentMethod = new PaymentMethod();
        payment = new GuaranteeOfPayment();
        refusalofCare = new RefusalofCare();
        mobility = new Mobility();
        disposal = new Disposal();
        crewDetails = new CrewDetails();
        acompPrac = new AcompPrac();
        handoff_frag = new Handoff_frag();
        notes = new Notes();
        death = new Death();



        map = new HashMap<>();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                current = i;
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });

    }


    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return medicalFormCalls;
                case 1 : return patientDetails;
                case 2 : return primarySurvey;
                case 3 : return presentingCondition;
                case 4: return sampleHistory;
                case 5: return vitalSigns;
                case 6: return airwayBreathing;
                case 7: return airwayAdjunct;
                case 8: return resuscitation;
                case 9: return cardiacMonitoring;
                case 10: return cannulation;
                case 11: return drugsAdministered;
                case 12: return injuryMatrix;
                case 13: return comaScore;
                case 14: return woundCare;
                case 15: return burns;
                case 16: return score;
                case 17: return stroke;
                case 18: return apgarScore;
                case 19: return blsAid;
                case 20: return treatment;
                case 21: return ventilatorSettings;
                case 22: return employerDetails;
                case 23: return paymentMethod;
                case 24: return payment;
                case 25: return refusalofCare;
                case 26: return mobility;
                case 27: return disposal;
                case 28: return crewDetails;
                case 29: return acompPrac;
                case 30: return handoff_frag;
                case 31: return notes;
                case 32: return death;







            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Call Details";
                case 1 :
                    return "Patient Details";
                case 2 :
                    return "Primary Survey";
                case 3:
                    return "Presenting Condition";
                case 4:
                    return "Sample History";
                case 5:
                    return "Vital Signs";
                case 6:
                    return "Airway & Breathing Management";
                case 7:
                    return "Airway Adjunct";
                case 8:
                    return "Resuscitation";
                case 9:
                    return "Cardiac Monitoring";
                case 10:
                    return "Cannulation";
                case 11:
                    return "Drugs Administered";
                case 12:
                    return "Injury Matrix";
                case 13:
                    return "Glasgow Coma Score";
                case 14:
                    return "Wound Care";
                case 15:
                    return "Burns";
                case 16:
                    return "Pain Score";

                case 17:
                    return "Stroke";

                case 18: return "APGAR Score";
                case 19: return "BLS/ILS Aid To Patient";
                case 20: return "Treatment";
                case 21: return "Ventilator Settings";
                case 22: return "Employers Details";
                case 23: return "Payment Method";
                case 24: return "Guarantee of Payment";
                case 25: return "Refusal of Care";
                case 26: return "Mobility";
                case 27: return "Disposal";
                case 28: return "Crew Details";
                case 29: return "Accompanying Practitioner";
                case 30: return "Items Handed Over";
               // case 31: return "Handover/Disposal";
                case 31: return "Notes";
                case 32: return "Death";



            }
            return null;
        }


    }
}
