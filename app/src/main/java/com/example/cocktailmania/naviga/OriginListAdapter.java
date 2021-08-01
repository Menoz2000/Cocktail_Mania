package com.example.cocktailmania.naviga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.OriginCocktail;

import java.util.List;

public class OriginListAdapter extends ArrayAdapter<OriginCocktail> {
    Context context;
    List<OriginCocktail> origElems;

    public OriginListAdapter(Context context, List<OriginCocktail> origElems) {
        super(context, 0, origElems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        OriginCocktail sp = getItem(position);

        if (convertView == null)

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_origin, parent, false);

        TextView id_origin = convertView.findViewById(R.id.idOrig);
        TextView origin_name = convertView.findViewById(R.id.OrigName);
        ImageView origin_image=convertView.findViewById(R.id.OriginImage);

        id_origin.setText(String.valueOf(sp.getId()));
        origin_name.setText(sp.getNazione());
        origin_image.setImageResource(sp.getImg());

        return convertView;
    }
}
