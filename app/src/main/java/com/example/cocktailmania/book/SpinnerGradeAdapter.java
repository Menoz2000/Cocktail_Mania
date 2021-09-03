package com.example.cocktailmania.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.GradeCocktail;

import java.util.ArrayList;

public class SpinnerGradeAdapter extends ArrayAdapter<GradeCocktail> {
    //adapter per lo spinner con i gradi alcolici
    Context context;
    ArrayList<GradeCocktail> elems;

    public SpinnerGradeAdapter(Context context, ArrayList<GradeCocktail> elems) {
        super(context, 0, elems);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        GradeCocktail sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_grade_row, parent, false);

        TextView id = convertView.findViewById(R.id.spinnerGradeId);
        TextView text = convertView.findViewById(R.id.spinnerGradeText);

        //setto i campi del layout
        id.setText(String.valueOf(sp.getId()));
        text.setText(sp.getNome());

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GradeCocktail sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_grade_row, parent, false);

        TextView id = convertView.findViewById(R.id.spinnerGradeId);
        TextView text = convertView.findViewById(R.id.spinnerGradeText);

        //setto i campi del layout
        id.setText(String.valueOf(sp.getId()));
        text.setText(sp.getNome());

        return convertView;
    }
}