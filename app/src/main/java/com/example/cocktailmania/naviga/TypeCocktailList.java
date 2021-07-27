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
import com.example.cocktailmania.utility.NonScrollGridView;
import com.example.cocktailmania.utility.TipoCocktail;

import java.util.ArrayList;

public class TypeCocktailList extends AppCompatActivity {

    private static final String TAG = "CocktailModule";
    private final DbManager db = new DbManager(this);
    ArrayList<CocktailElem> cocktailElems;
    TipoCocktail selectedType;
    NonScrollGridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_cocktail);

        if (getIntent().hasExtra("tipo_cocktail")) {
            int idType = getIntent().getIntExtra("tipo_cocktail", 0);
            cocktailElems=db.getCocktailType(idType);
            selectedType=db.getType(idType);

            //setto le informazione del tipo
            TextView name_type=findViewById(R.id.TypeTitle);
            ImageView image_type=findViewById(R.id.TypeIMG);
            name_type.setText(selectedType.getNome());
            image_type.setImageResource(selectedType.getImg());

            //imposto la griglia dei cocktail del tipo selezionato
            gridView = findViewById(R.id.grid_cocktail_type);
            GridCocktailAdapter gridAdapter = new GridCocktailAdapter(this, cocktailElems);
            gridView.setAdapter(gridAdapter);
            gridView.setFocusable(false);    //elimino focus sulla lista
            //rendo la lista cliccabile
            gridView.setClickable(true);
            gridView.setOnItemClickListener((parent, view, position, id) -> {
                Intent i = new Intent(TypeCocktailList.this, CocktailModule.class);
                i.putExtra("selected_ckt", cocktailElems.get(position).getId());
                startActivity(i);
            });
        }
    }
}

