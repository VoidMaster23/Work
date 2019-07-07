package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CrewDetails extends Fragment {
    Button go;
    public CrewDetails (){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_crew_details,container,false);
        go = view.findViewById(R.id.signatureButton);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Crew Details ");
                startActivity(i);
            }
        });

        return view;
    }
}
