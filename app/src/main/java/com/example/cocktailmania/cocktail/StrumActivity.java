package com.example.cocktailmania.cocktail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.Strumento;

public class StrumActivity extends AppCompatActivity {
    //activity in cui si mostrano le informazioni su uno strumento
    private final DbManager db = new DbManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strum);

        if (getIntent().hasExtra("selected_strum")) {
            int strumN = getIntent().getIntExtra("selected_strum", 0);

            Strumento s = db.getInstrument(strumN); //interrogo database (info sullo strumento selezionato)

            //passaggio dei dati alla grafica

            //nome dello strumento
            TextView strumName = findViewById(R.id.CocktailTitle);
            strumName.setText(s.getNome());

            //immagine strumento
            try {
                ImageView strumImage = findViewById(R.id.imgStrum);
                strumImage.setImageResource(s.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }

            //capacità del bicchiere
            TextView strumCapacity = findViewById(R.id.capacita);
            if (s.getCapacita() != null) {
                strumCapacity.setText(s.getCapacita());
            } else {
                strumCapacity.setVisibility(TextView.GONE);
            }

            //descrizione dello strumento
            TextView strumDesc = findViewById(R.id.descrizioneStrum);
            if (s.getDescrizione() != null) {
                strumDesc.setText(s.getDescrizione());
            } else {
                strumDesc.setVisibility(TextView.GONE);
            }

        }
    }
}