package barderasfernandez.pablo.tictactoe.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrearBD extends SQLiteOpenHelper {

    // Nombre de la base de datos
    public static final String NOMBREBD = "bdUsuarios.sdb";

    // Versión de la base de datos
    public static final int VERSION = 1;

    // Nombre de la tabla (puede haber tantas como necesitemos)
    public static final String NOMBRE_TABLA = "partidas";

    // Atributos o campos de la tabla
    public static final String NOMBRE = "nombre";
    public static final String CONTRASENYA = "contrasenya";
    public static final String GANADAS = "ganadas";
    public static final String PERDIDAS = "perdidas";


    // CONSTRUCTOR
    public CrearBD(Context context) {
        super(context, NOMBREBD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // CREAR UNA TABLA PARTDIAS SI NO EXISTE  CON DOS CAMPOS, REF (AUTO) Y NOMBRE
        db.execSQL("create table if not exists partidas " +
                "(nombre text not null, contrasenya text not null,ganadas integer, perdidas integer);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
