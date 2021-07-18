package com.example.cocktailmania.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;

import java.util.ArrayList;

public class CocktailModule extends AppCompatActivity {

    private static final String TAG = "CocktailModule";
    private DbManager db = new DbManager(this);
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_module);

        if (getIntent().hasExtra("selected_ckt")) {
            int cktN = getIntent().getIntExtra("selected_ckt", 0);
            ArrayList<StepPrep> stepPrep = db.getPrepCkt(cktN);
            CocktailElem ckt = db.getSingleCocktail(cktN);

            for(int i=0;i<stepPrep.size();i++){
                System.out.println(stepPrep.get(i).toString());
            }


            //passaggio dei dati alla grafica
            TextView textView = findViewById(R.id.CktName);
            textView.setText(ckt.getNome());

            try {
                ImageView imageView = findViewById(R.id.img);
                imageView.setImageResource(ckt.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView textView1 = findViewById(R.id.AlcolicGrade);
            textView1.setText(String.valueOf(ckt.getFk_gradoAlcolico()));

            TextView textView2 = findViewById(R.id.CktOrigin);
            textView2.setText(ckt.getFk_origine());

            //ListView con gli step delle preparazioni
            listView=(ListView)findViewById(R.id.StepPrep);
            CustomAdapter arrayAdapter = new CustomAdapter(this, stepPrep);
            listView.setAdapter(arrayAdapter);

        }
    }
}