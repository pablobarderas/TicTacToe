package barderasfernandez.pablo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import barderasfernandez.pablo.tictactoe.dialogos.ElegirOpcion;

public class Game extends AppCompatActivity {
    int[] tablero = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };

    TextView txtUserView;
    String opcionJugador;
    boolean juegoEmpezado = false;

    Integer[] botones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txtUserView = findViewById(R.id.txtUserView);

        // Mostrar usuario que ha iniciado sesion
        Bundle extras = getIntent().getExtras();
        String user = "";
        if (extras != null) {
            user = extras.getString("User");
            Toast.makeText(this, user, Toast.LENGTH_SHORT).show();
        }

        txtUserView.setText(user);

        // ALMACENAR BOTONES EN UNA LISTA
        botones = new Integer[]{
                R.id.btn1,R.id.btn2,R.id.btn3,
                R.id.btn4,R.id.btn5,R.id.btn6,
                R.id.btn7,R.id.btn8,R.id.btn9
        };


    }


    // MÉTODO PARA EMPEZAR EL JUEGO
    public void empezar(View view){

        // MOSTRAR DIALOGO
        ElegirOpcion dialogoOpcion = new ElegirOpcion();
        dialogoOpcion.show(getSupportFragmentManager(), "Dialogo de opción");
        opcionJugador = dialogoOpcion.opcion;

        juegoEmpezado = true;


    }

    // MOSTRAR IMAGEN SEGUN SE PULSA
    public void ponerFicha(View view){

        // OBTENER EL BOTÓN QUE SE PULSA Y MOSTRAR LA IMAGEN CORRESPONDIENTE
        int numBtn = Arrays.asList(botones).indexOf(view.getId());
        view.setBackgroundResource(R.drawable.circle);
        tablero[numBtn] = 1;

    }


}