package com.sorfet.cstats;

/**
 * Created by Jope on 26.3.2017.
 */

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FragmentSecond extends Fragment{


    private DatabaseReference databaseRef;
    private FirebaseDatabase database;
    private JSONObject obj;
    private List<String> list;

    private JSONObject json;
    private String date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);

        databaseRef = FirebaseDatabase.getInstance().getReference();


        System.out.println("GLOBAL STEAM ID: " +Global.steamID);


        //Tämä hakee KERRALLA kaikki
      databaseRef.child(Global.steamID).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap) {

                //toivottu json tulos esim:
                //{2017231={time=1490986838197, kdRatio=0.87}, 2017230={time=1490897616041, kdRatio=0.87}}

                //JSON Objekti
                //{"2017231":{"time":1490990579758,"kdRatio":"0.87"},"2017230":{"time":1490897616041,"kdRatio":"0.87"}}

                //("{interests : [{interestKey:Dogs}, {interestKey:Cats}]}");


                System.out.println("TULOSTETAAN HAETTU JSON OBJEKTI: " + snap.getValue());

                try {

                    //TODO
                    obj = new JSONObject((Map)snap.getValue());

                    System.out.println("UUSI JSON: "+obj.toString());

                   // obj = new JSONObject("{"steamuser":[".concat(snap.getValue().toString().concat("]}")));

                    /*
                    list = new ArrayList<String>();
                    JSONArray array = obj.getJSONArray(Global.steamID);
                    for (int i = 0; i < array.length(); i++) {
                        list.add(array.getJSONObject(i).getString("2017231"));
                    }
                    */

                }catch (Exception jee){
                    jee.printStackTrace();
                }

                /*
                int i=0;
                String mothafucka="";

                while(i < list.size()){

                   mothafucka=list.get(i);
                    i++;

                    System.out.println(mothafucka);
                }
                */





            }

            @Override public void onCancelled(DatabaseError e) {
                System.out.println("Jou mään, feilas " + e.getDetails().toString());
            }
        });


/*

        String finalJson = buffer.toString();

        JSONObject parentObject = null;
        try {
            parentObject = new JSONObject(finalJson);



            //eka on objekti
            JSONObject alkuObject = parentObject.getJSONObject("response");

            //Haetaan Array
            JSONArray parentArray = alkuObject.getJSONArray("players");

            //arrayn sisältä ohjekti järjestysnumero 0 (Eka ja tässä esim ainut)
            JSONObject finalObject = parentArray.getJSONObject(0);

            //poimitaan haluttu tieto kyseisestä objektista muuttujaan
            String pelaajaNimi = finalObject.getString("personaname");

            //poimitaan toinen tieto kyseisestä objektista muuttujaan
            int logoffTime = finalObject.getInt("lastlogoff");

            */



            //Graafi




        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 1)
        });
        graph.addSeries(series);



        return rootView;
    }


}
