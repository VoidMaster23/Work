package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class GuaranteeOfPayment extends Fragment {

    JSONObject guaranteeOfPayment;
    EditText name,company,positionHeld, company1;
    String authorisedPerson;
    CheckBox staff, passenger, person,chkNA;


    public GuaranteeOfPayment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_guarantee_of_payment,container,false);
        guaranteeOfPayment = new JSONObject();

        name = view.findViewById(R.id.nameEdit);
        company = view.findViewById(R.id.companyEdit);
        positionHeld = view.findViewById(R.id.positionEdit);
        company1 = view.findViewById(R.id.company2Edit);

        staff = view.findViewById(R.id.staffChk);
        passenger = view.findViewById(R.id.passengerChk);
        person = view.findViewById(R.id.personChk);
        chkNA = view.findViewById( R.id.notApplicableCheckBox);
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
        guaranteeOfPayment = new JSONObject();

        if(!chkNA.isChecked() && validate()){
        if(staff.isChecked()) authorisedPerson = "Staff";
        if(passenger.isChecked()) authorisedPerson = "Passenger";
        if(person.isChecked()) authorisedPerson = "Person";

        try{
            guaranteeOfPayment.put("Full Name", name.getText().toString());
            guaranteeOfPayment.put("Company Name", company.getText().toString());
            guaranteeOfPayment.put("Position Held", positionHeld.getText().toString());
            guaranteeOfPayment.put("Company Name", company1.getText().toString());
            guaranteeOfPayment.put("Authorised Person", authorisedPerson);

        }catch(Exception e){e.printStackTrace();}
        }else if(chkNA.isChecked()){
            try {
                guaranteeOfPayment.put("Status","Not applicable");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return guaranteeOfPayment;
    }

    public boolean validate(){
        boolean valid = true;

        if(name.getText().toString().isEmpty()) valid = false;
        if(company.getText().toString().isEmpty()) valid = false;
        if(company1.getText().toString().isEmpty()) valid = false;
        if(positionHeld.getText().toString().isEmpty()) valid = false;
        if(!staff.isChecked() && !passenger.isChecked() && !person.isChecked()) valid = false;

        if(!valid){
            Toast.makeText(getContext(),"Please fill in guarantee of payment", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }
}
