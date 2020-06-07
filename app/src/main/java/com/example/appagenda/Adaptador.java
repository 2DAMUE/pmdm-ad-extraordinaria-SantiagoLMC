package com.example.appagenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder> {
    private OnClickCustom onClickCustom;
    private Context cont;
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
        private Adaptador adapter;

        public MyHolder(@NonNull View v, OnClickCustom onClickCustom, Adaptador adapter) {
            //Llamo al constructor del padre
            super(v);
            nombTarea = v.findViewById(R.id.nombTarea);
            hecho = v.findViewById(R.id.hecho);
            this.adapter = adapter;
            hecho.setOnClickListener(this);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.hecho:
                    adapter.delete(getAdapterPosition());
                    break;
                default:
                    //Le paso a la interfaz la posición actual de cada tarjeta. No se implementa el método aquí.
                    onClickCustom.click(getAdapterPosition());
                    break;
            }
        }
    }


    /**
     * Constructor del adaptador en él le pasamos los objetos con los que se van a construir las tarjetas
     *
     * @param tareasList lista de hospitales
     * @see Tarea
     */
    public Adaptador(List<Tarea> tareasList, OnClickCustom onClickCustom, Context cont) {
        this.tareasList = tareasList;
        this.cont = cont;
        this.onClickCustom = onClickCustom;
    }

    public void delete(int indice) {
        BDSQLite bdsqLite = new BDSQLite(cont);
        Tarea tarea1 = tareasList.get(indice);
        bdsqLite.delete(tarea1);
        this.tareasList.remove(indice);
        notifyItemRemoved(indice);
        notifyItemRangeChanged(indice, this.tareasList.size());
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
        return new MyHolder(v, onClickCustom, this);
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
     * @see
     */
    public interface OnClickCustom {

        /**
         * Método que se implementa en el activity que contiene el RecyclerView
         *
         * @param position posición en del elemento seleccionado
         * @see
         * @see
         */
        void click(int position);
    }
}
