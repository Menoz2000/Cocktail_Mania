package com.example.cocktailmania.naviga;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.cocktail.CocktailModule;
import com.example.cocktailmania.utility.CocktailElem;
import com.example.cocktailmania.utility.GradeCocktail;
import com.example.cocktailmania.utility.NonScrollGridView;
import com.example.cocktailmania.utility.OriginCocktail;
import com.example.cocktailmania.utility.TipoCocktail;

import java.util.ArrayList;

public class TypeCocktailList extends AppCompatActivity {
    //activity con i cocktail appartenti al tipo/grado/origine selezionata
    private static final String TAG = "CocktailModule";
    private final DbManager db = new DbManager(this);
    ArrayList<CocktailElem> cocktailElems;  //arraylist con i cocktail
    NonScrollGridView gridView; //griglia con i cocktail

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_cocktail);

        if (getIntent().hasExtra("cocktail")) {
            TextView name = findViewById(R.id.TypeTitle);
            ImageView image = findViewById(R.id.TypeIMG);

            int[] val = getIntent().getIntArrayExtra("cocktail");
            //interrogazione a database (cocktail)
            cocktailElems = db.getCocktail(val[0], val[1]); //passo al metodo la configurazione e l'id

            //tipo cocktail
            if (val[0] == 0) {
                TipoCocktail selected;
                selected = db.getType(val[1]); //prendo i dati sul tipo

                //setto le informazione del tipo
                name.setText(selected.getNome());
                image.setImageResource(selected.getImg());
            }

            //grado alcolico
            if (val[0] == 1) {
                GradeCocktail selected;
                selected = db.getGrade(val[1]); //prendo i dati sul grado

                //setto le informazione del grado alcolico
                name.setText(selected.getNome());
                image.setVisibility(TextView.GONE);
            }

            //origine
            if (val[0] == 2) {
                OriginCocktail selected;
                selected = db.getOrigin(val[1]);    //prendo i dati sull'origine

                //setto le informazione del grado alcolico
                name.setText(selected.getNazione());
                image.setImageResource(selected.getImg());
            }

            //imposto la griglia dei cocktail
            gridView = findViewById(R.id.grid_cocktail_type);
            GridCocktailAdapter gridAdapter = new GridCocktailAdapter(this, cocktailElems);
            gridView.setAdapter(gridAdapter);
            gridView.setFocusable(false);    //elimino focus sulla lista
            //rendo la lista cliccabile
            gridView.setClickable(true);
            gridView.setOnItemClickListener((parent, view, position, id) -> {
                //passo all'activity le informazioni su un cocktail
                Intent i = new Intent(TypeCocktailList.this, CocktailModule.class);
                i.putExtra("selected_ckt", cocktailElems.get(position).getId());
                startActivity(i);
            });

        }
    }
}