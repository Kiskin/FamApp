package com.example.kindzekas.familyexpenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Validation extends CommonMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);


    }

    /**
     * Method handles loggin to the diary activity.
     * @param view edit view
     */
    public final void journalValidation(View view){

        final EditText ed = (EditText) findViewById(R.id.validation);
        String validate = ed.getText().toString();

             if (validate.equals("0000")) {
                //Erase the password.
                ed.setText("");
                //Move the diary activity.
                Intent journal = new Intent(this, Journal.class);
                startActivity(journal);
            } else{
                Toast.makeText(this,"Invalid entry!",Toast.LENGTH_LONG).show();
                Intent reval = new Intent(this, Validation.class);
                startActivity(reval);
            }
    }
    public void back(View v){
        Intent back = new Intent(this,MainActivity.class);
        startActivity(back);
    }
}
