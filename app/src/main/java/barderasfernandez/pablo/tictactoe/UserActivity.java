package barderasfernandez.pablo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import barderasfernandez.pablo.tictactoe.BaseDeDatos.CrearBD;

public class UserActivity extends AppCompatActivity {

    EditText txtUser;
    EditText txtPassword;
    Button btnEntrar;
    Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CREAR BD
        crearBD();
    }


    // METODO PARA INICIO DE SESION
    public void Entrar(View view){

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        String nombre = "";
        String contrasenya = "";
        boolean registrado = false;

        // LEER BASE DE DATOS
        CrearBD leerBD = new CrearBD(this);
        SQLiteDatabase bdLeer = leerBD.getReadableDatabase();

        // ALMACENA EL CONTENIDO DE LA CONSULTA
        Cursor contenido = bdLeer.rawQuery("Select * from partidas", null);

        // USUARIO Y CONTRASENYA
        if (!txtUser.getText().toString().equals("") || !txtPassword.getText().toString().equals("")) {

            // SI EL USUARIO ES VALIDO
            while (contenido.moveToNext()) {
                if (txtUser.getText().toString().equals(contenido.getString(0)) &&
                        txtPassword.getText().toString().equals(contenido.getString(1))){
                    registrado = true;
                }

            }
            contenido.close();

            // SI EL USUARIO YA ESTA REGISTRADO
            if (registrado){
            // ENTRAR AL JUEGO
            Intent intent = new Intent(this, Game.class);
            intent.putExtra("User", txtUser.getText().toString());
            startActivity(intent);
            }else{
                Toast.makeText(this, "Nombre o contrasenya incorrectas", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Rellene los campos", Toast.LENGTH_SHORT).show();
        }

    }

    // METODO PARA CREAR BD
    public void crearBD(){

        // CREAR BD
        CrearBD crearBD = new CrearBD(this);
        SQLiteDatabase bd = crearBD.getWritableDatabase();

        // BORRAR NOMBRES VACIOS
        bd.execSQL("DELETE FROM partidas WHERE nombre=''");


    }

    // REGISTRARSE
    public void registrarse(View view){

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);

        boolean registrado = false;

        // CREAR BD
        CrearBD crearBD = new CrearBD(this);
        SQLiteDatabase bd = crearBD.getWritableDatabase();

        // LEER BASE DE DATOS
        SQLiteDatabase bdLeer = crearBD.getReadableDatabase();

        // COMPROBACIONES
        // USUARIO Y CONTRASENYA
        if (!txtUser.getText().toString().equals("") || !txtPassword.getText().toString().equals("")){


        // COMPROBAR USUARIO **** TODO
        registrado = comprobarUsuario(bdLeer, txtUser.getText().toString());

            if(txtPassword.getText().length() >= 6){

                if (registrado){
                    Toast.makeText(this, "El usuario ya esta registrado", Toast.LENGTH_SHORT).show();
                }else{

                // INTRODUCIR DATOS
                bd.execSQL("INSERT INTO partidas VALUES" +
                "(" + "'" + txtUser.getText().toString()+ "'" + "," + "'" +
                        txtPassword.getText().toString() + "'" + "," + 0 + "," + 0 + ");");

                Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                }


            }else{
                Toast.makeText(this, "La contrasenya debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Por favor rellene usuario y contrasenya", Toast.LENGTH_SHORT).show();
        }




    }

    // METODO PARA COMPROBAR USUARIO
    private boolean comprobarUsuario(SQLiteDatabase bdLeer, String user) {

        boolean registrado = false;

        // ALMACENA EL CONTENIDO DE LA CONSULTA
        Cursor contenido = bdLeer.rawQuery("Select * from partidas", null);

        // SI EL NOMBRE YA ESTA REGISTRADO
        while(contenido.moveToNext()){
            if (user.equals(contenido.getString(0)))
                registrado = true;
        }

        contenido.close();
        return registrado;

    }

    // COMPROBAR


}