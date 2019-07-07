package com.example.cape_medics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Death2 extends Fragment {
Button go,send;


public Death2(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_death2,container,false);
        go = view.findViewById(R.id.signatureButton);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Signature.class);
                i.putExtra("name", "Commissioner of Oaths ");
                startActivity(i);
            }
        });

        send = view.findViewById(R.id.button13);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Home_Screen_Crew.class);


                Toast.makeText(getContext(),"Form Submitted", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        return view;
    }


}
