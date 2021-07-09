package com.example.cocktailmania.ingredient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cocktailmania.naviga.MainActivity;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.example.cocktailmania.cocktail.CocktailActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IngredientActivity extends AppCompatActivity {


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.ingredientiButton);
        bottomNavigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()) {

                case R.id.navigaButton:
                    intent = new Intent(IngredientActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;

                case R.id.cocktailButton:
                    intent = new Intent(IngredientActivity.this, CocktailActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;

                case R.id.bookButton:
                    intent = new Intent(IngredientActivity.this, BookActivity.class);
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