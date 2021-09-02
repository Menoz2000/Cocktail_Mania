package com.example.cocktailmania.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.SpinnerElem;

import java.util.ArrayList;


public class SpinnerAdapter extends ArrayAdapter<SpinnerElem> {
    Context context;
    ArrayList<SpinnerElem> elems;

    public SpinnerAdapter(Context context, ArrayList<SpinnerElem> elems) {
        super(context, 0, elems);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        SpinnerElem sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);

        TextView id = convertView.findViewById(R.id.spinnerId);
        TextView text = convertView.findViewById(R.id.spinnerText);
        ImageView i1 = convertView.findViewById(R.id.spinnerImage);

        id.setText(String.valueOf(sp.getId()));
        text.setText(sp.getNome());
        i1.setImageResource(sp.getImg());

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerElem sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);

        TextView id = convertView.findViewById(R.id.spinnerId);
        TextView text = convertView.findViewById(R.id.spinnerText);
        ImageView i1 = convertView.findViewById(R.id.spinnerImage);

        id.setText(String.valueOf(sp.getId()));
        text.setText(sp.getNome());
        i1.setImageResource(sp.getImg());

        return convertView;
    }

}
