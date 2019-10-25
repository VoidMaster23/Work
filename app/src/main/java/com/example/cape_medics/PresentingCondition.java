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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class PresentingCondition extends Fragment{
    public CheckBox chkNA;
    private TextView lblPrimary;
    private EditText edtPrimary;
    private TextView textView19;
    private CheckBox chkCardiac;
    private CheckBox chkChest;
    private CheckBox chkAst;
    private CheckBox chkCopd;
    private CheckBox chkAb;
    private CheckBox chkVomit;
    private CheckBox chkConvulse;
    private CheckBox chkStroke;
    private CheckBox chkDiabetes;
    private CheckBox chkHypo;
    private CheckBox chkHyper;
    private CheckBox chkAlcohol;
    private CheckBox chkDrugs;
    private CheckBox chkFirstAid;
    private CheckBox chkAssault;
    private CheckBox chkMoreTwo;
    private CheckBox chkLessTwo;
    private CheckBox chkHead;
    private CheckBox chkPen;
    private CheckBox chkFract;
    private CheckBox chkMva;
    private CheckBox chkDrown;
    private CheckBox chkBurn;
    private CheckBox chkGun;
    private CheckBox chkInhale;
    private CheckBox chkMusc;
    private CheckBox chkObs;
    private CheckBox chkDeliver;
    List<CheckBox> checkBoxList;
    Cache cache;
    String saved;
    JSONObject load;

  JSONObject presentingConditon;

    public PresentingCondition() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_presenting_condition, container, false);
        chkNA = (CheckBox)view.findViewById( R.id.notApplicableCheckBox );
        lblPrimary = (TextView)view.findViewById( R.id.lblPrimary );
        edtPrimary = (EditText)view.findViewById( R.id.edtPrimary );
        textView19 = (TextView)view.findViewById( R.id.textView19 );
        chkCardiac = (CheckBox)view.findViewById( R.id.chkCardiac );
        chkChest = (CheckBox)view.findViewById( R.id.chkChest );
        chkAst = (CheckBox)view.findViewById( R.id.chkAst );
        chkCopd = (CheckBox)view.findViewById( R.id.chkCopd );
        chkAb = (CheckBox)view.findViewById( R.id.chkAb );
        chkVomit = (CheckBox)view.findViewById( R.id.chkVomit );
        chkConvulse = (CheckBox)view.findViewById( R.id.chkConvulse );
        chkStroke = (CheckBox)view.findViewById( R.id.chkStroke );
        chkDiabetes = (CheckBox)view.findViewById( R.id.chkDiabetes );
        chkHypo = (CheckBox)view.findViewById( R.id.chkHypo );
        chkHyper = (CheckBox)view.findViewById( R.id.chkHyper );
        chkAlcohol = (CheckBox)view.findViewById( R.id.chkAlcohol );
        chkDrugs = (CheckBox)view.findViewById( R.id.chkDrugs );
        chkFirstAid = (CheckBox)view.findViewById( R.id.chkFirstAid );
        chkAssault = (CheckBox)view.findViewById( R.id.chkAssault );
        chkMoreTwo = (CheckBox)view.findViewById( R.id.chkMoreTwo );
        chkLessTwo = (CheckBox)view.findViewById( R.id.chkLessTwo );
        chkHead = (CheckBox)view.findViewById( R.id.chkHead );
        chkPen = (CheckBox)view.findViewById( R.id.chkPen );
        chkFract = (CheckBox)view.findViewById( R.id.chkFract );
        chkMva = (CheckBox)view.findViewById( R.id.chkMva );
        chkDrown = (CheckBox)view.findViewById( R.id.chkDrown );
        chkBurn = (CheckBox)view.findViewById( R.id.chkBurn );
        chkGun = (CheckBox)view.findViewById( R.id.chkGun );
        chkInhale = (CheckBox)view.findViewById( R.id.chkInhale );
        chkMusc = (CheckBox)view.findViewById( R.id.chkMusc );
        chkObs = (CheckBox)view.findViewById( R.id.chkObs );
        chkDeliver = (CheckBox)view.findViewById( R.id.chkDeliver );
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });

        checkBoxList = Arrays.asList(chkCardiac,chkAssault,chkChest,chkLessTwo,chkMoreTwo,chkAst,chkCopd,chkHead,chkAb,chkPen,chkVomit,chkFract,chkConvulse,chkMva,chkStroke,chkDrown,chkDiabetes,chkBurn,chkHypo,chkGun,chkHyper,chkInhale,chkAlcohol,chkMusc,chkDrugs,chkObs,chkFirstAid,chkDeliver);
        cache = new Cache(getContext());
        saved = cache.getStringProperty("presentingConditon");
        if(saved != null ){
            try {
                load = new JSONObject(saved);
                edtPrimary.setText(load.getString("Primary"));
                Iterator<String> keys = load.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    String item = load.getString(key);
                    for (int j = 0; j<checkBoxList.size();j++) {
                        if (checkBoxList.get(j) != null) {
                            if (item.equals(checkBoxList.get(j).getText().toString())) {
                                checkBoxList.get(j).setChecked(true);

                            }
                        }
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return view;

    }


    public JSONObject createJson(){
        //name

        String name = edtPrimary.getText().toString();

        //medical condition
        String condition = null;
        if(chkCardiac.isChecked()){
            condition = chkCardiac.getText().toString();
        }else  if(chkCardiac.isChecked()){
            condition = chkCardiac.getText().toString();
        }else  if(chkAssault.isChecked()){
            condition = chkAssault.getText().toString();
        }else  if(chkChest.isChecked()){
            condition = chkChest.getText().toString();
        }else  if(chkMoreTwo.isChecked()){
            condition = chkMoreTwo.getText().toString();
        }else  if(chkAst.isChecked()){
            condition = chkAst.getText().toString();
        }else  if(chkLessTwo.isChecked()){
            condition = chkLessTwo.getText().toString();
        }else  if(chkCopd.isChecked()){
            condition = chkCopd.getText().toString();
        }else  if(chkHead.isChecked()){
            condition = chkHead.getText().toString();
        }else  if(chkAb.isChecked()){
            condition = chkAb.getText().toString();
        }else  if(chkPen.isChecked()){
            condition = chkPen.getText().toString();
        }else  if(chkVomit.isChecked()){
            condition = chkVomit.getText().toString();
        }else  if(chkFract.isChecked()){
            condition = chkFract.getText().toString();
        }else  if(chkConvulse.isChecked()){
            condition = chkConvulse.getText().toString();
        }else  if(chkMva.isChecked()){
            condition = chkMva.getText().toString();
        }else  if(chkStroke.isChecked()){
            condition = chkStroke.getText().toString();
        }else  if(chkDrown.isChecked()){
            condition = chkDrown.getText().toString();
        }else  if(chkDiabetes.isChecked()){
            condition = chkDiabetes.getText().toString();
        }else  if(chkBurn.isChecked()){
            condition = chkBurn.getText().toString();
        }else  if(chkHypo.isChecked()){
            condition = chkHypo.getText().toString();
        }else  if(chkGun.isChecked()){
            condition = chkGun.getText().toString();
        }else  if(chkHyper.isChecked()){
            condition = chkHyper.getText().toString();
        }else  if(chkInhale.isChecked()){
            condition = chkInhale.getText().toString();
        }else  if(chkAlcohol.isChecked()){
            condition = chkAlcohol.getText().toString();
        }else  if(chkMusc.isChecked()){
            condition = chkMusc.getText().toString();
        }else  if(chkDrugs.isChecked()){
            condition = chkDrugs.getText().toString();
        }else  if(chkObs.isChecked()){
            condition = chkObs.getText().toString();
        }else  if(chkFirstAid.isChecked()){
            condition = chkFirstAid.getText().toString();
        }else  if(chkDeliver.isChecked()){
            condition = chkDeliver.getText().toString();
        }

        presentingConditon = new JSONObject();

        try{
            presentingConditon.put("Primary",name);
            presentingConditon.put("Medical Condition",condition);
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        cache.setStringProperty("presentingConditon",presentingConditon.toString());

        return presentingConditon;
    }

    public boolean validate(){
        boolean valid = true;

        if(edtPrimary.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(getContext(),"Please enter the name of the primary presenting the complaint", Toast.LENGTH_SHORT).show();
        }

        if(valid){
            boolean allEmpty = true;
            for(CheckBox b : checkBoxList){
                if(b.isChecked()){
                    allEmpty = false;
                    break;
                }
            }
            if(allEmpty){
                valid = false;
                Toast.makeText(getContext(),"Please select a medical condition", Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

}
