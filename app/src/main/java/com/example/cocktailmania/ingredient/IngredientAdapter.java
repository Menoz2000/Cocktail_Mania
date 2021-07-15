package com.example.cocktailmania.ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmania.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter implements Filterable {

    private ArrayList<IngredientElem> elems;
    private ArrayList<IngredientElem> elemsCpy;
    Context context;
    private OnIngListener onIngListener;

    public IngredientAdapter(ArrayList<IngredientElem> elems, Context context, OnIngListener onIngListener) {
        this.elems = elems;
        elemsCpy = new ArrayList<>(elems);
        this.context = context;
        this.onIngListener = onIngListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(v, onIngListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myViewHolder = (ViewHolder) holder;

        IngredientElem currentElem = elems.get(position);

        //senza il try non funziona sul samsung di sonc
        try{
        myViewHolder.img.setImageResource(currentElem.getImg());
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        public ViewHolder(@NonNull View itemView, OnIngListener onIngListener) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            nome = itemView.findViewById(R.id.rowNome);
            sottotitolo = itemView.findViewById(R.id.ingSottotitolo);
            this.onIngListener = onIngListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onIngListener.OnIngClick(getAdapterPosition());
        }
    }

    public interface OnIngListener {
        void OnIngClick(int position);
    }


    @Override
    public Filter getFilter() {
        return ingFilter;
    }

    private Filter ingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<IngredientElem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(elemsCpy);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (IngredientElem item : elemsCpy) {
                    if (item.getNome().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            elems.clear();
            elems.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
