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

public class Stroke extends Fragment {
    private ImageView imageView18;
    public CheckBox  chkNA;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private TextView textView55;
    private EditText editDoorDrug;
    private TextView textView56;
    private EditText editStrokeUnit;
    private TextView textView57;
    private EditText editCasualty;

    JSONObject stroke;




    public Stroke() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_stroke, container, false);

        imageView18 = (ImageView)view.findViewById( R.id.imageView18 );
        chkNA = (CheckBox)view.findViewById( R.id.chkNA );
        checkBox4 = (CheckBox)view.findViewById( R.id.checkBox4 );
        checkBox5 = (CheckBox)view.findViewById( R.id.checkBox5 );
        checkBox6 = (CheckBox)view.findViewById( R.id.checkBox6 );
        textView55 = (TextView)view.findViewById( R.id.textView55 );
        editDoorDrug = (EditText)view.findViewById( R.id.editDoorDrug );
        //textView56 = (TextView)view.findViewById( R.id.textView56 );
        editStrokeUnit = (EditText)view.findViewById( R.id.editStrokeUnit );
        //textView57 = (TextView)view.findViewById( R.id.textView57 );
        editCasualty = (EditText)view.findViewById( R.id.editCasualty );
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });

       return view;
    }

    public JSONObject createJson(){

        stroke = new JSONObject();

        String facial = null;
        String arm = null;
        String speech = null;
        if(checkBox4.isChecked()) facial = checkBox4.getText().toString();
        if(checkBox5.isChecked()) arm = checkBox5.getText().toString();
        if(checkBox6.isChecked()) speech = checkBox6.getText().toString();

        String door = editDoorDrug.getText().toString();
        String strokeStr = editStrokeUnit.getText().toString();
        String casual = editCasualty.getText().toString();

        try{

            stroke.put("Facial", facial);
            stroke.put("Arm", arm);
            stroke.put("Speech", speech);
            stroke.put("Door", door);
            stroke.put("Stroke", strokeStr);
            stroke.put("Casualty", casual);


        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return stroke;
    }

    public boolean validate(){
        boolean valid = true;



        return valid;
    }

}
