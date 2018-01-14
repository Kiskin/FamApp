package com.example.kindzekas.familyexpenses;

import java.util.Date;

/**
 * Created by Kindzekas on 2017-02-02.
 * This class describes a basic journal entry.
 */

public class aModelJournal {
    private String entry;

    public aModelJournal(){

    }

    /**
     *
     * @param entry
     */
    public aModelJournal(String entry){
        this.entry= entry;
        Date date = new Date();
        date.getDate();
    }
    public void setEntry(String entry){
        this.entry = entry;
    }

    public String getEntry(){
        return this.entry;
    }

    public String toString(){
        return this.getEntry();

    }
}
