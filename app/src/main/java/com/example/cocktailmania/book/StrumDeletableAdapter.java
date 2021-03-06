package com.example.cocktailmania.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.cocktail.StrumAdapter;
import com.example.cocktailmania.utility.Strumento;

import java.util.List;

public class StrumDeletableAdapter extends StrumAdapter {
    //adapter per la lista degli strumenti cancellabile nella costruzione di un my cocktail

    public StrumDeletableAdapter(Context context, List<Strumento> strumElems) {
        super(context, strumElems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Strumento sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.strum_deletable_row, parent, false);

        TextView id_strum = convertView.findViewById(R.id.idStrum);
        TextView strum_name = convertView.findViewById(R.id.StrumText);

        //imposto i campi del layout
        id_strum.setText(String.valueOf(sp.getId()));
        strum_name.setText(sp.getNome());

        //gestione click sul Cancella
        ImageButton delButton = convertView.findViewById(R.id.DeleteStrumButton);
        delButton.setOnClickListener(v -> {
            MyCocktail.MyStrums.remove(position);
            notifyDataSetChanged(); //aggiorno la lista degli strumenti
        });

        return convertView;
    }
}