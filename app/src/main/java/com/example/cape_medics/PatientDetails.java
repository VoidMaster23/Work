package com.example.cape_medics;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class PatientDetails extends Fragment {
    // TODO: Rename and change types of parameters
    private TextView lblName;
    private EditText edtName;
    private TextView lblSurname;
    private EditText edtSurname;
    private TextView lblDOB;
    private TextView edtDOB;
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
    List<CheckBox> checkBoxList;
    JSONObject load;
    Cache cache;
    String saved;

    private CheckBox chkNa;

    String fullName, surName, birth, idNo, emailAdd, phoneNo, altPhone, address, ageGroup, race, sex, ref;


    public PatientDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_details, container, false);
        lblName = (TextView) view.findViewById(R.id.lblName);
        edtName = (EditText) view.findViewById(R.id.edtName);
        lblSurname = (TextView) view.findViewById(R.id.lblSurname);
        edtSurname = (EditText) view.findViewById(R.id.edtSurname);
        lblDOB = (TextView) view.findViewById(R.id.lblDOB);
        edtDOB = view.findViewById(R.id.edtDOB);
        lblID = (TextView) view.findViewById(R.id.lblID);
        edtID = (EditText) view.findViewById(R.id.edtID);
        lblEmail = (TextView) view.findViewById(R.id.lblEmail);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        lblPhone = (TextView) view.findViewById(R.id.lblPhone);
        edtPhone = (EditText) view.findViewById(R.id.edtPhone);
        lblAlt = (TextView) view.findViewById(R.id.lblAlt);
        edtAlt = (EditText) view.findViewById(R.id.edtAlt);
        lblAddress = (TextView) view.findViewById(R.id.lblAddress);
        edtAddress1 = (EditText) view.findViewById(R.id.edtAddress1);
        edtAddress2 = (EditText) view.findViewById(R.id.edtAddress2);
        edtAddress3 = (EditText) view.findViewById(R.id.edtAddress3);
        textView21 = (TextView) view.findViewById(R.id.textView21);
        chkAdult = (CheckBox) view.findViewById(R.id.chkAdult);
        chkChild = (CheckBox) view.findViewById(R.id.chkChild);
        chkBaby = (CheckBox) view.findViewById(R.id.chkBaby);
        textView22 = (TextView) view.findViewById(R.id.textView22);
        chkWhite = (CheckBox) view.findViewById(R.id.chkWhite);
        chkColored = (CheckBox) view.findViewById(R.id.chkColored);
        chkBlack = (CheckBox) view.findViewById(R.id.chkBlack);
        chkAsian = (CheckBox) view.findViewById(R.id.chkAsian);
        textView23 = (TextView) view.findViewById(R.id.textView23);
        chkMale = (CheckBox) view.findViewById(R.id.chkMale);
        chkFemale = (CheckBox) view.findViewById(R.id.chkFemale);
        lblRef = (TextView) view.findViewById(R.id.lblRef);
        edtRef = (EditText) view.findViewById(R.id.edtRef);
        chkNa = view.findViewById(R.id.notApplicableCheckBox);
        chkNa.setOnClickListener(this::makeNa);
        checkBoxList = Arrays.asList(chkAdult, chkChild, chkBaby, chkAsian, chkBlack, chkWhite, chkMale, chkFemale, chkColored);

        for(CheckBox c : checkBoxList){
            c.setOnClickListener(view1 -> {
                if(c.equals(chkAdult) && c.isChecked()){
                    chkChild.setChecked(false);
                    chkBaby.setChecked(false);
                }else if(c.equals(chkChild) && c.isChecked()){
                    chkAdult.setChecked(false);
                    chkBaby.setChecked(false);
                }else if(c.equals(chkBaby) && c.isChecked()){
                    chkChild.setChecked(false);
                    chkAdult.setChecked(false);
                }

                if(c.equals(chkBlack) && c.isChecked()){
                    chkColored.setChecked(false);
                    chkWhite.setChecked(false);
                    chkAsian.setChecked(false);
                }else if(c.equals(chkColored) && c.isChecked()){
                    chkBlack.setChecked(false);
                    chkWhite.setChecked(false);
                    chkAsian.setChecked(false);
                }else if(c.equals(chkAsian) && c.isChecked()){
                    chkBlack.setChecked(false);
                    chkWhite.setChecked(false);
                    chkColored.setChecked(false);
                }else if(c.equals(chkWhite) && c.isChecked()){
                    chkBlack.setChecked(false);
                    chkAsian.setChecked(false);
                    chkColored.setChecked(false);
                }

                if(c.equals(chkMale) && c.isChecked()){
                    chkFemale.setChecked(false);
                }else if(c.equals(chkFemale) && c.isChecked()){
                    chkMale.setChecked(false);
                }

            });
        }

        DatePicker();

        cache = new Cache(getContext());
        saved = cache.getStringProperty("patientDetails");
        if (saved != null) {
            try {
                load = new JSONObject(saved);

                edtName.setText(load.getString("Full_name"));
                edtSurname.setText(load.getString("Surname"));
                edtDOB.setText(load.getString("DOB"));
                edtID.setText(load.getString("ID"));
                edtEmail.setText(load.getString("Email"));
                edtPhone.setText(load.getString("Contact"));
                edtAlt.setText(load.getString("Alt"));
                edtAddress1.setText(load.getString("Address"));
                edtRef.setText(load.getString("Reference_Number"));

                Iterator<String> keys = load.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String item = load.getString(key);
                    for (int j = 0; j < checkBoxList.size(); j++) {
                        if (checkBoxList.get(j) != null) {
                            if (item.equals(checkBoxList.get(j).getText().toString())) {
                                checkBoxList.get(j).setChecked(true);

                            }
                        }
                    }

                }


                //write code for check boxes
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return view;
    }

    Context mContext;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public void DatePicker(){

        mContext = getActivity();

        edtDOB.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    mContext,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            Log.d("tag", "onDateSet: yyyy/mm/dd: " + year + "/" + month + "/" + day);

            String date = year + "/" + month + "/" + day;
            edtDOB.setText(date);
        };
    }

    public JSONObject createJson(){

        //details
         fullName = edtName.getText().toString();
        surName =edtSurname.getText().toString();
        birth = edtDOB.getText().toString();
        idNo = edtID.getText().toString();
        emailAdd = edtEmail.getText().toString();
        phoneNo = edtPhone.getText().toString();
        altPhone = edtAlt.getText().toString();
        address = edtAddress1.getText().toString() +"\n"+edtAddress2.getText().toString() +"\n"+edtAddress3.getText().toString();


        //Age group
         ageGroup  = null;

        if(chkAdult.isChecked()){
            ageGroup = chkAdult.getText().toString();
        }else if(chkChild.isChecked()){
            ageGroup = chkChild.getText().toString();
        }else if(chkBaby.isChecked()){
            ageGroup = chkBaby.getText().toString();
        }

        //race
         race = null;
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
         sex = null;
        if(chkMale.isChecked()){
            sex = chkMale.getText().toString();
        }else if(chkFemale.isChecked()){
            sex = chkFemale.getText().toString();
        }

        //ref
         ref = edtRef.getText().toString();


        //json
        patientDetails = new JSONObject();
        try{

            if(!chkNa.isChecked()&&validate() ) {
                patientDetails.put("Full_name", fullName);
                patientDetails.put("Surname", surName);
                patientDetails.put("DOB", birth);
                patientDetails.put("ID", idNo);
                patientDetails.put("Email", emailAdd);
                patientDetails.put("Contact", phoneNo);
                patientDetails.put("Alt", altPhone);
                patientDetails.put("Address", address);
                patientDetails.put("Age_Group", ageGroup);
                patientDetails.put("Race", race);
                patientDetails.put("Sex", sex);
                patientDetails.put("Reference_Number", ref);
            }else if(chkNa.isChecked()){
                patientDetails.put("Status","Not applicable");
            }

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        cache.setStringProperty("patientDetails",patientDetails.toString());

        return patientDetails;

    }

    public void makeNa(View v){


        medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current + 1);

    }


    //validation
    public boolean validate(){
        boolean valid = true;
        Looper.prepare();

        if(fullName.isEmpty()){
            valid = false;
            Toast.makeText(getContext(), "Patient Details: Please enter the name of the patient",Toast.LENGTH_SHORT).show();

        }

        if(valid){
            if(surName.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter surname of the patient",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(birth.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter the birth date of the patient",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(surName.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter surname of the patient",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(emailAdd.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter email address of patient",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(phoneNo.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter phone number of the patient",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(altPhone.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter an alternate contact number for the patient",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(address.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter the patient's physical address",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(ageGroup.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please indicate which age group the patient is in",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(race.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter the patient's race",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(sex.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter the patient's sex",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(ref.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Patient Details: Please enter the case number",Toast.LENGTH_SHORT).show();
            }
        }


        Looper.loop();
        return valid;
    }
}


