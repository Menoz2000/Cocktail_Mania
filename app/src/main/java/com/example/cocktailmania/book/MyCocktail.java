package com.example.cocktailmania.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cocktailmania.R;
import com.example.cocktailmania.cocktail.CocktailActivity;

public class MyCocktail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cocktail);

        //provvisorio, da controllare
        Button addButton=findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> openAddCkt());

    }

    private void openAddCkt(){
        Intent intent = new Intent(this, addCocktail.class);
        startActivity(intent);
    }
}

/*
* passaggi per inserire un myCocktail
* nome e foto
* ingredienti/guarnizioni con quantita
* strumenti
* bicchiere
* categoria grado alcolico
* preparazione con aggiunta step by step
* opzione dove controlli il cocktail con tutte le caratteristiche e se vanno bene tasto pubblica
*
*
* nella pagina con elenco di mycocktail ci deve essere in alto il pulsante per aggiungere un nuovo cocktail
* e sotto l'elenco dei my cocktail fatti e ci deve essere l'opzione per eliminare i cocktail inseriti*/