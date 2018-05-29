package com.rdr.rodrigocorvera.personas.Adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdr.rodrigocorvera.personas.Activities.CategoryFilterActivity;
import com.rdr.rodrigocorvera.personas.Entidades.Category;
import com.rdr.rodrigocorvera.personas.Entidades.Note;
import com.rdr.rodrigocorvera.personas.Fragments.CategoryFilterFragment;
import com.rdr.rodrigocorvera.personas.Fragments.ProfileFragment;
import com.rdr.rodrigocorvera.personas.R;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Rodrigo Corvera on 27/5/2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    Context context;
    ArrayList<Category> dataCategories;



    public CategoriesAdapter (Context context, ArrayList<Category> dataCategories) {
        this.context = context;
        this.dataCategories = dataCategories;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.cardview_category_item, null);
        LinearLayout container = v.findViewById(R.id.category_section);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        final CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(v,context, dataCategories);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = categoriesViewHolder.getAdapterPosition();
                Intent intent = new Intent(context, CategoryFilterActivity.class);
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, dataCategories.get(position).getCategoryName() + "-"
                        + dataCategories.get(position).getCategoryId()
                );

                context.startActivity(intent);
            }
        });




        return  categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) { //AQUI SE PUEDE ASIGNAR EL CONTENIDO
        holder.categoryTittle.setText(dataCategories.get(position).getCategoryName().substring(0,1).toUpperCase() + dataCategories.get(position).getCategoryName().substring(1));
        //holder.numberOfCategories.setText(dataCategories.get(position).getCategoryId());
    }


    @Override
    public int getItemCount() {
        return dataCategories.size();
    }

    protected class CategoriesViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView categoryTittle;
        TextView numberOfCategories;
        ArrayList<Category> arregloCategorias = new ArrayList<Category>();
        Context context;

        public CategoriesViewHolder(View itemView, Context context, ArrayList<Category> dataCat) {
            super(itemView);
            this.arregloCategorias = dataCat;
            this.context = context;
            categoryTittle = itemView.findViewById(R.id.category_name_header);
            numberOfCategories = itemView.findViewById(R.id.number_of_notes);

            //tittleTxtView = itemView.findViewById(R.id.movieName);
            //descriptionTxtView = itemView.findViewById(R.id.movieDescription);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, CategoryFilterActivity.class);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, dataCategories.get(position).getCategoryName() + "-*"
                    + dataCategories.get(position).getCategoryId()
            );
            Bundle object = new Bundle();
            startActivity(context, intent, object);
        }
    }

}
