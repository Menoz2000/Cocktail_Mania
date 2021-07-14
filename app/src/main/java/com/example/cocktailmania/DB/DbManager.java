package com.example.cocktailmania.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {

    MyHelper helper=null;
    private final static String DATABASE="Ingrediente";
    private final static int VERSIONE_DATABASE=1;

    public DbManager(Context context)
    {

        helper=new MyHelper(context, DATABASE, null, VERSIONE_DATABASE);
    }

    public Cursor elencoIngredienti()
    {
        String query="SELECT * FROM Ingrediente";

        SQLiteDatabase db= helper.getReadableDatabase();
        return db.rawQuery(query, null);
    }

}
