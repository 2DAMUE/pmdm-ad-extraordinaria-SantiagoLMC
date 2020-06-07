package com.example.appagenda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class About_US extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Button volver = findViewById(R.id.btnBack2);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView descripcion = findViewById(R.id.textView3);
        descripcion.setText("Esta es una aplicación creada con el objetivo\n" +
                "de hacer que el usuario pueda llevar sus\n" +
                "trabajos de manera ordenada.");
    }
}
