package com.example.cocktailmania.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailmania.DB.DbManager;
import com.example.cocktailmania.R;
import com.example.cocktailmania.ingredient.IngredientModule;
import com.example.cocktailmania.utility.CocktailElem;
import com.example.cocktailmania.utility.IngRow;
import com.example.cocktailmania.utility.NonScrollListView;
import com.example.cocktailmania.utility.StepPrep;
import com.example.cocktailmania.utility.StrumRow;
import com.example.cocktailmania.utility.Strumento;

import java.util.ArrayList;

public class CocktailModule extends AppCompatActivity {

    private static final String TAG = "CocktailModule";
    private final DbManager db = new DbManager(this);
    NonScrollListView SteplistView, IngListView, StrumListView;
    ArrayList<IngRow> ingredients;
    ArrayList<StepPrep> stepPrep;
    ArrayList<Strumento> instruments;
    int cktN;
    CocktailElem ckt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_module);

        if (getIntent().hasExtra("selected_ckt")) {
            cktN = getIntent().getIntExtra("selected_ckt",0);
            stepPrep = db.getPrepCkt(cktN);
            ingredients = db.getIngredients(cktN);
            ckt = db.getSingleCocktail(cktN);
            instruments=db.getInstruments(cktN);

            /*for (int i = 0; i < ingredients.size(); i++) {
                System.out.println(ingredients.get(i).toString());
            }*/

            //passaggio dei dati alla grafica
            TextView textView = findViewById(R.id.StrumTitle);
            textView.setText(ckt.getNome());

            try {
                ImageView imageView = findViewById(R.id.imgStrum);
                imageView.setImageResource(ckt.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView textView1 = findViewById(R.id.AlcolicGrade);
            textView1.setText(String.valueOf(ckt.getFk_gradoAlcolico()));

            //NonScrollListView con gli ingredienti necessari
            StrumListView = findViewById(R.id.list_instruments);
            StrumAdapter strumAdapter = new StrumAdapter(this, instruments);
            StrumListView.setAdapter(strumAdapter);
            StrumListView.setFocusable(false);    //elimino focus sulla lista
            //rendo la lista cliccabile
            StrumListView.setClickable(true);
            StrumListView.setOnItemClickListener((parent, view, position, id) -> {
                Intent i = new Intent(CocktailModule.this, StrumActivity.class);
                i.putExtra("selected_strum", instruments.get(position).getId());
                startActivity(i);
            });

            TextView textView2 = findViewById(R.id.CktOrigin);
            if (ckt.getFk_origine() != null) {
                textView2.setText(ckt.getFk_origine());
            } else {
                textView2.setVisibility(TextView.GONE);
            }

            //NonScrollListView con gli ingredienti necessari
            IngListView = findViewById(R.id.IngredientList);
            IngredientCustomAdapter iAdapter = new IngredientCustomAdapter(this, ingredients);
            IngListView.setAdapter(iAdapter);
            IngListView.setFocusable(false);    //elimino focus sulla lista
            //rendo la lista cliccabile
            IngListView.setClickable(true);
            IngListView.setOnItemClickListener((parent, view, position, id) -> {
                Intent i = new Intent(CocktailModule.this, IngredientModule.class);
                i.putExtra("selected_ing", ingredients.get(position).getIdIng());
                startActivity(i);
            });

            //NonScrollListView con gli step delle preparazioni
            SteplistView = findViewById(R.id.StepPrep);
            StepCustomAdapter arrayAdapter = new StepCustomAdapter(this, stepPrep);
            SteplistView.setAdapter(arrayAdapter);
            SteplistView.setFocusable(false);      //elimino focus sulla lista


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.like_bar, menu);

        MenuItem likeItem = menu.findItem(R.id.app_bar_like_button);
        ToggleButton button = (ToggleButton) likeItem.getActionView();

        //setto il Like Button
        button.setChecked(ckt.isPreferito());

        //creo listener sul LikeButton che nel caso venga premuto cambia lo stato nel database
        button.setOnCheckedChangeListener((buttonView, isChecked) -> {
            db.OnUpdateInvertPreferito(cktN, !isChecked);
            /*if (isChecked) {
                // The toggle is enabled
            } else {
                // The toggle is disabled
            }*/
        });

        return true;
    }
}