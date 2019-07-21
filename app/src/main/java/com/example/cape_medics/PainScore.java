package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class PainScore extends Fragment {

    ImageView imageView;
    EditText pre, post1, post2, hosp;
    Button show;

    TextView textView1, textView2, textView3, textView4;
    RelativeLayout painLay;

    JSONObject painScore;

    public PainScore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pain_score, container, false);

        imageView = view.findViewById(R.id.imgPain);



        pre = view.findViewById(R.id.editPre);
        post1 = view.findViewById(R.id.editPost5);
        post2 = view.findViewById(R.id.editPost10);
        hosp = view.findViewById(R.id.editAtHosp);

        textView1 = view.findViewById(R.id.textView51);
        textView2 = view.findViewById(R.id.textView52);
        textView3 = view.findViewById(R.id.textView53);
        textView4 = view.findViewById(R.id.textView54);

        show = view.findViewById(R.id.btnPainScale);
        painLay = view.findViewById(R.id.relLayPain);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageView.getVisibility() == View.INVISIBLE){
                    pre.setVisibility(View.INVISIBLE);
                    post1.setVisibility(View.INVISIBLE);
                    post2.setVisibility(View.INVISIBLE);
                    hosp.setVisibility(View.INVISIBLE);

                    textView1.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                    textView4.setVisibility(View.INVISIBLE);

                    show.setVisibility(View.INVISIBLE);

                    painLay.setBackgroundColor(getResources().getColor(android.R.color.black));
                    imageView.setVisibility(View.VISIBLE);




                }
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageView.getVisibility() == View.VISIBLE){
                    pre.setVisibility(View.VISIBLE);
                    post1.setVisibility(View.VISIBLE);
                    post2.setVisibility(View.VISIBLE);
                    hosp.setVisibility(View.VISIBLE);

                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);

                    show.setVisibility(View.VISIBLE);

                    painLay.setBackground(getResources().getDrawable(R.mipmap.back));
                    show.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.INVISIBLE);




                }
            }
        });



        return view;
    }

    public JSONObject createJson(){

        painScore = new JSONObject();

        String preStr = pre.getText().toString();
        String post5Str = post1.getText().toString();
        String post10Str = post2.getText().toString();
        String hospStr = hosp.getText().toString();

        try{
            painScore.put("Pre-Analgesia",preStr);
            painScore.put("Post 5",post5Str);
            painScore.put("Post 10",post10Str);
            painScore.put("Hospital",hospStr);


        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return painScore;
    }

}
