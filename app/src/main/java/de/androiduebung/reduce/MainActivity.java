package de.androiduebung.reduce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvreuckmeldung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get button element with id
        Button button = findViewById(R.id.button);
        //Setting onclick listener
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try{

            System.out.println("__Button was pressed__");
            //Get elements by id
            EditText etZaehler = findViewById(R.id.etzaehler);
            EditText etNenner = findViewById(R.id.etnenner);

            tvreuckmeldung = findViewById(R.id.tvreumeldung);
            tvreuckmeldung.setText("");

            String sZaehler = etZaehler.getText().toString();
            String sNenner = etNenner.getText().toString();

            //If nothing was placed in the input fields
     //       if((sZaehler.length() == 0) || (sNenner.length() == 0)) return; // defencive technik
            if((sZaehler.length() == 0) || (sNenner.length() == 0)) throw new Exception("Bitte keinen Leerstring verwenden");

            //Getting values from elements and parsing them to integer
              int  iZaehler = Integer.parseInt(sZaehler);
              int  iNenner = Integer.parseInt(sNenner);


                int testauf_null = iZaehler / iNenner; // test auf StandartExeption Division by zero

                //Making calculation
                if (iZaehler * iNenner != 0) {
                    if(iZaehler==3) throw new reduceExeption("Spezielle ungünstige konfiguration", iZaehler, iNenner);
                    int rest;
                    int ggt = Math.abs(iZaehler);
                    int divisor = Math.abs(iNenner);
                    do {
                        rest = ggt % divisor;
                        ggt = divisor;
                        divisor = rest;
                    } while (rest > 0);
                    iZaehler /= ggt;
                    iNenner /= ggt;

                } else {
                    //Build dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //Set dialog message
                    builder.setMessage("Please insert no 0");
                    //We create build dialog
                    AlertDialog dialog = builder.create();
                    //We show the dialog we created
                    dialog.show();

                    throw new Exception("Bitte keine null im Nenner verwenden");
                }

                //Set input field to new integer values
                etZaehler.setText(Integer.toString(iZaehler));
                etNenner.setText(Integer.toString(iNenner));


        }catch(reduceExeption e){
            e.printStackTrace();
            tvreuckmeldung.setText("Rückmeldung: \n" + e.getMessage()+"\n mit Zähler "+e.getiZaehler()+ " und Nenner "+ e.getiNenner());
        }
        catch (Exception e) {
            e.printStackTrace();
            tvreuckmeldung.setText("Fehlermeldung: \n" + e.getMessage());
        }


        //Mit linux konsole arbeiten ist prüfungs relevant
        //Tasten abkürzungen sind prüfungs relevant
        //Version controll ist prüfungs relevant
        // etc.. Noch mehr prüfungs relevant

    }
}