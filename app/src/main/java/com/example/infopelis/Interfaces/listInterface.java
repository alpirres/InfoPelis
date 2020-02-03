package com.example.infopelis.Interfaces;

import android.content.Context;

import com.example.infopelis.Models.Pelicula;

import java.util.ArrayList;

public interface listInterface {

    interface Presenter {
        void Add();

        void onClickRecyclerView(Integer id);

        boolean deletePeli(int i);

        ArrayList<Pelicula> findAllPeliculas();
    }

    interface View{
        void showForm();
    }
}
