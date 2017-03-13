package com.cstats.cstats;

import android.app.Application;

/**
 * Created by Sasu on 12.3.2017.
 *
 * TÄNNE GLOBAL MUUTTUJAT JOITA VOI KÄYTTÄÄ VAPAASTI ERI ACTIVITYISSA
 Globaleita voi käyttää muualla määrittelemällä ne näin:

 Global g = (Global)getApplication();
 int numero = g.getNumero();  //tämä getter sijaitsee Global luokassa
 *
 */

public class Global extends Application {

    public String steamID;

    public String user;



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSteamID() {
        return steamID;
    }

    public void setSteamID(String steamID) {
        this.steamID = steamID;
    }
}
