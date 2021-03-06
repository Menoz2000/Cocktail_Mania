package com.example.cocktailmania.cocktail;

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
import com.example.cocktailmania.utility.CocktailElem;

import java.util.ArrayList;
import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter implements Filterable {
    //adapter per la lista dei cocktail
    private ArrayList<CocktailElem> elems;
    private final ArrayList<CocktailElem> elemsCpy;
    Context context;
    private final OnCktListener onCktListener;


    public CocktailAdapter(ArrayList<CocktailElem> elems, Context context, CocktailAdapter.OnCktListener onCktListener) {
        this.elems = elems;
        elemsCpy = new ArrayList<>(elems);
        this.context = context;
        this.onCktListener = onCktListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cocktail_row, parent, false);
        return new ViewHolder(v, onCktListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CocktailAdapter.ViewHolder myViewHolder = (CocktailAdapter.ViewHolder) holder;

        CocktailElem currentElem = elems.get(position);
        //setto i campi del layout
        myViewHolder.img.setImageResource(currentElem.getImg());
        myViewHolder.nome.setText(currentElem.getNome());
        myViewHolder.gradoAlcolico.setText(currentElem.getFk_gradoAlcolico());
    }

    @Override
    public int getItemCount() {
        return elems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nome, gradoAlcolico;
        ImageView img;
        CocktailAdapter.OnCktListener onCktListener;

        public ViewHolder(@NonNull View itemView, CocktailAdapter.OnCktListener onCktListener) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            nome = itemView.findViewById(R.id.rowNome);
            gradoAlcolico = itemView.findViewById(R.id.ingSottotitolo);
            this.onCktListener = onCktListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCktListener.OnCktClick(getAdapterPosition());
        }
    }

    public interface OnCktListener {
        void OnCktClick(int position);
    }


    @Override
    public Filter getFilter() {
        return cktFilter;
    }

    private final Filter cktFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CocktailElem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(elemsCpy);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CocktailElem item : elemsCpy) {
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

    public boolean setElems(ArrayList<CocktailElem> elems) {
        if(this.elems.size() != elems.size()){
            this.elems = elems;
            return true;    //true --> gli oggetti non sono "uguali"
        }

        return false;   //false --> gli oggetti sono uguali
    }
}