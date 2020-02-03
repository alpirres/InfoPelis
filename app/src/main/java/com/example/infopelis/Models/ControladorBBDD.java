package com.example.infopelis.Models;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ControladorBBDD {

    public SqLiteHelper sqLiteHelper;
    public SQLiteDatabase db;

    public ControladorBBDD(Context myContext) {
        sqLiteHelper = SqLiteHelper.getSqliteHelper(myContext, "infoPelis", null, 1);
        db = sqLiteHelper.getWritableDatabase();
    }



    public boolean insertar(Pelicula pelicula){

        boolean valor= true;
        ContentValues valores = new ContentValues();
        valores.put("titulo", pelicula.getTitulo());
        valores.put("fecha", pelicula.getFecha());
        valores.put("categoria", pelicula.getCategoria());
        valores.put("duracion", pelicula.getDuracion());
        valores.put("director", pelicula.getDirector());
        valores.put("comentario", pelicula.getComentario());
        valores.put("estrenada", pelicula.getEstreno());
        valores.put("imagen", pelicula.getImage());

        db = sqLiteHelper.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null){
            //Insertamos los datos en la tabla Usuarios
            db.insert("Peliculas",null, valores);

            //Cerramos la base de datos
            db.close();
        }else{
            valor=false;
        }

        return valor;
    }

    public boolean update(Pelicula pelicula, int codigo){
        boolean valor=true;
        ContentValues valores = new ContentValues();
        valores.put("titulo", pelicula.getTitulo());
        valores.put("fecha", pelicula.getFecha());
        valores.put("categoria", pelicula.getCategoria());
        valores.put("duracion", pelicula.getDuracion());
        valores.put("director", pelicula.getDirector());
        valores.put("comentario", pelicula.getComentario());
        valores.put("estrenada", pelicula.getEstreno());
        valores.put("imagen", pelicula.getImage());

        String[] args =new String[]{codigo+""};


        if(db != null){
            //Actualizamos el registro en la base de datos
            db.update("Peliculas", valores, "codigo=?",args);
        }else{
            valor=false;
        }

        return valor;
    }

    public boolean delete(int codigo){
        boolean valor = true;
        String[] args =new String[]{codigo+""};

        //Si hemos abierto correctamente la base de datos
        if(db != null){
            //Eliminamos el registro en la base de datos
            db.delete("Peliculas", "codigo=?", args);
        }else{
            valor= false;
        }
        return valor;
    }

    public ArrayList<Pelicula> findAllPelis(){

        Cursor c = db.rawQuery("SELECT * FROM Peliculas", null);

        ArrayList<Pelicula> listPelis = new ArrayList<Pelicula>();

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Pelicula p=new Pelicula();
                p.setId(c.getColumnIndex("codigo"));
                p.setTitulo(c.getString(c.getColumnIndex("titulo")));
                p.setFecha(c.getString(c.getColumnIndex("fecha")));
                p.setCategoria(c.getString(c.getColumnIndex("categoria")));
                p.setDuracion(c.getString(c.getColumnIndex("duracion")));
                p.setDirector(c.getString(c.getColumnIndex("director")));
                p.setComentario(c.getString(c.getColumnIndex("comentario")));
                p.setEstreno(c.getString(c.getColumnIndex("estrenada")));
                p.setImage(c.getString(c.getColumnIndex("imagen")));
                listPelis.add(p);
            } while(c.moveToNext());
        }

        db.close();

        return listPelis;
    }
}

