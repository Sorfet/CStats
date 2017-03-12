package com.cstats.cstats;

/**
 * Created by Sasu on 12.3.2017.
 *
 * TÄNNE GLOBAL MUUTTUJAT JOITA VOI KÄYTTÄÄ VAPAASTI ERI ACTIVITYISSA
 Globaleita voi käyttää muualla määrittelemällä ne näin:

 Global g = (Global)getApplication();
 int numero = g.getNumero();  //tämä getter sijaitsee Global luokassa
 *
 */

public class Global {

    public int steamID;




    public int getSteamID() {
        return steamID;
    }

    public void setSteamID(int steamID) {
        this.steamID = steamID;
    }
}
