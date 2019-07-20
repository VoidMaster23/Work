package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


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

        return view;
    }


    public void createJson(){

        String signs = edtSigns.getText().toString();
        String allergies = edtAllergies.getText().toString();
        String medication = edtMedication.getText().toString();
        String past = edtPast.getText().toString();
        String last = edtIntake.getText().toString();
        String events = edtEvents.getText().toString();

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

    }

}
