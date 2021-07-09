package com.example.cocktailmania.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cocktailmania.ingredient.IngredientActivity;
import com.example.cocktailmania.naviga.MainActivity;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CocktailActivity extends AppCompatActivity {


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cocktailButton);
        bottomNavigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()) {

                case R.id.navigaButton:
                    intent = new Intent(CocktailActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;

                case R.id.ingredientiButton:
                    intent = new Intent(CocktailActivity.this, IngredientActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;

                case R.id.bookButton:
                    intent = new Intent(CocktailActivity.this, BookActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;

                default:
                    break;
            }
            return true;
        });

    }
}