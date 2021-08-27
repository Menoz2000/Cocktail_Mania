package com.example.cocktailmania.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.R;
import com.example.cocktailmania.cocktail.CocktailModule;
import com.example.cocktailmania.cocktail.StepCustomAdapter;
import com.example.cocktailmania.utility.IngRow;
import com.example.cocktailmania.utility.NonScrollListView;
import com.example.cocktailmania.utility.StepPrep;
import com.example.cocktailmania.utility.Strumento;

import java.util.ArrayList;

public class StepMyCocktail extends AppCompatActivity implements View.OnClickListener {


    Button aggiungiStep;
    Button rimuoviStep;
    Button finish;
    NonScrollListView MyStepListView;
    StepCustomAdapter arrayAdapter;
    Intent intent;

    ArrayList<IngRow> MyIngredientsCpy = new ArrayList<>(MyCocktail.MyIngredients);
    ArrayList<Strumento> MyStrumsCpy = new ArrayList<>(MyCocktail.MyStrums);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_my_cocktail);

        if (MyCocktail.passaggi.size() != 0) {
            MyStepListView = findViewById(R.id.MyStepList);
            arrayAdapter = new StepCustomAdapter(this, MyCocktail.passaggi);
            MyStepListView.setAdapter(arrayAdapter);
            MyStepListView.setFocusable(false);      //elimino focus sulla lista
        }

        aggiungiStep = findViewById(R.id.AddStep);
        aggiungiStep.setOnClickListener(this);

        rimuoviStep = findViewById(R.id.DeleteStep);
        rimuoviStep.setOnClickListener(this);

        finish=findViewById(R.id.OnStepButton);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AddStep:

                intent = new Intent(this, BuildStep.class);
                startActivity(intent);

                break;
            case R.id.DeleteStep:

                if (MyCocktail.passaggi.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Nessun passaggio da eliminare", Toast.LENGTH_LONG).show();
                } else {
                    MyCocktail.passaggi.remove((MyCocktail.passaggi.size()) - 1);
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Ultimo step eliminato", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.OnStepButton:

                intent = new Intent(this, CocktailModule.class);
                startActivity(intent);

                break;

            default:
                break;
        }
    }
}