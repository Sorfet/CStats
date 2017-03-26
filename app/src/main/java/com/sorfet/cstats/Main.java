package com.sorfet.cstats;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;



/**
 * Created by Sasu on 12.3.2017.
 */

public class Main extends AppCompatActivity {

    private Global g;
    private TextView KD, HS, TP, MVP;
    private int kdRatio;
    private double valiKD, valiTapot, valiKuolemat, valiHS, HSlasku, valiTP, TPlasku;
    private String finalKD, finalHS, finalTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        /*
        //piilotetaan menubar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        */

        //alustetaan käyttöliittymän komponentit
        g = (Global)getApplication();
        KD = (TextView) findViewById(R.id.KDtextView);
        HS = (TextView) findViewById(R.id.HStextView);
        TP = (TextView) findViewById(R.id.TPtextView);
        MVP = (TextView) findViewById(R.id.MVPtextView);


        //lasketaan main sivun arvot
        try {
        valiTapot = Double.parseDouble(g.getKills());
        valiKuolemat = Double.parseDouble(g.getDeaths());

            //kd lasku
            valiKD = (valiTapot / valiKuolemat);
            finalKD = String.format("%.2f", valiKD);

            //HS lasku
            valiHS = Double.parseDouble(g.getHeadShot());
            HSlasku = (valiHS/valiTapot)*100;
            finalHS = String.format("%.2f", HSlasku);

            //timeplayed lasku
            valiTP = Double.parseDouble(g.getTimePlayed());
            TPlasku = (valiTP/60)/60;
            finalTP = String.format("%.0f", TPlasku);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        //asetetaan lasketut arvot TextView kenttiin.
        try {
            KD.setText(finalKD);
            HS.setText(finalHS);
            MVP.setText(g.getMVP());
            TP.setText(finalTP + " h");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        /*

        ####Google firebase database operaatiot:

//Kirjoita databaseen
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");



//Lue databasesta


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                System.out.println("Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value.\n"+ error.toException().toString());
            }
        });

        */




    }
}

