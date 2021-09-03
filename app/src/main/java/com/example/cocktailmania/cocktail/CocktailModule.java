package com.example.cocktailmania.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.example.cocktailmania.utility.Strumento;

import java.util.ArrayList;

public class CocktailModule extends AppCompatActivity {

    private static final String TAG = "CocktailModule";
    private final DbManager db = new DbManager(this);
    NonScrollListView SteplistView, IngListView, StrumListView; //lista degli step, degli ingredienti e degli strumenti di un cocktail
    Button delete;  //pulsante di cancella cocktail (solo per i my cocktail)
    ArrayList<IngRow> ingredients;  //arraylist con tutti gli ingredienti del cocktail
    ArrayList<StepPrep> stepPrep;   //arraylist con tutti gli step per la preparazione del cocktail
    ArrayList<Strumento> instruments;   //arraylist con tutti gli strumenti del cocktail
    int cktN;   //id del cocktail selezionato
    CocktailElem ckt;   //dati del cocktail selezionato
    int config; //configurazione della lista da cui è preso il cocktail (se my cocktail mostra il pulsante delete)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_module);

        if (getIntent().hasExtra("selected_ckt")) {
            cktN = getIntent().getIntExtra("selected_ckt", 0);  //salvo l'id del cocktail
            stepPrep = db.getPrepCkt(cktN); //interrogo database (step per la preparazione del cocktail)
            ingredients = db.getIngredients(cktN);  //interrogo database (ingredienti necessari per preparare il cocktail)
            ckt = db.getSingleCocktail(cktN);   //interrogo database (dati del cocktail selezionato)
            instruments = db.getInstruments(cktN);  //interrogo database (strumenti necessari per la preparazione del cocktail)

            //controllo se è un My Cocktail per aggiungere il button CANCELLA COCKTAIL in fondo alla pagina
            config = db.isMyCkt(cktN);
            //gestione pulsante cancella cocktail
            delete = findViewById(R.id.deleteCocktailButton);
            if (config == 1) {
                delete.setVisibility(View.VISIBLE); //mostro il pulsante cancella
                delete.setOnClickListener(v -> {
                    db.deleteCocktail(cktN);    //cancello il my cocktail
                    //ritorno all'activity con i my cocktail
                    Intent intent = new Intent(CocktailModule.this, CocktailActivity.class);
                    intent.putExtra("list_cocktail", 2);
                    startActivity(intent);
                });
            } else
                delete.setVisibility(View.GONE);

            //passaggio dei dati alla grafica
            TextView title = findViewById(R.id.CocktailTitle);
            title.setText(ckt.getNome());

            try {
                ImageView imageView = findViewById(R.id.imgStrum);
                imageView.setImageResource(ckt.getImg());
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView gradeName = findViewById(R.id.AlcolicGrade);
            gradeName.setText(String.valueOf(ckt.getFk_gradoAlcolico()));

            //NonScrollListView con gli strumenti necessari
            StrumListView = findViewById(R.id.list_instruments);
            StrumAdapter strumAdapter = new StrumAdapter(this, instruments);
            StrumListView.setAdapter(strumAdapter);
            StrumListView.setFocusable(false);    //elimino focus sulla lista
            //rendo la lista degli strumenti cliccabile
            StrumListView.setClickable(true);
            StrumListView.setOnItemClickListener((parent, view, position, id) -> {
                Intent i = new Intent(CocktailModule.this, StrumActivity.class);
                i.putExtra("selected_strum", instruments.get(position).getId());
                startActivity(i);
            });

            //dati sull'origine del cocktail
            TextView originName = findViewById(R.id.CktOrigin);
            TextView originLabel = findViewById(R.id.OriginLabel);
            if (ckt.getFk_origine() != null) {
                originName.setText(ckt.getFk_origine());
            } else {
                //non mostro riga dell'origine del cocktail se non presente
                originName.setVisibility(TextView.GONE);
                originLabel.setVisibility(TextView.GONE);
            }

            //NonScrollListView con gli ingredienti necessari
            IngListView = findViewById(R.id.IngredientList);
            IngredientCustomAdapter iAdapter = new IngredientCustomAdapter(this, ingredients);
            IngListView.setAdapter(iAdapter);
            IngListView.setFocusable(false);    //elimino focus sulla lista
            //rendo la lista degli ingredienti cliccabile
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

    //imposto la barra in alto
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.like_bar, menu);

        //metto la barra con il like al cocktail
        MenuItem likeItem = menu.findItem(R.id.app_bar_like_button);
        ToggleButton button = (ToggleButton) likeItem.getActionView();

        //setto il Like Button
        button.setChecked(ckt.isPreferito());

        //creo listener sul LikeButton che nel caso venga premuto cambia lo stato nel database
        button.setOnCheckedChangeListener((buttonView, isChecked) -> db.OnUpdateInvertPreferito(cktN, !isChecked));

        return true;
    }
}