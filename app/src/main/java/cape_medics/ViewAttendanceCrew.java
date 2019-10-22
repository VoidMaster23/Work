package cape_medics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.cape_medics.Home_Screen_Crew;
import com.example.cape_medics.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ViewAttendanceCrew extends AppCompatActivity {
    ListView Crew;
    List<Map<String, String>> data;
    Map<String, String> datum;
    String jobName, info, attendance;
    JSONObject response, jobInfo;
    String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_attendance_crew);
        Crew = findViewById(R.id.listView);
        data = new ArrayList<Map<String, String>>();

        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");
        jobName = bundle.getString("job");
        info = bundle.getString("names");

        try {
            response = new JSONObject(info); // turns all info into Json
            String item = response.getString(jobName); // gets clicked on job as string
            jobInfo = new JSONObject(item); // turns that specific job into json
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Iterator<String> keys = jobInfo.keys(); // parse through each item in Json
        while(keys.hasNext()) {
            String key = keys.next();
            try {
                datum = new HashMap<String, String>(2);
                attendance = jobInfo.getString(key); // key is persons name, item is the date and attendance
                datum.put("Name", key);
                JSONObject attendanceJson = new JSONObject(attendance);
                datum.put("Info", attendanceJson.getString("Date")+", "+attendanceJson.getString("Checkin"));
                data.add(datum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /*datum.put("Name", "Bob");
        datum.put("Info","Date: 2019-05-11              Present");
        data.add(datum);

        datum = new HashMap<String, String>(2);
        datum.put("Name", "Jim");
        datum.put("Info","Date: 2019-05-11              Absent");
        data.add(datum);*/

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                R.layout.twolines,
                new String[] {"Name", "Info" },
                new int[] {R.id.tex1, R.id.tex2 });

        Crew.setAdapter(adapter);
    }

    public void Done (View v){
        Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
        i.putExtra("first","not");
        i.putExtra("code",code);
        startActivity(i);
    }

}
