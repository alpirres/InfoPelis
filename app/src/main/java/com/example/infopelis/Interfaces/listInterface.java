package com.example.infopelis.Interfaces;

import android.content.Context;

import com.example.infopelis.Models.Pelicula;

import java.util.ArrayList;

public interface listInterface {

    interface Presenter {
        void Add();

        ArrayList<Pelicula> doBuscar(String t, String c, String f);

        void onClickRecyclerView(Integer id);

        void Find();

        boolean deletePeli(int i);

        ArrayList<Pelicula> findAllPeliculas();

        ArrayList<Pelicula> findRecyclerPeliculas();
    }

    interface View{
        void showForm();

        void showBuscar();
    }
}
