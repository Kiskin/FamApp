package com.example.kindzekas.familyexpenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Kindzekas on 2017-02-03.
 */

public class DBHelper extends SQLiteOpenHelper {
    //Create a Personal Data Assistant (PDA) database.
    private static final String DATABASE_NAME ="PDA_DATABASE";
    private static final int DATABASE_VERSION = 8;

    //Creat a table to keep track of itemIds
    private  static  final String OBJECT_SERIAL_ID_TABLENAME = "ObjectSerialNumbers";
    private  static  final String OBJECT_SERIAL_NUMBER_ID= "SerialNumberIDs";
    private static final String DIARY_OBJECT_ID ="DiaryObjID";
    private static final String APPOINTMENT_OBJECT_ID ="AppointmentObjID";
    private static final String SCHOOL_OBJECT_ID ="SchoolObjID";
    private static final String EXPENSES_OBJECT_ID ="ExpensesObjID";
    private static final String WORK_OBJECT_ID ="WorkObjID";
    //Create a journals table.
    private static final String JOURNAL_TABLE_NAME = "MyDiary";
    private static final String JOURNAL_TABLE_ID = "_id_Diary";
    private static final String JOURNAL_ITEM = "Diary";
    //Create a table for appointments.
    private static final String APPOITNMENT_TABLE_NAME = "Appointments";
    private static final String APPOINTMENT_ID = "_id_appoitnmets";
    private static final String APPOINTMENT_ITEM = "AppointmentsItem";
    private static final String APPOINTMENT_VENUE ="Venue";
    private static final String APPOINTMENT_DATE= "Date";
    private static final String APPOINTMENT_TIME="Time";
    private static final String APPOINTMENT_NUMBER="Number";



        private static final String objectTracking = "CREATE TABLE " + OBJECT_SERIAL_ID_TABLENAME + "(" + OBJECT_SERIAL_NUMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DIARY_OBJECT_ID + " TEXT," + APPOINTMENT_OBJECT_ID + " TEXT,"+ SCHOOL_OBJECT_ID + " TEXT,"+ EXPENSES_OBJECT_ID + " TEXT,"+ WORK_OBJECT_ID + " TEXT );";
        private static final String diary = "CREATE TABLE " + JOURNAL_TABLE_NAME + "(" + JOURNAL_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + JOURNAL_ITEM + " TEXT );";
        private static final String appointments = "CREATE TABLE " + APPOITNMENT_TABLE_NAME + "(" + APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + APPOINTMENT_ITEM + " TEXT,"+ APPOINTMENT_VENUE + " TEXT,"+ APPOINTMENT_DATE + " TEXT,"+ APPOINTMENT_TIME + " TEXT,"+APPOINTMENT_NUMBER + " LONG );";




    public DBHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
        Log.e("Database Operations","Data Base Created/opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(diary);
        db.execSQL(appointments);
        db.execSQL(objectTracking);

        Log.e("Database Operations","Object ID, Appointments and Diary tables have been created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + JOURNAL_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + APPOITNMENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OBJECT_SERIAL_ID_TABLENAME);
        onCreate(db);
        Log.e("Database Operations"," tables have been updated");
    }

}
