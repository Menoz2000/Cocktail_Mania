package com.example.cocktailmania.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;

public class CocktailModule extends AppCompatActivity {

    private static final String TAG = "CocktailModule";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_module);

        if (getIntent().hasExtra("selected_ckt")) {
            CocktailElem ckt = getIntent().getParcelableExtra("selected_ckt");
            Log.d(TAG, "onCreate: " + ckt.toString());

            //passaggio dei dati alla grafica
            TextView textView = findViewById(R.id.id);
            textView.setText(String.valueOf(ckt.getId()));

            TextView textView1 = findViewById(R.id.nome);
            textView1.setText(ckt.getNome());

            TextView textView2 = findViewById(R.id.sottotitolo);
            textView2.setText(ckt.getDescrizione());

            ImageView imageView = findViewById(R.id.img);
            imageView.setImageResource(ckt.getImg());
        }
    }
}