package com.example.appagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);

    }

    @Override
    public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence[] items = new CharSequence[3];
        items[0] = "Agregar tarea";
        items[1] = "Ver tarea";
        items[2] = "Borrar";

        builder.setTitle("Seleccionar tarea")
                .setItems (
            items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        //Agregar events
                        Intent intent = new Intent(getApplication(), AddActivity.class);
                        Bundle bundle = new Bundle();

                        intent.putExtras(bundle);
                        startActivity(intent);

                    } else if (i == 1) {
                        //var events
                        Intent intent = new Intent(getApplication(), Lista_de_Tareas.class);
                        Bundle bundle = new Bundle();

                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        return;
                    }
                }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.itemIdioma){
            Toast.makeText(this, "Opcion1", Toast.LENGTH_SHORT).show();
        }return super.onOptionsItemSelected(item);
    }
    //Método de boton idioma
    public void Idioma(View view){
        Intent idioma = new Intent(this, Language.class);
        startActivity(idioma);
    }
}
