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

public class EmployerDetails extends Fragment {
    CheckBox iod;
    TextView workmesn;
    EditText workmensEdit;

    public EmployerDetails(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_employer_details,container,false);
        iod = view.findViewById(R.id.iodChk);
        workmesn = view.findViewById(R.id.workmens);
        workmensEdit = view.findViewById(R.id.workmensEdit);

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


}
