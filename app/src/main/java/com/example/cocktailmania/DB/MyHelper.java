package com.example.cocktailmania.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cocktailmania.R;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String comando = "CREATE TABLE ingredienti (" +
                "id INTEGER  PRIMARY KEY ," +
                "img INTEGER," +
                "nome TEXT," +
                "sottotitolo TEXT)";

        db.execSQL(comando);

        inizializza(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void inizializza(SQLiteDatabase db) {
        String insert1 = "INSERT INTO ingredienti (id,img,nome,sottotitolo) " +
                "VALUES (1, " + R.drawable.ing_1 + ", 'Ghiaccio', 'Ghiaccio a cubetti'), " +
                " (2, " + R.drawable.ing_2 + ", 'Gin', 'Gin Mare'), " +
                " (3, " + R.drawable.ing_3 + ", 'Acqua Tonica', '')";

        db.execSQL(insert1);
    }
}