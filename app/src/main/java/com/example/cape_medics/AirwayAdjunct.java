package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;


public class AirwayAdjunct extends Fragment {

ArrayList<String> myList1;
ListView list;
CheckBox checkBox;
Spinner attempts;
Spinner check;
JSONObject airwayAdjunct;
String category;
String numAttempt;
String posChk;

Integer[] attempt = {1,2,3,4};
String[] pos = {"Auscultation","ETCO2"};
    public AirwayAdjunct() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_airway_adjunct, container, false);

        myList1 = new ArrayList<String>();

        myList1.add("OPA");
        myList1.add("NPA");
        myList1.add("SGA");
        myList1.add("ETT");
        myList1.add("N-CRICH");
        myList1.add("S-CRICH");

        list = view.findViewById(R.id.airway);
        checkBox = view.findViewById(R.id.chkAchieved);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.custom_checked_list,myList1);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView chk = view.findViewById(android.R.id.text1);
                category = chk.getText().toString();

                chk.toggle();
            }
        });

        //for the attempts
        attempts = view.findViewById(R.id.spnAttempt);
        ArrayAdapter aa = new ArrayAdapter(getContext(),R.layout.custom_spinner,attempt);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        attempts.setAdapter(aa);

        attempts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               numAttempt = attempt[i].toString();
            }
        });

        //for the pos
        Spinner spnPos = view.findViewById(R.id.spnPos);
        ArrayAdapter bb = new ArrayAdapter(getContext(),R.layout.custom_spinner, pos);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPos.setAdapter(bb);

        spnPos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posChk = pos[i];
            }
        });
        return view;



    }
    public void createJson(){


        try{

            airwayAdjunct.put("Category",category);
            airwayAdjunct.put("Achieved",checkBox.getText().toString());
            airwayAdjunct.put("Attempts",numAttempt);
            airwayAdjunct.put("Position Check", posChk);

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }

}
