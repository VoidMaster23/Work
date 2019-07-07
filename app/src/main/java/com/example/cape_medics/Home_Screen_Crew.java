package com.example.cape_medics;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Home_Screen_Crew extends AppCompatActivity {
    /*Button checkin;
    Button dashboard;
    Button HRform;
    Button MedRecords;
    Button timesheet;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_crew);

        /*checkin = findViewById(R.id.CheckIn);
        dashboard = findViewById(R.id.DashBoard);
        HRform = findViewById(R.id.HR_Forms);
        MedRecords = findViewById(R.id.MedicalRecords);
        timesheet = findViewById(R.id.TimeSheet);*/
        //create pull down menu with logout option
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == R.id.new_game){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Log out")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getApplicationContext(),Login_Page.class);
                            startActivity(i);
                        }
                    }).show();

            return true;
        }
        return true;
    }



    public void Dash(View v){
        Intent i = new Intent(getApplicationContext(), DashBoard.class);
        startActivity(i);
    }

    public  void CheckIn(View v){

    }

    public void vehicle(View v){

        Intent i = new Intent(getApplicationContext(),VehicleCheckList.class);
        startActivity(i);

    }

    public void MedRec(View v){
    Intent i = new Intent(getApplicationContext(), categoryType.class);
    startActivity(i);

    }

    public void TimeSheet(View v){
        Intent i = new Intent(getApplicationContext(), TimeSheet.class);
        startActivity(i);

    }

    public void Attendance (View v){
        Intent i = new Intent(getApplicationContext(), ViewAttendance.class);
        startActivity(i);

    }

    public void BroadCast (View v){
        Intent i = new Intent(getApplicationContext(), ChooseBroadCast.class);
        startActivity(i);

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(),Login_Page.class);
                        startActivity(i);
                    }
                }).show();
    }
}
