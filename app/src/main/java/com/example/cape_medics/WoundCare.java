package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;


public class WoundCare extends Fragment {

    String[] woundType = {"Abrasion","Laceration","Puncture","Avulsion","De-Gloving"};
    String[] treatedWith = {"Saline","Cetrimide","Alcohol Prep Pad","Iodine Solution","Anti Bacterial Cream"};
    String[] barrier = {"Strip(steri-strip etc)","Adhesive Plaster (strip or anchor)","Film Barrier (Tergaderm)","Island Dressing","FAD","Cohesive/Elastic Bandage"};

    ListView lisWound, lisTreat, lisBarrier;
    String woundStr, treatStr, barrierStr;

    JSONObject woundCare;
    public CheckBox chkNA;
    public WoundCare() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wound_care, container, false);

        lisWound = view.findViewById(R.id.woundType);
        lisTreat = view.findViewById(R.id.treatedWith);
        lisBarrier = view.findViewById(R.id.barrier);

        chkNA = view.findViewById( R.id.notApplicableCheckBox );
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });

        ArrayAdapter a1 = new ArrayAdapter(getContext(),R.layout.custom_checked_list,woundType);
        ArrayAdapter a2 = new ArrayAdapter(getContext(),R.layout.custom_checked_list,treatedWith);
        ArrayAdapter a3 = new ArrayAdapter(getContext(),R.layout.custom_checked_list,barrier);

        lisWound.setAdapter(a1);
        lisTreat.setAdapter(a2);
        lisBarrier.setAdapter(a3);

        lisTreat.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lisWound.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lisBarrier.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);


        lisWound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView text  =  view.findViewById(android.R.id.text1);

                woundStr = text.getText().toString();

            }
        });

        lisBarrier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView text  =  view.findViewById(android.R.id.text1);

                barrierStr = text.getText().toString();


            }
        });

        lisTreat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView text  =  view.findViewById(android.R.id.text1);

                treatStr = text.getText().toString();

            }
        });
        return view;
    }


    //no need validation
    public JSONObject createJson(){

        woundCare = new JSONObject();

       try{
           if(!chkNA.isChecked() && validate()) {
               woundCare.put("Wound Type", woundStr);
               woundCare.put("Barrier", barrierStr);
               woundCare.put("Treated With", treatStr);
           }else if(chkNA.isChecked()){
               woundCare.put("Status","Not applicable");
           }
       }catch (Exception e){
           Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
       }
       return woundCare;
    }

    public boolean validate(){
        boolean valid = true;
        Looper.prepare();

        if(woundStr.isEmpty()){
            valid = false;
            Toast.makeText(getContext(),"Wound Care: Please enter all details", Toast.LENGTH_SHORT).show();
        }

        if(valid){
            if(barrierStr.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Wound Care: Please enter all details", Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(treatStr.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Wound Care: Please enter all details", Toast.LENGTH_SHORT).show();
            }
        }



        Looper.loop();
        return valid;
    }

}
