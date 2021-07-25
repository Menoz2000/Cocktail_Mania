package com.example.cocktailmania.ingredient;

import android.content.Intent;
import android.database.Cursor;
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

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.example.cocktailmania.cocktail.CocktailActivity;
import com.example.cocktailmania.naviga.MainActivity;
import com.example.cocktailmania.utility.IngredientElem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class IngredientActivity extends AppCompatActivity implements IngredientAdapter.OnIngListener {


    Intent intent;
    RecyclerView recyclerView;
    List<IngredientElem> elems;
    IngredientAdapter ingredientAdapter;
    private static final String TAG = "IngredientActivity";
    private DbManager db = null;

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
                    intent.putExtra("list_cocktail", 0); //visualizzazione senza my_cocktail
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

        db = new DbManager(this);

        Cursor c = db.elencoIngredienti();
        IngredientElem ing;
        if (c != null) {
            while (c.moveToNext()) {
                ing = new IngredientElem(); // Note this addition
                ing.setId(c.getInt(0));
                ing.setImg(c.getInt(3));
                ing.setNome(c.getString(1));
                ing.setSottotitolo(c.getString(2));
                elems.add(ing);
            }
            c.close();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ingredientAdapter = new IngredientAdapter((ArrayList<IngredientElem>) elems, IngredientActivity.this, this);
        recyclerView.setAdapter(ingredientAdapter);

    }

    @Override
    public void OnIngClick(int position) {
        Log.d(TAG, "OnIngClick: clicked.");

        Intent intent = new Intent(this, IngredientModule.class);
        intent.putExtra("selected_ing", elems.get(position).getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);


        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
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