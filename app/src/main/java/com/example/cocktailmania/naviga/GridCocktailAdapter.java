package com.example.cocktailmania.naviga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.CocktailElem;

import java.util.List;

public class GridCocktailAdapter extends ArrayAdapter<CocktailElem> {
    Context context;
    List<CocktailElem> elems;

    public GridCocktailAdapter(Context context, List<CocktailElem> elems) {
        super(context, 0, elems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CocktailElem sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_type_cocktail, parent, false);

        TextView id_cocktail = convertView.findViewById(R.id.idCocktail);
        TextView cocktail_name = convertView.findViewById(R.id.cocktailName);
        ImageView image_cocktail = convertView.findViewById(R.id.imageCocktail);
        TextView grade_cocktail = convertView.findViewById(R.id.gradeCocktail);

        id_cocktail.setText(String.valueOf(sp.getId()));
        cocktail_name.setText(sp.getNome());
        image_cocktail.setImageResource(sp.getImg());
        grade_cocktail.setText(sp.getFk_gradoAlcolico());

        return convertView;
    }
}
