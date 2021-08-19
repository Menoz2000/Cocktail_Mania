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
import com.example.cocktailmania.utility.Strumento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCocktail extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String TAG = "IngredientActivity";
    private final DbManager db = new DbManager(this);

    TextView nomeText;
    ImageView imgMyCkt;
    Button uploadButton;
    Button addIngButton;
    Button addStrumButton;
    Spinner spIngrendient;
    Spinner spQuantity;
    Spinner spUnity;
    Spinner spStrumenti;
    Spinner spGrade;
    NonScrollListView ingListView;
    NonScrollListView strumListView;

    IngRow ingredient;
    public static ArrayList<IngRow> MyIngredients = new ArrayList<>();    //arraylist con gli ingredienti scelti dall'utente

    Strumento strum;
    public static ArrayList<Strumento> MyStrums = new ArrayList<>();     //arraylist con gli strumenti scelti dall'utente

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

        //Ingredient Spinner
        ingredient = new IngRow(); //oggetto passadati
        ArrayList<SpinnerElem> ingredientList = db.getIngredients();   //arraylist con tutti gli ingredienti disponibili (presi dal database)
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
        strum = new Strumento(); //oggetto passadati
        ArrayList<SpinnerElem> strumList = db.getInstruments();   //arraylist con tutti gli strumenti disponibili (presi dal database)
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
            case R.id.OnButton:


                //la funz controllaSeEsiste ci dice se il nome del ckt passato esiste gia tra i myCkt
                if (db.controllaSeEsiste(nomeText.getText().toString()) == true) {

                    Toast.makeText(getApplicationContext(), "nome Cocktail già esistente", Toast.LENGTH_LONG).show();

                } else {

                    nomeMyCkt = nomeText.getText().toString();

                    if(!db.uploadMyCkt()){
                        Toast.makeText(getApplicationContext(), "errore nel caricamento del cocktail", Toast.LENGTH_LONG).show();
                    }else{
                        //todo inizializzare none,grado e array list di ingredienti e strumenti
                        Intent intent = new Intent(this, StepMyCocktail.class);
                        startActivity(intent);
                    }

                }
                break;
            case R.id.AddIng:

                if (ingredient == null) {
                    Toast.makeText(getApplicationContext(), "ERRORE", Toast.LENGTH_LONG).show();
                }
                else{
                    //controllo se abbiamo sia l'unita di misura che la quantita selezionati
                    if (ingredient.getUnita_misura() == "" ^ ingredient.getQuantita() == "") {
                        Toast.makeText(getApplicationContext(), "selezionare quantità e unità di misura", Toast.LENGTH_LONG).show();
                    }
                    else{
                        //TODO: conrtollo se l'ingrediente è gia stato selezionato
                        /*for (int i = 0; i < MyIngredients.size(); i++) {
                            if (ingredient.getIdIng() == MyIngredients.get(i).getIdIng()) {
                                Toast.makeText(getApplicationContext(), "ingrediente già selezionato", Toast.LENGTH_LONG).show();
                                break;
                            }
                        }*/

                        //aggiungo l'ingrediente all'arraylist degli ingredienti del my cocktail
                        MyIngredients.add(ingredient);
                        ingredient = new IngRow(); //inizializzo l'oggetto utilizzato come passadati

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
                    }


                break;
            case R.id.AddStrum:
                //aggiungo lo strumento all'arraylist degli strumenti del my cocktail
                MyStrums.add(strum);
                strum = new Strumento(); //inizializzo l'oggetto utilizzato come passadati

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