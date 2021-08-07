package com.example.cocktailmania.cocktail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.Strumento;

import java.util.List;

public class StrumAdapter extends ArrayAdapter<Strumento> {
    Context context;
    List<Strumento> strumElems;

    public StrumAdapter(Context context, List<Strumento> strumElems) {
        super(context, 0, strumElems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Strumento sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.strum_row, parent, false);

        TextView id_strum = convertView.findViewById(R.id.idStrum);
        TextView strum_name = convertView.findViewById(R.id.StrumName);

        id_strum.setText(String.valueOf(sp.getId()));
        strum_name.setText(sp.getNome());

        return convertView;
    }
}
