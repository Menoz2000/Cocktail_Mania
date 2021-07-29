package com.example.cocktailmania.book;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.R;
import com.example.cocktailmania.cocktail.CocktailActivity;
import com.example.cocktailmania.ingredient.IngredientActivity;
import com.example.cocktailmania.naviga.MainActivity;
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
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                case R.id.cocktailButton:
                    intent = new Intent(BookActivity.this, CocktailActivity.class);
                    intent.putExtra("list_cocktail", 0); //visualizzazione senza my_cocktail
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                case R.id.ingredientiButton:
                    intent = new Intent(BookActivity.this, IngredientActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                default:
                    break;
            }

            return true;
        });

        Button button = findViewById(R.id.ckt_pref);
        button.setOnClickListener(v -> openActivityCkt(1));

        Button button1 = findViewById(R.id.my_ckt);
        button1.setOnClickListener(v -> openActivityCkt(2));

        Button button2 = findViewById(R.id.addCkt);
        button2.setOnClickListener(v -> {
            //TODO: implement MyCocktail.class
            //Intent intent = new Intent(this, MyCocktail.class);
            //startActivity(intent);
        });

    }

    public void openActivityCkt(int n) {
        Intent intent = new Intent(this, CocktailActivity.class);
        intent.putExtra("list_cocktail", n);
        startActivity(intent);

    }

}