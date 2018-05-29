package com.rdr.rodrigocorvera.personas.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rdr.rodrigocorvera.personas.Adapters.CategoriesAdapter;
import com.rdr.rodrigocorvera.personas.Fragments.CategoryFilterFragment;
import com.rdr.rodrigocorvera.personas.Fragments.ProfileFragment;
import com.rdr.rodrigocorvera.personas.R;

public class CategoryFilterActivity extends AppCompatActivity {

    TextView categoryTittle;
    FrameLayout frameLayout;
    String values[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_filter);

        Intent getIntent  = getIntent();
        String actionIntent = getIntent.getAction();
        String typeIntent = getIntent.getType();
        getValues(getIntent);
        findViews();
        callFragment();
    }


    public void findViews () {
        categoryTittle = findViewById(R.id.category_tittle);
        categoryTittle.setText(values[0].substring(0,1).toUpperCase() + values[0].substring(1));
        frameLayout = findViewById(R.id.frame_section_category);
    }
    public void callFragment() {
        CategoryFilterFragment categoryFilterFragment = CategoryFilterFragment.newInstance(values[1],"");
        frameLayout.removeAllViews();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_section_category, categoryFilterFragment).commit();
    }

    public void getValues(Intent intent) {
        values = intent.getStringExtra(intent.EXTRA_TEXT).split("-");
    }
}
