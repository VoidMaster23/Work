package com.example.cape_medics;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class MedicalFormCalls extends Fragment {
    private ImageView imageView9;
    private TextView textView5;
    private TextView KMs;
    private TextView received,callreceivedTime,calldispachedTime,callEnRouteTime,callArrivedTime,calldepartTime, callHospitalArriveTime, callHandoverTime,freeTime,timeOfDeathedit;
    String callTime,callKM,dispatchTime,dispatchKM,enrouteTime,enrouteKM,arriveTime,arriveKM,departTime,departKM,hospitalTime,hospitalKM,handoverTime,handoverKM,priority,free, freeRead;
    private EditText callreceived;
    private TextView dispached;
    private EditText calldispached;
    private TextView enRoute;
    private EditText callEnRoute;
    private TextView arrived;
    private EditText callArrived;
    private TextView depart;
    private EditText calldepart, freeKM;
    private TextView hospitalArrive;
    private EditText callHospitalArrive;
    private TextView handover;
    private EditText callHandover;
    private TextView textView6;
    private CheckBox checkBox8;
    private CheckBox checkBox9;
    private CheckBox checkBox10;
    private CheckBox checkBox11;
    private CheckBox checkBox12;
    private CheckBox checkBox14;
    private CheckBox checkBox3;
    List<CheckBox> checkBoxList;
    Cache cache;
    String saved;
    JSONObject load;

    JSONObject callDetails;



    public MedicalFormCalls(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_medical_form_calls,container,false);
        imageView9 = view.findViewById( R.id.imageView12 );
        textView5 = view.findViewById( R.id.textView5 );
        KMs = view.findViewById( R.id.KMs );
        received = view.findViewById( R.id.received );
        callreceived = view.findViewById( R.id.callreceived );
        dispached = view.findViewById( R.id.dispached );
        calldispached = view.findViewById( R.id.calldispached );
        enRoute = view.findViewById( R.id.enRoute );
        callEnRoute = view.findViewById( R.id.callEnRoute );
        arrived = view.findViewById( R.id.arrived );
        callArrived = view.findViewById( R.id.callArrived );
        depart = view.findViewById( R.id.depart );
        calldepart = view.findViewById( R.id.calldepart );
        hospitalArrive = view.findViewById( R.id.hospitalArrive );
        callHospitalArrive = view.findViewById( R.id.callHospitalArrive );
        handover = view.findViewById( R.id.handover );
        callHandover = view.findViewById( R.id.callHandover );

        callreceivedTime = view.findViewById( R.id.callreceivedTime );
        calldispachedTime = view.findViewById( R.id.calldispachedTime );
        callEnRouteTime = view.findViewById( R.id.callEnRouteTime );
        callArrivedTime = view.findViewById( R.id.callArrivedTime );
        calldepartTime = view.findViewById( R.id.calldepartTime );
        callHospitalArriveTime = view.findViewById( R.id.callHospitalArriveTime );
        callHandoverTime = view.findViewById( R.id.callHandoverTime );
        freeTime = view.findViewById( R.id.freeTime );
        freeKM = view.findViewById(R.id.free_edit);
        timeOfDeathedit = view.findViewById( R.id.timeOfDeathedit );

        textView6 = view.findViewById( R.id.textView6 );
        checkBox8 = view.findViewById( R.id.checkBox8 );
        checkBox9 = view.findViewById( R.id.checkBox9 );
        checkBox10 = view.findViewById( R.id.checkBox10 );
        checkBox11 = view.findViewById( R.id.checkBox11 );
        checkBox12 = view.findViewById( R.id.checkBox12 );
        checkBox14 = view.findViewById(R.id.checkBox14);



        //checkBox13 = view.findViewById( R.id.checkBox13 );
        checkBox3 = view.findViewById( R.id.notApplicableCheckBox );
        callDetails = new JSONObject();

        TimePicker();

        checkBoxList = Arrays.asList(checkBox8,checkBox9,checkBox10,checkBox11,checkBox12,checkBox14);
        for (CheckBox c:checkBoxList){
            c.setOnClickListener(view1 -> {
                limitChecks(c);
            });
        }

        cache = new Cache(getContext());
        saved = cache.getStringProperty("callDetails");
        if(saved != null ){
            try {
                load = new JSONObject(saved);
                callreceivedTime.setText(callDetails.getString("Call_Received_Time"));
                callreceived.setText(callDetails.getString("Call_Received_KM"));
                calldispachedTime.setText(callDetails.getString("Dispached_Time"));
                calldispached.setText(callDetails.getString("Dispached_KM"));
                callEnRouteTime.setText(callDetails.getString("En-Route_Time"));
                callEnRoute.setText(callDetails.getString("En-Route_KM"));
                callArrivedTime.setText(callDetails.getString("Arrived_Time"));
                calldepartTime.setText(callDetails.getString("Arrived_KM"));
                calldepart.setText(callDetails.getString("Depart_Time"));
                callHospitalArriveTime.setText(callDetails.getString("Depart_KM"));
                callHospitalArrive.setText(callDetails.getString("Hospital_Time"));
                callHandoverTime.setText(callDetails.getString("Hospital_KM"));
                callHandover.setText(callDetails.getString("Hospital_Time"));
                freeTime.setText(callDetails.getString("Free_Time"));
                freeKM.setText(callDetails.getString("Free_KM"));
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

    Context mContext;
    private void TimePicker()
    {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        mContext = getActivity();

        callreceivedTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> callreceivedTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        calldispachedTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> calldispachedTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        callEnRouteTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> callEnRouteTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        callArrivedTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> callArrivedTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        calldepartTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> calldepartTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        callHospitalArriveTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> callHospitalArriveTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        callHandoverTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> callHandoverTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        freeTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> freeTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

        timeOfDeathedit.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), (view1, hourOfDay, minute1) -> timeOfDeathedit.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

    }


    public JSONObject createJson(){



        callDetails = new JSONObject();

        try{
            //callRec
            callTime = callEnRouteTime.getText().toString();
            callKM = callreceived.getText().toString();

            //dispach
            dispatchTime = calldispachedTime.getText().toString();
            dispatchKM = calldispached.getText().toString();

            //enroute
            enrouteTime = callEnRouteTime.getText().toString();
            enrouteKM = callEnRoute.getText().toString();

            //arrived
            arriveTime = callArrivedTime.getText().toString();
            arriveKM = callArrived.getText().toString();

            //Depart
            departTime = calldepartTime.getText().toString();
            departKM = calldepart.getText().toString();

            //arriveHosp
            hospitalTime = callHospitalArriveTime.getText().toString();
            hospitalKM = callHospitalArrive.getText().toString();

            //handover
            handoverTime = callHandoverTime.getText().toString();
            handoverKM = callHandover.getText().toString();

            //free
            free = freeTime.getText().toString();
            freeRead=  freeKM.getText().toString();

            //call priority
            priority = "";



            for(CheckBox c: checkBoxList){
                if(c.isChecked()){
                    priority = c.getText().toString();
                }
            }

            if(validate() && !checkBox3.isChecked()) {

                callDetails.put("Call_Received_Time", callTime);
                callDetails.put("Call_Received_KM", callKM);
                callDetails.put("Dispached_Time", dispatchTime);
                callDetails.put("Dispached_KM", dispatchKM);
                callDetails.put("En-Route_Time", enrouteTime);
                callDetails.put("En-Route_KM", enrouteKM);
                callDetails.put("Arrived_Time", arriveTime);
                callDetails.put("Arrived_KM", arriveKM);
                callDetails.put("Depart_Time", departTime);
                callDetails.put("Depart_KM", departKM);
                callDetails.put("Hospital_Time", hospitalTime);
                callDetails.put("Hospital_KM", hospitalKM);
                callDetails.put("Handover_Time", handoverTime);
                callDetails.put("Handover_KM", handoverKM);
                callDetails.put("Call_Priority", priority);
                callDetails.put("Free_Time", free);
                callDetails.put("Free_KM", freeRead);
                callDetails.put("Time_of_Death", timeOfDeathedit.getText().toString());
            }else if(checkBox3.isChecked()){
                callDetails.put("Status","Not applicable");
            }


        }catch (Exception e){
          e.printStackTrace();
        }
        cache.setStringProperty("callDetails",callDetails.toString());
        return callDetails;
    }

    public void limitChecks(View v){

        for(CheckBox c: checkBoxList){
            if(!c.equals(v)){
                c.setChecked(false);
            }
        }

    }

    public void makeNa(View v){


        medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current + 1);

    }

    public boolean validate(){
        boolean valid = true;
        Looper.prepare();//Call looper.prepare()


        if(callTime.isEmpty()){
            valid = false;
            Toast.makeText(getContext(),"Call Details: Please enter the time of the call",Toast.LENGTH_SHORT).show();
        }

        if(valid){
            if(callKM.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the call km reading",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(dispatchTime.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the time of dispatch",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(dispatchKM.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the dispatch km reading",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(enrouteTime.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the time the crew was en-route",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(enrouteKM.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the en-route km reading",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(arriveTime.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the time of arrival",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(arriveKM.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the arrival km reading",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(departTime.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the time of departing the scene",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(departKM.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the departure km reading",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(hospitalTime.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the time of arrival at the hospital",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(hospitalKM.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the hospital arrival km reading",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(handoverTime.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the time of hospital handover",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(handoverKM.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the hospital handover km reading",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(free.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the time freed",Toast.LENGTH_SHORT).show();
            }
        }

        if(valid){
            if(freeRead.isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the free km reading",Toast.LENGTH_SHORT).show();
            }
        }

        //check that at least one call priority thing is checked
        if(valid){
            boolean nonechecked = true;
            for(CheckBox c: checkBoxList){
                if(c.isChecked()){
                    nonechecked = false;
                    break;
                }
            }

            if(nonechecked){
                valid = false;
                Toast.makeText(getContext(), "Call Details: Please select the call priority/triage",Toast.LENGTH_SHORT).show();
            }
        }

        //checks if the patient is dead
        if(valid){
            if(checkBox12.isChecked() && timeOfDeathedit.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Call Details: Please enter the time of death",Toast.LENGTH_SHORT).show();
            }
        }

        Looper.loop();

        return valid;
    }


}