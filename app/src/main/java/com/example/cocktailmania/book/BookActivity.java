package com.example.cocktailmania.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cocktailmania.cocktail.CocktailActivity;
import com.example.cocktailmania.ingredient.IngredientActivity;
import com.example.cocktailmania.naviga.MainActivity;
import com.example.cocktailmania.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bookButton);
        bottomNavigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()) {

                case R.id.navigaButton:
                    intent = new Intent(BookActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;

                case R.id.cocktailButton:
                    intent = new Intent(BookActivity.this, CocktailActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;

                case R.id.ingredientiButton:
                    intent = new Intent(BookActivity.this, IngredientActivity.class);
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