package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class Stroke extends Fragment {
    private ImageView imageView18;
    private CheckBox  chkNA;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private TextView textView55;
    private EditText editDoorDrug;
    private TextView textView56;
    private EditText editStrokeUnit;
    private TextView textView57;
    private EditText editCasualty;

    private Button fd_btnYes, fd_btnNo, aw_btnYes, aw_btnNo, sd_btnYes, sd_btnNo;

    JSONObject stroke;




    public Stroke() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_stroke, container, false);

        imageView18 = view.findViewById( R.id.imageView18 );
        chkNA = view.findViewById( R.id.chkNA );
        //checkBox4 = view.findViewById( R.id.checkBox4 );
        //checkBox5 = view.findViewById( R.id.checkBox5 );
        //checkBox6 = view.findViewById( R.id.checkBox6 );
        textView55 = view.findViewById( R.id.textView55 );
        editDoorDrug = view.findViewById( R.id.editDoorDrug );
        //textView56 = (TextView)view.findViewById( R.id.textView56 );
        editStrokeUnit = view.findViewById( R.id.editStrokeUnit );
        //textView57 = (TextView)view.findViewById( R.id.textView57 );
        editCasualty = view.findViewById( R.id.editCasualty );

        fd_btnYes = view.findViewById(R.id.fd_btnYes);
        fd_btnNo = view.findViewById(R.id.fd_btnNo);
        aw_btnYes = view.findViewById(R.id.aw_btnYes);
        aw_btnNo = view.findViewById(R.id.aw_btnNo);
        sd_btnYes = view.findViewById(R.id.sd_btnYes);
        sd_btnNo = view.findViewById(R.id.sd_btnNo);

        Init();

        return view;
    }

    String facial = "No", arm = "No", speech = "No";

    private void Init(){

        fd_btnYes.setOnClickListener(v -> {
            fd_btnYes.setBackgroundResource(R.drawable.orangeshape);
            fd_btnNo.setBackgroundResource(R.drawable.whiteshape);

            facial = "yes";
        });

        fd_btnNo.setOnClickListener(v -> {
            fd_btnYes.setBackgroundResource(R.drawable.whiteshape);
            fd_btnNo.setBackgroundResource(R.drawable.orangeshape);

            facial = "No";
        });

        aw_btnYes.setOnClickListener(v -> {
            aw_btnYes.setBackgroundResource(R.drawable.orangeshape);
            aw_btnNo.setBackgroundResource(R.drawable.whiteshape);

            arm = "Yes";
        });

        aw_btnNo.setOnClickListener(v -> {
            aw_btnYes.setBackgroundResource(R.drawable.whiteshape);
            aw_btnNo.setBackgroundResource(R.drawable.orangeshape);

            arm = "No";
        });

        sd_btnYes.setOnClickListener(v -> {
            sd_btnYes.setBackgroundResource(R.drawable.orangeshape);
            sd_btnNo.setBackgroundResource(R.drawable.whiteshape);

            speech = "Yes";
        });

        sd_btnNo.setOnClickListener(v -> {
            sd_btnYes.setBackgroundResource(R.drawable.whiteshape);
            sd_btnNo.setBackgroundResource(R.drawable.orangeshape);

            speech = "No";
        });
    }

    public JSONObject createJson(){

        stroke = new JSONObject();

        //String facial = null;
        //String arm = null;
        //String speech = null;
        //if(checkBox4.isChecked()) facial = checkBox4.getText().toString();
        //if(checkBox5.isChecked()) arm = checkBox5.getText().toString();
        //if(checkBox6.isChecked()) speech = checkBox6.getText().toString();

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

}
