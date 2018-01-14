package com.example.kindzekas.familyexpenses;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import static android.R.id.list;

public class Appointments extends CommonMenu {
    static ArrayList<aModel_Appoinment> appointments;
    ArrayList<String> items;
    ArrayAdapter adapter;
    //DBHelper mDBHelper = new DBHelper(this);
    DBHelperControllerDAO mDBHelper ;
    DisplayMetrics dm = new DisplayMetrics();
    ListView list;
    CheckedTextView myItem;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        appointments = new ArrayList<>();
        mDBHelper = new DBHelperControllerDAO(this);
        items = new ArrayList<>();
        list = (ListView) findViewById(R.id.app_listview);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter(this, R.layout.list_view_adapter, R.id.row_layout, appointments);
        list.setAdapter(adapter);
        myItem = (CheckedTextView)findViewById(R.id.row_layout);

        try{
            mDBHelper.openDB();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentView, View viewClicked, int position, long id) {
                TextView clickedItem = (TextView)viewClicked;

                String selected = parentView.getItemAtPosition(position).toString();
                String it = clickedItem.getText().toString();


                   // Toast.makeText(Appointments.this, position + it + " And " + " was clicked!!!", Toast.LENGTH_LONG).show();


            }

            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    // OnResume method to load date from database.
    @Override
    protected void onResume() {
        super.onResume();

        mDBHelper.openDB();
        //Read entries from database and bind to list view.
        appointments = mDBHelper.getAllAppointments();
        adapter = new ArrayAdapter(this, R.layout.list_view_adapter, R.id.row_layout,appointments);
        //adapter = new ArrayAdapter(this, R.layout.activity_appointments, R.id.row_layout, appoinments);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentView, View viewClicked, int position, long id) {
                TextView clickedItem = (TextView)viewClicked;
            }

            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

    }

    public void add(View view) {
//Popup window for appointment entry.
        setContentView(R.layout.activity_appointment_set_up);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 1), (int) (height * 1));

    }

    /** Method adds new appointment to list/database
     *
     * @param v
     */
    public void done(View v) {
        EditText about = (EditText) findViewById(R.id.app_about);
        EditText venue = (EditText) findViewById(R.id.app_venue);
        EditText date = (EditText) findViewById(R.id.app_date);
        EditText time = (EditText) findViewById(R.id.app_time);
        if (about.getText().toString().equals("") || venue.getText().toString().equals("") || date.getText().toString().equals("") || time.getText().toString().equals("")) {
            Toast.makeText(this, "Empty entries", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            aModel_Appoinment app_model = new aModel_Appoinment(about.getText().toString(), venue.getText().toString(), date.getText().toString(), time.getText().toString());
            appointments.add(app_model);
            items.add(appointments.toString());


            mDBHelper.addAppointments(about.getText().toString(), venue.getText().toString(), date.getText().toString(), time.getText().toString(),app_model.getAppointmentId());
           //Record the current appointment object ID on the database.
            mDBHelper.recordNewSerialNr("APPOINTMENTS",app_model.getAppointmentId());

            mDBHelper.close();
            adapter.notifyDataSetChanged();
            Intent a = new Intent(this, Appointments.class);
            startActivity(a);
        } catch (Exception e) {
            String ms = e.getMessage();
            Toast.makeText(this, ms, Toast.LENGTH_LONG).show();
            Log.e("Exception",ms);
        }
    }

    /**
     * Cancels the current appointment set-up activity.
     *
     * @param view
     */
    public void cancel(View view) {
        Intent cancel = new Intent(this, Appointments.class);
        startActivity(cancel);

    }



    /**
     * Clears entire table contents!
     *
     * @param view
     */
    public void delete(View view) {
        list = (ListView)findViewById(R.id.app_listview);
        myItem=(CheckedTextView)findViewById(R.id.row_layout);

        SparseBooleanArray checkedItemArray = list.getCheckedItemPositions();


       if(checkedItemArray !=null) {
           for (int i = 0; i < checkedItemArray.size(); i++) {

               //Toast.makeText(Appointments.this, list.getAdapter().getItem(checkedItemArray.keyAt(i)).toString(), Toast.LENGTH_SHORT).show();
               if(checkedItemArray.valueAt(i)) {
                   //Delete appointment from database
                   //int indexSelected = Integer.parseInt(list.getAdapter().getItem(checkedItemArray.keyAt(i)).toString());
                   mDBHelper.deleteEntry("APPOINTMENTS", appointments.get(i).getAppointmentId());
                   //Delete appointment from arraylist.
                   //appointments.remove(i);

               }
           }
       }
        else if(checkedItemArray ==null){
           Toast.makeText(Appointments.this," Nothing cheched",Toast.LENGTH_SHORT).show();
       }

        checkedItemArray.clear();
        Intent afterDelet = new Intent(this, Appointments.class);
        startActivity(afterDelet);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Appointments Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /**
     * Handles the entries made in the appoinment entry activity.
     * @param v
     */
}


