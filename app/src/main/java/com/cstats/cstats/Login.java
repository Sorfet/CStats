package com.cstats.cstats;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.ActionBar;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.*;
import android.media.MediaPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.math.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.*;
import java.io.File;
import java.lang.*;
import android.app.AlertDialog;
import android.content.DialogInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private EditText idKentta;
    private Button submitNappi;
    private ImageButton apuNappi;
    private String id;
    private Global g;
    private String tulos;
    private StringBuffer buffer;
    String result = "";
    URL url;
    BufferedReader reader=null;
    String line;
    HttpURLConnection connection = null;
    InputStream stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //piilotetaan menubar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Määritetään id input kenttä
        idKentta = (EditText) findViewById(R.id.loginIdKentta);

        //Määritetään submitnappi ja asetetaan sille kuuntelija
        submitNappi = (Button) findViewById(R.id.loginNappi);
        submitNappi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // tänne submit napin painalluksen toiminta
                try {
                    id = idKentta.getText().toString();

                    haeStatsit(id);

                    //tää siirtää activityn toiseen
                    startActivity(new Intent("com.cstats.cstats.Main"));

                }catch(Exception e){

                    System.out.println("Error");
                }

            }
        });

        //Määritetään HELP -nappi ja asetetaan sille kuuntelija
        apuNappi = (ImageButton) findViewById(R.id.loginHelpButton);
        apuNappi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // tänne apunapin painalluksen toiminta
                //eli popup käynnistäjä ohjeelle
                naytaApu();
            }
        });


    } /* OnCreate loppuu */

    private void naytaApu(){

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Ohje Steam ID:n löytämiseen");
        helpBuilder.setMessage("Tämä ohje neuvoo kuinka löydät oman Steam ID:si ! (JOSKUS KUN JAKSAA TEHDÄ) TODO");
        helpBuilder.setPositiveButton("Poistu",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    public void haeStatsit(String id){

        String totalKills = "", totalDeaths = "", timePlayed = "", MVP = "", headShot = "";
        String url = "http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key=7B99A8C2129627CC394A4B3FD17F0A12&steamid=";

        StringBuffer jsonTulos;
        jsonTulos = haeJson(id, url);

        //JSON PARSERI
        String finalJson = jsonTulos.toString();

        JSONObject parentObject = null;
        try {
            parentObject = new JSONObject(finalJson);

            //eka on objekti
            JSONObject alkuObject = parentObject.getJSONObject("playerstats");

            //Haetaan Array
            JSONArray parentArray = alkuObject.getJSONArray("stats");

            //arrayn sisältä ohjekti järjestysnumero 0
            JSONObject finalObject = parentArray.getJSONObject(0);

            //poimitaan haluttu tieto kyseisestä objektista muuttujaan
            totalKills = finalObject.getString("value");

            JSONObject finalObject2 = parentArray.getJSONObject(1);

            //poimitaan toinen tieto kyseisestä objektista muuttujaan
            totalDeaths = finalObject2.getString("value");

            //2 ja 102
            JSONObject finalObject3 = parentArray.getJSONObject(2);
            timePlayed = finalObject3.getString("value");

            JSONObject finalObject4 = parentArray.getJSONObject(102);
            MVP = finalObject4.getString("value");

            JSONObject finalObject5 = parentArray.getJSONObject(25);
            headShot = finalObject5.getString("value");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            g = (Global)getApplication();
            g.setKills(totalKills);
            g.setDeaths(totalDeaths);
            g.setTimePlayed(timePlayed);
            g.setMVP(MVP);
            g.setHeadShot(headShot);

            System.out.println("Tulostus tapoille: \n" +g.getKills());
            System.out.println("Tulostus kuolemille: \n" +g.getDeaths());
            System.out.println("Tulostus pelatulle ajalle: \n" +g.getTimePlayed());
            System.out.println("Tulostus MVP tähdille: \n" +g.getMVP());
            System.out.println("Tulostus headshottien lkm: \n" +g.getHeadShot());

        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Parseri ends. RETURN: buffer (json result) ------------");
        //poistetaan tää return ja asetetaan tässä luokassa global muuttujiin ne arvot joita halutaan ja käytetään sitä kautta.

    }

    //Tästä pelaajanimi yms (ehkä)
    //http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=7B99A8C2129627CC394A4B3FD17F0A12&steamids=76561197990716071

    //Tästä linkistä saa statsit :)
    //http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key=7B99A8C2129627CC394A4B3FD17F0A12&steamid=76561197982347980

    //76561197990716071 Sorfet
    //76561197982347980 _RK

    public StringBuffer haeJson(String id, String url1){

        //Haetaan ensin APIsta haluttu JSON kokonaisena
        //Sitten parsetaan siitä mitä halutaan ja tallennetaan global muuttujiin.

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.out.println("Parseri starts ------------");

        //kovakoodattu id:
        id = "76561197982347980";

        try {
            url = new URL(url1 + id);

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            buffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            if(connection != null) {
                connection.disconnect();
            }
            try {
                if(reader != null) {
                    reader.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return buffer;
    }
    public StringBuffer parseWeb(String id) {

        //Haetaan ensin APIsta haluttu JSON kokonaisena
        //Sitten parsetaan siitä mitä halutaan ja tallennetaan global muuttujiin.

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.out.println("Parseri starts ------------");

        //kovakoodattu id:
        id = "76561197990716071";


        try {
            url = new URL("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=7B99A8C2129627CC394A4B3FD17F0A12&steamids=" + id);

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            buffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            if(connection != null) {
                connection.disconnect();
            }
            try {
                if(reader != null) {
                    reader.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //JSON PARSERI
        String finalJson = buffer.toString();

        JSONObject parentObject = null;
        try {
            parentObject = new JSONObject(finalJson);

            /*
            Esim json:

            {
	"response": {
		"players": [
			{
				"steamid": "76561197990716071",
				"communityvisibilitystate": 3,
				"profilestate": 1,
				"personaname": "Sorfet",
				"lastlogoff": 1489918504,
				"profileurl": "http://steamcommunity.com/id/sorfet/",
				"avatar": "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/57/57ee93c81538ad7e0a4f0cdd29420eecdb266541.jpg",
				"avatarmedium": "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/57/57ee93c81538ad7e0a4f0cdd29420eecdb266541_medium.jpg",
				"avatarfull": "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/57/57ee93c81538ad7e0a4f0cdd29420eecdb266541_full.jpg",
				"personastate": 1,
				"primaryclanid": "103582791436048820",
				"timecreated": 1182705176,
				"personastateflags": 0
			}
		]

	}
}
             */

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

            //testitulostus
            System.out.println(pelaajaNimi +" "+ logoffTime);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Parseri ends. RETURN: buffer (json result) ------------");
        //poistetaan tää return ja asetetaan tässä luokassa global muuttujiin ne arvot joita halutaan ja käytetään sitä kautta.

        return buffer;
    }
}
