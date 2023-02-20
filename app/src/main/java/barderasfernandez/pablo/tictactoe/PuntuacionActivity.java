package barderasfernandez.pablo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import barderasfernandez.pablo.tictactoe.BaseDeDatos.CrearBD;

public class PuntuacionActivity extends AppCompatActivity {

    TextView txtUser;
    String usuario;
    TextView txtGanadas;
    TextView txtPerdidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuacion);

        txtUser = findViewById(R.id.txtUserPuntuacion);
        txtGanadas = findViewById(R.id.txtGanadas);
        txtPerdidas = findViewById(R.id.txtPerdidas);

        // Mostrar usuario
        Bundle extras = getIntent().getExtras();
        usuario = "";
        if (extras != null) {
            usuario = extras.getString("User");
        }

        txtUser.setText(usuario);
        mostrarPartidas();


    }

    // MOSTRAR LAS PUNTUACIONES
    private void mostrarPartidas() {

        // LEER BASE DE DATOS
        CrearBD leerBD = new CrearBD(this);
        SQLiteDatabase bdLeer = leerBD.getReadableDatabase();

        // ALMACENA EL CONTENIDO DE LA CONSULTA
        Cursor contenido = bdLeer.rawQuery("Select * from partidas", null);

        // BUSCAR USUARIO Y MOSTRAR PARTIDAS GANADAS Y PERDIDAS
        while(contenido.moveToNext()){
            if(contenido.getString(0).equals(usuario)){
                txtGanadas.setText(String.valueOf(contenido.getInt(2)));
                txtPerdidas.setText(String.valueOf(contenido.getInt(3)));
            }
        }

        contenido.close();

    }

    public void reiniciar(View view){
        finish();
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("User", usuario);
        startActivity(intent);

    }

    public void salir(View v){
        finish();
    }

}