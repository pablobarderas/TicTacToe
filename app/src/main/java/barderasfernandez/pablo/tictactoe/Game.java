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

    int turno = 1;
    int[] posGanadora = new int[]{-1,-1,-1};
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
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        b5 = findViewById(R.id.btn5);
        b6 = findViewById(R.id.btn6);
        b7 = findViewById(R.id.btn7);
        b8 = findViewById(R.id.btn8);
        b9 = findViewById(R.id.btn9);

        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.VISIBLE);
        b5.setVisibility(View.VISIBLE);
        b6.setVisibility(View.VISIBLE);
        b7.setVisibility(View.VISIBLE);
        b8.setVisibility(View.VISIBLE);
        b9.setVisibility(View.VISIBLE);


    }

    // MOSTRAR IMAGEN SEGUN SE PULSA
    public void ponerFicha(View view){

        if (estado == 0) {

            // TURNO DEL USUARIO
            turno = 1;
            // OBTENER EL BOTÓN QUE SE PULSA Y MOSTRAR LA IMAGEN CORRESPONDIENTE
            int numBtn = Arrays.asList(botones).indexOf(view.getId());

            if (tablero[numBtn] == 0){
                if (ElegirOpcion.opcion.equals("circulo")) {
                    view.setBackgroundResource(R.drawable.circle);
                } else {
                    view.setBackgroundResource(R.drawable.x);
                }
                tablero[numBtn] = 1;

                // SE COLOCA UNA FICHA EN UNA POSICIÓN
                fichasPuestas +=1;
                estado = comprobarEstado();
                terminarPartida();

                // COLOCA LA IA
                if (estado == 0){
                    turno = -1;
                    ia();
                    fichasPuestas +=1;
                    estado = comprobarEstado();
                    terminarPartida();

                    // VICTORIA
                }
//                    findViewById(botones[0]).setBackgroundResource(R.drawable.xverde);
//                    findViewById(botones[3]).setBackgroundResource(R.drawable.xverde);
//                    findViewById(botones[6]).setBackgroundResource(R.drawable.xverde);



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

        int nuevoEstado = 0;



            // HORIZONTAL
            if (Math.abs(tablero[0] + tablero[1] + tablero[2])  == 3){
                posGanadora = new int[]{0,1,2};
                nuevoEstado = turno;
            }else if (Math.abs(tablero[3] + tablero[4] + tablero[5]) == 3){
                posGanadora = new int[]{3,4,5};
                nuevoEstado = turno;
            }else if (Math.abs(tablero[6] + tablero[7] + tablero[8]) == 3){
                posGanadora = new int[]{6,7,8};
                nuevoEstado = turno;
            }

            // VERTICAL
            else if (Math.abs(tablero[0] + tablero[3] + tablero[6]) == 3){
                posGanadora = new int[]{0,3,6};
                nuevoEstado = turno;
            }else if (Math.abs(tablero[1] + tablero[4] + tablero[7]) == 3){
                posGanadora = new int[]{1,4,7};
                nuevoEstado = turno;
            }else if (Math.abs(tablero[2] + tablero[5] + tablero[8]) == 3){
                posGanadora = new int[]{2,5,8};
                nuevoEstado = turno;
            }

            // DIAGONALES
            else if (Math.abs(tablero[0] + tablero[4] + tablero[8]) == 3){
                posGanadora = new int[]{0,4,8};
                nuevoEstado = turno;
            }else if (Math.abs(tablero[2] + tablero[4] + tablero[6]) == 3){
                posGanadora = new int[]{2,4,6};
                nuevoEstado = turno;
            } else if (fichasPuestas == 9) {
                nuevoEstado = 2;
        }


        return nuevoEstado;


    }

    // METODO PARA DETERMINAR EL GANADOR
    public void terminarPartida(){

        int fichaVictoria = R.drawable.xverde;

        if (ElegirOpcion.opcion.equals("circulo")){
            fichaVictoria = R.drawable.circleverde;
        }else {

        }

        if (estado == 1 || estado == -1){
            // GANA EL USUARIO
            if(estado == 1){
                Toast.makeText(this, "Has ganado!!", Toast.LENGTH_SHORT).show();

                // GANA LA MAQUINA
            } else {
                Toast.makeText(this, "Has perdido!!", Toast.LENGTH_SHORT).show();

                if (ElegirOpcion.opcion.equals("equis")){
                    fichaVictoria = R.drawable.circleverde;
                }else{
                    fichaVictoria = R.drawable.xverde;
                }
            }

            // COLOREAR GANADORES
            for (int i = 0; i < posGanadora.length; i++) {
                findViewById(botones[posGanadora[i]]).setBackgroundResource(fichaVictoria);
            }

        } else if (estado == 2) {
            Toast.makeText(this, "Empate!!", Toast.LENGTH_SHORT).show();
        }

    }

}