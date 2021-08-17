package com.example.cocktailmania.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Spinner;

import com.example.cocktailmania.utility.CocktailElem;
import com.example.cocktailmania.utility.GradeCocktail;
import com.example.cocktailmania.utility.IngRow;
import com.example.cocktailmania.utility.IngredientElem;
import com.example.cocktailmania.utility.OriginCocktail;
import com.example.cocktailmania.utility.SpinnerElem;
import com.example.cocktailmania.utility.StepPrep;
import com.example.cocktailmania.utility.Strumento;
import com.example.cocktailmania.utility.TipoCocktail;

import java.util.ArrayList;


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
        String query = "SELECT i.id,nome,i.img,sottotitolo,descrizione,nazione,grado_alcolico FROM Ingrediente i LEFT JOIN Origine o ON i.fk_origine=o.id WHERE i.id=" + id;

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

    public Cursor elencoCocktail(int config) {
        String query = "SELECT * FROM Cocktail C LEFT JOIN GradoAlcolico G ON G.id=C.fk_gradoAlcolico ";

        /*passo alla prossima activity il tipo di lista che voglio:
                     [0] --> lista di tutti i cocktail non my_cocktail
                     [1] --> lista dei cocktail preferiti
                     [2] --> lista dei my_cocktail
        */
        if (config == 0)
            query += "WHERE my_cocktail=0 ";
        if (config == 1)
            query += "WHERE preferito=1 ";
        if (config == 2)
            query += "WHERE my_cocktail=1 ";


        query += "ORDER BY nome";
        SQLiteDatabase db = helper.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public CocktailElem getSingleCocktail(int id) {
        String query = "SELECT C.id,C.nome,G.gradazione,O.nazione,T.nome,C.preferito,C.iconico,C.my_cocktail,C.img " +
                "FROM Cocktail C " +
                "LEFT JOIN GradoAlcolico G ON G.id=C.fk_gradoAlcolico " +
                "LEFT JOIN Origine O ON O.id=C.fk_origine " +
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

    public ArrayList<StepPrep> getPrepCkt(int id) {

        int cont = 1, contIng = 0;
        StepPrep stepPrep;
        ArrayList<StepPrep> arrStepPrep = new ArrayList<>();

        while (true) {

            String query = "SELECT i.nome,a.nome,s.nome,a.img " +
                    "FROM Preparazione p " +
                    "LEFT JOIN Strumento s ON p.fk_strumento=s.id " +
                    "JOIN Azione a ON a.id=p.fk_azione " +
                    "LEFT JOIN Ingrediente i  ON i.id=p.fk_ingrediente " +
                    "WHERE p.step=" + cont +
                    " AND p.fk_cocktail=" + id +
                    " ORDER BY p.step";

            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor c = db.rawQuery(query, null);

            if (c != null) {

                if (c.moveToFirst()) {

                    stepPrep = new StepPrep();

                    do {

                        if (contIng == 0) {

                            stepPrep.setStepNum(cont);
                            stepPrep.setCktId(id);
                            stepPrep.setAzione(c.getString(1));
                            stepPrep.setStrumento(c.getString(2));
                            stepPrep.setAzioneImg(c.getInt(3));
                            stepPrep.setIng(c.getString(0));
                        } else {
                            stepPrep.addIng(c.getString(0));
                        }

                        contIng++;

                    } while (c.moveToNext());

                    contIng = 0;
                } else {
                    break;
                }

            } else {
                break;
            }
            c.close();

            cont++;
            arrStepPrep.add(stepPrep);
        }

        return arrStepPrep;

    }

    public ArrayList<IngRow> getIngredients(int id) {
        int cont = 0;
        IngRow ing;
        ArrayList<IngRow> arrIng = new ArrayList<>();

        String query = "SELECT i.id, i.nome, c.quantita, c.unita_misura " +
                "FROM Ingrediente i " +
                "JOIN Composizione c ON i.id=c.fk_ingrediente " +
                "WHERE c.fk_cocktail=" + id + " " +
                "ORDER BY c.unita_misura DESC, c.quantita DESC";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    ing = new IngRow();

                    ing.setIdIng(c.getInt(0));
                    if (c.getString(2) != null)
                        ing.setQuantita(c.getString(2));
                    else
                        ing.setQuantita("");
                    ing.setUnita_misura(c.getString(3));
                    ing.setIngName(c.getString(1));

                    arrIng.add(cont, ing);
                    cont++;
                } while (c.moveToNext());

            }

        }
        if (c != null) {
            c.close();
        }

        return arrIng;

    }

    public void OnUpdateInvertPreferito(int id, boolean chk) {

        int n = 0;
        if (!chk)
            n = 1;

        String query = "UPDATE Cocktail SET preferito=" + n + " WHERE id=" + id;

        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL(query);

    }

    public Strumento getInstrument(int id) {
        //query per tutti i dati di un ingrediente specifico
        String query = "SELECT s.nome, s.img, s.descrizione, s.capacita " +
                "FROM Strumento s " +
                "WHERE s.id=" + id;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        Strumento str = new Strumento();

        if (c != null) {
            if (c.moveToFirst()) {
                str.setId(id);
                str.setNome(c.getString(0));
                str.setImg(c.getInt(1));
                str.setDescrizione(c.getString(2));
                str.setCapacita(c.getString(3));
            }
            c.close();

        }

        return str;
    }

    public ArrayList<Strumento> getInstruments(int id) {
        int cont = 0;
        Strumento strum;
        ArrayList<Strumento> arrStrum = new ArrayList<>();

        String query = "SELECT s.id, s.nome " +
                "FROM Strumento s " +
                "JOIN Procurare p ON s.id=p.fk_strumento " +
                "WHERE p.fk_cocktail=" + id + " " +
                "ORDER BY s.nome";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    //inizializzo l'oggetto Strumento ed inserisco i dati al suo interno
                    strum = new Strumento(
                            c.getInt(0),
                            c.getString(1)
                    );

                    //aggiungo l'oggetto al ArrayList
                    arrStrum.add(cont, strum);
                    cont++;
                } while (c.moveToNext());

            }

        }
        if (c != null) {
            c.close();
        }

        return arrStrum;

    }

    public ArrayList<TipoCocktail> getType() {
        int cont = 0;
        TipoCocktail tipe;
        ArrayList<TipoCocktail> arrTipe = new ArrayList<>();

        String query = "SELECT t.id, t.nome, t.img " +
                "FROM TipoCocktail t";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    //inizializzo l'oggetto StrumRow
                    tipe = new TipoCocktail();
                    //inserisco i dati al suo interno
                    tipe.setId(c.getInt(0));
                    tipe.setNome(c.getString(1));
                    tipe.setImg(c.getInt(2));
                    //aggiungo l'oggetto al ArrayList
                    arrTipe.add(cont, tipe);
                    cont++;
                } while (c.moveToNext());

            }

        }
        if (c != null) {
            c.close();
        }

        return arrTipe;
    }

    public TipoCocktail getType(int id) {

        TipoCocktail tipe = new TipoCocktail();

        String query = "SELECT t.id, t.nome, t.img " +
                "FROM TipoCocktail t " +
                "WHERE t.id=" + id;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {
                //inserisco i dati al suo interno
                tipe.setId(c.getInt(0));
                tipe.setNome(c.getString(1));
                tipe.setImg(c.getInt(2));
            }

        }
        if (c != null) {
            c.close();
        }

        return tipe;
    }

    public ArrayList<GradeCocktail> getGrade() {
        int cont = 0;
        GradeCocktail grade;
        ArrayList<GradeCocktail> arrGrade = new ArrayList<>();

        String query = "SELECT g.id, g.gradazione " +
                "FROM GradoAlcolico g " +
                "ORDER BY g.id";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    //inizializzo l'oggetto StrumRow
                    grade = new GradeCocktail();
                    //inserisco i dati al suo interno
                    grade.setId(c.getInt(0));
                    grade.setNome(c.getString(1));

                    //aggiungo l'oggetto al ArrayList
                    arrGrade.add(cont, grade);
                    cont++;
                } while (c.moveToNext());

            }

        }
        if (c != null) {
            c.close();
        }

        return arrGrade;
    }

    public GradeCocktail getGrade(int id) {

        GradeCocktail grd = new GradeCocktail();

        String query = "SELECT g.id, g.gradazione " +
                "FROM GradoAlcolico g " +
                "WHERE g.id=" + id;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {
                //inserisco i dati al suo interno
                grd.setId(c.getInt(0));
                grd.setNome(c.getString(1));
            }

        }
        if (c != null) {
            c.close();
        }

        return grd;
    }

    public ArrayList<CocktailElem> getCocktail(int config, int type) {
        int cont = 0;
        CocktailElem elem;
        ArrayList<CocktailElem> arrElem = new ArrayList<>();

        String query = "SELECT  c.id, c.nome, c.img, g.gradazione " +
                "FROM Cocktail c " +
                "LEFT JOIN GradoAlcolico g ON g.id=c.fk_gradoAlcolico ";

        /* [0] --> tipo cocktail
           [1] --> grado cocktail
           [2] --> origine */
        if (config == 0)
            query += "WHERE c.fk_tipo=" + type + " ";
        if (config == 1)
            query += "WHERE c.fk_gradoAlcolico=" + type + " ";
        if (config == 2)
            query += "WHERE c.fk_origine=" + type + " ";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    elem = new CocktailElem(c.getInt(0),
                            c.getString(1),
                            c.getInt(2),
                            c.getString(3));

                    arrElem.add(cont, elem);
                    cont++;
                } while (c.moveToNext());

            }

        }
        if (c != null) {
            c.close();
        }

        return arrElem;

    }

    public ArrayList<OriginCocktail> getOrigin() {
        int cont = 0;
        OriginCocktail origin;
        ArrayList<OriginCocktail> arrOrigin = new ArrayList<>();

        String query = "SELECT o.id, o.nazione, o.img " +
                "FROM Origine o " +
                "JOIN Cocktail c ON o.id=c.fk_origine " +
                "GROUP BY o.id " +
                "HAVING COUNT(*) > 0 " +
                "ORDER BY COUNT(*) DESC " +
                "LIMIT 5";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    //inizializzo l'oggetto origin
                    origin = new OriginCocktail();
                    //inserisco i dati al suo interno
                    origin.setId(c.getInt(0));
                    origin.setNazione(c.getString(1));
                    origin.setImg(c.getInt(2));

                    //aggiungo l'oggetto al ArrayList
                    arrOrigin.add(cont, origin);
                    cont++;
                } while (c.moveToNext());

            }

        }
        if (c != null) {
            c.close();
        }

        return arrOrigin;
    }

    public OriginCocktail getOrigin(int id) {

        OriginCocktail orig = new OriginCocktail();

        String query = "SELECT o.id, o.nazione, o.img " +
                "FROM Origine o " +
                "WHERE o.id=" + id;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null) {

            if (c.moveToFirst()) {
                //inserisco i dati al suo interno
                orig.setId(c.getInt(0));
                orig.setNazione(c.getString(1));
                orig.setImg(c.getInt(2));
            }

        }
        if (c != null) {
            c.close();
        }

        return orig;
    }

    //query per sezione My_Cocktail
    public boolean updateNewMyCkt() {
        //TODO: implement method
        return false;
    }

    public ArrayList<SpinnerElem> getInstruments() {
        int cont = 0;
        String query = "SELECT id,nome,img FROM Strumento ORDER BY nome";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        SpinnerElem str;
        ArrayList<SpinnerElem> elems = new ArrayList<>();

        if (c != null) {
            while (c.moveToNext()) {
                str = new SpinnerElem(
                        c.getInt(0),
                        c.getString(1),
                        c.getInt(2)
                );

                elems.add(cont, str);
                cont++;
            }
            c.close();
        }

        return elems;
    }

    public ArrayList<SpinnerElem> getIngredients() {
        int cont = 0;
        String query = "SELECT id,nome,img FROM Ingrediente ORDER BY nome";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        SpinnerElem ing;
        ArrayList<SpinnerElem> elems = new ArrayList<>();

        if (c != null) {
            while (c.moveToNext()) {
                ing = new SpinnerElem(
                        c.getInt(0),
                        c.getString(1),
                        c.getInt(2)
                );

                elems.add(cont, ing);
                cont++;
            }
            c.close();
        }

        return elems;
    }

    public ArrayList<GradeCocktail> getGrades() {
        int cont = 0;
        String query = "SELECT id,gradazione FROM GradoAlcolico ORDER BY id";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        GradeCocktail grade;
        ArrayList<GradeCocktail> elems = new ArrayList<>();

        if (c != null) {
            while (c.moveToNext()) {
                grade = new GradeCocktail(
                        c.getInt(0),
                        c.getString(1)
                );

                elems.add(cont, grade);
                cont++;
            }
            c.close();
        }

        return elems;
    }

}


