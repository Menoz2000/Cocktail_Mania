package com.example.cocktailmania.cocktail;

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
import com.example.cocktailmania.ingredient.IngredientActivity;
import com.example.cocktailmania.naviga.MainActivity;
import com.example.cocktailmania.utility.CocktailElem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CocktailActivity extends AppCompatActivity implements CocktailAdapter.OnCktListener {


    Intent intent;
    RecyclerView recyclerView;
    List<CocktailElem> elems;
    CocktailAdapter cocktailAdapter;
    private static final String TAG = "CocktailActivity";
    private DbManager db = null;

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
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                case R.id.ingredientiButton:
                    intent = new Intent(CocktailActivity.this, IngredientActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                case R.id.bookButton:
                    intent = new Intent(CocktailActivity.this, BookActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    break;

                default:
                    break;
            }
            return true;
        });

        if (getIntent().hasExtra("list_cocktail")) {
            int config = getIntent().getIntExtra("lista_cocktail", 0);

            recyclerView = findViewById(R.id.cktRv);

            elems = new ArrayList<>();

            db = new DbManager(this);

            Cursor c = db.elencoCocktail(config);
            CocktailElem ckt;
            if (c != null) {
                while (c.moveToNext()) {
                    ckt = new CocktailElem(); // Note this addition
                    ckt.setId(c.getInt(0));
                    ckt.setImg(c.getInt(8));
                    ckt.setNome(c.getString(1));
                    ckt.setFk_gradoAlcolico(c.getString(10));
                    elems.add(ckt);
                }
                c.close();
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            cocktailAdapter = new CocktailAdapter((ArrayList<CocktailElem>) elems, CocktailActivity.this, this);
            recyclerView.setAdapter(cocktailAdapter);
        }

    }

    @Override
    public void OnCktClick(int position) {
        Log.d(TAG, "OnCktClick: clicked.");

        Intent intent = new Intent(this, CocktailModule.class);
        intent.putExtra("selected_ckt", elems.get(position).getId());
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
                cocktailAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    /*@Override
    public void onBackPressed(){
        super.onBackPressed();
    }*/
}