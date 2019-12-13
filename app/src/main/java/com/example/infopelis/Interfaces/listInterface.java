package com.example.infopelis.Interfaces;

import com.example.infopelis.Models.Pelicula;

import java.util.ArrayList;

public interface listInterface {

    interface Presenter {
        void Add();

        ArrayList<Pelicula> getAllPeliculas();
        void onClickRecyclerView(Integer id);

    }

    interface View{
        void showForm();
    }
}
