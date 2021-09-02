package com.example.cocktailmania.cocktail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.Strumento;

public class StrumActivity extends AppCompatActivity {

    private final DbManager db = new DbManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strum);

        if (getIntent().hasExtra("selected_strum")) {
            int strumN = getIntent().getIntExtra("selected_strum", 0);

            Strumento s = db.getInstrument(strumN);

            //passaggio dei dati alla grafica
            TextView textView = findViewById(R.id.CocktailTitle);
            textView.setText(s.getNome());

            try {
                ImageView imageView = findViewById(R.id.imgStrum);
                imageView.setImageResource(s.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView textView1 = findViewById(R.id.capacita);
            if (s.getCapacita() != null) {
                textView1.setText(s.getCapacita());
            } else {
                textView1.setVisibility(TextView.GONE);
            }

            TextView textView2 = findViewById(R.id.descrizioneStrum);
            if (s.getDescrizione() != null) {
                textView2.setText(s.getDescrizione());
            } else {
                textView2.setVisibility(TextView.GONE);
            }

        }
    }
}