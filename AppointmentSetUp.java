package com.example.kindzekas.familyexpenses;
/**
Set-up for a the appointments activity view
**/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AppointmentSetUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_set_up);
    }
    public void add(View view){
        Object object = new Object();
        String test = object.toString();
        Toast.makeText(this,"Trial"+ test,Toast.LENGTH_LONG).show();
    }
    public void delete(View view){
        Object object = new Object();
        String test = object.toString();
        Toast.makeText(this,"Trial"+ test,Toast.LENGTH_LONG).show();
    }
}
