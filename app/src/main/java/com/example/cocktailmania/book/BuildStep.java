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

    private final DbManager db = new DbManager(this);

    StepPrep passaggio;
    ChipGroup chipGroupIng;
    ChipGroup chipGroupStrum;
    Button pippoButton;
    Spinner selectAzione;

    SpinnerAdapter spinnerAzAdapter;
    ArrayList<SpinnerElem> ActionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_step);

        passaggio = new StepPrep();

        chipGroupIng = findViewById(R.id.AddIngGroup);
        chipGroupStrum = findViewById(R.id.AddStrumGroup);

        ActionList = db.getActions();

        selectAzione = findViewById(R.id.AddActionGroup);
        spinnerAzAdapter = new SpinnerAdapter(this, ActionList);
        selectAzione.setAdapter(spinnerAzAdapter);
        selectAzione.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                passaggio.setIdAzione(ActionList.get(position).getId());
                passaggio.setAzione(ActionList.get(position).getNome());
                passaggio.setAzioneImg(ActionList.get(position).getImg());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        for (int i = 0; i < MyCocktail.MyIngredients.size(); i++) {
            Chip chip = new Chip(this);
            chip.setText(MyCocktail.MyIngredients.get(i).getIngName());
            chip.setCheckable(true);
            chip.setId(MyCocktail.MyIngredients.get(i).getIdIng());
            chipGroupIng.addView(chip);
        }
        chipGroupIng.setVisibility(View.VISIBLE);

        for (int i = 0; i < MyCocktail.MyStrums.size(); i++) {
            Chip chip = new Chip(this);
            chip.setText(MyCocktail.MyStrums.get(i).getNome());
            chip.setId(MyCocktail.MyStrums.get(i).getId());
            chip.setCheckable(true);
            chipGroupStrum.addView(chip);
        }
        chipGroupStrum.setVisibility(View.VISIBLE);
        chipGroupStrum.setSingleSelection(true);

        pippoButton = findViewById(R.id.PippoButton);
        //click sul button
        pippoButton.setOnClickListener(v -> {

            //System.out.println(MyCocktail.MyIngredients.size());
            if (!chipGroupIng.getCheckedChipIds().isEmpty()) {
                //System.out.println("--------------------------------------------");
                //.out.println(chipGroupIng.getCheckedChipIds());
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

            //System.out.println(MyCocktail.MyStrums.size());
            if (chipGroupStrum.getCheckedChipId() != View.NO_ID) {
                //System.out.println("--------------------------------------------");
                //System.out.println(chipGroupStrum.getCheckedChipId());
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

            passaggio.setStepNum((StepMyCocktail.passaggi.size()) + 1);

            StepMyCocktail.passaggi.add(passaggio);

            //torno all'activity precedente
            Intent intent = new Intent(this, StepMyCocktail.class);
            startActivity(intent);
        });


    }


}