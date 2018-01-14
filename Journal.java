package com.example.kindzekas.familyexpenses;

import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;

import static android.R.attr.start;
import static android.R.attr.width;
import static com.example.kindzekas.familyexpenses.R.attr.height;

public class Journal extends CommonMenu implements View.OnClickListener{
   private  ArrayList<String> journals = new ArrayList<String>();
    ArrayAdapter adapter;
    ListView listView;
    aModelJournal aJournal;
    DisplayMetrics dm = new DisplayMetrics();
    DBHelperControllerDAO mDBHelper;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
        journals = new ArrayList<>();
        mDBHelper = new DBHelperControllerDAO(this);

        Button add = (Button)findViewById(R.id.add_btn);
        add.setOnClickListener(this);
        Button delete = (Button)findViewById(R.id.delete_btn);
        delete.setOnClickListener(this);


        listView = (ListView)findViewById(R.id.journal);

        adapter = new ArrayAdapter(this,R.layout.list_view_adapter,journals);
        listView.setAdapter(adapter);

        }


    @Override
    protected void onResume(){
        super.onResume();
        mDBHelper.openDB();
        //Read entries from database and bind to list view.
        //journals = mDBHelper.getAllJournals();
        adapter = new ArrayAdapter(this,R.layout.list_view_adapter,journals);

        listView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..

            if(v.getId()== R.id.add_btn){

                setContentView(R.layout.add_entry);
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int width = dm.widthPixels;
                int height = dm.heightPixels;
                getWindow().setLayout((int) (width * .9), (int) (height * .9));


            }
            else if( v.getId() ==R.id.delete_btn){

                //mDBHelper.deleteEntry("DIARY","*",mDBHelper.getWritableDatabase());
                mDBHelper.clearTable("MyDiary");


                Toast.makeText(this,"ITEM TO BE DELETED!",Toast.LENGTH_SHORT).show();
                Intent r = new Intent(this,Journal.class);
                startActivity(r);

            }

        }



    @Override
    protected void onDestroy(){
        super.onDestroy();

    }

    //Adds entry to database.


    public void done(View view){
        Date date = new Date();

                EditText jounal = (EditText) findViewById(R.id.journal_entry);

                if (jounal.getText() != null) {
                    aJournal = new aModelJournal((date.toString()+" \n "+jounal.getText().toString()));

                    mDBHelper.addToDiary(aJournal.getEntry());


                    Intent j = new Intent(this,Journal.class);
                    startActivity(j);
                    Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Empty text", Toast.LENGTH_SHORT).show();
                }

            }


    public void cancel(View view){
        Toast.makeText(this, "Delete clicked", Toast.LENGTH_SHORT).show();
        //mDBHelper.deleteEntry("DIARY","Name",mDBHelper.getWritableDatabase());

    }

    }


