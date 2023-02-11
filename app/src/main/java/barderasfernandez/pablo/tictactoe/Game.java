package barderasfernandez.pablo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import barderasfernandez.pablo.tictactoe.dialogos.ElegirOpcion;

public class Game extends AppCompatActivity {

    TextView txtUserView;
    String opcion;

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

    }


    // MÉTODO PARA EMPEZAR EL JUEGO
    public void empezar(View view){

        // HACER FILAS VISIBLES


        // MOSTRAR DIALOGO
        ElegirOpcion dialogoOpcion = new ElegirOpcion();
        dialogoOpcion.show(getSupportFragmentManager(), "Dialogo de opción");
        opcion = dialogoOpcion.opcion;
        Toast.makeText(this, "La opción ha sido: ", Toast.LENGTH_SHORT).show();

    }

}