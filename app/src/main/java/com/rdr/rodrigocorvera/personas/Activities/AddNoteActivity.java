package com.rdr.rodrigocorvera.personas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.rdr.rodrigocorvera.personas.R;

public class AddNoteActivity extends AppCompatActivity {

    Spinner categoriesSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        
    }
}
