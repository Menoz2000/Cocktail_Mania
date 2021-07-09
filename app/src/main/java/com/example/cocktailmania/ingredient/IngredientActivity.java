package com.example.cocktailmania.ingredient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.cocktailmania.naviga.MainActivity;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.example.cocktailmania.cocktail.CocktailActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class IngredientActivity extends AppCompatActivity {


    Intent intent;
    RecyclerView recyclerView;
    List<IngredientElem> elems;

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
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                case R.id.cocktailButton:
                    intent = new Intent(IngredientActivity.this, CocktailActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                case R.id.bookButton:
                    intent = new Intent(IngredientActivity.this, BookActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                default:
                    break;
            }

            return true;
        });

        recyclerView = findViewById(R.id.ingRv);

        elems = new ArrayList<>();
        elems.add(new IngredientElem(1, R.drawable.ing_1, "Ghiaccio", "Ghiaccio a cubetti"));
        elems.add(new IngredientElem(2, R.drawable.ing_2, "Gin", "Gin mare"));
        elems.add(new IngredientElem(3, R.drawable.ing_3, "Acqua Tonica", ""));
        elems.add(new IngredientElem(4, R.drawable.ing_4, "Limone", ""));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        IngredientAdapter ingredientAdapter = new IngredientAdapter((ArrayList<IngredientElem>) elems, IngredientActivity.this);
        recyclerView.setAdapter(ingredientAdapter);
        /*recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(IngredientActivity.this, IngredientModule.class);
                Bundle b=new Bundle();
                b.putInt("Id",v.getId());
                intent.putExtras(b);
                startActivity(intent);
            }
        });*/
    }
}