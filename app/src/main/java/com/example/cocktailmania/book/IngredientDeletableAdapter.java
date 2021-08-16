package com.example.cocktailmania.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.cocktail.IngredientCustomAdapter;
import com.example.cocktailmania.utility.IngRow;

import java.util.List;

public class IngredientDeletableAdapter extends IngredientCustomAdapter {

    public IngredientDeletableAdapter(Context context, List<IngRow> ingList) {
        super(context, ingList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IngRow sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_row_deletable, parent, false);

        TextView id_ing = convertView.findViewById(R.id.idIng);
        TextView ingredient_name = convertView.findViewById(R.id.IngText);
        TextView quantity = convertView.findViewById(R.id.QuantityIng);
        TextView unity = convertView.findViewById(R.id.UnityIng);

        id_ing.setText(String.valueOf(sp.getIdIng()));
        quantity.setText(String.valueOf(sp.getQuantita()));
        unity.setText(sp.getUnita_misura());
        ingredient_name.setText(sp.getIngName());

        Button delButton = convertView.findViewById(R.id.DeleteIngButton);
        delButton.setOnClickListener(v -> {
            MyCocktail.MyIngredients.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
