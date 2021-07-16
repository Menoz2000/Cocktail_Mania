package com.example.cocktailmania.ingredient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;

public class IngredientModule extends AppCompatActivity {

    private static final String TAG = "IngredientModule";
    private final DbManager db = new DbManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_module);

        if (getIntent().hasExtra("selected_ing")) {
            int ingN = getIntent().getIntExtra("selected_ing", 0);


            IngredientElem ing = db.getSingleIngredient(ingN);

            //passaggio dei dati alla grafica
            TextView textView = findViewById(R.id.nome);
            textView.setText(ing.getNome());

            TextView textView1 = findViewById(R.id.sottotitolo);
            textView1.setText(ing.getSottotitolo());

            try {
                ImageView imageView = findViewById(R.id.img);
                imageView.setImageResource(ing.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView textView2 = findViewById(R.id.grado_alcolico);
            textView2.setText(Html.fromHtml("<b>Gradazione Alcolica:</b> " + ing.getGrado_alcolico() + "%"));

            if (ing.getOrigine() != null) {
                TextView textView3 = findViewById(R.id.origine);
                textView3.setText(Html.fromHtml("<b>Origine:</b> " + ing.getOrigine()));
            }


        }

    }
}