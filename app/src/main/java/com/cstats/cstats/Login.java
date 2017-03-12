package com.cstats.cstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {



    private EditText idKentta;
    private Button submitNappi, apuNappi;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //piilotetaan menubar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        /*

        //Määritetään id input kenttä
        idKentta = (EditText) findViewById(R.id.idInput);

        //Määritetään submitnappi ja asetetaan sille kuuntelija
        submitNappi = (Button) findViewById(R.id.etuNappi);
        submitNappi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Parse p = new Parse();



                // tänne submit napin painalluksen toiminta
                try {
                    id = idKentta.getText().toString();

                    System.out.println("Testitulostus ----- syötetty id: " + id);

                    //tää siirtää activityn toiseen
                    startActivity(new Intent(Main.this, Tulos.class));

                }catch(Exception e){

                    System.out.println("Error");
                }
                p.parseWeb(id);
            }
        });

*/

/*
        //Määritetään HELP -nappi ja asetetaan sille kuuntelija
        apuNappi = (Button) findViewById(R.id.helpNappi);
        apuNappi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // tänne apunapin painalluksen toiminta
                //eli popup käynnistäjä ohjeelle


                naytaApu();


            }
        });

        */


/*
        private void naytaApu() {

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

        */



    }
}
