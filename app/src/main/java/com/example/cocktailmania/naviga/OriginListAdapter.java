package com.example.cocktailmania.naviga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.OriginCocktail;

import java.util.ArrayList;

public class OriginListAdapter extends RecyclerView.Adapter{


    private final ArrayList<OriginCocktail> origini;
    Context context;
    private final OnOriginListener onOriginListener;

    public OriginListAdapter(ArrayList<OriginCocktail> origini, Context context, OnOriginListener onOriginListener) {
        this.origini = origini;
        this.context = context;
        this.onOriginListener = onOriginListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_origin, parent, false);
        return new ViewHolder(v, onOriginListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myViewHolder = (ViewHolder) holder;

        OriginCocktail currentElem = origini.get(position);

        //senza il try non funziona sul samsung di sonc
        try{
            myViewHolder.img.setImageResource(currentElem.getImg());
        } catch (Exception e) {
            e.printStackTrace();
        }

        myViewHolder.nome.setText(currentElem.getNazione());
        myViewHolder.id.setText(String.valueOf(currentElem.getId()));
    }

    @Override
    public int getItemCount() {
        return origini.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nome,id;
        ImageView img;
        OnOriginListener onOriginListener;

        public ViewHolder(@NonNull View itemView, OnOriginListener onOriginListener) {
            super(itemView);
            img=itemView.findViewById(R.id.OriginImage);
            id= itemView.findViewById(R.id.idOrig);
            nome=itemView.findViewById(R.id.OrigName);
            this.onOriginListener=onOriginListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onOriginListener.OnOriginClick(getAdapterPosition());
        }
    }

    public interface OnOriginListener {
        void OnOriginClick(int position);
    }

}


