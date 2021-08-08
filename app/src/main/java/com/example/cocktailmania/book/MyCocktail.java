package com.example.cocktailmania.book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cocktailmania.R;

public class MyCocktail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cocktail);
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