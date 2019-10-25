package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

public class Handoff_frag extends Fragment {

    EditText destination,name,quality,service;
    ListView itemsHanded;
    JSONObject items;
    CheckBox chkNA;

    public Handoff_frag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_handoff_frag, container, false);
        items = new JSONObject();

        destination = view.findViewById(R.id.destinationEdit);
        name = view.findViewById(R.id.nameEdit);
        quality = view.findViewById(R.id.regEdit);
        service = view.findViewById(R.id.serviceEdit);

        chkNA = view.findViewById( R.id.notApplicableCheckBox);
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });

        itemsHanded = view.findViewById(R.id.items);
        itemsHanded.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] items = {"Refferal Letter", "X-Rays", "WCA Forms", "Medications", "Patient Valuables", "Cell-Phone",
                "No items Handed Over"};
        ArrayAdapter<String> ItemsAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_checked_list,items);

        itemsHanded.setAdapter(ItemsAdapter);

        return view;

    }

    public JSONObject createJson (){
        items = new JSONObject();
        items = new JSONObject();
        try{
            items.put("Destination", destination.toString());
            items.put("Name", name.toString());
            items.put("Quality and Reg No.", quality.toString());
            items.put("Service", service.toString());
        }catch(Exception e){}

        for (int i = 0; i < itemsHanded.getChildCount(); i++) {

            if (itemsHanded.isItemChecked(i)){
                try{
                    items.put(itemsHanded.getItemAtPosition(i).toString(),itemsHanded.getItemAtPosition(i).toString());

                }catch (Exception e){}
            }
        }

        return items;
    }

    public void Go (View v){

    }

    public void skip (View v){
        //skip this one
    }
}
