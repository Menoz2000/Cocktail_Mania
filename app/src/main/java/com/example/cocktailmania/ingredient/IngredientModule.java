package com.example.cocktailmania.ingredient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;

public class IngredientModule extends AppCompatActivity {

    private static final String TAG = "IngredientModule";
    private DbManager db=new DbManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_module);

        if (getIntent().hasExtra("selected_ing")) {
            int ingN = getIntent().getIntExtra("selected_ing", 0);


            IngredientElem ing = db.getSingleIngredient(ingN);


            //passaggio dei dati alla grafica
            TextView textView = findViewById(R.id.id);
            textView.setText(String.valueOf(ing.getId()));

            TextView textView1 = findViewById(R.id.nome);
            textView1.setText(ing.getNome());

            TextView textView2 = findViewById(R.id.sottotitolo);
            textView2.setText(ing.getOrigine());

            try{
                ImageView imageView = findViewById(R.id.img);
                imageView.setImageResource(ing.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}