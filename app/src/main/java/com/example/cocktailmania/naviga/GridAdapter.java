package com.example.cocktailmania.naviga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.TipoCocktail;

import java.util.List;

public class GridAdapter extends ArrayAdapter<TipoCocktail> {
    //adapter gli elementi della lista dei tipi di cocktail
    Context context;
    List<TipoCocktail> tipeElems;

    public GridAdapter(Context context, List<TipoCocktail> tipeElems) {
        super(context, 0, tipeElems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TipoCocktail sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_grid_type, parent, false);

        TextView id_tipe = convertView.findViewById(R.id.idType);
        ImageView image_tipe = convertView.findViewById(R.id.imageType);
        TextView tipe_name = convertView.findViewById(R.id.TypeName);
        //setto i campi del layout
        id_tipe.setText(String.valueOf(sp.getId()));
        image_tipe.setImageResource(sp.getImg());
        tipe_name.setText(sp.getNome());

        return convertView;
    }
}