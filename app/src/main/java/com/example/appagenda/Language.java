package com.example.appagenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class Language extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        



    }

    //Método para el boton guardar
    public void GuardarYVolver(View view) {
        Intent save = new Intent(this, MainActivity.class);
        startActivity(save);
    }
}
