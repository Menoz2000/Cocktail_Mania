package com.example.cocktailmania.cocktail;

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

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.book.BookActivity;
import com.example.cocktailmania.book.MyCocktail;
import com.example.cocktailmania.ingredient.IngredientActivity;
import com.example.cocktailmania.naviga.MainActivity;
import com.example.cocktailmania.utility.CocktailElem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CocktailActivity extends AppCompatActivity implements CocktailAdapter.OnCktListener {

    DbManager db = new DbManager(this);

    Intent intent;
    RecyclerView recyclerView;  //view dei cocktail
    ArrayList<CocktailElem> elems;  //arraylist con tutti i cocktail
    CocktailAdapter cocktailAdapter;    //adapter per la lista
    private static final String TAG = "CocktailActivity";
    int config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail);

        if (getIntent().hasExtra("list_cocktail")) {
            //salvo la configurazione della lista
            config = getIntent().getIntExtra("list_cocktail", 0);

            //gestione menù in basso
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

                    case R.id.cocktailButton:
                        intent = new Intent(CocktailActivity.this, CocktailActivity.class);
                        intent.putExtra("list_cocktail", 0); //visualizzazione senza my_cocktail
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

            elems = db.elencoCocktail(config);  //interrogazione a database (tutti i cocktail in base alla configurazione)

            //gestione lista cocktail
            recyclerView = findViewById(R.id.cktRv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            cocktailAdapter = new CocktailAdapter(elems, CocktailActivity.this, this);
            recyclerView.setAdapter(cocktailAdapter);
        }

    }

    //gestione click su un cocktail
    @Override
    public void OnCktClick(int position) {
        Log.d(TAG, "OnCktClick: clicked.");

        Intent intent = new Intent(this, CocktailModule.class);
        intent.putExtra("selected_ckt", elems.get(position).getId());
        startActivity(intent);
    }

    //gestione barra in alto
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        //in caso di My Cocktail inserisce la barra dei my cocktail (aggiunta cocktail)
        if (config == 2) {
            inflater.inflate(R.menu.my_cocktail_bar, menu);
            MenuItem addItem = menu.findItem(R.id.add_myckt);

            //click sul pulsante di aggiunta cocktail
            addItem.setOnMenuItemClickListener(item -> {
                Intent intent = new Intent(this, MyCocktail.class); //passo all'activity di creazione cocktail
                startActivity(intent);
                return false;
            });
        } else {
            //barra con la sola ricerca
            inflater.inflate(R.menu.search_bar, menu);
        }

        //gestione pulsante ricerca
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        //gestion click sul pulsante
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

    //gestione click su pulsante 'indietro'
    @Override
    public void onResume() {
        super.onResume();
        //se ci troviamo nella pagina dei Preferiti
        if (config == 1) {
            elems = db.elencoCocktail(config); //rinterrogo il database per modifiche
            boolean ret = cocktailAdapter.setElems(elems);
            if (ret)
                cocktailAdapter.notifyDataSetChanged(); //aggiorno la lista dei cocktail
        }
    }

}