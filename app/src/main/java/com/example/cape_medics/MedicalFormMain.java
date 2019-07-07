package com.example.cape_medics;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MedicalFormMain extends AppCompatActivity {
    ListView Categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_form_main);
        String[] categoryarray = {"Film&TV", "Event","IFT/IHT","Primary","CTICC","ACSA","GCC","ORTIA","CTIA","KSIA"};
        Categories = findViewById(R.id.category);
        ArrayAdapter<String> categories = new ArrayAdapter<String>(this, R.layout.list_row,categoryarray);
        Categories.setAdapter(categories);

        Categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent i = new Intent(getApplicationContext(), MedicalFormCalls.class);
                i.putExtra("job", String.valueOf(Categories.getItemAtPosition(position)));
                startActivity(i);
            }
        });
    }
}
