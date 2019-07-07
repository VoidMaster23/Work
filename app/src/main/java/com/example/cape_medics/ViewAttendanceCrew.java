package com.example.cape_medics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAttendanceCrew extends AppCompatActivity {
    ListView Crew;
    String jobName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_attendance_crew);
        Crew = findViewById(R.id.listView);

        Bundle bundle = getIntent().getExtras();
        jobName = bundle.getString("job");


        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        Map<String, String> datum = new HashMap<String, String>(2);
        datum.put("Name", "Bob");
        datum.put("Info","Date: 2019-05-11              Present");
        data.add(datum);

        datum = new HashMap<String, String>(2);
        datum.put("Name", "Jim");
        datum.put("Info","Date: 2019-05-11              Absent");
        data.add(datum);

        //pull from db based on job id
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                R.layout.twolines,
                new String[] {"Name", "Info" },
                new int[] {R.id.tex1, R.id.tex2 });

        Crew.setAdapter(adapter);
    }

    public void Done (View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        startActivity(i);
    }

}
