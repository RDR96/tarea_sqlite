package com.rdr.rodrigocorvera.personas.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rdr.rodrigocorvera.personas.Adapters.NotesAdapter;
import com.rdr.rodrigocorvera.personas.Database.DbHelper;
import com.rdr.rodrigocorvera.personas.Entidades.Note;
import com.rdr.rodrigocorvera.personas.R;

import java.util.ArrayList;


public class GradesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private NotesAdapter notesAdapter;
    private ArrayList<Note> notesArray;
    private DbHelper db;


    public GradesFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GradesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GradesFragment newInstance(String param1, String param2) {
        GradesFragment fragment = new GradesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2); 
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_grades, container, false);
        fillArray();
        notesAdapter = new NotesAdapter(getActivity(), notesArray);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_notes);
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linear);
        recyclerView.setAdapter(notesAdapter);

        return view;
    }
    public void fillArray() {
        db = DbHelper.getIntanceState(getContext());
        notesArray = db.retrieveNotes();
    };

}
