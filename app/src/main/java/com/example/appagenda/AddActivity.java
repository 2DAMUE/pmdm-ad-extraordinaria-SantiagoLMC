package com.example.appagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private BDSQLite bdsqLite;
    private Button aniadir;
    private EditText tarea;
    private EditText desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        aniadir = findViewById(R.id.add);
        aniadir.setOnClickListener(this);
        tarea = findViewById(R.id.txtNameTarea);
        desc = findViewById(R.id.txtDesc);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add:
                create();
                break;
        }
    }

    /**
     *
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
        Intent change = new Intent(this, ViewActivity.class);
        finish();
        startActivity(change);
    }
}
