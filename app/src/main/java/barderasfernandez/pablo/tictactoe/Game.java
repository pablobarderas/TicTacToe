package barderasfernandez.pablo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    TextView txtUserView;

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
}