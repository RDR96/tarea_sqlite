package com.rdr.rodrigocorvera.personas.Acitivies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rdr.rodrigocorvera.personas.Database.DbHelper;
import com.rdr.rodrigocorvera.personas.R;

public class InicioSesion extends AppCompatActivity {


    EditText campoUsuario;
    EditText campoContrasena;
    Button botonIniciarSesion;
    public static DbHelper dblite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        checkForUsers();

        findElements();

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( !campoUsuario.getText().toString().equals("") && !campoContrasena.getText().toString().equals("") ) {
                    verifyStudent(campoUsuario.getText().toString(), campoContrasena.getText().toString());
                }else {
                    Toast.makeText(getApplicationContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    public void findElements() {
        campoUsuario = findViewById(R.id.campo_usuario);
        campoContrasena = findViewById(R.id.campo_contraseña);
        botonIniciarSesion = findViewById(R.id.boton_iniciar_sesion);
    }

    public void checkForUsers () {

        dblite = DbHelper.getIntanceState(getApplicationContext());

        if ( dblite.checkNumberOfRows() == 0) {
            Intent intent = new Intent(getApplicationContext(), RegistroAlumno.class);
            startActivity(intent);
        }


    }

    public void verifyStudent (String carnet, String password) {

        if ( dblite.checkSingUp(carnet, password) == 1 ) {
            Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(getIntent().EXTRA_TEXT, carnet + "/" + password + "/" + dblite.getNameFromCarnet(carnet));
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), R.string.data_wrong, Toast.LENGTH_SHORT).show();
        }

    }


}
