package com.example.kindzekas.familyexpenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void appointments(View view){

        Intent appointments = new Intent(this,Appointments.class);
        startActivity(appointments);
    }
    public void work(View view){
        Intent work = new Intent(this, Work.class);
        startActivity(work);
    }
    public void school(View view){
        Intent school = new Intent(this,School.class);
        startActivity(school);
    }
    public void expenses(View view){
        Intent expenses = new Intent(this, Expenses.class);
        startActivity(expenses);
    }

    public void journal(View view){
        Intent journal = new Intent(this, Validation.class);
        startActivity(journal);
    }
}
