package barderasfernandez.pablo.tictactoe.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import barderasfernandez.pablo.tictactoe.R;


public class ElegirOpcion extends DialogFragment {

    ImageView circulo, equis;
    Activity actividad;

    public String opcion = "";

    public ElegirOpcion() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            actividad = (Activity) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogElegirOpcion();
    }

    private AlertDialog crearDialogElegirOpcion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_elegir_opcion, null);
        builder.setView(v);

        // ASIGNAR ID'S
        circulo = v.findViewById(R.id.circulo);
        equis = v.findViewById(R.id.equis);


        // ELEGIR OPCION
        elegirOpcion();

        return builder.create();
    }

    // EVENTO DE PULSAR UNA IMAGEN
    private void elegirOpcion() {

        circulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(actividad, "Circulo", Toast.LENGTH_SHORT).show();
                opcion = "circulo";
                dismiss();
            }
        });

        equis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(actividad, "Equis", Toast.LENGTH_SHORT).show();
                opcion = "equis";
                dismiss();
            }
        });



    }

}