package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

public class AcompPrac extends Fragment {

    ListView practitioner;
    Button go;
    JSONObject accompanyingPractitioner;
    EditText healthRegistration, name;
    CheckBox chkNA;

    public AcompPrac(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_acomp_prac,container,false);
        accompanyingPractitioner = new JSONObject();
        healthRegistration = view.findViewById(R.id.healthEdit);
        name = view.findViewById(R.id.nameEdit);
        practitioner = view.findViewById(R.id.items);

        practitioner.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] items = {"Doctor", "Nurse", "Paramedic", "Other"};
        ArrayAdapter<String> PractionerAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_checked_list,items);

        practitioner.setAdapter(PractionerAdapter);

        go = view.findViewById(R.id.signatureButton);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Crew Details ");
                startActivity(i);
            }
        });

        chkNA.setOnClickListener(view1 -> {
            medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current + 1);
        });

        //add recording mechanism
        return view;
    }


    public JSONObject createJson (){
        accompanyingPractitioner = new JSONObject();
        for (int i = 0; i < practitioner.getChildCount(); i++) {

            if (practitioner.isItemChecked(i)){
                try{
                    accompanyingPractitioner.put(practitioner.getItemAtPosition(i).toString(),practitioner.getItemAtPosition(i).toString());

                }catch (Exception e){}
            }

            try{
                if(!chkNA.isChecked()) {
                    accompanyingPractitioner.put("Name", name);
                    accompanyingPractitioner.put("Health and Registration Number", healthRegistration);
                }else if(chkNA.isChecked()){
                    accompanyingPractitioner.put("Status","Not applicable");
                }
            }catch (Exception e){e.printStackTrace();}
        }
        return accompanyingPractitioner;
    }
}