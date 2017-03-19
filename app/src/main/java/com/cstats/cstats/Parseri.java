package com.cstats.cstats;

/**
 * Created by Sasu on 12.3.2017.
 *
 * Parseri käy läpi haetun  JSON tiedon ja hakee siitä tarvittavat datat..
 *
 * KESKEN
 *
 *       http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key=7B99A8C2129627CC394A4B3FD17F0A12&steamid=id
 *     Sivu josta haetaan pelaajien tiedot
 *     jossa id = steamid64
 *
 *
 *       sasun kehittäjäavain Steamiin:
 *       7B99A8C2129627CC394A4B3FD17F0A12
 *
 */


import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class Parseri extends AppCompatActivity {

    Global g;
    String result = "";
    URL url;
    BufferedReader reader=null;
    String line;
    StringBuffer buffer;
    HttpURLConnection connection = null;
    InputStream stream;


    //http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=7B99A8C2129627CC394A4B3FD17F0A12&steamids=76561197990716071

    //http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key=7B99A8C2129627CC394A4B3FD17F0A12&steamid=

    //76561197990716071

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
        }catch (IOException e){
            e.printStackTrace();
        }finally{
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

/*
public class JSONTask extends AsyncTask<URL,String,String> {

    @Override
    protected String doInBackground(URL... params){
        return null;
    }

    @Override
    protected void onPostExcecute(String s){

        super.onPostExecute(s);
    }

}


*/