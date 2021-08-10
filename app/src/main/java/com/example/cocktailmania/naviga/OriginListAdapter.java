package com.example.cocktailmania.naviga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.OriginCocktail;

import java.util.ArrayList;
import java.util.List;
/*
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
}*/

public class OriginListAdapter extends RecyclerView.Adapter<OriginListAdapter.MyViewHolder> {
    private ArrayList<OriginCocktail> originList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,id;
        ImageView img;

        MyViewHolder(View view) {
            super(view);
            id=view.findViewById(R.id.idOrig);
            name=view.findViewById(R.id.OrigName);
            img=view.findViewById(R.id.OriginImage);
        }
    }

    public OriginListAdapter(ArrayList<OriginCocktail> originList) {
        this.originList=originList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_origin,parent,false);

        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OriginCocktail origin = originList.get(position);
        holder.id.setText(origin.getId());
        holder.name.setText(origin.getNazione());
        holder.img.setImageResource(origin.getImg());
    }
    @Override
    public int getItemCount(){
        return originList.size();
    }
}