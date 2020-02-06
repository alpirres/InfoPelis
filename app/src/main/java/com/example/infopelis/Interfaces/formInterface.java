package com.example.infopelis.Interfaces;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.infopelis.Models.Pelicula;

import java.util.ArrayList;

public interface formInterface {

    interface Presenter {
        Bitmap pass64toImage(String ImagenCodigo);

        String passImageto64(Bitmap bm);

        void Back();

        boolean saveDataForm(Pelicula pelicula, Context myContext);

        boolean actualizaPeli(Pelicula pelicula, Context myContext);

        ArrayList<String> obtenerCategorias(Context myContext);

        String selectImage();

        Pelicula getPeliculaId(int id, Context myContext);
    }

    interface View{

        void returnToList();
    }
}
