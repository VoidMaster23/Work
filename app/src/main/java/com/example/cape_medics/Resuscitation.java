package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Resuscitation extends Fragment {


    TextView textView;
    int minutes, sec;
    String time;

    Button start,stop,reset;

    public Resuscitation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resuscitation, container, false);

        minutes = 3;
        sec = 60;
        textView = view.findViewById(R.id.countDown);

        final CountDownTimer timer = new CountDownTimer(180000,1000){
            @Override
            public void onTick(long l) {
                if(textView.getText().toString().endsWith("00") && Integer.parseInt(textView.getText().toString().substring(1,2))> 0){
                    minutes--;
                    sec = 59;
                    if (sec < 10){
                        time = "0"+ minutes +":0"+ sec;

                    }else {
                        time = "0" + minutes + ":" + sec;
                    }
                    textView.setText(time);
                }else{
                    sec--;
                    if (sec < 10){
                        time = "0"+ minutes +":0"+ sec;

                    }else {
                        time = "0" + minutes + ":" + sec;
                    }
                    textView.setText(time);

                }
            }

            @Override
            public void onFinish() {
                textView.setText("03:00");
                minutes = 3;
                sec = 60;
            }
        };

        start = view.findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
            }
        });

        stop = view.findViewById(R.id.btnStop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
            }
        });

        reset = view.findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                timer.onFinish();
            }
        });

        return view;
    }


}
