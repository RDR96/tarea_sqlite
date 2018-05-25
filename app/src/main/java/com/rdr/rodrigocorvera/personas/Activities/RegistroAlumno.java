package com.rdr.rodrigocorvera.personas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rdr.rodrigocorvera.personas.Database.DbHelper;
import com.rdr.rodrigocorvera.personas.R;

public class RegistroAlumno extends AppCompatActivity {

    EditText campoCarnet;
    EditText campoContrasena;
    EditText campoNombre;
    Button botonRegistro;
    public static DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_alumno);
        getViews();

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( !campoCarnet.getText().toString().equals("") && !campoNombre.getText().toString().equals("") && !campoContrasena.getText().toString().equals("") ) {

                    if (verifyStudent(campoCarnet.getText().toString(), campoNombre.getText().toString() , campoContrasena.getText().toString())) {
                        Intent intent = new Intent(getApplicationContext(), InicioSesion.class);
                        startActivity(intent);
                        finish();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    public void getViews () {
        db = DbHelper.getIntanceState(getApplicationContext());
        campoCarnet = findViewById(R.id.campo_carnet);
        campoContrasena = findViewById(R.id.campo_contrasena);
        campoNombre = findViewById(R.id.campo_usuario);
        botonRegistro = findViewById(R.id.boton_iniciar_sesion);

    }


    public boolean verifyStudent (String carnet, String name,String password) {

        if ( db.checkSingUp(carnet, password) == 0 ) {
            db.add(carnet, name, password);
            return true;
        } else {
            Toast.makeText(getApplicationContext(), R.string.data_wrong, Toast.LENGTH_SHORT).show();
            return false;
        }

    }



}
