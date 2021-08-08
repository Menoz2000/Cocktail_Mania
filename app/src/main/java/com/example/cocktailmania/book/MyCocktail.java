package com.example.cocktailmania.book;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.cocktail.CocktailActivity;

public class MyCocktail extends AppCompatActivity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imgToUpload;
    Button uploadButton;
    EditText uploadCktName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cocktail);

        imgToUpload = (ImageView) findViewById(R.id.imgUpload);
        uploadButton = (Button) findViewById(R.id.uploadButton);
        uploadCktName = (EditText) findViewById(R.id.cktName);

        imgToUpload.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.uploadButton:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage=data.getData();
            imgToUpload.setImageURI(selectedImage);
        }
    }
}

/*
* passaggi per inserire un myCocktail
* nome e foto
* ingredienti/guarnizioni con quantita
* strumenti
* bicchiere
* categoria grado alcolico
* preparazione con aggiunta step by step
* opzione dove controlli il cocktail con tutte le caratteristiche e se vanno bene tasto pubblica
*
*
* nella pagina con elenco di mycocktail ci deve essere in alto il pulsante per aggiungere un nuovo cocktail
* e sotto l'elenco dei my cocktail fatti e ci deve essere l'opzione per eliminare i cocktail inseriti*/