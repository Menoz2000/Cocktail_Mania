package com.example.cocktailmania.cocktail;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.example.cocktailmania.book.MyCocktail;
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
    int config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail);

        if (getIntent().hasExtra("list_cocktail")) {
            config = getIntent().getIntExtra("list_cocktail", 0);

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            /*se config == 0 sono nella pagina Cocktail
              se config == 1 sono in Preferiti nella pagina Book
              se config == 2 sono in My_Cocktail nella pagina Book*/
            if (config == 0)
                bottomNavigationView.setSelectedItemId(R.id.cocktailButton);
            if (config == 1 || config == 2)
                bottomNavigationView.setSelectedItemId(R.id.bookButton);
            //set Listener on BottomNavigationView
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

                    case R.id.cocktailButton:
                        intent = new Intent(CocktailActivity.this, CocktailActivity.class);
                        intent.putExtra("list_cocktail", 0);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    default:
                        break;
                }
                return true;
            });

            recyclerView = findViewById(R.id.cktRv);

            elems = new ArrayList<>();

            DbManager db = new DbManager(this);

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

        if (config == 2) {
            inflater.inflate(R.menu.my_cocktail_bar, menu);
            MenuItem addItem = menu.findItem(R.id.add_myckt);

            Button add_ckt = (Button) addItem.getActionView();

            addItem.setOnMenuItemClickListener(item -> {
                Intent intent = new Intent(this, MyCocktail.class);
                startActivity(intent);
                return false;
            });
        } else {
            inflater.inflate(R.menu.search_bar, menu);
        }

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