package com.example.kindzekas.familyexpenses;

import android.app.Activity;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Kindzekas on 2017-02-04.
 * The attributes date and time are defined as strings for easy entry by the user.
 */

public class aModel_Appoinment extends Activity {
    //DBHelperControllerDAO mdHelperDAO = new DBHelperControllerDAO(this);
    private String appointment;
    private String venue;
    private String date;
    private String time;
    private long appointmentId;


   // public aModel_Appoinment(){Date date = new Date();}

    public aModel_Appoinment( String appointment,String venue, String date, String time){
        this.appointment = appointment;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.appointmentId = uniqueAppointmentId();

     }

    public void setAppointment(String appointment){
        this.appointment = appointment;
    }
    public String getAppointment(){
        return  this.appointment;
    }
    public void setVenue(String venue){
        this.venue = venue;
    }
    public String getVenue(){
        return this.venue;
    }
    public void setDate(String date){this.date = date;}
    public String getDate(){
        return this.date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getTime(){
        return this.time;
    }
    public void setAppointmentId(long appointmentId){this.appointmentId = appointmentId;}
    public long getAppointmentId(){ return this.appointmentId;}

    private long uniqueAppointmentId(){

        DBHelperControllerDAO mdHelperDAO = new DBHelperControllerDAO(this);

        return 1+ mdHelperDAO.getAllObjectSerialNrs("APPOINTMENTS");
    }


    public String toString(){

        return "Type: "+this.appointment + " Venue: "+ this.venue  + " Date: "+ this.date + " Time: "+ this.time ;
    }
}
