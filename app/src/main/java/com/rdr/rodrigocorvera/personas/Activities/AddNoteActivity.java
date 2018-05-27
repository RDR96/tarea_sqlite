package com.rdr.rodrigocorvera.personas.Activities;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.rdr.rodrigocorvera.personas.Database.DbHelper;
import com.rdr.rodrigocorvera.personas.R;

import java.util.ArrayList;

public class AddNoteActivity extends AppCompatActivity {

    Spinner categoriesSpinner;
    EditText newCategoryNameTextBox;
    EditText textBoxNote;
    LinearLayout addNoteButton;
    LinearLayout addCategoryButton;
    Toolbar toolbar;
    DbHelper db;
    boolean isSpinnerValuesSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        findElements();
        verifyCategories();

    }

    public void findElements () {
        categoriesSpinner = findViewById(R.id.categories_spinner);
        textBoxNote = findViewById(R.id.text_box_note);
        addNoteButton = findViewById(R.id.add_note_button);
        addCategoryButton = findViewById(R.id.new_category_button);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( (!textBoxNote.getText().toString().equals("")) ) {
                    if ( isSpinnerValuesSet ) {
                        db.addNote(categoriesSpinner.getSelectedItem().toString(), textBoxNote.getText().toString());
                        Toast.makeText(getApplicationContext(), R.string.add_successful, Toast.LENGTH_SHORT).show();
                    } else {
                        db.addNote(newCategoryNameTextBox.getText().toString(), textBoxNote.getText().toString());
                    }
                }

            }
        });

        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddNoteActivity.this);
                View dialogElements = getLayoutInflater().inflate(R.layout.dialog_new_category, null);
                final EditText newCategoryName  = dialogElements.findViewById(R.id.new_category_textbox);
                LinearLayout addCategory = dialogElements.findViewById(R.id.add_category_button);

                mBuilder.setView(dialogElements);
                final AlertDialog alert = mBuilder.create();
                alert.show();

                addCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ( db.checkCategoryName(newCategoryName.getText().toString(), true) == 0) {
                            db.addCategory(newCategoryName.getText().toString());
                            Toast.makeText(AddNoteActivity.this, R.string.add_successful, Toast.LENGTH_SHORT).show();
                            fillSpinnerData();
                            alert.hide();
                        }else {
                            Toast.makeText(AddNoteActivity.this, R.string.category_already_exist, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public void verifyCategories () {
        db = DbHelper.getIntanceState(getApplicationContext());

        if ( db.checkCategoryIfExist() != 0 ) {
            categoriesSpinner.setEnabled(true);
            fillSpinnerData();
            isSpinnerValuesSet = true;
        } else {
            categoriesSpinner.setEnabled(false);
            Toast.makeText(getApplicationContext(), R.string.no_categories, Toast.LENGTH_SHORT).show();
            isSpinnerValuesSet = false;
        }

    }

    public void fillSpinnerData () {
        categoriesSpinner.setAdapter(null);
        ArrayList<String> data  = db.getCategoriesById();
        String array [] = data.toArray(new String[0]);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(spinnerAdapter);

    }

}
