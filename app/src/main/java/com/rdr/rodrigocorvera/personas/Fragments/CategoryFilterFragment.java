package com.rdr.rodrigocorvera.personas.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rdr.rodrigocorvera.personas.Adapters.CategoriesAdapter;
import com.rdr.rodrigocorvera.personas.Adapters.CategoriesFilterAdapter;
import com.rdr.rodrigocorvera.personas.Database.DbHelper;
import com.rdr.rodrigocorvera.personas.Entidades.Category;
import com.rdr.rodrigocorvera.personas.Entidades.Note;
import com.rdr.rodrigocorvera.personas.R;

import java.util.ArrayList;

import static com.rdr.rodrigocorvera.personas.Activities.RegistroAlumnoActivity.db;


public class CategoryFilterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CategoriesFilterAdapter categoriesFilterAdapter;
    private ArrayList<Note> categoryNotesArray;



    public CategoryFilterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFilterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFilterFragment newInstance(String param1, String param2) {
        CategoryFilterFragment fragment = new CategoryFilterFragment();
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

        View view = inflater.inflate(R.layout.fragment_category_filter, container, false);
        fillArray();

        categoriesFilterAdapter = new CategoriesFilterAdapter(getActivity(), categoryNotesArray);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_notes_categories);
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linear);
        recyclerView.setAdapter(categoriesFilterAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public void fillArray () {
        db  = DbHelper.getIntanceState(getContext());
        categoryNotesArray = db.getNotesByCategory(mParam1);
    }
}
