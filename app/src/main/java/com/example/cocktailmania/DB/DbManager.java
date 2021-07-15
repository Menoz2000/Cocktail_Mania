package com.example.cocktailmania.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {

    MyHelper helper;
    private final static String DATABASE="Ingrediente";
    private final static int VERSIONE_DATABASE=1;

    public DbManager(Context context)
    {

        helper=new MyHelper(context, DATABASE, null, VERSIONE_DATABASE);
    }

    public Cursor elencoIngredienti()
    {
        String query="SELECT * FROM Ingrediente ORDER BY nome";

        SQLiteDatabase db= helper.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public Cursor elencoCocktail()
    {
        String query="SELECT * FROM Cocktail C, GradoAlcolico G WHERE G.id=C.fk_gradoAlcolico ORDER BY nome";

        SQLiteDatabase db= helper.getReadableDatabase();
        return db.rawQuery(query, null);
    }

}
