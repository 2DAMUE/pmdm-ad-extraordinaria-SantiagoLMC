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

import java.util.LinkedList;
import java.util.List;


public class ViewActivity extends AppCompatActivity implements Adaptador.OnClickCustom {
    private SQLiteDatabase db;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lm;
    private RecyclerView.Adapter adapter;
    private BDSQLite bdsqLite;
    private FloatingActionButton aDD;
    List<Tarea> listaTareas;

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

        //Inicializo y hago un set del LayoutManager del RecyclerView
        lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

        listaTareas = bdsqLite.getTareas();

        //Creo el adaptador y hago un set del adaptador en el RecyclerView
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

    private void toADD() {
        Intent aniadir = new Intent(this, AddActivity.class);
        finish();
        startActivity(aniadir);
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void click(int position) {
        Tarea t = listaTareas.get(position);
        Intent lanzar = new Intent(this, Descripcion.class);
        lanzar.putExtra("Tarea", t);
        startActivity(lanzar);
        Log.d("Nombre Tarea: ", t.getTarea());
    }
}

