package com.example.cocktailmania.ingredient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cocktailmania.R;

public class IngredientModule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_module);

        Bundle b = getIntent().getExtras();
        int value = -1;
        if (b != null) value = b.getInt("key");

        IngredientElem elem = (new IngredientActivity()).elems.get(value);

        TextView t=findViewById(R.id.prova);
        t.setText(elem.getNome());

    }
}