package com.example.cocktailmania.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.SpinnerElem;
import com.example.cocktailmania.utility.StepPrep;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class BuildStep extends AppCompatActivity {
    //activity per costruire lo step del My Cocktail

    private final DbManager db = new DbManager(this);

    StepPrep passaggio; //oggetto "passadati"
    ChipGroup chipGroupIng; //per selezionare gli ingredienti
    ChipGroup chipGroupStrum;   //per selezionare lo strumento
    Button pubblica_button; //bottone PUBBLICA
    Spinner selectAzione;   //spinner per selezionare l'azione

    SpinnerAdapter spinnerAzAdapter;    //adapter per lo spinner degli strumenti
    ArrayList<SpinnerElem> ActionList;  //arraylist con gli tutte le azioni (tramite interrogazione database)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_step);

        passaggio = new StepPrep();

        //gestione chipGroup
        chipGroupIng = findViewById(R.id.AddIngGroup);
        chipGroupStrum = findViewById(R.id.AddStrumGroup);

        //interrogazione a databse
        ActionList = db.getActions();

        //gestione spinner Azioni
        selectAzione = findViewById(R.id.AddActionGroup);
        spinnerAzAdapter = new SpinnerAdapter(this, ActionList);
        selectAzione.setAdapter(spinnerAzAdapter);
        selectAzione.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //prendo i dati dell'azione selezionata
                passaggio.setIdAzione(ActionList.get(position).getId());
                passaggio.setAzione(ActionList.get(position).getNome());
                passaggio.setAzioneImg(ActionList.get(position).getImg());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //gestione chipGroup degli ingredienti
        for (int i = 0; i < MyCocktail.MyIngredients.size(); i++) {
            Chip chip = new Chip(this);
            chip.setText(MyCocktail.MyIngredients.get(i).getIngName());
            chip.setCheckable(true);
            chip.setId(MyCocktail.MyIngredients.get(i).getIdIng());
            chipGroupIng.addView(chip);
        }
        chipGroupIng.setVisibility(View.VISIBLE);

        //gestione chipGroup degli strumenti
        for (int i = 0; i < MyCocktail.MyStrums.size(); i++) {
            Chip chip = new Chip(this);
            chip.setText(MyCocktail.MyStrums.get(i).getNome());
            chip.setId(MyCocktail.MyStrums.get(i).getId());
            chip.setCheckable(true);
            chipGroupStrum.addView(chip);
        }
        chipGroupStrum.setVisibility(View.VISIBLE);
        chipGroupStrum.setSingleSelection(true);


        pubblica_button = findViewById(R.id.PippoButton);
        //click sul button PUBBLICA
        pubblica_button.setOnClickListener(v -> {
            //prendo i dati degli ingredienti per quello step
            if (!chipGroupIng.getCheckedChipIds().isEmpty()) {
                passaggio.setIngStep(chipGroupIng.getCheckedChipIds());

                int i = 0;
                while (i < MyCocktail.MyIngredients.size()) {
                    Chip control = (Chip) chipGroupIng.getChildAt(i);
                    if (control.isChecked()) {
                        if (passaggio.getIng() == null)
                            passaggio.setIng(control.getText().toString());
                        else
                            passaggio.addIng(control.getText().toString());
                    }

                    i++;
                }
                //lascio NULL nei campi degli ingredienti se nessuno selezionato
            }

            //prendo i dati dello strumento per quello step
            if (chipGroupStrum.getCheckedChipId() != View.NO_ID) {
                passaggio.setIdStrumento(chipGroupStrum.getCheckedChipId());

                int i = 0;
                while (i < MyCocktail.MyStrums.size()) {
                    Chip control = (Chip) chipGroupStrum.getChildAt(i);
                    if (control.isChecked())
                        passaggio.setStrumento(control.getText().toString());
                    i++;
                }
                //lascio NULL nei campi degli strumenti se nessuno selezionato
            }

            //imposto il numero dello step
            passaggio.setStepNum((MyCocktail.passaggi.size()) + 1);

            //aggiungo l'oggetto passadati all'arraylist statico con tutti gli step
            MyCocktail.passaggi.add(passaggio);

            //stampa di prova: passaggio
            //System.out.println(passaggio.toString());

            //torno all'activity precedente
            Intent intent = new Intent(this, StepMyCocktail.class);
            startActivity(intent);
        });

    }

}