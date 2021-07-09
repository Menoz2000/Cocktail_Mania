package com.example.cocktailmania.ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmania.R;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter{

    ArrayList<IngredientElem> elems;
    Context context;

    public IngredientAdapter(ArrayList<IngredientElem> elems, Context context) {
        this.elems = elems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ing_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myViewHolder=(ViewHolder)holder;
        myViewHolder.img.setImageDrawable(elems.get(position));
        myViewHolder.nome.setText(elems.get(position));
        myViewHolder.sottotitolo.setText(elems.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), elems.get(position),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return elems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome,sottotitolo;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.imageView);
            nome=itemView.findViewById(R.id.ingNome);
            sottotitolo=itemView.findViewById(R.id.ingSottotitolo);
        }
    }
}
