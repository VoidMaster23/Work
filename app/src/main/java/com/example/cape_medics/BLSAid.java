package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BLSAid extends Fragment {

    ListView items;
    ListView items2;

    public BLSAid(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_blsaid,container,false);
        items = view.findViewById(R.id.items);
        items2 = view.findViewById(R.id.items1);

        items.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        items2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] aid = {"Abdominal Thrusts", "AED Applied", "Manuel Defib", "AED by Public","Dressing/Bandage", "BVM Ventilation", "Chest Thrusts", "CPR","KED","Hemorrhage Control","IV Therapy","PASG Applied", "Oral Glucose","IV Dextrose","SPO2 (Pulse Ox)", "Chest Decompression","Spinal Immob","Splints","Suction","Traction Splint","Drugs","Nebulisation"};
        String[] als = {"Cardioversion","Needle Crich","Surgical Crich","ECG","SP02 (Pulse Ox)","External Pacing","External Jug Cann.","Femoral Jug Cann.","Magills Forceps","Drugs"};

        ArrayAdapter<String> AidAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_checked_list,aid);

        ArrayAdapter<String> AlsAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_checked_list,als);

        items.setAdapter(AidAdapter);
        items2.setAdapter(AlsAdapter);

        return view;

    }
}
