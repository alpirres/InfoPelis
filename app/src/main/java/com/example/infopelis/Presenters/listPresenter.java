package com.example.infopelis.Presenters;

import com.example.infopelis.Interfaces.listInterface;
import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.Models.PeliculaModel;

import java.util.ArrayList;

public class listPresenter implements listInterface.Presenter {

    private listInterface.View view;
    private PeliculaModel pelicula;

    public listPresenter(listInterface.View view){

        this.view=view;
        pelicula=new PeliculaModel();
    }

    @Override
    public void Add() {
        view.showForm();
    }


    public ArrayList<Pelicula> getAllPeliculas(){
        return pelicula.getAllPelis();
    }

    @Override
    public void onClickRecyclerView(Integer id) {

    }
}
