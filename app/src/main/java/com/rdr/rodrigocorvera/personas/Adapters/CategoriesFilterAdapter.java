package com.rdr.rodrigocorvera.personas.Adapters;

/**
 * Created by Rodrigo Corvera on 28/5/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdr.rodrigocorvera.personas.Activities.CategoryFilterActivity;
import com.rdr.rodrigocorvera.personas.Entidades.Category;
import com.rdr.rodrigocorvera.personas.Entidades.Note;
import com.rdr.rodrigocorvera.personas.R;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Rodrigo Corvera on 27/5/2018.
 */

public class CategoriesFilterAdapter extends RecyclerView.Adapter<CategoriesFilterAdapter.CategoriesViewHolder> {

    Context context;
    ArrayList<Note> dataCategories;

    public CategoriesFilterAdapter (Context context, ArrayList<Note> dataCategories) {
        this.context = context;
        this.dataCategories = dataCategories;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.cardview_notas, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);

        return new CategoriesViewHolder(v,context.getApplicationContext(), dataCategories);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) { //AQUI SE PUEDE ASIGNAR EL CONTENIDO
        holder.categoryTittle.setText(dataCategories.get(position).getCategoryName().substring(0,1).toUpperCase() + dataCategories.get(position).getCategoryName().substring(1));
        holder.content.setText(dataCategories.get(position).getNoteContent());
        //holder.numberOfCategories.setText(dataCategories.get(position).getCategoryId());
    }


    @Override
    public int getItemCount() {
        return dataCategories.size();
    }

    protected class CategoriesViewHolder extends  RecyclerView.ViewHolder{
        TextView categoryTittle;
        TextView content;
        ArrayList<Note> arregloCategorias = new ArrayList<Note>();
        Context context;

        public CategoriesViewHolder(View itemView, Context context, ArrayList<Note> dataCat) {
            super(itemView);
            this.arregloCategorias = dataCat;
            this.context = context;
            categoryTittle = itemView.findViewById(R.id.category_name);
            content = itemView.findViewById(R.id.content_note);

            //tittleTxtView = itemView.findViewById(R.id.movieName);
            //descriptionTxtView = itemView.findViewById(R.id.movieDescription);
        }

    }

}
