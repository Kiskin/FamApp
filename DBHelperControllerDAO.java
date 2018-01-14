package com.example.kindzekas.familyexpenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kindzekas.familyexpenses.DBHelper;
import com.example.kindzekas.familyexpenses.aModelJournal;
import com.example.kindzekas.familyexpenses.aModel_Appoinment;

import java.util.ArrayList;

/**
 * Created by Kindzekas on 2017-08-26.
 */

public class DBHelperControllerDAO {
    private DBHelper dbHelperController;
    private SQLiteDatabase db;

    public DBHelperControllerDAO(Context context) {dbHelperController = new DBHelper(context);}
    //Open the databse
    public void openDB() throws SQLException{

        dbHelperController.getWritableDatabase();
    }
    public  void close() throws SQLException{
        dbHelperController.getWritableDatabase().close();

    }
    //Clear the entire database
    public  void clearDataBase(){
        db.delete("APPOITNMENT_TABLE_NAME",null,new String[]{});
        db.delete("JOURNAL_TABLE_NAME",null, new String[]{});
        db.delete("OBJECT_SERIAL_ID_TABLENAME",null, new String[]{});

    }
    //Add journal entry to database
    public void addToDiary(String diary) {
        dbHelperController.getWritableDatabase();

        ContentValues values = new ContentValues(1);

        //ADD KEY-VALUE PAIR INFORMATION FOR THE DIARY DESCRIPTION
        values.put("Diary", diary); // task name



        //ADD KEY-VALUE PAIR INFORMATION FOR
        //IS_DONE VALUE: 0- NOT DONE, 1 - IS DONE
        //values.put(KEY_IS_DONE, task.getIs_done());

        // INSERT THE ROW IN THE TABLE
        db.insert("MyDiary", null, values);

        // Insert the new row, returning the primary key value of the new row
        //long newRowId = db.insert(JOURNAL_TABLE_NAME, null, values);


        // CLOSE THE DATABASE CONNECTION
        db.close();
    }
    //Add new appointments to the database.
    public void addAppointments(String appointment,String venue,String date,String time,long appointmentId) {
        db = dbHelperController.getWritableDatabase();
        ContentValues values = new ContentValues(5);

        //ADD KEY-VALUE PAIR INFORMATION FOR THE DIARY DESCRIPTION
        values.put("AppointmentsItem", appointment); // task name
        values.put("Venue", venue);
        values.put("Date", date);
        values.put("Time", time);
        values.put("Number",appointmentId);



        //ADD KEY-VALUE PAIR INFORMATION FOR
        //IS_DONE VALUE: 0- NOT DONE, 1 - IS DONE
        //values.put(KEY_IS_DONE, task.getIs_done());

        // INSERT THE ROW IN THE TABLE
        db.insert("Appointments", null, values);

        // Insert the new row, returning the primary key value of the new row

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    //Deletes contents of a table in database.
    public void deleteEntry(String tableName, Long valueId) {
        db = dbHelperController.getWritableDatabase();

        try {
            if (tableName.equals("DIARY")) {


                db.delete("MyDiary", "Number" + "= '" + valueId + "'", null);
                Log.e("Database Operations", "diary entry " + valueId + " deleted");
            }

            if (tableName.equals("APPOINTMENTS")) {


                db.delete("Appointments","Number"+ "= '" + valueId+ "'",null);
                Log.e("Database Operations", "appointment entry " + valueId + " deleted");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    //Record the unique IDsuhk,
    public void recordNewSerialNr(String tableName,Long newId){
        Log.e("ID recording1","ID");
        dbHelperController.getWritableDatabase();
        ContentValues values = new ContentValues(5);
        //Add to serial nr table
        if(tableName.equals("APPOINTMENTS")){

            try {

                ContentValues cv = new ContentValues();
               // cv.put(ColumnName, newValue);
               // db.update(TABLE_NAME, cv, Column + "= ?", new String[] {rowId});

                values.put("AppointmentObjID",newId);
                values.put("AppointmentObjID", newId.toString());
                //db.update("ObjectSerialNumbers", values, Column + "= ?", new String[] {});


               db.insert("ObjectSerialNumbers",null,values);
                Log.e(" Database action ","  ID Inserted");

            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                db.close();
            }
        }
        if(tableName.equals("Diary")){
            try {
                values.put("DiaryObjID", newId.toString());
                db.insert("ObjectSerialNumbers",null,values);
                db.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        if(tableName.equals("SCHOOL")){
            try {
                values.put("SchoolObjID", newId.toString());
                db.insert("Appointments",null,values);
                db.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        if(tableName.equals("EXPENSES")){
            try {
                values.put("ExpensesObjID", newId.toString());
                db.insert("Appointments",null,values);
                db.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        if(tableName.equals("WORK")){
            try {
                values.put("WorkObjID", newId.toString());
                db.insert("Appointments",null,values);
                db.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }



    }


    //Get last Object serial number IDS
    public long getAllObjectSerialNrs(String tableName) {
        Cursor cursor =null;
        //db = dbHelperController.getWritableDatabase();

        String selectQuery1 = "SELECT  * FROM  ObjectSerialNumbers";


        if(db !=null){cursor = db.rawQuery(selectQuery1, null);}


        //If table is not empty
        if ((cursor != null)) {
            if (tableName.equals("DAIRY")) {
                if(cursor.getString(1) !=null)
                    return Long.parseLong(cursor.getString(1));
                Log.e("Current diary Object ID", cursor.getString(1));
            }
            if (tableName.equals("APPOINTMENTS")) {
                if(cursor.getString(2) !=null)

                Log.e("Current appointmentID", cursor.getString(2));
                return Long.parseLong(cursor.getString(2));

            }
            if (tableName.equals("SCHOOL")) {
                if(cursor.getString(3) !=null)
                    return Long.parseLong(cursor.getString(3));

            }
            if (tableName.equals("EXPENSES")) {
                if(cursor.getString(4) !=null)
                    return Long.parseLong(cursor.getString(4));

            }
            if (tableName.equals("WORK")) {
                if(cursor.getString(5) !=null)
                    return Long.parseLong(cursor.getString(5));

            }
        }

        //If table is empty
        return 1L;
    }


    public ArrayList<String> getAllJournals() {

        //GET ALL THE EXERCISE ITEMS ON THE LIST
        ArrayList<String> diaryList = new ArrayList<String>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  * FROM  MyDiary";

        SQLiteDatabase db = dbHelperController.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // LOOP THROUGH THE DIARY ENTRIES
        if (cursor.moveToFirst()) {
            do {
                aModelJournal diaryEntry = new aModelJournal(cursor.getString(1));
                diaryEntry.setEntry(cursor.getString(1));

                diaryList.add(diaryEntry.getEntry());
            } while (cursor.moveToNext());
        }
        // RETURN THE LIST OF TASKS FROM THE TABLE
        return diaryList;
    }

    /**
     * Returns all appointments in database
     */
    public ArrayList<aModel_Appoinment> getAllAppointments() {

        //GET ALL THE EXERCISE ITEMS ON THE LIST
        ArrayList<aModel_Appoinment> apptList = new ArrayList<aModel_Appoinment>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  * FROM  Appointments";

         db = dbHelperController.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // LOOP THROUGH THE DIARY ENTRIES
        if (cursor.moveToFirst()) {
            do {
                aModel_Appoinment appointment = new aModel_Appoinment(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(5));
                appointment.setAppointment(cursor.getString(1));
                appointment.setVenue(cursor.getString(2));
                appointment.setDate(cursor.getString(3));
                appointment.setTime(cursor.getString(4));
                appointment.setAppointmentId(cursor.getLong(5));


                apptList.add(appointment);
            } while (cursor.moveToNext());
        }
        // RETURN THE LIST OF APPOINTMENTS FROM THE TABLE
        return apptList;
    }


    //Method clears the corresponding table content.
    public void clearTable(String tableName) {

        //Clears all contents in the to-do list.

        if (tableName.equals("JOURNAL_TABLE_NAME")) {

            db.delete("JOURNAL_TABLE_NAME", null, null);
        }
        //Clears all contents in the time logs table.
        else if(tableName.equals("APPOITNMENT_TABLE_NAME")) {

            db.delete("APPOITNMENT_TABLE_NAME", null, null);
        }
        //Clears all contents in the expenses table.
        else if (tableName.equals("EXPENSES_TABLE_NAME")) {

            db.delete("", null, null);
        }
        //Clears all contents in the exercise logs table.
        else if (tableName.equals("EXERCISE_TABLE_NAME")){

            db.delete("", null, null);

            return;

        }
        db.close();

    }





}
