package com.example.cocktailmania.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.cocktailmania.ingredient.IngredientActivity;
import com.example.cocktailmania.naviga.MainActivity;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CocktailActivity extends AppCompatActivity implements CocktailAdapter.OnCktListener{


    Intent intent;
    RecyclerView recyclerView;
    List<CocktailElem> elems;
    CocktailAdapter cocktailAdapter;
    private static final String TAG = "CocktailActivity";


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

        recyclerView = findViewById(R.id.cktRv);

        elems = new ArrayList<>();
        elems.add(new CocktailElem(1, R.drawable.ing_1, "Ghiaccio", "Ghiaccio a cubetti"));
        elems.add(new CocktailElem(2, R.drawable.ing_2, "Gin", "Gin mare"));
        elems.add(new CocktailElem(3, R.drawable.ing_3, "Acqua Tonica", "xzfkjm"));
        elems.add(new CocktailElem(4, R.drawable.ing_4, "Limone", "zsrfm"));
        elems.add(new CocktailElem(5, R.drawable.ing_4, "Limone", "zfm"));
        elems.add(new CocktailElem(6, R.drawable.ing_4, "Limone", "yks"));
        elems.add(new CocktailElem(7, R.drawable.ing_4, "Limone", "zmfr"));
        elems.add(new CocktailElem(8, R.drawable.ing_4, "Limone", "ykm"));
        elems.add(new CocktailElem(9, R.drawable.ing_4, "Limone", "mxf"));
        elems.add(new CocktailElem(10, R.drawable.ing_4, "Limone", "yks"));
        elems.add(new CocktailElem(11, R.drawable.ing_4, "Limone", "xmy"));
        elems.add(new CocktailElem(12, R.drawable.ing_4, "Limone", "xky"));
        elems.add(new CocktailElem(13, R.drawable.ing_4, "Limone", "ksyt"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        cocktailAdapter = new CocktailAdapter((ArrayList<CocktailElem>) elems, CocktailActivity.this,this);
        recyclerView.setAdapter(cocktailAdapter);
    }

    @Override
    public void OnCktClick(int position) {
        Log.d(TAG, "OnCktClick: clicked.");

        Intent intent=new Intent(this, CocktailModule.class);
        intent.putExtra("selected_ckt", elems.get(position));
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
                cocktailAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}