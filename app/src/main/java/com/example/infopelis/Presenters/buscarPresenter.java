package com.example.infopelis.Presenters;

import android.content.Context;

import com.example.infopelis.Interfaces.buscarInterface;
import com.example.infopelis.Models.PeliculaModel;

import java.util.ArrayList;

public class buscarPresenter implements buscarInterface.Presenter{

    private buscarInterface.View view;
    public PeliculaModel pelicula;

    public buscarPresenter(buscarInterface.View view, Context myContext){
        this.view=view;
        pelicula= PeliculaModel.getInstance(myContext);
    }

    @Override
    public ArrayList<String> obtenerCategorias(Context myContext){
        return pelicula.getCategorias();
    }
}
