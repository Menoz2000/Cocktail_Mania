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

import java.util.List;

public class StepCustomAdapter extends ArrayAdapter<StepPrep> {
    //adapter per gli elementi della lista dei passaggi per la preparazione di un cocktail
    Context context;
    List<StepPrep> stepPreps;

    public StepCustomAdapter(Context context, List<StepPrep> stepPreps) {
        super(context, 0, stepPreps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StepPrep sp = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.prep_row, parent, false);

        TextView action_name = convertView.findViewById(R.id.ActionName);
        ImageView action_image = convertView.findViewById(R.id.AzioneImg);
        TextView ingList = convertView.findViewById(R.id.IngList);
        TextView stepN = convertView.findViewById(R.id.StepNumber);
        TextView sName = convertView.findViewById(R.id.textStrum);
        //setto i campi del layout
        action_image.setImageResource(sp.getAzioneImg());
        action_name.setText(sp.getAzione());
        ingList.setText(sp.getIng());
        stepN.setText(String.valueOf(sp.getStepNum()) /*+ "/" + stepPreps.size()*/);
        sName.setText(sp.getStrumento());

        return convertView;
    }
}