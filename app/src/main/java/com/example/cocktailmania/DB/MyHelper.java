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

        String comando = "CREATE TABLE Cocktail (" +
                "id integer primary key AUTOINCREMENT," +
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
        db.execSQL(comando);


        comando = "CREATE TABLE Ingrediente (" +
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


        comando = "CREATE TABLE GradoAlcolico (" +
                "id integer PRIMARY KEY," +
                "gradazione text NOT NULL" +
                ");";
        db.execSQL(comando);


        comando = "CREATE TABLE Composizione (" +
                "fk_cocktail integer," +
                "fk_ingrediente integer," +
                "quantita real," +
                "unita_misura text," +
                "PRIMARY KEY(fk_cocktail, fk_ingrediente)," +
                "FOREIGN KEY (fk_cocktail) REFERENCES Cocktail(id)," +
                "FOREIGN KEY (fk_ingrediente) REFERENCES Ingrediente(id)" +
                ");";
        db.execSQL(comando);


         /*comando = "CREATE TABLE Procurare (" +
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

        comando = "CREATE TABLE TipoCocktail (" +
                "    id integer PRIMARY KEY," +
                "    nome text NOT NULL" +
                ");";
        db.execSQL(comando);

        comando = "CREATE TABLE Azione (" +
                "    id integer PRIMARY KEY," +
                "    nome text NOT NULL," +
                "    img integer default " + R.drawable.ing_131 + "/*NOT NULL*/" +
                ");";
        db.execSQL(comando);

        comando = "CREATE TABLE Strumento (" +
                "id integer PRIMARY KEY," +
                "nome text NOT NULL," +
                "descrizione text," +
                "capacita text," +
                "img integer NOT NULL" +
                ");";
        db.execSQL(comando);

        comando = "CREATE TABLE Preparazione (" +
                "    id integer PRIMARY KEY AUTOINCREMENT," +
                "    fk_cocktail integer NOT NULL," +
                "    fk_ingrediente integer," +
                "    step integer NOT NULL ," +
                "    fk_strumento integer," +
                "    fk_azione integer," +
                "    FOREIGN KEY (fk_cocktail) REFERENCES Cocktail(id)," +
                "    FOREIGN KEY (fk_ingrediente) REFERENCES Ingrediente(id)," +
                "    FOREIGN KEY (fk_strumento) REFERENCES Strumento(id)," +
                "    FOREIGN KEY (fk_azione) REFERENCES Azione(id)" +
                ");";
        db.execSQL(comando);

        inizializza(db);

        //db.close();
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
                "(123, 'Gin', 40, 'Il gin è prodotto ridistillando un liquore neutro ad alta resistenza con prodotti botanici ed include sempre il ginepro, ma può anche includere buccia d''arancia scorza di limone, anice, coriandolo, cacao e molti altri.', 'Acquavite prodotta ridistillando liquore neutro con sostanze vegetali (prevalentemente ginepro)', 9, " + R.drawable.ing_123 + ")," +
                "(153, 'Rum bianco', 40, 'Che siano composti con melassa o succo di canna da zucchero, rum bianchi, coi sapori più leggere dei rum d''oro, sono un''ottima base per i cocktail. Molti contengono più del 40% di alcol e sono tenuti in contenitori in acciaio inox o botti per diverse settimane per ottenere aromi più morbidi.', 'Conosciuto anche come rum leggero', 9, " + R.drawable.ing_153 + ")," +
                "(156, 'Rum scuro', 40, 'I rum scuri sono invecchiati per due anni o più in botti di rovere e rientrano nella categoria dei rum da gustare puri.', 'Conosciuto anche come Rum marrone o nero', 9, " + R.drawable.ing_156 + ")," +
                "(174, 'Tequila', 40, 'La tequila è prodotta da una sola specie di agave, l''Agave Azul Tequilana Weber. Le agave cotte vengono macinate, fermentate e distillate in vaso - o in colonna - almeno due volte.', 'Acquavite messicana distillata dall''agave blu', 3, " + R.drawable.ing_174 + ")," +
                "(180, 'Vodka', 40, 'La parola vodka è un''abbreviazione della parola slava voda (che significa acqua), che è da interpretare come ''poca acqua''. Oltre ai cereali e alle patate, la vodka può essere prodotta con una vasta gamma di materiali naturali come orzo, uva, linfa degli alberi o mais.', 'Acquavite prodotta distillando principalmente cereali o patate', 13, " + R.drawable.ing_180 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Ingrediente (id, nome, grado_alcolico, sottotitolo, fk_origine, img)" +
                "VALUES (48, 'London Dry Gin', 45, 'Categoria del gin secchi', 5, " + R.drawable.ing_48 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Ingrediente (id, nome, grado_alcolico, img)" +
                "VALUES (71, 'Salsa Worcestershire', 0, " + R.drawable.ing_71 + ")," +
                "(77, 'Sciroppo di frutto della passione', 0, " + R.drawable.ing_77 + ")," +
                "(80, 'Sedano', 0, " + R.drawable.ing_80 + ")," +
                "(110, 'Caffé', 0, " + R.drawable.ing_110 + ")," +
                "(125, 'Ginger', 0, " + R.drawable.ing_125 + ")," +
                "(130, 'Lime', 0, " + R.drawable.ing_130 + ")," +
                "(131, 'Limone', 0, " + R.drawable.ing_131 + ")," +
                "(138, 'Liquore di pesca', 20, " + R.drawable.ing_138 + ")," +
                "(147, 'Pepe', 0, " + R.drawable.ing_147 + ")," +
                "(157, 'Sale', 0, " + R.drawable.ing_157 + ")," +
                "(158, 'Salsa tabasco', 0, " + R.drawable.ing_158 + ")," +
                "(169, 'Succo di mirtillo rosso', 0, " + R.drawable.ing_169 + ")," +
                "(171, 'Succo di pomodoro', 0, " + R.drawable.ing_171 + ")," +
                "(184, 'Zucchero', 0, " + R.drawable.ing_184 + ")," +
                "(186, 'Zucchero marrone', 0, " + R.drawable.ing_186 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Ingrediente (id, nome, grado_alcolico, descrizione, sottotitolo, img)" +
                "VALUES (73, 'Sciroppo Semplice', 0, 'Combina parti uguali di zucchero superfino e acqua calda in un contenitore. Agita o mescola fino a quando lo zucchero si sarà sciolto.', 'Sciroppo creato sciogliendo zucchero granulato in acqua', " + R.drawable.ing_73 + ")," +
                "(120, 'Foglia di menta', 0, 'La menta è una pianta aromatica usata per i cocktail, come guarnizione o ingrediente mischiato nei drink per aggiungere un aroma fresco.', 'Usa foglie giovani di menta dopo averle lavate', " + R.drawable.ing_120 + ")," +
                "(126, 'Granatina', 0, 'La granatina è un ingrediente popolare nei cocktail sia per il suo sapore che per il colore che dona una sfumatura rossastra ai drink mixati.', 'Sciroppo agrodolce non alcolico dal colore rosso profondo', " + R.drawable.ing_126 + ")," +
                "(163, 'Succo d''arancia', 0, 'Il succo fresco spremuto muta nel tempo, a differenza degli altri agrumi. Il succo d''arancia ha un invecchiamento ottimale molto breve, deve essere usato nella prima ora in cui è stato spremuto fresco.', '1 arancia produce circa 90 ml di succo', " + R.drawable.ing_163 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Ingrediente (id, nome, grado_alcolico, sottotitolo, img)" +
                "VALUES (101, 'Acqua di seltz', 0, 'Acqua gassata con minerali aggiunti', " + R.drawable.ing_101 + ")," +
                "(122, 'Ghiaccio', 0, 'Anima di un cocktail', " + R.drawable.ing_122 + ")," +
                "(146, 'Panna montata', 0, 'Anche conosciuta come cream Chantilly', " + R.drawable.ing_146 + ")," +
                "(166, 'Succo di lime', 0, '1 lime produce circa 30 ml di succo', " + R.drawable.ing_166 + ")," +
                "(167, 'Succo di limone', 0, '1 limone produce circa 45 ml di succo', " + R.drawable.ing_167 + ")," +
                "(175, 'Tonic', 0, 'Acqua tonica', " + R.drawable.ing_175 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Origine (id, nazione)" +
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

        insert = "INSERT INTO Cocktail (id, nome, fk_gradoAlcolico, img)" +
                "VALUES (2, '4th of July', 2, " + R.drawable.ckt_2 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Cocktail (id, nome, fk_gradoAlcolico, fk_origine, fk_tipo, iconico, img)" +
                "VALUES (37, 'Bloody Mary', 5, 4, 4, 1, " + R.drawable.ckt_37 + ")," +
                "(46, 'Caipirinha', 1, 17, 3, 1, " + R.drawable.ckt_46 + ")," +
                "(71, 'Irish Coffee', 5, 19, 2, 1, " + R.drawable.ckt_71 + ")," +
                "(81, 'Margarita', 2, 3, 5, 1, " + R.drawable.ckt_81 + ")," +
                "(83, 'Mojito', 5, 25, 4, 1, " + R.drawable.ckt_83 + ")," +
                "(85, 'Moscow Mule', 5, 4, 4, 1, " + R.drawable.ckt_85 + ");";
        db.execSQL(insert);


        insert = "INSERT INTO Cocktail (id, nome, fk_gradoAlcolico, fk_origine, fk_tipo, img)" +
                "VALUES (65, 'Gin Fizz', 5, 5, 1, " + R.drawable.ckt_65 + ")," +
                "(94, 'Sex on the Beach', 3, 4, 4, " + R.drawable.ckt_94 + ")," +
                "(104, 'Zombie', 2, 4, 5, " + R.drawable.ckt_104 + ");";
        db.execSQL(insert);


        insert = "INSERT INTO Cocktail (id, nome, fk_gradoAlcolico, fk_tipo, img)" +
                "VALUES (66, 'Gin and Tonic', 5, 4, " + R.drawable.ckt_66 + ");";
        db.execSQL(insert);


        insert = "INSERT INTO GradoAlcolico (id, gradazione)" +
                "VALUES (1, 'Estremamente Forte')," +
                "(2, 'Forte')," +
                "(3, 'Mediamente Forte')," +
                "(4, 'Debole')," +
                "(5, 'Leggero')," +
                "(6, 'Analcolico');";
        db.execSQL(insert);

        insert = "INSERT INTO TipoCocktail (id, nome)" +
                "VALUES (1, 'Classici')," +
                "(2, 'Cremoso')," +
                "(3, 'Corto')," +
                "(4, 'Longdrink')," +
                "(5, 'Tropicale')," +
                "(6, 'Frizzante')," +
                "(7, 'Martini');";
        db.execSQL(insert);


        insert = "INSERT INTO Azione (id, nome)" +
                "VALUES (1, 'Aggiungi')," +
                "(2, 'Agita Bene')," +
                "(3, 'Filtra')," +
                "(4, 'Frulla')," +
                "(5, 'Guarnisci')," +
                "(6, 'Lascia Cadere')," +
                "(7, 'Mescola')," +
                "(8, 'Mescola Bene')," +
                "(9, 'Pesta')," +
                "(10, 'Riempi')," +
                "(11, 'Spruzza')," +
                "(12, 'Versa');";
        db.execSQL(insert);

        insert = "INSERT INTO Strumento (id, nome, descrizione, capacita, img)\n" +
                "VALUES (3, 'Bicchiere Da Irish Coffee', 'Il bicchiere da Irish coffee è un bicchiere molto resistente e adatto a sopportare alte temperature. Puoi usarlo infatti per servire i classici hot drink come appunto l''Irish coffee.', '130 ml', " + R.drawable.strum_3 + ")," +
                "(5, 'Bicchiere Da Margarita', 'Come dice il suo nome, questo bicchiere è stato inventato appositamente per il drink da cui prende il nome anche se puoi utilizzarlo per servire un''ampia gamma di frozen drink.', '330 ml', " + R.drawable.strum_5 + ")," +
                "(9, 'Bicchiere Da Sherry', 'Questo bicchierino dotato di stelo e dalla capacità limitata è l''ideale se vuoi sorseggiare uno Sherry oppure se vuoi sstupire tutti preparando il famoso Brain Hemorrhage, uno scenografico cocktail.', '90 ml', " + R.drawable.strum_9 + ")," +
                "(19, 'Doppio Old Fashion', 'Il doppio old fashion fa parte della categoria de Tumbler e viene chiamato anche doppio on-the-rocks perché potrai utilizzarlo per gustare drink a base di liquori con molto ghiaccio.', '350-440 ml', " + R.drawable.strum_19 + ")," +
                "(22, 'Grande Collins', 'Questo bicchiere fa parte della categoria dei Tumbler (viene anche chiamato Tumbler alto) e ti servirà per gustare non solo i collins, ma anche long drink alla frutta o analcolici.', '330-410 ml', " + R.drawable.strum_22 + ")," +
                "(25, 'Hurricane', 'Questo bicchiere elegante e sinuoso prende il nome dal famoso drink di New Orleans. Potrai sfoggiarlo per il servizio dell''Hurricane e di alcuni cocktails analcolici.', '250-440 ml', " + R.drawable.strum_25 + ")," +
                "(10, 'Bicchiere Da Shot', 'Questo bicchierino è originariamente progettato per contenere o misurare liquori, che viene assorbito direttamente dal bicchiere o versato in un cocktail. Una bevanda alcolica servita in questo tipo di bicchiere è in genere consumata velocemente, in uno sorso.', '30 ml', " + R.drawable.strum_10 + ")," +
                "(31, 'Old Fashion', 'L''Old fashion fa parte della categoria dei Tumbler (viene chiamato anche Tumbler basso). In pratica è il classico bicchiere on-the-rocks, nel quale potrai servire, manco a dirlo, l''Old Fashion.', '180-300 ml', " + R.drawable.strum_31 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Strumento (id, nome, descrizione, img)" +
                "VALUES (30, 'Muddler', 'Ne esistono di varie forme, materiali e misure. Ti servirà per preparare cocktrail pestati.', " + R.drawable.strum_30 + ")," +
                "(33, 'Passino Julep', 'Si tratta di un colino più praitco dell''Hawthorne. Normalmente è fatto di acciaio e potrai adoperarlo con lo shaker Boston o il mixing glass. Anche questo serve per fermare il ghiaccio al momento di versare i cocktail nei bicchieri o per evitare che cadano i residui dei frutti utilizzati in un cocktail.', " + R.drawable.strum_33 + ")," +
                "(36, 'Shaker Boston', 'Questo recipiente è costituito da due parti, una inferiore di metallo ed una superiore di vetro in cui vengono versati gli ingredienti. Una volta unite le due parti devi agitare facendo finire il cocktail che hai preparato nel bicchiere dalla parte metallica.', " + R.drawable.strum_36 + ")," +
                "(37, 'Shaker Continentale', 'Lo shaker continentale è composto da tre parti. Solitamente è realizato tutto in acciaio ed è costituito da un bicchiere inferiore dove dovrai versare gli ingredienti, una prima parte superiore costituita da un tappo-colino ed un tappo superiore che ti servirà per sigillare il tutto.', " + R.drawable.strum_37 + ")," +
                "(39, 'Stir', 'È un cucchiaino lungo che ti tornerà utile in diverse occasioni come ad esempio mescolare i cocktails che non vanno shakerati (ha anche una spirale intorno alla lunghezza del manico, per facilitare la mescolatura), versare lo zucchero, pestare lo zucchero o altri ingredienti in un cocktail utilizzando il Muddler (il pestello) che si trova dalla parte opposta al cucchiaio e mescolare la base pestata in un cocktail.', " + R.drawable.strum_39 + ");";
        db.execSQL(insert);

        insert = "INSERT INTO Preparazione (fk_cocktail, fk_ingrediente, fk_strumento, step, fk_azione)" +
                "VALUES (2, 126, 10, 1, 12)," +
                "(2, 180, 10, 2, 12)," +
                "(2, 13, 10, 3, 12)," +
                "(37, 122, 22, 1, 10)," +
                "(37, 180, 22, 2, 12)," +
                "(37, 167, 22, 2, 12)," +
                "(37, 157, 22, 3, 1)," +
                "(37, 147, 22, 3, 1)," +
                "(37, 71, 22, 3, 1)," +
                "(37, 158, 22, 3, 1)," +
                "(37, 171, 22, 4, 10)," +
                "(37, 131, 22, 6, 5)," +
                "(37, 80, 22, 6, 5);";
        db.execSQL(insert);

        insert = "INSERT INTO Preparazione (fk_cocktail, fk_strumento, step, fk_azione)" +
                "VALUES (37, 39, 5, 8);";
        db.execSQL(insert);

        insert="INSERT INTO Composizione (fk_cocktail, fk_ingrediente, quantita, unita_misura)" +
                "VALUES (2, 13, 1, 'parte')," +
                "(2, 126, 1, 'parte')," +
                "(2, 180, 1, 'parte')," +
                "(37, 167, 1, 'parte')," +
                "(37, 171, 6, 'parte')," +
                "(37, 71, 2, 'spruzzo')," +
                "(37, 158, 1, 'spruzzo')," +
                "(37, 147, 1, 'pizzico')," +
                "(37, 157, 1, 'pizzico')," +
                "(37, 180, 3, 'parte')," +
                "(46, 15, 2, 'parte')," +
                "(46, 130, 4, 'pezzo')," +
                "(46, 184, 2, 'cucchi.')," +
                "(65, 123, 4.5, 'parte')," +
                "(65, 167, 3, 'parte')," +
                "(65, 101, 8, 'parte')," +
                "(65, 73, 1, 'parte')," +
                "(66, 123, 1, 'parte')," +
                "(66, 175, 2, 'parte')," +
                "(71, 42, 2, 'parte')," +
                "(71, 186, 1, 'cucchi.')," +
                "(71, 110, 4.5, 'parte')," +
                "(71, 145, 1.5, 'parte')," +
                "(81, 174, 1.75, 'parte')," +
                "(81, 20, 1, 'parte')," +
                "(81, 166, 0.75, 'parte')," +
                "(83, 153, 2, 'parte')," +
                "(83, 184, 2, 'cucchi.')," +
                "(83, 166, 1.5, 'parte')," +
                "(83, 101, 3, 'parte')," +
                "(83, 120, 6, 'pezzo')," +
                "(85, 166, 0.25, 'parte')," +
                "(85, 125, 6, 'parte')," +
                "(85, 180, 2.25, 'parte')," +
                "(94, 180, 2, 'parte')," +
                "(94, 138, 1, 'parte')," +
                "(94, 126, 1, 'spruzzo')," +
                "(94, 163, 2, 'parte')," +
                "(94, 169, 2, 'parte')," +
                "(104, 156, 4, 'parte')," +
                "(104, 153, 4, 'parte')," +
                "(104, 57, 4, 'parte')," +
                "(104, 167, 2, 'parte')," +
                "(104, 166, 2, 'parte')," +
                "(104, 163, 6, 'parte')," +
                "(104, 77, 2, 'parte')," +
                "(104, 126, 1, 'parte')," +
                "(104, 4, 2, 'spruzzo');";
        db.execSQL(insert);

        insert="INSERT INTO Composizione (fk_cocktail, fk_ingrediente)" +
                "VALUES (37, 122)," +
                "(46, 122)," +
                "(65, 122)," +
                "(66, 122)," +
                "(81, 122)," +
                "(83, 122)," +
                "(85, 122)," +
                "(94, 122)," +
                "(104, 122);";
        db.execSQL(insert);
    }
}