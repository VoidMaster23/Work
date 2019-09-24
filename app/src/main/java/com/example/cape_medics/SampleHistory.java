package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;


public class SampleHistory extends Fragment {
    private ImageView imageView14;
    private CheckBox chkNA;
    private TextView lblSigns;
    private EditText edtSigns;
    private EditText edtAllergies;
    private TextView lblAllergies;
    private EditText edtMedication;
    private EditText edtPast;
    private EditText edtIntake;
    private EditText edtEvents;
    private TextView lblMedication;
    private TextView lblPast;
    private TextView lblIntake;
    private TextView lblEvents;
    JSONObject sampleHistory;
    JSONObject myOBJ;
    List<CheckBox> checkBoxList;
    Cache cache;
    String saved;
    JSONObject load;




    public SampleHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sample_history, container, false);

        imageView14 = (ImageView)view.findViewById( R.id.imageView14 );
        chkNA = (CheckBox)view.findViewById( R.id.chkNA );
        lblSigns = (TextView)view.findViewById( R.id.lblSigns );
        edtSigns = (EditText)view.findViewById( R.id.edtSigns );
        edtAllergies = (EditText)view.findViewById( R.id.edtAllergies );
        lblAllergies = (TextView)view.findViewById( R.id.lblAllergies );
        edtMedication = (EditText)view.findViewById( R.id.edtMedication );
        edtPast = (EditText)view.findViewById( R.id.edtPast );
        edtIntake = (EditText)view.findViewById( R.id.edtIntake );
        edtEvents = (EditText)view.findViewById( R.id.edtEvents );
        lblMedication = (TextView)view.findViewById( R.id.lblMedication );
        lblPast = (TextView)view.findViewById( R.id.lblPast );
        lblIntake = (TextView)view.findViewById( R.id.lblIntake );
        lblEvents = (TextView)view.findViewById( R.id.lblEvents );

        sampleHistory = new JSONObject();
        myOBJ = new JSONObject();
        cache = new Cache(getContext());
        saved = cache.getStringProperty("sampleHistory");
        if(saved != null ){
            try {
                load = new JSONObject(saved);
                edtSigns.setText(sampleHistory.getString("Signs"));
                edtAllergies.setText(sampleHistory.getString("Allergies"));
                edtMedication.setText(sampleHistory.getString("Medications"));
                edtPast.setText(sampleHistory.getString("Past"));
                edtIntake.setText(sampleHistory.getString("Last"));
                edtEvents.setText(sampleHistory.getString("Events"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }


    public JSONObject createJson(){

        String signs = edtSigns.getText().toString();
        String allergies = edtAllergies.getText().toString();
        String medication = edtMedication.getText().toString();
        String past = edtPast.getText().toString();
        String last = edtIntake.getText().toString();
        String events = edtEvents.getText().toString();

    sampleHistory = new JSONObject();





        try{

            sampleHistory.put("Signs",signs);
            sampleHistory.put("Allergies",allergies);
            sampleHistory.put("Medications",medication);
            sampleHistory.put("Past",past);
            sampleHistory.put("Last",last);
            sampleHistory.put("Events",events);


        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }


        Log.i("Called","Summon");
        cache.setStringProperty("sampleHistory",sampleHistory.toString());
        return sampleHistory;
    }



}
