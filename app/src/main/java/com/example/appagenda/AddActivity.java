package com.example.appagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Añade las tareas y sus descripciones.
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * BDSQLite conecta con la BBDD
     */
    private BDSQLite bdsqLite;
    private Button aniadir;
    private EditText tarea;
    private EditText desc;

    /**
     * Inicializo las variables
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        aniadir = findViewById(R.id.add);
        aniadir.setOnClickListener(this);
        tarea = findViewById(R.id.txtNameTarea);
        desc = findViewById(R.id.txtDesc);
    }

    /**
     * Se usa el switch para filtrar las elecciones en pantalla y hacer
     * que al pulsar dicha opción se cree la tarea.
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add:
                create();
                break;
        }
    }

    /**
     * "bdsqLite" Conecta con la base de datos
     * Se declara el nombre y la descripción
     * Se crea una booleana para decir si la tarea está hecha o no
     * Luego se indica si el insert, al guardar, se ha llevado a cabo,
     * en caso contrario dirá que no se ha guardado.
     * Una vez hecho lo anterior, el Intent (independientemente de la condición),
     * volverá a la actividad de View Activity
     */
    public void create() {
        bdsqLite = new BDSQLite(this);
        String name = tarea.getText().toString();
        String descrip = desc.getText().toString();
        boolean tf = false;
        Tarea t1 = new Tarea(name, descrip, tf);
        boolean exito = bdsqLite.insert(t1);
        if (exito) {
            Toast.makeText(this, "Se ha guardado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se ha guardado", Toast.LENGTH_SHORT).show();
        }
        Intent change = new Intent(this, Lista_de_Tareas.class);
        finish();
        startActivity(change);
    }
}
