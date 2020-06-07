package com.example.appagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Descripcion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        Tarea descTar = (Tarea) getIntent().getSerializableExtra("Tarea");
        TextView descripcion = findViewById(R.id.txtDescripcion);
        Button volver = findViewById(R.id.btnBack);
        descripcion.setText(descTar.getDescripcion());
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
