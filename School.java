package com.example.kindzekas.familyexpenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class School extends CommonMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
    }

    public  void todoList(View view){
        Intent todolist = new Intent(this, ToDOList.class);
        startActivity(todolist);

    }

    public void timeTable(View view){
        Intent timeTable = new Intent(this, TimeTable.class);
        startActivity(timeTable);

    }
}
