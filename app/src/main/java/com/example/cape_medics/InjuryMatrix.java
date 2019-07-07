package com.example.cape_medics;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.GridView;


public class InjuryMatrix extends Fragment {
    GridView grid;
    CheckBox[] chk;

    public InjuryMatrix() {
        // Required empty public constructor
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_injury_matrix, container, false);
        chk = new CheckBox[99];

        grid = view.findViewById(R.id.gridLay);

        for(int i = 0; i < 99; i++){
            chk[i] = new CheckBox(getContext());
            //int id  = View.generateViewId();
            chk[i].setId(i);
            chk[i].setText(null);

            Log.i("CheckBoc number ", Integer.toString(i));
        }

        CustomAdapter adapter = new CustomAdapter(getContext(),chk);
        grid.setAdapter(adapter);


        return view;
    }


}
