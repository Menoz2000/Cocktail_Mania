package com.example.cocktailmania.ingredient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;

public class IngredientModule extends AppCompatActivity {

    private static final String TAG = "IngredientModule";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_module);

        if (getIntent().hasExtra("selected_ing")) {
            IngredientElem ing = getIntent().getParcelableExtra("selected_ing");
            Log.d(TAG, "onCreate: " + ing.toString());


            TextView textView = findViewById(R.id.id);
            textView.setText(ing.getId());

            TextView textView1 = findViewById(R.id.nome);
            textView1.setText(ing.getNome());

            TextView textView2 = findViewById(R.id.sottotitolo);
            textView2.setText(ing.getSottotitolo());

            ImageView imageView = findViewById(R.id.img);
            imageView.setImageResource(ing.getImg());
        }

    }
}