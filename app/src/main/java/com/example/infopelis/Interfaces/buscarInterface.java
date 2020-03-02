package com.example.infopelis.Interfaces;

import android.content.Context;

import java.util.ArrayList;

public interface buscarInterface {


    interface Presenter{

        ArrayList<String> obtenerCategorias(Context myContext);

        void Help();
    }

    interface View{

        void showAyuda();

    }
}
