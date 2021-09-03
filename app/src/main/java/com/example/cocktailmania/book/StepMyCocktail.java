package com.example.cocktailmania.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.cocktail.CocktailActivity;
import com.example.cocktailmania.cocktail.StepCustomAdapter;
import com.example.cocktailmania.utility.NonScrollListView;

public class StepMyCocktail extends AppCompatActivity implements View.OnClickListener {

    DbManager db = new DbManager(this);

    Button aggiungiStep;    //button Aggiungi Step
    Button rimuoviStep;     //button per rimuovere l'ultimo step aggiunto
    Button finish;          //button PUBBLICA (per terminare la costruzione del cocktail)
    NonScrollListView MyStepListView;   //lista dinamica con gli step costruiti
    StepCustomAdapter arrayAdapter;     //adapter gli step
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_my_cocktail);

        //controllo se sono presenti step nella lista statica degli step aggiungi
        if (MyCocktail.passaggi.size() != 0) {
            MyStepListView = findViewById(R.id.MyStepList);
            arrayAdapter = new StepCustomAdapter(this, MyCocktail.passaggi);
            MyStepListView.setAdapter(arrayAdapter);
            MyStepListView.setFocusable(false);      //elimino focus sulla lista
        }

        //button AGGIUNGI STEP
        aggiungiStep = findViewById(R.id.AddStep);
        aggiungiStep.setOnClickListener(this);

        //button RIMUOVI ULTIMO STEP
        rimuoviStep = findViewById(R.id.DeleteStep);
        rimuoviStep.setOnClickListener(this);

        //button PUBBLICA
        finish = findViewById(R.id.OnStepButton);
        finish.setOnClickListener(this);
    }

    //gestione click su button sulla pagina
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //button aggiungi step
            case R.id.AddStep:
                //passo all'activity per la costruzione di uno step
                intent = new Intent(this, BuildStep.class);
                startActivity(intent);

                break;

            //button rimuovi ultimo step
            case R.id.DeleteStep:
                //controllo se ci sono passaggi da eliminare
                if (MyCocktail.passaggi.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Nessun passaggio da eliminare", Toast.LENGTH_LONG).show();
                } else {
                    //cancello lo step dalò'arraylist statico con gli step costruiti dall'utente
                    MyCocktail.passaggi.remove((MyCocktail.passaggi.size()) - 1);
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Ultimo step eliminato", Toast.LENGTH_LONG).show();
                }
                break;

            //button PUBBLICA
            case R.id.OnStepButton:
                //controllo se è stato inserito almeno uno step
                if (MyCocktail.passaggi.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Inserire almeno uno Step", Toast.LENGTH_LONG).show();
                } else {
                    db.uploadMyCkt();   //update su database (aggiungo il my cocktail appena costruito)

                    //passo all'activity con la lista dei My Cocktail
                    intent = new Intent(this, CocktailActivity.class);
                    intent.putExtra("list_cocktail", 2);
                    startActivity(intent);
                }
                break;

            default:
                break;
        }
    }
}