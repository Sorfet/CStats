package com.sorfet.cstats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Jope on 26.3.2017.
 */

public class fragmentFirst extends Fragment{


    private Global g;
    private TextView KD, HS, TP, MVP;
    private int kdRatio;
    private double valiKD, valiTapot, valiKuolemat, valiHS, HSlasku, valiTP, TPlasku;
    private String finalKD, finalHS, finalTP;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));


        //Firebase  - kirjoita databaseen:
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference("message");
        databaseRef.setValue("Hello World!");



        /* FIREBASE lue databasesta TODO

        // Read from the database
myRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        String value = dataSnapshot.getValue(String.class);
        Log.d(TAG, "Value is: " + value);
    }

    @Override
    public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
    }
});



         */


        //alustetaan käyttöliittymän komponentit
        //((MyActivity)getActivity()).getX()
        // g = ((Global)getActivity()).getApplicationContext();
        // g = (Global)rootView.getContext();
        KD = (TextView) rootView.findViewById(R.id.KDtextView);
        HS = (TextView) rootView.findViewById(R.id.HStextView);
        TP = (TextView) rootView.findViewById(R.id.TPtextView);
        MVP = (TextView) rootView.findViewById(R.id.MVPtextView);


        //lasketaan main sivun arvot
        try {
          //  valiTapot = Double.parseDouble(g.getKills());
          //  valiKuolemat = Double.parseDouble(g.getDeaths());

            valiTapot = Double.parseDouble(Global.kills);
            valiKuolemat = Double.parseDouble(Global.deaths);

            //kd lasku
            valiKD = (valiTapot / valiKuolemat);
            finalKD = String.format("%.2f", valiKD);

            //HS lasku
            valiHS = Double.parseDouble(Global.headShot);
            HSlasku = (valiHS/valiTapot)*100;
            finalHS = String.format("%.2f", HSlasku);

            //timeplayed lasku
            valiTP = Double.parseDouble(Global.timePlayed);
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
            MVP.setText(Global.MVP);
            TP.setText(finalTP + " h");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rootView;
    }

}
