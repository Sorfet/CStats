package com.cstats.cstats;

/**
 * Created by Sasu on 12.3.2017.
 *
 * Parseri käy läpi haetun XML  / JSON tiedon ja hakee siitä tarvittavat datat..
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


import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Parseri {


    public void parseWeb(String id){

        System.out.println("Parseri debug: täällä ollaan");
        URL url;
        URLConnection uc;
        BufferedReader in;
        String input;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            url = new URL("http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key=7B99A8C2129627CC394A4B3FD17F0A12&steamid="+id);
            uc = url.openConnection();
            in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

            while((input = in.readLine())!=null){
                System.out.println(input);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
