package com.example.cocktailmania.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;

public class CocktailModule extends AppCompatActivity {

    private static final String TAG = "CocktailModule";
    private DbManager db = new DbManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_module);

        if (getIntent().hasExtra("selected_ckt")) {
            int cktN = getIntent().getIntExtra("selected_ckt", 0);


            CocktailElem ckt = db.getSingleCocktail(cktN);

            //passaggio dei dati alla grafica
            TextView textView = findViewById(R.id.id);
            textView.setText(String.valueOf(ckt.getId()));

            TextView textView1 = findViewById(R.id.nome);
            textView1.setText(ckt.getNome());

            TextView textView2 = findViewById(R.id.origine);
            textView2.setText(ckt.getFk_gradoAlcolico());

            try {
                ImageView imageView = findViewById(R.id.img);
                imageView.setImageResource(ckt.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}