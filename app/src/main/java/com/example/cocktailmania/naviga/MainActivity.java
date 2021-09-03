package com.example.cocktailmania.naviga;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.example.cocktailmania.cocktail.CocktailActivity;
import com.example.cocktailmania.ingredient.IngredientActivity;
import com.example.cocktailmania.utility.GradeCocktail;
import com.example.cocktailmania.utility.NonScrollGridView;
import com.example.cocktailmania.utility.NonScrollListView;
import com.example.cocktailmania.utility.OriginCocktail;
import com.example.cocktailmania.utility.TipoCocktail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OriginListAdapter.OnOriginListener {
    private static final String TAG = "CocktailModule";
    private final DbManager db = new DbManager(this);
    Intent intent;
    NonScrollGridView TypeGridView; //griglia dei tipi di cocktail
    NonScrollListView GradeListView;    //lista dei gradi alcolici dei cocktail
    RecyclerView recyclerView;  //lista orizzontale delle origini
    ArrayList<TipoCocktail> tipoCocktailArrayList;  //arraylist con i tipi di cocktail
    ArrayList<GradeCocktail> gradeCocktailArrayList;    //arraylist con i gradi alcolici dei cocktail
    ArrayList<OriginCocktail> originCocktailArrayList;  //arraylist con le origini dei cocktail

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipoCocktailArrayList = db.getType();       //interrogo database (tutti i tipi di cocktail)
        gradeCocktailArrayList = db.getGrades();    //interrogo database (tutti i gradi alcolici dei cocktail)
        originCocktailArrayList = db.getOrigin();   //interrogo database (tutte le origini dei cocktail)

        //gestione menù in basso
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

        /* griglia con i tipi di cocktail */
        TypeGridView = findViewById(R.id.GrigliaTipoCocktail);
        GridAdapter gridAdapter = new GridAdapter(this, tipoCocktailArrayList);
        TypeGridView.setAdapter(gridAdapter);
        TypeGridView.setFocusable(false);    //elimino focus sulla lista
        //rendo la lista cliccabile
        TypeGridView.setClickable(true);
        TypeGridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(MainActivity.this, TypeCocktailList.class);
            int[] val = {0, tipoCocktailArrayList.get(position).getId()}; //array con {config, id tipo}
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
            int[] val = {1, gradeCocktailArrayList.get(position).getId()}; //array con {config, id grado}
            i.putExtra("cocktail", val);
            startActivity(i);
        });

        //lista orizzontale con le origini
        recyclerView = findViewById(R.id.OriginList);
        OriginListAdapter oAdapter = new OriginListAdapter(originCocktailArrayList, MainActivity.this, this);
        LinearLayoutManager oLayoutManager = new LinearLayoutManager(getApplicationContext());
        oLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(oLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(oAdapter);

    }

    //click su una origine di un cocktail
    @Override
    public void OnOriginClick(int position) {
        Intent i = new Intent(MainActivity.this, TypeCocktailList.class);
        int[] val = {2, originCocktailArrayList.get(position).getId()}; //array con {config, id origine}
        i.putExtra("cocktail", val);
        startActivity(i);
    }
}