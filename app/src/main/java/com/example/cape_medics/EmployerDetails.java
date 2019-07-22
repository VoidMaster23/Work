package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class EmployerDetails extends Fragment {
    JSONObject employerDetails;
    CheckBox iod;
    TextView workmesn;
    EditText workmensEdit, company,address,address1,address2,email,contactPerson,contactNumber;

    public EmployerDetails(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_employer_details,container,false);
        employerDetails = new JSONObject();
        iod = view.findViewById(R.id.iodChk);
        workmesn = view.findViewById(R.id.workmens);
        workmensEdit = view.findViewById(R.id.workmensEdit);
        company = view.findViewById(R.id.companyEdit);
        address = view.findViewById(R.id.addressEdit);
        address1 = view.findViewById(R.id.addressEdit1);
        address2 = view.findViewById(R.id.addressEdit2);
        email = view.findViewById(R.id.emailEdit);
        contactPerson = view.findViewById(R.id.personEdit);
        contactNumber = view.findViewById(R.id.numberEdit);


        iod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iod.isChecked()) {
                    workmensEdit.setVisibility(View.VISIBLE);
                    workmesn.setVisibility(View.VISIBLE);
                }
                else{
                    workmensEdit.setVisibility(View.INVISIBLE);
                    workmesn.setVisibility(View.INVISIBLE);
                }
            }
        });
        return view;
    }

    public JSONObject Send(View v){
        employerDetails = new JSONObject();
        if (iod.isChecked()) {
            try{
                employerDetails.put("Workmens Comp No.", workmensEdit.toString());
                employerDetails.put("IOD",iod.getText().toString());

            }catch(Exception e){}
        }
        try{
            employerDetails.put("Company Name", company.toString());
            employerDetails.put("Address", address.toString()+" "+address1.toString()+" "+address2.toString());
            employerDetails.put("Email", email.toString());
            employerDetails.put("Contact Person", contactPerson.toString());
            employerDetails.put("Contact Number", contactNumber.toString());
        }catch(Exception e){}

        return employerDetails;
    }


}
