package com.example.cocktailmania.cocktail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.StepPrep;
import com.example.cocktailmania.utility.StrumElem;

import java.util.List;

public class StrumAdapter extends ArrayAdapter<StrumElem> {
    Context context;
    List<StrumElem> strumElems;

    public StrumAdapter(Context context, List<StrumElem> strumElems) {
        super(context, 0, strumElems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StrumElem sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.prep_row, parent, false);

        TextView strum_name = convertView.findViewById(R.id.ActionName);

        strum_name.setText(sp.getName());

        return convertView;
    }
}
