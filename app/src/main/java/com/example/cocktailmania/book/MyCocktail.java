package com.example.cocktailmania.book;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
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

public class MyCocktail extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String TAG = "IngredientActivity";
    private final DbManager db = new DbManager(this);

    Button uploadButton;
    Spinner spIngrendient;

    ArrayList<IngredientElem> ingredientList;   //arraylist con tutti gli ingredienti disponibili
    ArrayList<IngredientElem> MyIngredients;    //arraylist con gli ingredienti scelti dall'utente

    SpinnerAdapter spinnerAdapter;

    static TextView nomeMyCkt;
    static ImageView imgMyCkt;
    static ArrayList<IngRow> ingMyCkt;
    static ArrayList<Strumento> strumMyCkt;
    static int gradoAlcolico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cocktail);

        //prendo i dati dal database
        ingredientList = db.getIngredients();

        imgMyCkt = findViewById(R.id.imgUpload);
        nomeMyCkt = findViewById(R.id.cktName);
        spIngrendient = findViewById(R.id.IngSpinner);

        imgMyCkt.setOnClickListener(this);

        spinnerAdapter = new SpinnerAdapter(this, ingredientList);
        spIngrendient.setAdapter(spinnerAdapter);
        spIngrendient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ingredientList.get(position).getNome(), Toast.LENGTH_LONG).show();
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