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

    /*{
	"playerstats": {
		"steamID": "76561197982347980",
		"gameName": "ValveTestApp260",
		"stats": [
			{
				"name": "total_kills",
				"value": 81963
			},
			{
				"name": "total_deaths",
				"value": 63394
			},
			{
				"name": "total_time_played",
				"value": 4568880
			},
			{
				"name": "total_planted_bombs",
				"value": 2407
				*/


    /*                    try {
                        g = (Global) getApplication();
                        g.setJsontulos(valitulos);

                        System.out.println("Tulostus tulokselle global: \n" +g.getJsontulos());

                    }catch(Exception e){
                        e.printStackTrace();
                    }*/
    public void haeStatsit(String id){

        String totalKills = "", totalDeaths = "";
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

            //testitulostus
            System.out.println(totalKills + " " + totalDeaths);

           /* // set
            ((Global) this.getApplication()).setKills(totalKills);

            // get
            String s = ((Global) this.getApplication()).getKills();

            System.out.println(s);*/

        } catch (JSONException e) {
            e.printStackTrace();
        }

  /*      try {
            g = (Global)getApplication();
            g.setKills(totalKills);
            g.setDeaths(totalDeaths);

            System.out.println("Tulostus tapoille: \n" +g.getKills());
            System.out.println("Tulostus kuolemille: \n" +g.getDeaths());

        }catch(Exception e){
            e.printStackTrace();
        }*/
        System.out.println("Parseri ends. RETURN: buffer (json result) ------------");
        //poistetaan tää return ja asetetaan tässä luokassa global muuttujiin ne arvot joita halutaan ja käytetään sitä kautta.

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