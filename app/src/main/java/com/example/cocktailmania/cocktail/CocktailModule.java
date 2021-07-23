package com.example.cocktailmania.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.ingredient.IngredientModule;
import com.example.cocktailmania.utility.CocktailElem;
import com.example.cocktailmania.utility.IngRow;
import com.example.cocktailmania.utility.NonScrollListView;
import com.example.cocktailmania.utility.StepPrep;

import java.util.ArrayList;

public class CocktailModule extends AppCompatActivity {

    private static final String TAG = "CocktailModule";
    private final DbManager db = new DbManager(this);
    NonScrollListView SteplistView, IngListView;
    ArrayList<IngRow> ingredients;
    ArrayList<StepPrep> stepPrep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_module);

        if (getIntent().hasExtra("selected_ckt")) {
            int cktN = getIntent().getIntExtra("selected_ckt", 0);
            stepPrep = db.getPrepCkt(cktN);
            ingredients = db.getIngredients(cktN);
            CocktailElem ckt = db.getSingleCocktail(cktN);


            for (int i = 0; i < ingredients.size(); i++) {
                System.out.println(ingredients.get(i).toString());
            }

            //passaggio dei dati alla grafica
            TextView textView = findViewById(R.id.CktName);
            textView.setText(ckt.getNome());

            try {
                ImageView imageView = findViewById(R.id.img);
                imageView.setImageResource(ckt.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView textView1 = findViewById(R.id.AlcolicGrade);
            textView1.setText(String.valueOf(ckt.getFk_gradoAlcolico()));

            TextView textView2 = findViewById(R.id.CktOrigin);
            if (ckt.getFk_origine() != null) {
                textView2.setText(ckt.getFk_origine());
            } else {
                textView2.setVisibility(TextView.GONE);
            }

            //NonScrollListView con gli ingredienti necessari
            IngListView = findViewById(R.id.IngredientList);
            IngredientCustomAdapter iAdapter = new IngredientCustomAdapter(this, ingredients);
            IngListView.setAdapter(iAdapter);
            IngListView.setFocusable(false);

            //NonScrollListView con gli step delle preparazioni
            SteplistView = findViewById(R.id.StepPrep);
            StepCustomAdapter arrayAdapter = new StepCustomAdapter(this, stepPrep);
            SteplistView.setAdapter(arrayAdapter);
            SteplistView.setFocusable(false);


        }
    }

}