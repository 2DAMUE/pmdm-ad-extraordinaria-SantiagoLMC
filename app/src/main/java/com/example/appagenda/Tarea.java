package com.example.appagenda;

import java.io.Serializable;

public class Tarea implements Serializable {
    private String tarea;
    private String descripcion;
    private boolean hecho;

    public Tarea(String tarea, String descripcion, boolean hecho) {
        this.tarea = tarea;
        this.descripcion = descripcion;
        this.hecho = hecho;
    }

    public Tarea() {
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isHecho() {
        return hecho;
    }

    public void setHecho(boolean hecho) {
        this.hecho = hecho;
    }
}
