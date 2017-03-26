package com.sorfet.cstats;

import android.app.Application;
import android.support.v4.app.Fragment;

/**
 * Created by Sasu on 12.3.2017.
 * <p>
 * TÄNNE GLOBAL MUUTTUJAT JOITA VOI KÄYTTÄÄ VAPAASTI ERI ACTIVITYISSA
 * Globaleita voi käyttää muualla määrittelemällä ne näin:
 * <p>
 * Global g = (Global)getApplication();
 * int numero = g.getNumero();  //tämä getter sijaitsee Global luokassa
 */

public class Global extends Application {

    public String steamID;
    public String user;
    public String jsontulos;
    public static String kills = " ", deaths = " ", MVP = " ", timePlayed = " ", headShot = " ";

    public String getHeadShot() {
        return headShot;
    }

    public void setHeadShot(String headShot) {
        this.headShot = headShot;
    }

    public String getJsontulos() {
        return jsontulos;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getMVP() {
        return MVP;
    }

    public void setMVP(String MVP) {
        this.MVP = MVP;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public String getKills() {

        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public void setJsontulos(String jsontulos) {
        this.jsontulos = jsontulos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSteamID() {
        return steamID;
    }

    public void setSteamID(String steamID){
    this.steamID = steamID;
    }
}
