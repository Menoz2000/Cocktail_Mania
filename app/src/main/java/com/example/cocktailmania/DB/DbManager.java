package com.example.cocktailmania.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cocktailmania.ingredient.IngredientElem;

public class DbManager {

    MyHelper helper;
    private final static String DATABASE = "Ingrediente";
    private final static int VERSIONE_DATABASE = 1;

    public DbManager(Context context) {

        helper = new MyHelper(context, DATABASE, null, VERSIONE_DATABASE);
    }

    public Cursor elencoIngredienti() {
        String query = "SELECT id,nome,sottotitolo,img FROM Ingrediente ORDER BY nome";

        SQLiteDatabase db = helper.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public IngredientElem getSingleIngredient(int id) {
        String query = "SELECT i.id,nome,img,sottotitolo,descrizione,nazione,grado_alcolico FROM Ingrediente i,Origine o WHERE i.id=" + id+" AND i.fk_origine=o.id";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c=db.rawQuery(query, null);

        IngredientElem ing = new IngredientElem(); // Note this addition
        ing.setId(c.getInt(0));
        ing.setNome(c.getString(1));
        ing.setImg(c.getInt(2));
        ing.setSottotitolo(c.getString(3));
        ing.setDescrizione(c.getString(4));
        ing.setOrigine(c.getString(5));
        ing.setGrado_alcolico(c.getDouble(6));

        return ing;
    }

    public Cursor elencoCocktail() {
        String query = "SELECT * FROM Cocktail C, GradoAlcolico G WHERE G.id=C.fk_gradoAlcolico ORDER BY nome";

        SQLiteDatabase db = helper.getReadableDatabase();
        return db.rawQuery(query, null);
    }

}
