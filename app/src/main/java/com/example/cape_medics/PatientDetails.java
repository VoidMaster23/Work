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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class PatientDetails extends Fragment {
    // TODO: Rename and change types of parameters
    private TextView lblName;
    private EditText edtName;
    private TextView lblSurname;
    private EditText edtSurname;
    private TextView lblDOB;
    private EditText edtDOB;
    private TextView lblID;
    private EditText edtID;
    private TextView lblEmail;
    private EditText edtEmail;
    private TextView lblPhone;
    private EditText edtPhone;
    private TextView lblAlt;
    private EditText edtAlt;
    private TextView lblAddress;
    private EditText edtAddress1;
    private EditText edtAddress2;
    private EditText edtAddress3;
    private TextView textView21;
    private CheckBox chkAdult;
    private CheckBox chkChild;
    private CheckBox chkBaby;
    private TextView textView22;
    private CheckBox chkWhite;
    private CheckBox chkColored;
    private CheckBox chkBlack;
    private CheckBox chkAsian;
    private TextView textView23;
    private CheckBox chkMale;
    private CheckBox chkFemale;
    private TextView lblRef;
    private EditText edtRef;
    JSONObject patientDetails;


    public PatientDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_details, container, false);
        lblName = (TextView)view.findViewById( R.id.lblName );
        edtName = (EditText)view.findViewById( R.id.edtName );
        lblSurname = (TextView)view.findViewById( R.id.lblSurname );
        edtSurname = (EditText)view.findViewById( R.id.edtSurname );
        lblDOB = (TextView)view.findViewById( R.id.lblDOB );
        edtDOB = (EditText)view.findViewById( R.id.edtDOB );
        lblID = (TextView)view.findViewById( R.id.lblID );
        edtID = (EditText)view.findViewById( R.id.edtID );
        lblEmail = (TextView)view.findViewById( R.id.lblEmail );
        edtEmail = (EditText)view.findViewById( R.id.edtEmail );
        lblPhone = (TextView)view.findViewById( R.id.lblPhone );
        edtPhone = (EditText)view.findViewById( R.id.edtPhone );
        lblAlt = (TextView)view.findViewById( R.id.lblAlt );
        edtAlt = (EditText)view.findViewById( R.id.edtAlt );
        lblAddress = (TextView)view.findViewById( R.id.lblAddress );
        edtAddress1 = (EditText)view.findViewById( R.id.edtAddress1 );
        edtAddress2 = (EditText)view.findViewById( R.id.edtAddress2 );
        edtAddress3 = (EditText)view.findViewById( R.id.edtAddress3 );
        textView21 = (TextView)view.findViewById( R.id.textView21 );
        chkAdult = (CheckBox)view.findViewById( R.id.chkAdult );
        chkChild = (CheckBox)view.findViewById( R.id.chkChild );
        chkBaby = (CheckBox)view.findViewById( R.id.chkBaby );
        textView22 = (TextView)view.findViewById( R.id.textView22 );
        chkWhite = (CheckBox)view.findViewById( R.id.chkWhite );
        chkColored = (CheckBox)view.findViewById( R.id.chkColored );
        chkBlack = (CheckBox)view.findViewById( R.id.chkBlack );
        chkAsian = (CheckBox)view.findViewById( R.id.chkAsian );
        textView23 = (TextView)view.findViewById( R.id.textView23 );
        chkMale = (CheckBox)view.findViewById( R.id.chkMale );
        chkFemale = (CheckBox)view.findViewById( R.id.chkFemale );
        lblRef = (TextView)view.findViewById( R.id.lblRef );
        edtRef = (EditText)view.findViewById( R.id.edtRef );
        return view;
    }

    public JSONObject createJson(){

        //details
        String name = edtName.getText().toString();
        String surname =edtSurname.getText().toString();
        String DOB = edtDOB.getText().toString();
        String ID = edtID.getText().toString();
        String email = edtEmail.getText().toString();
        String contact = edtPhone.getText().toString();
        String alt = edtAlt.getText().toString();
        String address = edtAddress1.getText().toString() +"\n"+edtAddress2.getText().toString() +"\n"+edtAddress3.getText().toString();


        //Age group
        String ageGroup  = null;

        if(chkAdult.isChecked()){
            ageGroup = chkAdult.getText().toString();
        }else if(chkChild.isChecked()){
            ageGroup = chkChild.getText().toString();
        }else if(chkBaby.isChecked()){
            ageGroup = chkBaby.getText().toString();
        }

        //race
        String race = null;
        if(chkBlack.isChecked()){
            race = chkBlack.getText().toString();
        }else if(chkColored.isChecked()){
            race = chkColored.getText().toString();
        }else if(chkWhite.isChecked()){
            race = chkWhite.getText().toString();
        }else if(chkAsian.isChecked()){
            race = chkAsian.getText().toString();
        }

        //sex
        String sex = null;
        if(chkMale.isChecked()){
            sex = chkMale.getText().toString();
        }else if(chkFemale.isChecked()){
            sex = chkFemale.getText().toString();
        }

        //ref
        String ref = edtRef.getText().toString();


        //json
        patientDetails = new JSONObject();
        try{
            patientDetails.put("Full name",name);
            patientDetails.put("Surname",surname);
            patientDetails.put("DOB",DOB);
            patientDetails.put("ID",ID);
            patientDetails.put("Email",email);
            patientDetails.put("Contact",contact);
            patientDetails.put("Alt",alt);
            patientDetails.put("Address",address);
            patientDetails.put("Age Group",ageGroup);
            patientDetails.put("Race",race);
            patientDetails.put("Sex",sex);
            patientDetails.put("Reference Number",ref);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return patientDetails;

    }

    }



