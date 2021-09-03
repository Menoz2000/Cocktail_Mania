package com.example.cocktailmania.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.GradeCocktail;
import com.example.cocktailmania.utility.IngRow;
import com.example.cocktailmania.utility.NonScrollListView;
import com.example.cocktailmania.utility.SpinnerElem;
import com.example.cocktailmania.utility.StepPrep;
import com.example.cocktailmania.utility.Strumento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCocktail extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyCocktail";
    private final DbManager db = new DbManager(this);

    Button afterButton;     //
    TextView nomeText;      //textview con il nome del my cocktail
    Button addIngButton;    //bottone per aggiungere un ingrediente al my cocktail
    Button addStrumButton;  //bottone per aggiungere uno strumento al my cocktail
    Spinner spIngrendient;  //spinner con tutti gli ingredienti disponibili
    Spinner spQuantity;     //spinner con le quantità relative agli ingredienti
    Spinner spUnity;        //spinner con le unità di misura relativo agli ingredienti
    Spinner spStrumenti;    //spinner con tutti gli strumenti disponibili
    Spinner spGrade;        //spinner con tutti i gradi alcolici
    NonScrollListView ingListView;  //lista con gli ingredienti aggiunti
    NonScrollListView strumListView;//lista con gli strumenti aggiunti

    ArrayList<SpinnerElem> ingredientList = new ArrayList<>();  //arraylist con tutti gli ingredienti (tramite interrogazione database)
    IngRow ingredient = new IngRow();   //oggetto passadati
    public static ArrayList<IngRow> MyIngredients = new ArrayList<>();    //arraylist statico con gli ingredienti scelti dall'utente

    ArrayList<SpinnerElem> strumList = new ArrayList<>();       //arraylist con tutti gli strumenti (tramite interrogazione database)
    Strumento strum = new Strumento();  //oggetto passadati
    public static ArrayList<Strumento> MyStrums = new ArrayList<>();     //arraylist statico con gli strumenti scelti dall'utente

    public static ArrayList<StepPrep> passaggi = new ArrayList<>();     //arraylist statico con gli step costruiti dall'utente

    SpinnerAdapter spinnerIngAdapter;   //adapter per lo spinner degli ingredienti
    SpinnerAdapter spinnerStrAdapter;   //adapter per lo spinner degli strumenti
    IngredientDeletableAdapter iAdapter;//adapter per gli elementi della lista cancellabile degli ingredienti selezionati dall'utente
    StrumDeletableAdapter sAdapter;     //adapter per gli elementi della lista cancellabile degli strumenti selezionati dall'utente
    SpinnerGradeAdapter spinnerGradeAdapter;    //adapter per lo spinner dei gradi alcolici

    public static String nomeMyCkt; //variabile statica con il nome del my cocktail scelto dall'utente
    public static GradeCocktail gradoAlcolico = new GradeCocktail();    //oggetto statico con il grado alcolico del my cocktail scelto dall'utente

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cocktail);

        //RESETTO GLI ARRAYLIST STATICI
        MyIngredients.clear();
        MyStrums.clear();
        passaggi.clear();
        nomeMyCkt = "";

        ingListView = findViewById(R.id.MyIngList); //ListView con gli ingredienti
        strumListView = findViewById(R.id.MyStrumList); //ListView con gli strumenti

        //nome cocktail
        nomeText = findViewById(R.id.cktName);
        //button addIng
        addIngButton = findViewById(R.id.AddIng);
        addIngButton.setOnClickListener(this);
        //button addStrum
        addStrumButton = findViewById(R.id.AddStrum);
        addStrumButton.setOnClickListener(this);
        //button avanti
        afterButton = findViewById(R.id.AfterButton);
        afterButton.setOnClickListener(this);

        //Ingredient Spinner
        ingredientList = db.getIngredients();   //arraylist con tutti gli ingredienti disponibili (interrogazione a database)
        spIngrendient = findViewById(R.id.IngSpinner);
        spinnerIngAdapter = new SpinnerAdapter(this, ingredientList);
        spIngrendient.setAdapter(spinnerIngAdapter);
        spIngrendient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //prendo i dati dell'ingredienti selezionato
                ingredient.setIdIng(ingredientList.get(position).getId());
                ingredient.setIngName(ingredientList.get(position).getNome());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //creo l'arraylist con tutte le quantità
        List<String> QList = new ArrayList<>(Arrays.asList("", "0.25", "0.50", "0.75", "1", "1.5", "2", "2.5"));
        for (int i = 3; i <= 100; i++)
            QList.add(String.valueOf(i));
        //Quantity Spinner
        spQuantity = findViewById(R.id.QuantitaSpinner);
        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, QList);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(quantityAdapter);
        spQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //salvo la quantità selezionata dall'utente
                ingredient.setQuantita(spQuantity.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //creo arraylist con unità di misura
        List<String> UList = new ArrayList<>(Arrays.asList("", "parte", "spruzzo", "cucchi.", "pezzo", "pizzico"));
        //Unity Spinner
        spUnity = findViewById(R.id.UnitaSpinner);
        ArrayAdapter<String> unityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, UList);
        unityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnity.setAdapter(unityAdapter);
        spUnity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //salvo l'unità di misura selezionata dall'utente
                ingredient.setUnita_misura(spUnity.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Strumenti Spinner
        strumList = db.getInstruments();   //arraylist con tutti gli strumenti disponibili (interrogazione a database)
        spStrumenti = findViewById(R.id.StrumSpinner);
        spinnerStrAdapter = new SpinnerAdapter(this, strumList);
        spStrumenti.setAdapter(spinnerStrAdapter);
        spStrumenti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //salvo i dati dello strumento selezionato
                strum.setId(strumList.get(position).getId());
                strum.setNome(strumList.get(position).getNome());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Grade Spinner
        spGrade = findViewById(R.id.GradeSpinner);
        //creo l'arraylist con tutti i gradi alcolici
        ArrayList<GradeCocktail> gradeList = db.getGrades();   //arraylist con tutti i gradi alcolici (interrogazione a database)
        spinnerGradeAdapter = new SpinnerGradeAdapter(this, gradeList);
        spGrade.setAdapter(spinnerGradeAdapter);
        spGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //salvo il grado alcolico del my cocktail
                gradoAlcolico.setId(gradeList.get(position).getId());
                gradoAlcolico.setNome(gradeList.get(position).getNome());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //gestione dei click sui diversi button presenti nella activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //pulsante GO
            case R.id.AfterButton:
                if (MyIngredients.size() == 0) {
                    //controllo se è stato inserito almeno un ingrediente
                    Toast.makeText(getApplicationContext(), "Inserire ingrediente", Toast.LENGTH_LONG).show();
                    break;
                }
                if (MyStrums.size() == 0) {
                    //controllo se è stato inserito almeno uno strumento
                    Toast.makeText(getApplicationContext(), "Inserire strumento", Toast.LENGTH_LONG).show();
                    break;
                }

                //la funz controllaSeEsiste ci dice se il nome del ckt passato esiste già tra i myCkt
                if (db.controllaSeEsiste(nomeText.getText().toString())) {
                    //nome cocktail già esistente
                    Toast.makeText(getApplicationContext(), "Nome Cocktail già esistente", Toast.LENGTH_LONG).show();

                } else {
                    //controllo se è stato inserito il nome del my cocktail
                    if (nomeText.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Inserire Nome Cocktail", Toast.LENGTH_LONG).show();
                        break;
                    } else {
                        //salvo il nome del my cocktail
                        nomeMyCkt = nomeText.getText().toString();
                        Intent intent = new Intent(this, StepMyCocktail.class);
                        startActivity(intent);
                    }

                }
                break;

            //pulsante AGGIUNGI INGREDIENTE
            case R.id.AddIng:

                //controllo se oggetto passadati contiene valori NULL
                if (ingredient.getIngName() == null) {
                    ingredient.setIngName(ingredientList.get(0).getNome());
                    ingredient.setIdIng(ingredientList.get(0).getId());
                }

                //controllo se aggiungo Unita NULL
                if (ingredient.getUnita_misura() == null)
                    ingredient.setUnita_misura("");

                //controllo se aggiungo Quantita NULL
                if (ingredient.getQuantita() == null)
                    ingredient.setQuantita("");

                //controllo se uno tra i campi Unita di misura e Quantità è NULL
                if (ingredient.getUnita_misura().equals("") ^ ingredient.getQuantita().equals("")) {
                    Toast.makeText(getApplicationContext(), "Aggiungere Quantità e Unità di misura", Toast.LENGTH_LONG).show();
                } else {
                    //flag che indica che l'ingrediente che si vuole aggiungere è già presente nella lista degli ingredienti selezionati
                    boolean flag = false;
                    for (int i = 0; i < MyIngredients.size(); i++) {
                        if (ingredient.getIdIng() == MyIngredients.get(i).getIdIng()) {
                            Toast.makeText(getApplicationContext(), "Ingrediente già aggiunto", Toast.LENGTH_LONG).show();
                            flag = true;
                            break;  //break per terminare ciclo for
                        }
                    }
                    //break per terminare il case dello switch senza aggiungere l'ingrediente
                    if (flag)
                        break;

                    //aggiungo l'ingrediente all'arraylist degli ingredienti del my cocktail
                    MyIngredients.add(ingredient);
                    ingredient = new IngRow(); //inizializzo l'oggetto utilizzato come passadati

                    //scrittura in output degli ingredienti presenti nella lista dei selezionati
                    System.out.println(MyIngredients.toString());

                    //aggiorno la lista degli ingredienti
                    if (MyIngredients.size() == 1) {
                        iAdapter = new IngredientDeletableAdapter(this, MyIngredients);
                        ingListView.setAdapter(iAdapter);
                        ingListView.setFocusable(false);    //elimino focus sulla lista
                    }
                    if (MyIngredients.size() != 0 && MyIngredients.size() != 1)
                        iAdapter.notifyDataSetChanged();    //aggiorno lista degli ingredienti selezionati

                    //resetto tutti gli spinner riguardanti gli ingredienti
                    spUnity.setSelection(0);
                    spQuantity.setSelection(0);
                    spIngrendient.setSelection(0);
                }
                break;

            //pusante AGGIUNGI STRUMENTO
            case R.id.AddStrum:

                //controllo se non aggiunto strumento
                if (strum.getNome() == null) {
                    strum.setNome(strumList.get(0).getNome());
                    strum.setId(strumList.get(0).getId());
                }

                //controllo se lo strumento selezionato è già presente
                boolean flag = false;
                for (int i = 0; i < MyStrums.size(); i++) {
                    if (strum.getId() == MyStrums.get(i).getId()) {
                        Toast.makeText(getApplicationContext(), "Strumento già aggiunto", Toast.LENGTH_LONG).show();
                        flag = true;
                        break;  //break per terminare ciclo for
                    }
                }
                //break per terminare il case dello switch senza aggiungere lo strumento
                if (flag)
                    break;

                //aggiungo lo strumento all'arraylist degli strumenti del my cocktail
                MyStrums.add(strum);
                strum = new Strumento(); //inizializzo l'oggetto utilizzato come passadati

                //scrittura in output degli strumenti presenti nella lista dei selezionati
                System.out.println(MyStrums.toString());

                //aggiorno la lista degli strumenti
                if (MyStrums.size() == 1) {
                    sAdapter = new StrumDeletableAdapter(this, MyStrums);
                    strumListView.setAdapter(sAdapter);
                    strumListView.setFocusable(false);    //elimino focus sulla lista
                }
                if (MyStrums.size() != 0 && MyStrums.size() != 1)
                    sAdapter.notifyDataSetChanged();    //aggiorno lista degli ingredienti del my cocktail

                //resetto tutti gli spinner riguardanti gli strumenti
                spStrumenti.setSelection(0);
                break;

            default:
                break;
        }
    }
}