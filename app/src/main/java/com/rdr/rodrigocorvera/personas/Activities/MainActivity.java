package com.rdr.rodrigocorvera.personas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rdr.rodrigocorvera.personas.Database.DbHelper;
import com.rdr.rodrigocorvera.personas.Fragments.CategoriesFragment;
import com.rdr.rodrigocorvera.personas.Fragments.GradesFragment;
import com.rdr.rodrigocorvera.personas.Fragments.ProfileFragment;
import com.rdr.rodrigocorvera.personas.R;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toogle;
    NavigationView mNavigationView;
    Toolbar toolbar;
    FrameLayout frameLayout;
    Intent intent;
    TextView nameSection;
    NavigationView navigationView;
    String values[];
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getViews();
        intentValues();

    }

    public void getViews() {
        db = DbHelper.getIntanceState(getApplicationContext());
        navigationView = findViewById(R.id.navigation_view);
        frameLayout = findViewById(R.id.frame_section);
        drawerLayout = findViewById(R.id.drawer_layout);
        toogle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNavigationView = findViewById(R.id.navigation_view);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.profile_item:
                ProfileFragment profileFragment = ProfileFragment.newInstance(db.getNameFromCarnet(db.checkUserLog()), "");
                frameLayout.removeAllViews();
                getSupportFragmentManager().beginTransaction().add(R.id.frame_section, profileFragment).commit();
                break;
            case R.id.grades:
                GradesFragment gradesFragment = GradesFragment.newInstance("","");
                frameLayout.removeAllViews();
                getSupportFragmentManager().beginTransaction().add(R.id.frame_section, gradesFragment).commit();
                break;

            case R.id.categories:
                CategoriesFragment categoriesFragment = CategoriesFragment.newInstance("","");
                frameLayout.removeAllViews();
                getSupportFragmentManager().beginTransaction().add(R.id.frame_section, categoriesFragment).commit();

                break;
            case R.id.change_user:
                db.closeSession();
                Intent backToLogin = new Intent(getApplicationContext(), InicioSesionActivity.class);
                startActivity(backToLogin);
                finish();
                break;
        }

        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(intent);
                break;

            case R.id.action_exit:
                db.closeSession();
                Intent backToLogin = new Intent(getApplicationContext(), InicioSesionActivity.class);
                startActivity(backToLogin);
                finish();
                break;
        }

        //getSupportActionBar().show();
        if (toogle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void intentValues () {
        intent  = getIntent();
        String actionIntent = intent.getAction();
        String typeIntent = intent.getType();

        if( Intent.ACTION_SEND.equals(actionIntent) && typeIntent != null ){
            if( typeIntent.equals("text/plain") ){

                values = gettingValues(intent);
                View header = navigationView.getHeaderView(0);
                nameSection = header.findViewById(R.id.nombre_header);
                nameSection.setText(values[2]);

            }
        }
    }

    public String[] gettingValues (Intent intent) {

        String values[] = intent.getStringExtra(intent.EXTRA_TEXT).split("/");
        return values;
    }
}
