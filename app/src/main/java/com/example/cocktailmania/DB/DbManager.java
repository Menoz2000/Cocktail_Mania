package com.example.cocktailmania.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cocktailmania.cocktail.CocktailElem;
import com.example.cocktailmania.ingredient.IngredientElem;

public class DbManager {

    MyHelper helper;
    private final static String DATABASE = "Ingrediente";
    private final static int VERSIONE_DATABASE = 2;

    public DbManager(Context context) {

        helper = new MyHelper(context, DATABASE, null, VERSIONE_DATABASE);
    }

    public Cursor elencoIngredienti() {
        String query = "SELECT id,nome,sottotitolo,img FROM Ingrediente ORDER BY nome";

        SQLiteDatabase db = helper.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public IngredientElem getSingleIngredient(int id) {
        String query = "SELECT i.id,nome,img,sottotitolo,descrizione,nazione,grado_alcolico FROM Ingrediente i LEFT JOIN Origine o ON i.fk_origine=o.id WHERE i.id=" + id;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        IngredientElem ing = new IngredientElem();

        if (c != null) {
            if (c.moveToFirst()) {
                ing.setId(id);
                ing.setNome(c.getString(1));
                ing.setImg(c.getInt(2));
                ing.setSottotitolo(c.getString(3));
                ing.setDescrizione(c.getString(4));
                ing.setOrigine(c.getString(5));
                ing.setGrado_alcolico(c.getDouble(6));
            }
            c.close();

        }

        return ing;
    }

    public Cursor elencoCocktail() {
        String query = "SELECT * FROM Cocktail C, GradoAlcolico G WHERE G.id=C.fk_gradoAlcolico ORDER BY nome";

        SQLiteDatabase db = helper.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public CocktailElem getSingleCocktail(int id) {
        String query = "SELECT C.id,C.nome,G.gradazione,O.nazione,T.nome,C.preferito,C.iconico,C.my_cocktail,C.img " +
                "FROM Cocktail C " +
                "LEFT JOIN GradoAlcolico G ON G.id=C.fk_gradoAlcolico " +
                "LEFT JOIN Origine O ON O.id=C.fk_gradoAlcolico " +
                "LEFT JOIN TipoCocktail T ON T.id=C.fk_tipo " +
                "WHERE C.id=" + id;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        CocktailElem ckt = new CocktailElem();

        if (c != null) {
            if (c.moveToFirst()) {
                ckt.setId(id);
                ckt.setNome(c.getString(1));
                ckt.setFk_gradoAlcolico(c.getString(2));
                ckt.setFk_origine(c.getString(3));
                ckt.setFk_tipo(c.getString(4));

                if (c.getInt(5) == 0) {
                    ckt.setPreferito(false);
                } else {
                    ckt.setPreferito(true);
                }

                if (c.getInt(6) == 0) {
                    ckt.setIconico(false);
                } else {
                    ckt.setIconico(true);
                }

                if (c.getInt(7) == 0) {
                    ckt.setMy_cocktail(false);
                } else {
                    ckt.setMy_cocktail(true);
                }

                ckt.setImg(c.getInt(8));

            }
            c.close();

        }

        return ckt;
    }

    public void addMyCocktail(){
                /*"CREATE TABLE Cocktail (" +
                "id integer PRIMARY KEY," +
                "nome text NOT NULL," +
                "fk_gradoAlcolico integer," +
                "fk_origine integer," +
                "fk_tipo integer," +
                "preferito integer NOT NULL DEFAULT 0," +
                "iconico integer NOT NULL DEFAULT 0," +
                "my_cocktail integer NOT NULL DEFAULT 0," +
                "img integer NOT NULL," +
                "FOREIGN KEY (fk_origine) REFERENCES Origine(id)," +
                "FOREIGN KEY (fk_gradoAlcolico) REFERENCES GradoAlcolico(id)," +
                "FOREIGN KEY (fk_tipo) REFERENCES TipoCocktail(id)" +
                ");"*/
    }
}
