package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RefusalofCare extends Fragment {
    Button go1, go2;
    public RefusalofCare(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_refusalof_care,container,false);

        go1 = view.findViewById(R.id.signatureButton);
        go2 = view.findViewById(R.id.witnessButton);

        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Patient Signature ");
                startActivity(i);
            }
        });

        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Witness Signature");
                startActivity(i);
            }
        });
        return view;
    }


}
