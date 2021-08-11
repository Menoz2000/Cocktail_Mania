package com.example.cocktailmania.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.GradeCocktail;
import com.example.cocktailmania.utility.IngredientElem;

import java.util.ArrayList;


public class SpinnerAdapter extends ArrayAdapter<IngredientElem> {
    Context context;
    ArrayList<IngredientElem> elems;

    public SpinnerAdapter(Context context, ArrayList<IngredientElem> elems) {
        super(context, 0, elems);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        IngredientElem sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);

        TextView t1 = convertView.findViewById(R.id.spinnerText);
        ImageView i1 = convertView.findViewById(R.id.spinnerImage);

        t1.setText(sp.getNome());
        i1.setImageResource(sp.getImg());

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IngredientElem sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);

        TextView t1 = convertView.findViewById(R.id.spinnerText);
        ImageView i1 = convertView.findViewById(R.id.spinnerImage);

        t1.setText(sp.getNome());
        i1.setImageResource(sp.getImg());

        return convertView;
    }

}
