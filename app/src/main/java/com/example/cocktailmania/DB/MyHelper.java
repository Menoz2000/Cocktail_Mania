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

        /*String comando = "CREATE TABLE Cocktail (" +
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
                ");";
        db.execSQL(comando);*/


        String comando = "CREATE TABLE Ingrediente (" +
                "    id integer PRIMARY KEY," +
                "    nome text NOT NULL," +
                "    img integer NOT NULL," +
                "    sottotitolo text," +
                "    descrizione text," +
                "    fk_origine integer," +
                "    grado_alcolico real NOT NULL," +
                "FOREIGN KEY (fk_origine) REFERENCES Origine(id)" +
                ");";
        db.execSQL(comando);


        /*comando = "CREATE TABLE GradoAlcolico (" +
                "id integer PRIMARY KEY," +
                "gradazione text NOT NULL" +
                ");";
        db.execSQL(comando);


        comando = "CREATE TABLE Composizione (" +
                "fk_cocktail integer," +
                "fk_ingrediente integer," +
                "quantita," +
                "PRIMARY KEY(fk_cocktail, fk_ingrediente)," +
                "FOREIGN KEY (fk_cocktail) REFERENCES Cocktail(id)," +
                "FOREIGN KEY (fk_ingrediente) REFERENCES Ingrediente(id)" +
                ");";
        db.execSQL(comando);


        comando = "CREATE TABLE Procurare (" +
                "fk_cocktail integer," +
                "fk_strumento integer," +
                "PRIMARY KEY (fk_cocktail, fk_strumento)," +
                "FOREIGN KEY (fk_cocktail) REFERENCES Cocktail (id)," +
                "FOREIGN KEY (fk_strumento) REFERENCES Strumento(id)" +
                ");";
        db.execSQL(comando);


        */
        comando = "CREATE TABLE Origine (" +
                "id integer PRIMARY KEY," +
                "nazione text NOT NULL" +
                ");";
        db.execSQL(comando);
        /*


        comando = "CREATE TABLE Strumento (" +
                "id integer PRIMARY KEY," +
                "nome text NOT NULL," +
                "descrizione text," +
                "capacita text," +
                "img integer NOT NULL" +
                ");";
        db.execSQL(comando);


        comando = "CREATE TABLE TipoCocktail (" +
                "id integer PRIMARY KEY," +
                "nome text NOT NULL" +
                ")";
        db.execSQL(comando);*/

        inizializza(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void inizializza(SQLiteDatabase db) {
        String insert = "INSERT INTO Ingrediente (id, nome, grado_alcolico, fk_origine, img)" +
                "VALUES (12, 'Beefeater Gin', 40, 5, " + R.drawable.ing_12 + ")," +
                "(42, 'Irish Whiskey', 40, 19, " + R.drawable.ing_42 + ")," +
                "(64, 'Plymouth Gin', 41.2, 5, " + R.drawable.ing_64 + ")," +
                "(90, 'Tanqueray Gin', 47.3, 5, " + R.drawable.ing_90 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Ingrediente (id, nome, grado_alcolico, descrizione, sottotitolo, fk_origine, img)" +
                "VALUES (4, 'Angostura', 44.7, 'L''amaro Angostura Aromatic è il marchio più famoso e più usato tra gli amari alle erbe. L''amaro è stato sviluppato da un chirurgo tedesco nell''esercito di Simon Bolivar in Venezuela per curare i disturbi di stomaco e l''indigestione. Da allora è diventato un ingrediente chiave in molti cocktail.', 'Aroma per cocktail', 12, " + R.drawable.ing_4 + ")," +
                "(13, 'Blue Curacao', 40, 'Simile al Triple sec Curacao, è un liquore all''arancia ma prodotto con bucce essiccate di Laraha, l''arancia amara originaria dell''isola di Curacao. Il Curacao ha un sapore più sottile rispetto al Triple sec, e nello stesso tempo il sapore di Curacao è più complesso per via delle erbe e delle spezie aggiuntive.', 'Liquore al sapore d''arancia', 10, " + R.drawable.ing_13 + ")," +
                "(15, 'Cachaca', 40, 'La Cachaca è il liquore nazionale del Brasile. Fuori dal Brasile, è usato quasi esclusivamente come ingrediente per drink miscelati, come la Caipirinha. La cachaca è un liquore a base di canna da zucchero (prodotta usando il succo pressato della canna da zucchero).', 'Acquavite distillata da succo di canna da zucchero fermentato', 17, " + R.drawable.ing_15 + ")," +
                "(20, 'Cointreau', 40, 'Il Cointreau è un marchio di triple sec (un liquore all''arancia) prodotto a Saint-Barthélemy-d''Anjou, in Francia. Si beve come aperitivo e digestivo ed è un ingrediente di molti noti cocktail.', 'Liquore aromatizzato all''arancia', 2, " + R.drawable.ing_20 + ")," +
                "(55, 'Old Tom Gin', 40, 'Cugino storico di London Dry che vanta un profilo aromatico simile al ginepro, ma con un corpo più ricco e un sapore più dolce. Tradizionalmente, il gin Old Tom veniva addolcito con zucchero e gli venivano aggiunti ingredienti botanici.', 'Categoria di gin leggermente più dolci', 5, " + R.drawable.ing_55 + ")," +
                "(57, 'Orange Curacao', 40, 'Simile al Triple sec Curacao, è un liquore all''arancia ma prodotto con bucce essiccate di Laraha, l''arancia amara originaria dell''isola di Curacao. Il Curacao ha un sapore più sottile rispetto al Triple sec, e nello stesso tempo il sapore di Curacao è più complesso per via delle erbe e delle spezie aggiuntive.', 'Liquore al sapore d''arancia', 10, " + R.drawable.ing_57 + ")," +
                "(153, 'Rum bianco', 40, 'Che siano composti con melassa o succo di canna da zucchero, rum bianchi, coi sapori più leggere dei rum d''oro, sono un''ottima base per i cocktail. Molti contengono più del 40% di alcol e sono tenuti in contenitori in acciaio inox o botti per diverse settimane per ottenere aromi più morbidi.', 'Conosciuto anche come rum leggero', 9, " + R.drawable.ing_153 + ")," +
                "(156, 'Rum scuro', 40, 'I rum scuri sono invecchiati per due anni o più in botti di rovere e rientrano nella categoria dei rum da gustare puri.', 'Conosciuto anche come Rum marrone o nero', 9, " + R.drawable.ing_156 + ")," +
                "(174, 'Tequila', 40, 'La tequila è prodotta da una sola specie di agave, l''Agave Azul Tequilana Weber. Le agave cotte vengono macinate, fermentate e distillate in vaso - o in colonna - almeno due volte.', 'Acquavite messicana distillata dall''agave blu', 3, " + R.drawable.ing_174 + ")," +
                "(180, 'Vodka', 40, 'La parola vodka è un''abbreviazione della parola slava voda (che significa acqua), che è da interpretare come ''poca acqua''. Oltre ai cereali e alle patate, la vodka può essere prodotta con una vasta gamma di materiali naturali come orzo, uva, linfa degli alberi o mais.', 'Acquavite prodotta distillando principalmente cereali o patate', 13, " + R.drawable.ing_180 + ");";
        db.execSQL(insert);

        insert="INSERT INTO Ingrediente (id, nome, grado_alcolico, sottotitolo, fk_origine, img)" +
                "VALUES (48, 'London Dry Gin', 45, 'Categoria del gin secchi', 5, "+R.drawable.ing_48+");";
        db.execSQL(insert);

        insert="INSERT INTO Ingrediente (id, nome, grado_alcolico, img)" +
                "VALUES (71, 'Salsa Worcestershire', 0, "+R.drawable.ing_71+")," +
                "(77, 'Sciroppo di frutto della passione', 0, "+R.drawable.ing_77+")," +
                "(110, 'Caffé', 0, "+R.drawable.ing_110+")," +
                "(125, 'Ginger', 0, "+R.drawable.ing_125+")," +
                "(138, 'Liquore di pesca', 20, "+R.drawable.ing_138+")," +
                "(147, 'Pepe', 0, "+R.drawable.ing_147+")," +
                "(157, 'Sale', 0, "+R.drawable.ing_157+")," +
                "(158, 'Salsa tabasco', 0, "+R.drawable.ing_158+")," +
                "(169, 'Succo di mirtillo rosso', 0, "+R.drawable.ing_169+")," +
                "(171, 'Succo di pomodoro', 0, "+R.drawable.ing_171+")," +
                "(184, 'Zucchero', 0, "+R.drawable.ing_184+")," +
                "(186, 'Zucchero marrone', 0, "+R.drawable.ing_186+");";
        db.execSQL(insert);

        insert="INSERT INTO Ingrediente (id, nome, grado_alcolico, descrizione, sottotitolo, img)" +
                "VALUES (73, 'Sciroppo Semplice', 0, 'Combina parti uguali di zucchero superfino e acqua calda in un contenitore. Agita o mescola fino a quando lo zucchero si sarà sciolto.', 'Sciroppo creato sciogliendo zucchero granulato in acqua', "+R.drawable.ing_73+")," +
                "(120, 'Foglia di menta', 0, 'La menta è una pianta aromatica usata per i cocktail, come guarnizione o ingrediente mischiato nei drink per aggiungere un aroma fresco.', 'Usa foglie giovani di menta dopo averle lavate', "+R.drawable.ing_120+")," +
                "(126, 'Granatina', 0, 'La granatina è un ingrediente popolare nei cocktail sia per il suo sapore che per il colore che dona una sfumatura rossastra ai drink mixati.', 'Sciroppo agrodolce non alcolico dal colore rosso profondo', "+R.drawable.ing_126+")," +
                "(163, 'Succo d''arancia', 0, 'Il succo fresco spremuto muta nel tempo, a differenza degli altri agrumi. Il succo d''arancia ha un invecchiamento ottimale molto breve, deve essere usato nella prima ora in cui è stato spremuto fresco.', '1 arancia produce circa 90 ml di succo', "+R.drawable.ing_163+");";
        db.execSQL(insert);

        insert="INSERT INTO Ingrediente (id, nome, grado_alcolico, sottotitolo, img)" +
                "VALUES (101, 'Acqua di seltz', 0, 'Acqua gassata con minerali aggiunti', "+R.drawable.ing_101+")," +
                "(122, 'Ghiaccio', 0, 'Anima di un cocktail', "+R.drawable.ing_122+")," +
                "(146, 'Panna montata', 0, 'Anche conosciuta come cream Chantilly', "+R.drawable.ing_146+")," +
                "(166, 'Succo di lime', 0, '1 lime produce circa 30 ml di succo', "+R.drawable.ing_166+")," +
                "(167, 'Succo di limone', 0, '1 limone produce circa 45 ml di succo', "+R.drawable.ing_167+")," +
                "(175, 'Tonic', 0, 'Acqua tonica', "+R.drawable.ing_175+");";
        db.execSQL(insert);

        insert="INSERT INTO Origine (id, nazione)" +
                "VALUES (1, 'Italia')," +
                "(2, 'Francia')," +
                "(3, 'Messico')," +
                "(4, 'Stati Uniti')," +
                "(5, 'Regno Unito')," +
                "(6, 'Barbados')," +
                "(7, 'Perù')," +
                "(8, 'Giappone')," +
                "(9, 'In tutto il mondo')," +
                "(10, 'Olanda')," +
                "(11, 'Portogallo')," +
                "(12, 'Trinidad e Tobago')," +
                "(13, 'Russia')," +
                "(14, 'Giamaica')," +
                "(15, 'Porto Rico')," +
                "(16, 'Caraibico')," +
                "(17, 'Brasile')," +
                "(18, 'Danimarca')," +
                "(19, 'Irlanda')," +
                "(20, 'Guyana')," +
                "(21, 'Scozia')," +
                "(22,'Bermuda')," +
                "(23, 'Spagna')," +
                "(24, 'Israele')," +
                "(25, 'Cuba')," +
                "(26, 'Belgio');";
        db.execSQL(insert);
    }
}