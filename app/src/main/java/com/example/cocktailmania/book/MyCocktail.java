package com.example.cocktailmania.book;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String TAG = "IngredientActivity";
    private final DbManager db = new DbManager(this);

    Button afterButton;
    TextView nomeText;
    ImageView imgMyCkt;
    Button addIngButton;
    Button addStrumButton;
    Spinner spIngrendient;
    Spinner spQuantity;
    Spinner spUnity;
    Spinner spStrumenti;
    Spinner spGrade;
    NonScrollListView ingListView;
    NonScrollListView strumListView;

    ArrayList<SpinnerElem> ingredientList = new ArrayList<>();
    IngRow ingredient = new IngRow();   //oggetto passadati
    public static ArrayList<IngRow> MyIngredients = new ArrayList<>();    //arraylist con gli ingredienti scelti dall'utente

    ArrayList<SpinnerElem> strumList = new ArrayList<>();
    Strumento strum = new Strumento();  //oggetto passadati
    public static ArrayList<Strumento> MyStrums = new ArrayList<>();     //arraylist con gli strumenti scelti dall'utente

    public static ArrayList<StepPrep> passaggi = new ArrayList<>();

    SpinnerAdapter spinnerIngAdapter;
    SpinnerAdapter spinnerStrAdapter;
    IngredientDeletableAdapter iAdapter;
    StrumDeletableAdapter sAdapter;
    SpinnerGradeAdapter spinnerGradeAdapter;

    public static String nomeMyCkt;
    public static GradeCocktail gradoAlcolico = new GradeCocktail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final int[] idIngSelect = new int[1];  //id ingrediente selezionato

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cocktail);

        //RESETTO GLI ARRAYLIST STATICI
        MyIngredients.clear();
        MyStrums.clear();
        passaggi.clear();
        nomeMyCkt="";

        //uploadButton.findViewById(R.id.OnButton);

        ingListView = findViewById(R.id.MyIngList); //ListView con gli ingredienti
        strumListView = findViewById(R.id.MyStrumList); //ListView con gli strumenti

        //nome cocktail
        nomeText = findViewById(R.id.cktName);
        //immagine cocktail
        imgMyCkt = findViewById(R.id.imgUpload);
        imgMyCkt.setOnClickListener(this);
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
        ingredientList = db.getIngredients();   //arraylist con tutti gli ingredienti disponibili (presi dal database)
        spIngrendient = findViewById(R.id.AzioneSpinner);
        spinnerIngAdapter = new SpinnerAdapter(this, ingredientList);
        spIngrendient.setAdapter(spinnerIngAdapter);
        spIngrendient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), ingredientList.get(position).getNome(), Toast.LENGTH_LONG).show();

                ingredient.setIdIng(ingredientList.get(position).getId());
                ingredient.setIngName(ingredientList.get(position).getNome());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Quantity Spinner
        spQuantity = findViewById(R.id.ingredientCkt);
        //creo l'arraylist con tutte le quantità
        List<String> QList = new ArrayList<>(Arrays.asList("", "0.25", "0.50", "0.75", "1", "1.5", "2", "2.5"));
        for (int i = 3; i <= 100; i++)
            QList.add(String.valueOf(i));
        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, QList);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(quantityAdapter);
        spQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ingredient.setQuantita(spQuantity.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Unity Spinner
        spUnity = findViewById(R.id.strumCkt);
        //creo arraylist con unità di misura
        List<String> UList = new ArrayList<>(Arrays.asList("", "parte", "spruzzo", "cucchi.", "pezzo", "pizzico"));
        ArrayAdapter<String> unityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, UList);
        unityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnity.setAdapter(unityAdapter);
        spUnity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ingredient.setUnita_misura(spUnity.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Strumenti Spinner
        //todo controllo su non inserire due strum uguali
        strumList = db.getInstruments();   //arraylist con tutti gli strumenti disponibili (presi dal database)
        spStrumenti = findViewById(R.id.StrumSpinner);
        spinnerStrAdapter = new SpinnerAdapter(this, strumList);
        spStrumenti.setAdapter(spinnerStrAdapter);
        spStrumenti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), strumList.get(position).getNome(), Toast.LENGTH_LONG).show();

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
        ArrayList<GradeCocktail> gradeList = db.getGrades();   //arraylist con tutti i gradi alcolici (presi dal database)
        spinnerGradeAdapter = new SpinnerGradeAdapter(this, gradeList);
        spGrade.setAdapter(spinnerGradeAdapter);
        spGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), strumList.get(position).getNome(), Toast.LENGTH_LONG).show();
                gradoAlcolico.setId(gradeList.get(position).getId());
                gradoAlcolico.setNome(gradeList.get(position).getNome());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

            case R.id.AfterButton:
                //todo:controllo se è stato inserito almeno un ingrediente e uno strumento
                //la funz controllaSeEsiste ci dice se il nome del ckt passato esiste gia tra i myCkt
                if (db.controllaSeEsiste(nomeText.getText().toString())) {

                    Toast.makeText(getApplicationContext(), "Nome Cocktail già esistente", Toast.LENGTH_LONG).show();

                } else {
                    if (nomeText.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(), "Inserire Nome Cocktail", Toast.LENGTH_LONG).show();
                    else {
                        nomeMyCkt = nomeText.getText().toString();
                        Intent intent = new Intent(this, StepMyCocktail.class);
                        startActivity(intent);
                    }

                }


                break;
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
                        iAdapter.notifyDataSetChanged();

                    //resetto tutti gli spinner riguardanti gli ingredienti
                    spUnity.setSelection(0);
                    spQuantity.setSelection(0);
                    spIngrendient.setSelection(0);
                }


                break;
            case R.id.AddStrum:

                if (strum.getNome() == null) {
                    strum.setNome(strumList.get(0).getNome());
                    strum.setId(strumList.get(0).getId());
                }

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
                    sAdapter.notifyDataSetChanged();

                //resetto tutti gli spinner riguardanti gli strumenti
                spStrumenti.setSelection(0);

                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imgMyCkt.setImageURI(selectedImage);
            imgMyCkt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
    }

}

/*
 * passaggi per inserire un myCocktail
 * nome e foto
 * ingredienti/guarnizioni con quantita
 * strumenti
 * (per lista "dinamica" ingredienti e strumenti: https://stackoverflow.com/questions/2250770/how-to-refresh-android-listview)
 * (per rimuovere gli ingredienti o strumenti: https://youtu.be/hJQmvRHF5xg)
 * categoria grado alcolico
 * preparazione con aggiunta step by step
 * opzione dove controlli il cocktail con tutte le caratteristiche e se vanno bene tasto pubblica
 *
 *
 * nella pagina con elenco di mycocktail ci deve essere in alto il pulsante per aggiungere un nuovo cocktail
 * e sotto l'elenco dei my cocktail fatti e ci deve essere l'opzione per eliminare i cocktail inseriti*/