package com.example.appagenda;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder> {

    private OnClickCustom onClickCustom;
    private BDSQLite bdsqLite;
    private FloatingActionButton done;
    private EditText tarea;
    private EditText desc;
    private List<Tarea> tareasList;

    /**
     * ViewHolder del RecylerView. Se encarga de cargar el layout de cada tarjeta el RecyclerView. Contiene todos los elementos de cada tarjeta.
     * Implementa la interfaz View.OcClickListener para detectar la tarjeta escogida.
     * Tiene un atributo del mismo tipo de la interfaz creada para detectar el click en cada tarjeta.
     * <p>
     * seeAdaptdor.OnClickCustom
     * see
     */
    public static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nombTarea;
        private Button hecho;

        private OnClickCustom onClickCustom;

        public MyHolder(@NonNull View v, OnClickCustom onClickCustom) {
            //Llamo al constructor del padre
            super(v);
            nombTarea = v.findViewById(R.id.nombTarea);
            hecho = v.findViewById(R.id.hecho);
            hecho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    delete();
                }
            });
        }

        public void delete() {
            BDSQLite bdsqLite = new BDSQLite();
            String name = tarea.getText().toString();
            String descrip = desc.getText().toString();
            boolean tf = false;
            Tarea t1 = new Tarea(name, descrip, tf);
            boolean exito = bdsqLite.delete(t1);
            if (exito) {
                Toast.makeText(this, "Se ha borrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se ha borrado", Toast.LENGTH_SHORT).show();
            }
            boolean exito = bdsqLite.delete(t1);
            tareasList.remove(position);

        }

        @Override
        public void onClick(View v) {
            //Le paso a la interfaz la posición actual de cada tarjeta. No se implementa el método aquí.
            onClickCustom.click(getAdapterPosition());

        }
    }


    /**
     * Constructor del adaptador en él le pasamos los objetos con los que se van a construir las tarjetas
     *
     * @param tareasList lista de hospitales
     * @see Tarea
     */
    public Adaptador(List<Tarea> tareasList, OnClickCustom onClickCustom) {
        this.tareasList = tareasList;
        this.onClickCustom = onClickCustom;
    }


    @NonNull
    @Override
    public Adaptador.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creo un View con el layout personalizado para cada tarjeta
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptador, parent, false);

        /**
         * Devuelvo una instancia de la clase personalizada MyHolder con la vista y el atributo de
         * la interfaz personalzada
         *
         * @see Adaptdor.OnClickCustom
         * @see Adaptdor#onClickCustom
         */
        return new MyHolder(v, onClickCustom);
    }

    /**
     * Carga los datos en los elementos de cada tarjeta
     *
     * @param holder   objeto de la clase MyHolder en el que se inicializan los elementos de cada tarjeta
     * @param position posición del List que recorre en ese momento
     * @see Adaptador#tareasList
     * @see MyHolder
     */
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //Cojo el objeto de la lista en la posición en la que se encuentre el adaptador
        Tarea t = tareasList.get(position);

        holder.nombTarea.setText(t.getTarea());
    }

    /**
     * Cantidad de items que se le pasan al adaptador
     *
     * @return size del List de hospitales
     * @see Adaptador#tareasList
     */
    @Override
    public int getItemCount() {
        return tareasList.size();
    }

    /**
     * Interfaz personalizada que contiene los métodos a implementar en el
     * activity que contiene el RecyclerView
     *
     * @see ActivityRecycler
     */
    public interface OnClickCustom {

        /**
         * Método que se implementa en el activity que contiene el RecyclerView
         *
         * @param position posición en del elemento seleccionado
         * @see Adaptdor#onClickCustom
         * @see ActivityRecycler#click(int)
         */
        void click(int position);
    }
}
