package com.rdr.rodrigocorvera.personas.Adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdr.rodrigocorvera.personas.Entidades.Note;
import com.rdr.rodrigocorvera.personas.R;

import java.util.ArrayList;

/**
 * Created by Rodrigo Corvera on 27/5/2018.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    Context context;
    ArrayList<Note> dataNotes;

    public NotesAdapter (Context context, ArrayList<Note> dataNotes) {
        this.context = context;
        this.dataNotes = dataNotes;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.cardview_notas, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);

        return new NotesViewHolder(v,context.getApplicationContext(), dataNotes);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) { //AQUI SE PUEDE ASIGNAR EL CONTENIDO
        holder.categoryTittle.setText(dataNotes.get(position).getCategoryName());
        holder.content.setText(dataNotes.get(position).getNoteContent());
    }


    @Override
    public int getItemCount() {
        return dataNotes.size();
    }

    protected class NotesViewHolder extends  RecyclerView.ViewHolder{
        TextView categoryTittle;
        TextView content;
        ArrayList<Note> arregloNotas = new ArrayList<Note>();
        Context context;

        public NotesViewHolder(View itemView, Context context, ArrayList<Note> movies) {
            super(itemView);
            this.arregloNotas = movies;
            this.context = context;
            categoryTittle = itemView.findViewById(R.id.category_name);
            content = itemView.findViewById(R.id.content_note);

            //tittleTxtView = itemView.findViewById(R.id.movieName);
            //descriptionTxtView = itemView.findViewById(R.id.movieDescription);
        }

    }

}
