package barderasfernandez.pablo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import barderasfernandez.pablo.tictactoe.dialogos.ElegirOpcion;

public class Game extends AppCompatActivity {
    int[] tablero = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };

    int estado = 0;
    int fichasPuestas = 0;
    TextView txtUserView;
    ElegirOpcion dialogoOpcion;
    String opcionJugador;
    boolean juegoEmpezado = false;

    Integer[] botones;

    ImageView b1, b2, b3, b4, b5, b6, b7,b8,b9;

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
        dialogoOpcion = new ElegirOpcion();
        dialogoOpcion.show(getSupportFragmentManager(), "Dialogo de opción");

        juegoEmpezado = true;

        // HACER BOTONES VISIBLES
        ImageView b1, b2, b3, b4, b5, b6, b7,b8,b9;
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        b5 = findViewById(R.id.btn5);
        b6 = findViewById(R.id.btn6);
        b7 = findViewById(R.id.btn7);
        b8 = findViewById(R.id.btn8);
        b9 = findViewById(R.id.btn9);



    }

    // MOSTRAR IMAGEN SEGUN SE PULSA
    public void ponerFicha(View view){

        if (estado == 0) {
            // OBTENER EL BOTÓN QUE SE PULSA Y MOSTRAR LA IMAGEN CORRESPONDIENTE
            int numBtn = Arrays.asList(botones).indexOf(view.getId());

            if (tablero[numBtn] == 0){
                if (ElegirOpcion.opcion.equals("circulo")) {
                    view.setBackgroundResource(R.drawable.circle);



                } else {
                    view.setBackgroundResource(R.drawable.x);
                    Toast.makeText(this, "Opcion: " + opcionJugador, Toast.LENGTH_SHORT).show();
                }
                tablero[numBtn] = 1;

                // SE COLOCA UNA FICHA EN UNA POSICIÓN
                fichasPuestas +=1;
                estado = comprobarEstado();
                if (estado == 0){
                    ia();
                    fichasPuestas +=1;
                    estado = comprobarEstado();
                }

            }


        }

    }

    // IA
    public void ia(){
        Random random = new Random();
        int pos = random.nextInt(tablero.length);

        // POSICION VACIA DEL TABLERO
        while(tablero[pos] != 0){
            pos = random.nextInt(tablero.length);
        }

        ImageView b = findViewById(botones[pos]);
        if(ElegirOpcion.opcion.equals("circulo")){
            b.setBackgroundResource(R.drawable.x);
        }else {
            b.setBackgroundResource(R.drawable.circle);
        }
        tablero[pos] = -1;
    }

    // ESTADO DE LA PARTIDA
    public int comprobarEstado(){
        if(fichasPuestas < 9){

            if (tablero[0] + tablero[1] + tablero[2] == 3){
                return 2;
            }

            if (tablero[0] + tablero[3] + tablero[6] == 3){
                return 2;
            }
            return 0;
        }else{
            return 2;
        }


    }

}