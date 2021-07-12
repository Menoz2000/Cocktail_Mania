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

public class IngredientAdapter extends RecyclerView.Adapter {

    ArrayList<IngredientElem> elems;
    Context context;
    private OnIngListener onIngListener;

    public IngredientAdapter(ArrayList<IngredientElem> elems, Context context,OnIngListener onIngListener) {
        this.elems = elems;
        this.context = context;
        this.onIngListener=onIngListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ing_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v,onIngListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myViewHolder = (ViewHolder) holder;

        IngredientElem currentElem = elems.get(position);

        myViewHolder.img.setImageResource(currentElem.getImg());
        myViewHolder.nome.setText(currentElem.getNome());
        myViewHolder.sottotitolo.setText(currentElem.getSottotitolo());

    }

    @Override
    public int getItemCount() {
        return elems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nome, sottotitolo;
        ImageView img;
        OnIngListener onIngListener;

        public ViewHolder(@NonNull View itemView, OnIngListener onIngListener){
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            nome = itemView.findViewById(R.id.ingNome);
            sottotitolo = itemView.findViewById(R.id.ingSottotitolo);
            this.onIngListener=onIngListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onIngListener.OnIngClick(getAdapterPosition());
        }
    }

    public interface OnIngListener{
        void OnIngClick(int position);
    }

}
