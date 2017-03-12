package com.cstats.cstats;

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
import java.util.*;
import java.math.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.*;
import java.io.File;
import java.lang.*;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Login extends AppCompatActivity {



    private EditText idKentta;
    private Button submitNappi;
    private ImageButton apuNappi;
    private String id;


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

                Parseri p = new Parseri();



                // tänne submit napin painalluksen toiminta
                try {
                    id = idKentta.getText().toString();


                    System.out.println("Testitulostus ----- syötetty id: " + id);

                    //tää siirtää activityn toiseen
                    startActivity(new Intent("com.cstats.cstats.Main"));

                }catch(Exception e){

                    System.out.println("Error");
                }
                p.parseWeb(id);
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
}
