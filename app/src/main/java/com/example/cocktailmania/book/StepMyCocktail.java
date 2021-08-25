package com.example.cocktailmania.book;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.IngRow;
import com.example.cocktailmania.utility.StepPrep;
import com.example.cocktailmania.utility.Strumento;

import android.os.Bundle;

import java.util.ArrayList;

public class StepMyCocktail extends AppCompatActivity {

    StepPrep passaggio;
    static ArrayList<StepPrep> passaggi;

    ArrayList<IngRow> MyIngredientsCpy = new ArrayList<>(MyCocktail.MyIngredients);
    ArrayList<Strumento> MyStrumsCpy = new ArrayList<>(MyCocktail.MyStrums);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_my_cocktail);
    }
}