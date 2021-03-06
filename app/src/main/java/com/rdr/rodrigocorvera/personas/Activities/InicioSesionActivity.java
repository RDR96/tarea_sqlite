package com.rdr.rodrigocorvera.personas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rdr.rodrigocorvera.personas.Database.DbHelper;
import com.rdr.rodrigocorvera.personas.R;

public class InicioSesionActivity extends AppCompatActivity {


    EditText campoUsuario;
    EditText campoContrasena;
    Button botonIniciarSesion;
    TextView textoRegistrarse;
    public static DbHelper dblite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        checkForUsers();
        checkLogIn();
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
        textoRegistrarse = findViewById(R.id.texto_registrarse);
        textoRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistroAlumnoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkForUsers () {

        dblite = DbHelper.getIntanceState(getApplicationContext());

        if ( dblite.checkNumberOfRows() == 0) {
            Intent intent = new Intent(getApplicationContext(), RegistroAlumnoActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void verifyStudent (String carnet, String password) {

        if ( dblite.checkSingUp(carnet, password) == 1 ) {
            dblite.setActualUser(carnet);
            Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(getIntent().EXTRA_TEXT, carnet + "/" + password + "/" + dblite.getNameFromCarnet(carnet));
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), R.string.data_wrong, Toast.LENGTH_SHORT).show();
        }
    }

    public void checkLogIn () {
        if ( !dblite.checkUserLog().equals("") ) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
