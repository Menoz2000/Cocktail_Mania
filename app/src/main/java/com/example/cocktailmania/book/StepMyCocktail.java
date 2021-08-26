package com.example.cocktailmania.book;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.IngRow;
import com.example.cocktailmania.utility.StepPrep;
import com.example.cocktailmania.utility.Strumento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class StepMyCocktail extends AppCompatActivity implements View.OnClickListener {

    static ArrayList<StepPrep> passaggi;

    Button aggiungiStep;

    ArrayList<IngRow> MyIngredientsCpy = new ArrayList<>(MyCocktail.MyIngredients);
    ArrayList<Strumento> MyStrumsCpy = new ArrayList<>(MyCocktail.MyStrums);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_my_cocktail);

        aggiungiStep = findViewById(R.id.AddStep);
        aggiungiStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AddStep:

                Intent intent = new Intent(this, BuildStep.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}