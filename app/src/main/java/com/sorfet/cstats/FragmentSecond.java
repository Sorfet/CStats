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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentSecond extends Fragment{


    private DatabaseReference databaseRef;
    private FirebaseDatabase database;
    private JSONObject obj,obj2;
    private List<String> list;

    private KdRatio kdRatio;
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

                    //TODO TODO TODO iha fffffffidun TODOOOOOOOO
                    obj = new JSONObject((Map)snap.getValue());

                    System.out.println("UUSI JSON: "+obj.toString());

                    //UUSI JSON: {"2017231":{"time":1490991604850,"kdRatio":"0,87"},"2017230":{"time":1490897616041,"kdRatio":"0.87"},"201731":{"time":1491043727441,"kdRatio":"0.87"}}

                    String tulos="";
                    tulos=obj.getString("2017230");


                    String objektiString=obj.toString();


                    //ei toimi
                        if (objektiString.matches("\\d{1,8}")) {
                            System.out.println("Found good SSN: " + objektiString.matches("\\d{1,8}"));
                        }

                    //false
                    System.out.println(objektiString.matches("\\d{1,8}"));



                    System.out.println("TULOS JEE: "+tulos);
                    //TULOS JEE: {"time":1490897616041,"kdRatio":"0.87"}

                    JSONObject tulos2object = new JSONObject("{ date:[".concat(tulos).concat("]}"));



                    JSONArray jArray = tulos2object.getJSONArray("date");



                    //arrayn sisältä ohjekti järjestysnumero 0 (Eka ja tässä esim ainut)
                    JSONObject finalObject = jArray.getJSONObject(0);

                    //poimitaan haluttu tieto kyseisestä objektista muuttujaan
                    String time1 = finalObject.getString("time");

                    //poimitaan toinen tieto kyseisestä objektista muuttujaan
                    String kdrat = finalObject.getString("kdRatio");

                    //testitulostus
                    System.out.println(time1 +" "+ kdrat);




                }catch (Exception jee){
                    jee.printStackTrace();
                }



            }

            @Override public void onCancelled(DatabaseError e) {
                System.out.println("Jou mään, feilas " + e.getDetails().toString());
            }
        });




            //Graafi




        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 1)
        });
        graph.addSeries(series);



        return rootView;
    }


}
