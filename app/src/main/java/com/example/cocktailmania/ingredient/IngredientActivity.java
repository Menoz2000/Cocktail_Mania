package com.example.cocktailmania.ingredient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.example.cocktailmania.cocktail.CocktailActivity;
import com.example.cocktailmania.naviga.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class IngredientActivity extends AppCompatActivity implements IngredientAdapter.OnIngListener {


    Intent intent;
    RecyclerView recyclerView;
    List<IngredientElem> elems;
    IngredientAdapter ingredientAdapter;
    private static final String TAG = "IngredientActivity";

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
        elems.add(new IngredientElem(5, R.drawable.ing_4, "Limone", ""));
        elems.add(new IngredientElem(6, R.drawable.ing_4, "Limone", ""));
        elems.add(new IngredientElem(7, R.drawable.ing_4, "Limone", ""));
        elems.add(new IngredientElem(8, R.drawable.ing_4, "Limone", ""));
        elems.add(new IngredientElem(9, R.drawable.ing_4, "Limone", ""));
        elems.add(new IngredientElem(10, R.drawable.ing_4, "Limone", ""));
        elems.add(new IngredientElem(11, R.drawable.ing_4, "Limone", ""));
        elems.add(new IngredientElem(12, R.drawable.ing_4, "Limone", ""));
        elems.add(new IngredientElem(13, R.drawable.ing_4, "Limone", ""));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ingredientAdapter = new IngredientAdapter((ArrayList<IngredientElem>) elems, IngredientActivity.this,this);
        recyclerView.setAdapter(ingredientAdapter);

    }

    @Override
    public void OnIngClick(int position) {
        Log.d(TAG, "OnIngClick: clicked.");

        Intent intent=new Intent(this,IngredientModule.class);
        intent.putExtra("selected_ing", elems.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_bar,menu);


        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ingredientAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}