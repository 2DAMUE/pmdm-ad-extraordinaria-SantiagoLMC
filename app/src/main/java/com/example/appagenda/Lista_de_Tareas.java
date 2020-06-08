package com.example.appagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

/**
 * Muestra la lista de tareas
 */

public class Lista_de_Tareas extends AppCompatActivity implements Adaptador.OnClickCustom {
    /**
     * RecyclerView (Como optativa al List View)
     * RecyclerView.LayoutManager (Android dice que se necesita)
     * RecyclerView.Adapter (Para rellenar el RecyclerView con el botón de "Hecho")
     * BDSQLite Para conectar con la BBDD
     * FloatingActionButton ("Fab" para añadir)
     * List<Tarea> (Acumular las tareas)
     */
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lm;
    private RecyclerView.Adapter adapter;
    private BDSQLite bdsqLite;
    private FloatingActionButton aDD;
    List<Tarea> listaTareas;

    /**
     * Inicializo la conexión con la BBDD
     * Y cargo las tareas
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        bdsqLite = new BDSQLite(this);
        cargarVista();
    }

    /**
     * Para que se muestre el menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Para darle funcionalidad a los botones del menú
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemIdioma:
                Intent lanzar = new Intent(this, Language.class);
                startActivity(lanzar);
                break;
            case R.id.itemAU:
                Intent enviar = new Intent(this, About_US.class);
                startActivity(enviar);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Carga los elementod de la vista
     */
    private void cargarVista() {
        //Inicializo el RecyclerView
        recyclerView = findViewById(R.id.ltvListaTareas);
        //Indico que el número de hospitales puede variar
        recyclerView.setHasFixedSize(false);

       /**
         * Inicializo y hago un set del LayoutManager del RecyclerView para evitar un NPE
         *
         */
        lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

        listaTareas = bdsqLite.getTareas();

        //Creo el adaptador y hago un set del adaptador en el RecyclerView
        /**
         * Introduzco un adapter para cargar las tarjetas.
         */
        adapter = new Adaptador(listaTareas, this, this);
        recyclerView.setAdapter(adapter);
        aDD = findViewById(R.id.fab);
        aDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toADD();
            }
        });
    }

    /**
     * Lleva a la ventana de añadir tareas
     */
    private void toADD() {
        Intent aniadir = new Intent(this, AddActivity.class);
        finish();
        startActivity(aniadir);
    }

    /**
     * Muestra los mensajes
     * @param mensaje
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    /**
     * Me lleva a la descripción de la tarea
     * putExtra (le pasa la tarea al Activity de Descripción con un nombre en clave "Tarea")
     * @param position posición en del elemento seleccionado
     */
    @Override
    public void click(int position) {
        Tarea t = listaTareas.get(position);
        Intent lanzar = new Intent(this, Descripcion.class);
        lanzar.putExtra("Tarea", t);
        startActivity(lanzar);
        Log.d("Nombre Tarea: ", t.getTarea());
    }
}

