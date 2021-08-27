package com.example.cocktailmania.book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.SpinnerElem;
import com.example.cocktailmania.utility.StepPrep;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class BuildStep extends AppCompatActivity  {

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
            chipGroupIng.addView(chip);
        }
        chipGroupIng.setVisibility(View.VISIBLE);

        for (int i = 0; i < MyCocktail.MyStrums.size(); i++) {
            Chip chip = new Chip(this);
            chip.setText(MyCocktail.MyStrums.get(i).getNome());
            chip.setCheckable(true);
            chipGroupStrum.addView(chip);
        }
        chipGroupStrum.setVisibility(View.VISIBLE);
        chipGroupStrum.setSingleSelection(true);

        pippoButton = findViewById(R.id.PippoButton);
        pippoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passaggio.setIdStrumento(MyCocktail.MyStrums.get((chipGroupStrum.getCheckedChipId())-5).getId());
                System.out.println("--------------------------------------------");
                System.out.println(chipGroupStrum.getCheckedChipId());
                System.out.println(MyCocktail.MyStrums.get((chipGroupStrum.getCheckedChipId())-5).getId());
                StepMyCocktail.passaggi.add(passaggio);
            }
        });


    }


}