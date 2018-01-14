package com.example.kindzekas.familyexpenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Kindzekas on 2017-01-09.
 */

public class CommonMenu  extends AppCompatActivity{
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);

        // Find the menuItem to add your SubMenu
        MenuItem myMenuItem = menu.findItem(R.id.time_table);
        // Inflating the sub_menu menu this way, will add its menu items
        // to the empty SubMenu you created in the xml
        getMenuInflater().inflate(R.menu.sub_menu, myMenuItem.getSubMenu());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DisplayMetrics dm = new DisplayMetrics();
        final int width;
        final int height;

        switch (item.getItemId()) {
            case R.id.menu_exit:
                setContentView(R.layout.terminate);
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                width = dm.widthPixels;
                height = dm.heightPixels;
                getWindow().setLayout((int) (width * .7), (int) (height * .5));

                return true;
            case R.id.menu_help:

                Intent helpIntent = new Intent(this, Help.class);
                startActivity(helpIntent);
                return true;
            case R.id.Home:

                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);
                return true;

            case R.id.work:
                Intent workIntent = new Intent(this, Work.class);
                startActivity(workIntent);
                return true;

            case R.id.appointments:
                Intent apntIntent = new Intent(this, Appointments.class);
                startActivity(apntIntent);
                return true;

            case R.id.school:
                Intent schoolIntent = new Intent(this, School.class);
                startActivity(schoolIntent);
                return true;

            case R.id.expenses:
                Intent expenseIntent = new Intent(this, Expenses.class);
                startActivity(expenseIntent);
                return true;
            case R.id.journal:
                Intent diary = new Intent(this, Validation.class);
                startActivity(diary);
                return true;

            case R.id.time_table:
                setContentView(R.layout.settings_options);
                Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
                String[] settings = new String[]{" ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, settings);
                dropdown.setAdapter(adapter);
                dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



            default:
                return super.onOptionsItemSelected(item);
        }


    }



    //Method handles the termination interaction with user.
    public void exitApplication(View view) {
        //Terminates the activity.
        this.finish();
        System.exit(0);
    }

    public void reSume(View view) {
        //Resumes the activity.
        this.finish();
        Intent home = new Intent(this, this.getClass());
        startActivity(home);
    }
}
