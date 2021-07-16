package com.example.cocktailmania.cocktail;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailmania.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<StepPrep> stepPreps;

    CustomAdapter(Context context, List<StepPrep> stepPreps) {
        this.context = context;
        this.stepPreps = stepPreps;
    }

    @Override
    public int getCount() {
        return stepPreps.size();
    }

    @Override
    public Object getItem(int position) {
        return stepPreps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return stepPreps.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView action_image;
        TextView action_name;
        TextView ingList;
        TextView stepN;
        TextView strumName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;

        LayoutInflater mInflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {

            convertView = mInflater.inflate(R.layout.prep_row, null);
            holder=new ViewHolder();

            holder.action_name= convertView.findViewById(R.id.ActionName);
            holder.action_image= convertView.findViewById(R.id.AzioneImg);
            holder.ingList= convertView.findViewById(R.id.IngList);
            holder.stepN= convertView.findViewById(R.id.StepNumber);
            holder.strumName= convertView.findViewById(R.id.textStrum);

            StepPrep row=stepPreps.get(position);

            holder.action_image.setImageResource(row.getAzioneImg());
            holder.action_name.setText(row.getAzione());
            holder.ingList.setText(row.getIng());
            holder.stepN.setText(row.getStepNum());
            holder.strumName.setText(row.getStrumento());
        }

        return convertView;
    }
}
