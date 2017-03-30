package com.sorfet.cstats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

/**
 * Created by Jope on 26.3.2017.
 */

public class FragmentFirst extends Fragment{


    private Global g;
    private TextView KD, HS, TP, MVP;
    private double valiKD, valiTapot, valiKuolemat, valiHS, HSlasku, valiTP, TPlasku;
    private String finalKD, finalHS, finalTP;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef, inception;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));


        // FIREBASE lue databasesta TODO

        // Read from the database
   /* databaseRef.addValueEventListener(new ValueEventListener() {
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
});*/


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


            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);

            String stringYear = String.valueOf(mYear);
            String stringMonth = String.valueOf(mMonth);
            String stringDay = String.valueOf(mDay);

            String pvm = stringYear.concat(stringMonth.concat(stringDay));

            //String pvm = (String.valueOf(mYear) + String.format("%1$02d",String.valueOf(mMonth)) + String.format("%1$02d",String.valueOf(mDay));
            //String pvm = (String.valueOf(mYear) + String.format("%1$02d %2$02d", mMonth, mDay));

            //Firebase  - kirjoita databaseen:
            //Hieno ratkaisu pitkän päänraapimisen päätteeksi
            // String strLong = Long.toString(System.currentTimeMillis());
            KdRatio kdLuku = new KdRatio(System.currentTimeMillis(), finalKD);
            databaseRef = FirebaseDatabase.getInstance().getReference();
            databaseRef.child(Global.steamID).child(pvm).setValue(kdLuku);

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
