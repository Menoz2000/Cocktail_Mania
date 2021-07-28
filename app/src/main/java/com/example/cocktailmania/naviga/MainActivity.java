package com.example.cocktailmania.naviga;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.example.cocktailmania.cocktail.CocktailActivity;
import com.example.cocktailmania.ingredient.IngredientActivity;
import com.example.cocktailmania.utility.GradeCocktail;
import com.example.cocktailmania.utility.NonScrollGridView;
import com.example.cocktailmania.utility.NonScrollListView;
import com.example.cocktailmania.utility.TipoCocktail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CocktailModule";
    private final DbManager db = new DbManager(this);
    Intent intent;
    NonScrollGridView TypeGridView;
    NonScrollListView GradeListView;
    ArrayList<TipoCocktail> tipoCocktailArrayList;
    ArrayList<GradeCocktail> gradeCocktailArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prendo i dati dal database
        tipoCocktailArrayList = db.getType();
        gradeCocktailArrayList = db.getGrade();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigaButton);
        bottomNavigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()) {

                case R.id.cocktailButton:
                    intent = new Intent(MainActivity.this, CocktailActivity.class);
                    intent.putExtra("list_cocktail", 0); //visualizzazione senza my_cocktail
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                case R.id.ingredientiButton:
                    intent = new Intent(MainActivity.this, IngredientActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                case R.id.bookButton:
                    intent = new Intent(MainActivity.this, BookActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                default:
                    break;
            }
            return true;
        });

        //griglia con i tipi di cocktail
        TypeGridView = findViewById(R.id.GrigliaTipoCocktail);
        GridAdapter gridAdapter = new GridAdapter(this, tipoCocktailArrayList);
        TypeGridView.setAdapter(gridAdapter);
        TypeGridView.setFocusable(false);    //elimino focus sulla lista
        //rendo la lista cliccabile
        TypeGridView.setClickable(true);
        TypeGridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(MainActivity.this, TypeCocktailList.class);
            int[] val = {0, tipoCocktailArrayList.get(position).getId()}; //array con {config, id}
            i.putExtra("cocktail", val);
            startActivity(i);
        });

        //imposto la lista dei gradi di cocktail
        GradeListView = findViewById(R.id.list_AlcolicGrade);
        GradeListAdapter gridListAdapter = new GradeListAdapter(this, gradeCocktailArrayList);
        GradeListView.setAdapter(gridListAdapter);
        GradeListView.setFocusable(false);    //elimino focus sulla lista
        //rendo la lista cliccabile
        GradeListView.setClickable(true);
        GradeListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(MainActivity.this, TypeCocktailList.class);
            int[] val = {1, gradeCocktailArrayList.get(position).getId()}; //array con {config, id}
            i.putExtra("cocktail", val);
            startActivity(i);
        });
    }
}