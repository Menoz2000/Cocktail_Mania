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
import com.example.cocktailmania.utility.IngRow;
import com.example.cocktailmania.utility.IngredientElem;
import com.example.cocktailmania.utility.Strumento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCocktail extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String TAG = "IngredientActivity";
    private final DbManager db = new DbManager(this);

    Button uploadButton;
    Button addIngButton;
    Spinner spIngrendient;
    Spinner spQuantity;
    Spinner spUnity;

    IngRow ingredient=new IngRow();
    static ArrayList<IngRow> MyIngredients = new ArrayList<>();    //arraylist con gli ingredienti scelti dall'utente

    SpinnerAdapter spinnerAdapter;

    static TextView nomeMyCkt;
    static ImageView imgMyCkt;
    static ArrayList<Strumento> strumMyCkt;
    static int gradoAlcolico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final int[] idIngSelect = new int[1];  //id ingrediente selezionato

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cocktail);

        //nome cocktail
        nomeMyCkt = findViewById(R.id.cktName);
        //immagine cocktail
        imgMyCkt = findViewById(R.id.imgUpload);
        imgMyCkt.setOnClickListener(this);
        //button addIng
        addIngButton=findViewById(R.id.AddIng);
        addIngButton.setOnClickListener(this);

        //Ingredient Spinner
        ArrayList<IngredientElem> ingredientList = db.getIngredients();   //arraylist con tutti gli ingredienti disponibili (presi dal database)
        spIngrendient = findViewById(R.id.IngSpinner);
        spinnerAdapter = new SpinnerAdapter(this, ingredientList);
        spIngrendient.setAdapter(spinnerAdapter);
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
        spQuantity = findViewById(R.id.IngQty);
        //creo l'arraylist con tutte le quantità
        List<String> QList = new ArrayList<>(Arrays.asList("-", "0.25", "0.50", "0.75", "1", "1.5", "2", "2.5"));
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
        spUnity = findViewById(R.id.IngUnity);
        //creo arraylist con unità di misura
        List<String> UList = new ArrayList<>(Arrays.asList("-", "parte", "spruzzo", "cucchi.", "pezzo", "pizzico"));
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

        //ListView con gli ingredienti

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.OnButton:
                break;
            case R.id.AddIng:
                MyIngredients.add(ingredient);
                System.out.println(ingredient.toString());
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