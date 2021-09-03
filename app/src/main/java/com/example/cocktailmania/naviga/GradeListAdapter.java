package com.example.cocktailmania.naviga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.GradeCocktail;

import java.util.List;

public class GradeListAdapter extends ArrayAdapter<GradeCocktail> {
    //adapter per gli elementi della lista dei gradi alcolici
    Context context;
    List<GradeCocktail> gradeElems;

    public GradeListAdapter(Context context, List<GradeCocktail> gradeElems) {
        super(context, 0, gradeElems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GradeCocktail sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_grade, parent, false);

        TextView id_grade = convertView.findViewById(R.id.idGrade);
        TextView grade_name = convertView.findViewById(R.id.GradeName);
        //setto i campi del layout
        id_grade.setText(String.valueOf(sp.getId()));
        grade_name.setText(sp.getNome());

        return convertView;
    }
}