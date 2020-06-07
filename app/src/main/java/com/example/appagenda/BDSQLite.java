package com.example.appagenda;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BDSQLite {
    /**
     * Nombre de la tabla
     */
    private static final String TABLE_NAME = "tareas";
    /**
     * SQL para crear la tablaa
     */
    private String sql = "create table tareas(" +
            "nombreTarea varchar(40)," +
            "descripcion varchar(60)," +
            "hecho varchar, CONSTRAINT restriccion PRIMARY KEY (nombreTarea))";
    /**La PK será el nombre de la tarea y se filtrará por nombre
     *
     */


    /** private static final String CREAR_BBDD = "CREATE TABLE " + TABLE_NAME + "(tarea TEXT, descripcion TEXT, hecho TEXT, CONSTRAINT " +
     "tarea_pk PRIMARY KEY (tarea))";
     **/
    /**
     * Nombre de la base de datos
     */

    private static final String BBDD_NAME = "lista";
    /**
     *
     */
    private Context context;

    private SQLiteDatabase bbdd;

    private SQLiteOpenHelper helper;

    /**
     * @param context
     */
    public BDSQLite(Context context) {
        this.context = context;

        helper = new SQLiteOpenHelper(context, BBDD_NAME, null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(sql);
                Log.d("CREAR BBDD", "ÉXITO");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        bbdd = helper.getWritableDatabase();
    }

    /**
     * @param tarea
     * @return
     */
    public boolean insert(Tarea tarea) {
        try (SQLiteStatement stm = bbdd.compileStatement("INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?)");) {

            stm.bindString(1, tarea.getTarea());
            stm.bindString(2, tarea.getDescripcion());
            if (tarea.isHecho())
                stm.bindString(3, "true");
            else
                stm.bindString(3, "false");
            stm.execute();
            return true;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param tarea
     * @return
     */
    public boolean update(Tarea tarea) {
        try (SQLiteStatement stm = bbdd.compileStatement("UPDATE " + TABLE_NAME + " SET nombreTarea = ?, " +
                "descripcion = ?, hecho = ? WHERE nombre = ?");) {

            stm.bindString(1, tarea.getTarea());
            stm.bindString(2, tarea.getDescripcion());
            if (tarea.isHecho())
                stm.bindString(3, "true");
            else
                stm.bindString(3, "false");
            stm.bindString(4, tarea.getTarea());
            stm.execute();
            return true;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Tarea tarea) {
        try (SQLiteStatement stm = bbdd.compileStatement("DELETE FROM " + TABLE_NAME + " WHERE nombreTarea = ?")) {

            stm.bindString(1, tarea.getTarea());
            stm.execute();
            Log.d("DELETE", "Exit");
            return true;
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.w("DELETE", e.getMessage());
            return false;
        }
    }

    public List<Tarea> getTareas() {
        List<Tarea> tareas = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cur = bbdd.rawQuery(sql, null);
        while (cur.moveToNext()) {
            tareas.add(new Tarea(cur.getString(0), cur.getString(1),
                    Boolean.parseBoolean(cur.getString(2))));
        }
        return tareas;
    }
}

